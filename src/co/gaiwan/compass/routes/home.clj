(ns co.gaiwan.compass.routes.home
  "Front page routes and business logic"
  (:require
   [clojure.string :as str]
   [co.gaiwan.compass.html.home :as h]
   [co.gaiwan.compass.html.sessions :as sessions]
   [co.gaiwan.compass.db :as db]))

(defn all-sessions
  [{:keys [location type] :or {location "all" type "all"}}]
  (->> (db/q
        '[:find
          [(pull ?e [* {:session/type [*]
                        :session/location [*]}]) ...]
          :where
          [?e :session/title]]
        (db/db))
       (sort-by :session/time)
       (filter #(if (= type "all")
                  true
                  (= (keyword "session.type" type)
                     (get-in % [:session/type :db/ident]))))
       (filter #(if (= location "all")
                  true
                  (= (keyword "location.type" location)
                     (get-in % [:session/location :db/ident]))))))

(defn GET-home [req]
  {:html/head [:title "Conference Compass"]
   :html/body [h/home {:user (:identity req)
                       :sessions (all-sessions {:type "all"
                                                :location "all"})}]})

(defn GET-conf-sessions [req]
  (let [qs-m (:query-params req)]
    {:html/head [:title "sessions"]
     :html/body [sessions/session-list {:sessions (all-sessions qs-m)
                                        :user (:identity req)}]}))

(defn routes []
  [""
   ["/"
    {:name :index
     :get {:handler GET-home}}]
   ["/conf-sessions"
    {:get {:handler GET-conf-sessions}}]])
