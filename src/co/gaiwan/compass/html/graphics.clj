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

(o/defstyled person-remove :svg
  {:--_remove-color "#ff4d4d"}
  [:path {:fill "var(--_remove-color)"}]
  ([]
   [:<> {:viewBox "0 0 24 24"}
    [:rect {:fill "none", :height "24", :width "24"}]
    [:path
     {:d
      "M14,8c0-2.21-1.79-4-4-4S6,5.79,6,8s1.79,4,4,4S14,10.21,14,8z M17,10v2h6v-2H17z M2,18v2h16v-2c0-2.66-5.33-4-8-4 S2,15.34,2,18z"}]]))

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

(o/defstyled scan-icon :svg
  ([]
   [:<> {:viewbox "0 0 1040 1024"}
    [:path
     {:d "M331.312778 27.631314l0-0.002047L200.124923 27.629267c-93.889367 0-170.006975 76.115562-170.006975 170.006975l0 131.107013c0 0.024559 0 0.053212 0 0.080841 0 33.271778 26.970258 60.239989 60.238966 60.239989 33.269731 0 60.239989-26.968212 60.239989-60.239989l0-0.002047 0 0L150.596903 211.858152c0-35.208896 28.54308-63.751976 63.752999-63.751976l116.879987 0c0.024559 0 0.053212 0.002047 0.080841 0.002047 33.268708 0 60.239989-26.968212 60.239989-60.238966C391.549697 54.601572 364.579439 27.631314 331.312778 27.631314z"}]
    [:path
     {:d "M993.938334 690.254823c0-33.267685-26.969235-60.236919-60.238966-60.236919s-60.238966 26.969235-60.238966 60.236919l0 0.002047 0 0 0 116.962875c0 35.20992-28.544103 63.752999-63.754023 63.752999L692.743504 870.972744c-33.268708 0-60.241013 26.970258-60.241013 60.237943 0 33.270754 26.972305 60.238966 60.241013 60.238966 0.026606 0 0.054235-0.002047 0.082888-0.002047l131.104967 0c93.892437 0 170.006975-76.116585 170.006975-170.007999L993.938334 690.25687l0 0L993.938334 690.254823z"}]
    [:path
     {:d "M331.312778 870.972744 214.349903 870.972744c-35.20992 0-63.752999-28.54308-63.752999-63.752999L150.596903 690.25687l0 0 0-0.002047c0-33.267685-26.970258-60.236919-60.239989-60.236919-33.268708 0-60.238966 26.969235-60.238966 60.236919 0 0.026606 0 0.056282 0 0.080841l0 131.103944c0 93.891414 76.117608 170.007999 170.006975 170.007999l131.104967 0c0.028653 0 0.056282 0.002047 0.082888 0.002047 33.269731 0 60.239989-26.968212 60.239989-60.238966C391.551744 897.943003 364.581486 870.972744 331.312778 870.972744z"}]
    [:path
     {:d "M823.931359 27.629267l-131.187855 0 0 0.002047c-33.268708 0-60.241013 26.970258-60.241013 60.237943 0 33.270754 26.972305 60.238966 60.241013 60.238966 0.026606 0 0.054235-0.002047 0.082888-0.002047l116.879987 0c35.20992 0 63.754023 28.54308 63.754023 63.751976l0 116.966968 0 0c0 33.271778 26.969235 60.237943 60.238966 60.237943s60.238966-26.966165 60.238966-60.237943l0 0L993.938334 197.636243C993.938334 103.744829 917.823795 27.629267 823.931359 27.629267z"}]
    [:path
     {:d "M963.820386 449.299983c-0.026606 0-0.056282 0.002047-0.080841 0.002047L60.321854 449.302029c-0.028653 0-0.056282-0.002047-0.082888-0.002047-33.270754 0-60.238966 26.970258-60.238966 60.241013 0 33.266661 26.968212 60.237943 60.238966 60.237943l903.579373 0 0 0 0.002047 0c33.267685 0 60.234873-26.970258 60.234873-60.237943C1024.055259 476.270241 997.087047 449.299983 963.820386 449.299983z"}]]))

(o/defstyled github-icon :svg
  ([]
   [:<> {:viewbox "0 0 24 24"}
    [:path {:d "M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z"}]]))

(o/defstyled cog-icon :svg
  ([]
   [:<> {:viewbox "0 -256 1536 1536"}
    [:path {:d "m 1024,512 q 0,106 -75,181 -75,75 -181,75 -106,0 -181,-75 -75,-75 -75,-181 0,-106 75,-181 75,-75 181,-75 106,0 181,75 75,75 75,181 z m 512,109 V 399 q 0,-12 -8,-23 -8,-11 -20,-13 l -185,-28 q -19,-54 -39,-91 35,-50 107,-138 10,-12 10,-25 0,-13 -9,-23 -27,-37 -99,-108 -72,-71 -94,-71 -12,0 -26,9 L 1035,-4 q -44,-23 -91,-38 -16,-136 -29,-186 -7,-28 -36,-28 H 657 q -14,0 -24.5,8.5 Q 622,-239 621,-226 l -28,184 q -49,16 -90,37 L 362,-112 q -10,-9 -25,-9 -14,0 -25,11 Q 186,4 147,58 q -7,10 -7,23 0,12 8,23 15,21 51,66.5 36,45.5 54,70.5 -27,50 -41,99 L 29,367 Q 16,369 8,379.5 0,390 0,403 v 222 q 0,12 8,23 8,11 19,13 l 186,28 q 14,46 39,92 -40,57 -107,138 -10,12 -10,24 0,10 9,23 26,36 98.5,107.5 72.5,71.5 94.5,71.5 13,0 26,-10 l 138,-107 q 44,23 91,38 16,136 29,186 7,28 36,28 h 222 q 14,0 24.5,-8.5 Q 914,1263 915,1250 l 28,-184 q 49,-16 90,-37 l 142,107 q 9,9 24,9 13,0 25,-10 129,-119 165,-170 7,-8 7,-22 0,-12 -8,-23 -15,-21 -51,-66.5 -36,-45.5 -54,-70.5 26,-50 41,-98 l 183,-28 q 13,-2 21,-12.5 8,-10.5 8,-23.5 z"}]]
   ))
