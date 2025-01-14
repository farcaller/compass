(ns co.gaiwan.compass.css.tokens
  "Design tokens, partly imported from Open Props, partly defined here"
  {:ornament/prefix ""}
  (:require
   [charred.api :as charred]
   [clojure.java.io :as io]
   [garden.stylesheet :as gs]
   [lambdaisland.ornament :as o]))

(o/import-tokens! (charred/read-json (io/resource "open-props.tokens.json")) {:include-values? false})

(o/defprop --size-0 0)
(o/defprop --text-1)
(o/defprop --text-2)
(o/defprop --link)

(o/defprop --hoc-pink "#e25f7d")
(o/defprop --hoc-pink-1 "#e7879d")
(o/defprop --hoc-pink-2 "#cd4e6a")
(o/defprop --hoc-pink-3 --hoc-pink)
(o/defprop --hoc-pink-4 "#c0415b")
(o/defprop --hoc-green "#99db70")

(o/defprop --surface-1)
(o/defprop --surface-2)
(o/defprop --surface-3)
(o/defprop --surface-4)

(o/defprop --icon-color)

(o/defprop --talk-color)
(o/defprop --workshop-color)
(o/defprop --office-hours-color)
(o/defprop --activity-color)

(o/defprop --highlight)
(o/defprop --highlight-yellow)

(o/defprop --arc-thickness "30px")

(o/defrules session-colors
  [":where(html)"
   {--talk-color         --blue-2
    --workshop-color     --teal-2
    --office-hours-color --red-2
    --activity-color     --red-2
    --highlight-yellow   --yellow-2
    --highlight          --hoc-pink-1}]

  (gs/at-media
   {:prefers-color-scheme 'dark}
   [":where(html)"
    {--talk-color         --blue-9
     --workshop-color     --teal-8
     --office-hours-color --red-9
     --activity-color     --red-9
     --highlight-yellow   --teal-12
     --highlight          --hoc-pink-4}]))
