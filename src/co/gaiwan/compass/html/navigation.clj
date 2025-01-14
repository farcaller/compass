(ns co.gaiwan.compass.html.navigation
  (:require
   [co.gaiwan.compass.css.tokens :as t :refer :all]
   [co.gaiwan.compass.html.auth :as auth]
   [co.gaiwan.compass.html.components :as c]
   [co.gaiwan.compass.html.graphics :as graphics]
   [co.gaiwan.compass.http.routing :refer [url-for]]
   [co.gaiwan.compass.model.user :as user]
   [co.gaiwan.compass.model.assets :as assets]
   [lambdaisland.ornament :as o]))

(o/defrules notifier-dot
  [:.notifier-dot
   :rounded-full
   {:background-color t/--red-8
    :width "0.6rem"
    :height "0.6rem"}])

(o/defstyled nav-bar :nav
  :flex :items-center
  :bg-surface-1
  :mb-5
  [:h1 :font-size-7 :mr-auto :ml-2]
  [:svg :grow-0 :shrink-0
   {:width  t/--font-size-5
    :height t/--font-size-5}]
  [:button :relative]
  [:.notifier-dot :absolute
   {:top "-0.2rem"
    :right "-0.2rem"}]
  ([user]
   [:<>
    [graphics/compass-logo]
    [:h1 [:a {:href (url-for :sessions/index)} "Compass"]]
    [:button {:cx-toggle "menu-open" :cx-target "body"}
     [graphics/hamburger]
     (when (and user (not (user/assigned-ticket user)))
       [:div.notifier-dot])]]))

(defn a-auth [props & children]
  (if (:user props)
    (into [:a props] children)
    (into
     [:a {:hx-target "#modal"
          :hx-get (str (url-for :login/index) "?next=" (:href props))
          :href "#"}]
     children)))

(o/defstyled menu-panel-user :div
  :flex :items-center :gap-2
  [:a {:text-decoration "underline"}]
  ([user]
   [:<>
    (when user
      [:<>
       [c/avatar (user/avatar-css-value user)]
       "Signed in as " (:public-profile/name user) "." [:a {:href (url-for :logout/index)} "Sign out"]])]
   ))

(o/defstyled menu-panel :nav
  :bg-surface-2
  :h-screen
  :flex :flex-col
  :overflow-y-auto :shadow-5 :z-1 :fixed
  {:transition "transform 300ms ease-in"
   :width      t/--size-fluid-10
   :right      0
   :max-width  "100vw"
   :transform  "translate(100%, 0)"}
  [:svg {:width  t/--font-size-5
         :height t/--font-size-5}]
  [:.bar :flex :justify-between :p-3 :mb-2 :border-b-4 :border-surface-3]
  [:li :font-size-3 :my-4
   [:>hr :my-6]
   #_[:svg [:path :w-5 :h-5]]
   [:>a
    :block
    :bg-surface-1 :hover:bg-surface-3
    ;; {:background t/--highlight}
    ;; [:&:hover {:background t/--highlight-yellow}]
    :px-4 :py-4 :mx-4 :my-2 :rounded-lg
    :no-underline]
   {:font-size t/--font-size-3}
   [:&:last-child :border-0]
   [:&.discord-button :border-0]]
  [c/avatar {:height "2rem"}]
  [:.discord-button :flex :justify-center :py-3
   [:>a
    {:background-color "#7289da"}
    [:&:hover {:background-color "#7289da"}]]
   [#{:a :a:visited} {:color t/--gray-2}]]
  [:a:visited {:color t/--link}]
  ["a:has(.notifier-dot)" :flex :gap-1 :items-baseline]
  [:svg :inline :mr-1 {:height "1em" :margin-bottom "-0.15em"}
   [:path
    #_{:fill t/--text-1}
    #_{:color t/--text-1}]]
  [:.bottom :mt-auto :w-full :mb-3 :text-center
   [:p  :m-2]]
  ([user]
   [:<>
    [:div.bar
     [menu-panel-user user]
     [:button {:cx-toggle "menu-open" :cx-target "body"}
      [graphics/cross]]]
    #_[:pre (pr-str user)]
    [:ul
     (when-not user
       [:li.discord-button
        [auth/discord-button {:text "Sign in with Discord"}]])
     (when user
       (if-let [ticket (user/assigned-ticket user)]
         [:li "Ti.to ticket " [:strong (:tito.ticket/reference ticket)]]
         [:li
          [:a {:href (url-for :ticket/connect)
               :on-click "document.body.classList.toggle('menu-open')"}
           [graphics/ticket-icon]
           [:strong "Claim your Ti.to ticket for full access"]
          [:div.notifier-dot]]]))

     [:li
      [:a
       {:href (url-for :sessions/index), :on-click "document.body.classList.toggle('menu-open')"}
       [graphics/sessions-icon]
       "Sessions & Activities"]]
     [:li
      [:a
       {:href (url-for :session/new), :on-click "document.body.classList.toggle('menu-open')"}
       [graphics/plus-icon]
       "Create Activity"]]
     [:li
      [:hr]]
     [:li
      [:a
       {:href (url-for :profile/edit), :on-click "document.body.classList.toggle('menu-open')"}
       [graphics/cog-icon]
       [:span "Profile & Settings"]]]
     [:li
      [:a
       {:href (url-for :contacts/index), :on-click "document.body.classList.toggle('menu-open')"}
       [graphics/queue-list-icon]
       "Contacts"]]
     [:li
      [:a {:href (url-for :contact/qr)
           :hx-target "#modal"
           :on-click "document.body.classList.toggle('menu-open')"}
       [graphics/scan-icon] "Add Contact"]]
     [:li
      [:hr]]
     [:li
      [:a {:href "https://github.com/heartofclojure/heartofclojure-site-2024/wiki/Attendee-Guide-2024"
           :target "_blank"}
       [graphics/book-open-icon]
       "Attendee Guide"]]]
    [:div.bottom
     [:p "Proudly made by the " [:a {:href "https://gaiwan.co" :target "_blank"} "Gaiwan Team"] " and contributors."]
     [:p [:a {:href "https://github.com/GaiwanTeam/compass" :target "_blank"} [graphics/github-icon] "GaiwanTeam/compass"]]]
    ]))

(o/defrules toggle-menu-button)

(o/defrules toggle-menu
  [:body.menu-open
   [menu-panel {:transform "translate(0, 0)"}]])
