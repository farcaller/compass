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
   :stroke t/--gray-9}
  ([& _]
   [:<> {:viewBox "0 0 20 15"}
    (for [x [1 7.5 14]]
      [:path {:d (str "M 2," x " H 18")}])]))

(o/defstyled cross :svg
  {:stroke-width "13%"
   :stroke-linecap "round"
   :stroke t/--gray-9}
  ([]
   [:<> {:viewBox "0 0 20 15"}
    [:path {:d "M 4.0348568,1.5348556 15.965143,13.465142"}]
    [:path {:d "M 15.965143,1.5348556 4.0348568,13.465142"}]
    ]))