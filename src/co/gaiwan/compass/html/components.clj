(ns co.gaiwan.compass.html.components
  "Generic components"
  (:require
   [co.gaiwan.compass.css.tokens :as t]
   [co.gaiwan.compass.html.graphics :as graphics]
   [co.gaiwan.compass.http.routing :refer [url-for]]
   [lambdaisland.ornament :as o]
   [ring.middleware.anti-forgery :as anti-forgery]))

(o/defprop --toggle-radius-left t/--radius-2)
(o/defprop --toggle-radius-right t/--radius-2)

(o/defstyled close-dialog-button :button
  ([]
   [:<> {:id "close-dialog"
         :hx-get (str (url-for :sessions/index))
         :hx-push-url (str (url-for :sessions/index))
         :hx-target "body"
         :hx-swap "outerHTML"}
    [graphics/cross]
    [:script
     "document.addEventListener('keydown', function(event) {
      if (event.key === 'Escape') { // check if ESC is pressed 
        document.getElementById('close-dialog').click(); // click the button 
      }});"]]))

(o/defstyled toggle-button :label
  "Toggle implemented as a checkbox (can also be used as a radio button)."
  {:color t/--text-2}
  [:input :hidden]
  [:>.btn :w-full
   {:border-top-left-radius     --toggle-radius-left
    :border-bottom-left-radius  --toggle-radius-left
    :border-top-right-radius    --toggle-radius-right
    :border-bottom-right-radius --toggle-radius-right}]
  ["input:checked ~ .btn"
   :font-semibold
   {:color            t/--text-1
    :background-color t/--highlight}]
  ([props & children]
   (let [id (:id props)]
     [:<>
      {:for id}
      [:input
       (merge
        {:type    "checkbox"
         :name    id
         :value   id
         :checked (when (:checked? props) "checked")}
        (dissoc props :checked?))]
      [:div.btn
       children]])))

(o/defstyled toggle-group :div
  :flex :flex-row
  "Multiple toggle buttons that act as a group (implemented as radio buttons)"
  {--toggle-radius-left 0
   --toggle-radius-right 0}
  [":first-child > .btn" {--toggle-radius-left "0.5em"}]
  [":last-child > .btn" {--toggle-radius-right "0.5em"}]
  ([props]
   (for [[k v] (:options props)]
     [toggle-button (assoc (dissoc props :options :value)
                           :id (name k)
                           :type "radio"
                           :checked? (= (:value props) k)) v])))

(o/defstyled form
  :form
  ([props & children]
   (into
    [:<>
     props
     [:input {:type "hidden"
              :name  "__anti-forgery-token"
              :value anti-forgery/*anti-forgery-token*}]]
    children)))

(o/defstyled avatar :div.img
  :rounded-full
  {:aspect-ratio 1
   :background-size "cover"}
  ([image]
   [:<> {:style {:background-image image}}]))

(o/defstyled image-frame :div
  [:.img :w-full
   {:padding t/--arc-thickness
    #_#_:margin-left "-100%"}
   [:>* :w-full :aspect-square :rounded-full
    {:background-size "cover"
     :background-position "50% 50%"}]]
  ([{:profile/keys [image]}]
   [:<>
    [:div.img
     [:div
      {:style {:background-image image}}]]]))
