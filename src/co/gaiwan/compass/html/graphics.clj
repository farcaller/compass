(ns co.gaiwan.compass.html.graphics
  {:ornament/prefix "graphics-"}
  (:require
   [co.gaiwan.compass.css.tokens :as t]
   [lambdaisland.ornament :as o]))

(o/defstyled compass-logo :svg
  [:path {:fill t/--hoc-pink}]
  ([]
   [:<> {:viewBox "0 0 100 100"}
    [:path.circle
     {:d "M 50,0 C 22.430762,2.9834866e-7 2.9834868e-7,22.430762 0,50 0,77.569238 22.430762,100 50,100 77.569238,100 100,77.569238 100,50 100,22.430762 77.569238,0 50,0 Z m 0,7.59375 C 73.465194,7.59375 92.40625,26.534806 92.40625,50 92.40625,73.465194 73.465194,92.40625 50,92.40625 26.534806,92.40625 7.59375,73.465194 7.59375,50 7.5937503,26.534806 26.534806,7.5937503 50,7.59375 Z"}]
    [:path.line {:d "m 29.758444,21.898408 34.567378,59.874338 c 0.555141,0.960418 1.468895,1.661181 2.540493,1.948009 1.07144,0.287248 2.213171,0.137559 3.174146,-0.41677 2.001922,-1.155251 2.68743,-3.714899 1.531333,-5.716347 L 37.004317,17.715 33.381379,19.806704 Z"}]
    [:path.triangle {:d "m 27.014067,26.404815 14.950042,-8.631421 -0.0025,-0.0028 -11.790743,-3.159345 -3.159301,11.790767 z"}]
    [:path.leg {:d "M 39.334691,86.310778 C 37.137742,85.592591 35.93936,83.229026 36.658422,81.032361 l 9.585531,-31.540687 5.276789,-2.675454 2.675453,5.276791 -9.584714,31.542313 c -1.000278,2.14767 -3.166747,3.329559 -5.27679,2.675454 z"}]]))


(o/defstyled hamburger :svg
  {:stroke-width "13%"
   :stroke-linecap "round"
   :stroke t/--text-1}
  ([& _]
   [:<> {:viewBox "0 0 20 15"}
    (for [x [1 7.5 14]]
      [:path {:d (str "M 2," x " H 18")}])]))

(o/defstyled cross :svg
  {:stroke-width "13%"
   :stroke-linecap "round"
   :stroke t/--text-1}
  ([]
   [:<> {:viewBox "0 0 20 15"}
    [:path {:d "M 4.0348568,1.5348556 15.965143,13.465142"}]
    [:path {:d "M 15.965143,1.5348556 4.0348568,13.465142"}]]))

(o/defstyled discord :svg
  {:--_logo-color "#5865F2"}
  [:path {:fill "var(--_logo-color)"}]
  ([]
   [:<>
    {:viewbox "0 -28.5 256 256" :preserveaspectratio "xMidYMid"}
    [:path {:d "M216.856339,16.5966031 C200.285002,8.84328665 182.566144,3.2084988 164.041564,0 C161.766523,4.11318106 159.108624,9.64549908 157.276099,14.0464379 C137.583995,11.0849896 118.072967,11.0849896 98.7430163,14.0464379 C96.9108417,9.64549908 94.1925838,4.11318106 91.8971895,0 C73.3526068,3.2084988 55.6133949,8.86399117 39.0420583,16.6376612 C5.61752293,67.146514 -3.4433191,116.400813 1.08711069,164.955721 C23.2560196,181.510915 44.7403634,191.567697 65.8621325,198.148576 C71.0772151,190.971126 75.7283628,183.341335 79.7352139,175.300261 C72.104019,172.400575 64.7949724,168.822202 57.8887866,164.667963 C59.7209612,163.310589 61.5131304,161.891452 63.2445898,160.431257 C105.36741,180.133187 151.134928,180.133187 192.754523,160.431257 C194.506336,161.891452 196.298154,163.310589 198.110326,164.667963 C191.183787,168.842556 183.854737,172.420929 176.223542,175.320965 C180.230393,183.341335 184.861538,190.991831 190.096624,198.16893 C211.238746,191.588051 232.743023,181.531619 254.911949,164.955721 C260.227747,108.668201 245.831087,59.8662432 216.856339,16.5966031 Z M85.4738752,135.09489 C72.8290281,135.09489 62.4592217,123.290155 62.4592217,108.914901 C62.4592217,94.5396472 72.607595,82.7145587 85.4738752,82.7145587 C98.3405064,82.7145587 108.709962,94.5189427 108.488529,108.914901 C108.508531,123.290155 98.3405064,135.09489 85.4738752,135.09489 Z M170.525237,135.09489 C157.88039,135.09489 147.510584,123.290155 147.510584,108.914901 C147.510584,94.5396472 157.658606,82.7145587 170.525237,82.7145587 C183.391518,82.7145587 193.761324,94.5189427 193.539891,108.914901 C193.539891,123.290155 183.391518,135.09489 170.525237,135.09489 Z" :fill-rule "nonzero"}]]))

(o/defstyled checkmark :svg
  [:path {:stroke t/--icon-color
          :stroke-width "0.8"
          :stroke-dasharray 10
          :stroke-dashoffset 10
          :animation "dash 1s linear forwards"}]
  ([]
   [:<> {:viewbox "0 0 5.2916665 5.2916667"}
    [:path
     {:style "fill:none;stroke-linecap:round;stroke-linejoin:round"
      :d "M 0.42631356,2.9707555 C 1.069576,3.6627525 1.3437526,4.258271 1.6622465,4.8653766 2.1954501,3.393024 3.1913798,1.9143208 4.8653452,0.42631395"}]]))

(o/defrules animate-dash
  (garden.stylesheet/at-keyframes
   :dash
   [:to {:stroke-dashoffset 0}]))
