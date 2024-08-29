(ns co.gaiwan.compass.routes.profiles
  "We need a page/route for user's profile"
  (:require
   [clojure.java.io :as io]
   [co.gaiwan.compass.config :as config]
   [co.gaiwan.compass.db :as db]
   [co.gaiwan.compass.db.queries :as q]
   [co.gaiwan.compass.model.attendees :as attendees]
   [co.gaiwan.compass.html.profiles :as h]
   [co.gaiwan.compass.http.response :as response]
   [ring.util.response :as ring-response]))

(defn GET-profile [req]
  {:html/body [h/profile-detail
               (:identity req)]})

(defn GET-profile-form [req]
  {:html/body [h/profile-form
               (:identity req)]})

(defn GET-private-name [{:keys [params] :as req}]
  {:html/body [h/private-name
               (:identity req)
               params]})

(defn GET-link [{:keys [params] :as req}]
  {:html/body [h/links-table
               {}
               params]})

(defn index->link-data
  [{:keys [user-id] :as params} idx]
  (let [user-id (parse-long user-id)
        private-profile-links (set (db/q '[:find [?e ...]
                                           :in $ ?p
                                           :where [?p :private-profile/links ?e]]
                                         (db/db) user-id))
        ;;_ (tap> {:private-links private-profile-links})
        public-profile-links (set (db/q '[:find [?e ...]
                                          :in $ ?p
                                          :where [?p :public-profile/links ?e]]
                                        (db/db) user-id))
        ;; _ (tap> {:public-links public-profile-links})
        link-id-key (keyword (str "link-id-" idx))
        href-key (keyword (str "link-ref-" idx))
        type-key (keyword (str "link-type-" idx))
        private-link-key (keyword (str "private-" idx))
        public-link-key (keyword (str "public-" idx))
        link-id-val (link-id-key params)
        link-id-val (when link-id-val (parse-long link-id-val))
        href-val (href-key params)
        type-val (type-key params)
        private-link-val (private-link-key params)
        public-link-val (public-link-key params)
        profile-data {:db/id (if link-id-val
                               link-id-val
                               (str "temp-" idx))
                      :profile-link/user user-id
                      :profile-link/type type-val
                      :profile-link/href href-val}]
    (tap> {:cond1 [link-id-val (private-profile-links link-id-val) (nil? private-link-val)]
           :cond2 [link-id-val (nil? (private-profile-links link-id-val)) private-link-val]
           :cond3 [link-id-val (public-profile-links link-id-val) (nil? public-link-val)]
           :cond4 [link-id-val (nil? (public-profile-links link-id-val)) public-link-val]})
    (cond-> [profile-data]
      ;; new profile-link
      (and (nil? link-id-val) private-link-val)
      (conj [:db/add user-id :private-profile/links (str "temp-" idx)])
      (and (nil? link-id-val) public-link-val)
      (conj [:db/add user-id :public-profile/links (str "temp-" idx)])
      ;; existing proflie-link
      (and link-id-val (private-profile-links link-id-val) (nil? private-link-val))
      (conj [:db/retract user-id :private-profile/links link-id-val])
      (and link-id-val (nil? (private-profile-links link-id-val)) private-link-val)
      (conj [:db/add user-id :private-profile/links link-id-val])
      (and link-id-val (public-profile-links link-id-val) (nil? public-link-val))
      (conj [:db/retract user-id :public-profile/links link-id-val])
      (and link-id-val (nil? (public-profile-links link-id-val)) public-link-val)
      (conj [:db/add user-id :public-profile/links link-id-val]))))

(defn params->profile-data
  [{:keys [user-id hidden?
           private-name-switch
           bio_public name_public
           bio_private name_private
           rows-count] :as params}]
  (tap> params)
  (let [user-id (parse-long user-id)
        out {:db/id user-id
             :public-profile/bio bio_public
             :private-profile/bio bio_private
             :public-profile/name name_public}
        ;; handle the links data
        links (vec (mapcat #(index->link-data params %)
                           (range (parse-long rows-count))))
        txes (conj links out)
        ;; handle the hidden?
        txes (cond
               (= "on" hidden?)
               (conj txes [:db/add user-id :public-profile/hidden? true])
               (nil? hidden?)
               (conj txes [:db/retract user-id :public-profile/hidden? true]))
        ;; handle the user private name
        txes (cond
               (and name_private
                    (= "on" private-name-switch))
               (conj txes [:db/add user-id :private-profile/name name_private])
               (nil? private-name-switch)
               (conj txes [:db/retract user-id :private-profile/name])
               :else
               txes)]
    txes))

(defn profile-image-file
  (^java.io.File
   [profile-eid filename]
   (io/file (config/value :uploads/dir) (str profile-eid "_" filename))))

(defn save-uploaded-image
  [profile-eid {:keys [filename tempfile]}]
  (let [image-file (profile-image-file profile-eid filename)]
    (io/make-parents image-file)
    (io/copy tempfile image-file)
    {:db/id profile-eid
     :public-profile/avatar-url (.getName image-file)}))

(defn POST-save-profile
  "Save profile to DB

  The typical params is like:
  {:name \"Arne\"
   :tityle \"CEO of Gaiwan\"
   :image {:content-type :filename :size :tempfile}}"
  [{:keys [params] {:keys [image]} :params}]
  (let [{:keys [tempids]} @(db/transact (params->profile-data params))]
    (tap> {:transact-profile true})
    (when image
      @(db/transact
        [(save-uploaded-image (parse-long (:user-id params)) image)]))
    (tap> {:image image
           :transact-image true})
    (response/redirect "/profile"
                       {:flash "Successfully Saved!"})))

(defn file-handler [req]
  (let [file (io/file (config/value :uploads/dir) (get-in req [:path-params :filename]))]
    (if (.exists file)
      (ring-response/file-response (.getPath file))
      (ring-response/not-found "File not found"))))

(defn GET-attendees [req]
  (let [attendees (q/all-users)]
    {:html/body
     [:<>
      [:p "The Attendees List"]
      (for [atd (attendees/user-list attendees)]
        (h/attendee-card atd))]}))

(defn routes []
  [["/profile"
    [""
     {:middleware [[response/wrap-requires-auth]]
      :get        {:handler GET-profile}}]
    ["/edit"
     {:get {:handler GET-profile-form}}]
    ["/edit/private-name"
     {:name :profile/private-name
      :get {:handler GET-private-name}}]
    ["/edit/link"
     {:name :profile/add-link
      :get {:handler GET-link}}]
    ["/save"
     {:middleware [[response/wrap-requires-auth]]
      :post       {:handler POST-save-profile}}]]
   ["/uploads/:filename"
    {:middleware [[response/wrap-requires-auth]]
     :get        {:handler file-handler}}]
   ["/attendees"
    {:middleware [[response/wrap-requires-auth]]
     :get        {:handler GET-attendees}}]])
