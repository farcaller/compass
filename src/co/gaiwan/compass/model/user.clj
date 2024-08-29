(ns co.gaiwan.compass.model.user
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]
   [co.gaiwan.compass.config :as config]
   [co.gaiwan.compass.model.assets :as assets]
   [hato.client :as hato])
  (:import
   (java.security MessageDigest)
   (java.util Formatter)))

(defn sha256 [bytes]
  (.digest (MessageDigest/getInstance "SHA-256") bytes))

(defn sha256-hex [bytes]
  (let [f (Formatter.)]
    (run! (fn [b]
            (.format ^Formatter f "%02x" (into-array Byte [b])))
          (sha256 bytes))
    (str f)))

(defn file-extension [mime]
  (let [[mime] (str/split mime #"\s*;\s*")]
    ({"image/png" "png"
      "image/jpeg" "jpeg"
      "image/gif" "gif"
      "image/webp" "webp"
      "image/svg+xml" "svg"} mime)))

(defn avatar-css-value [user]
  (if-let [url (:public-profile/avatar-url user)]
    (str "url(" (assets/asset-url url) ")")
    (str "var(--gradient-" (inc (mod (:db/id user) 7)) ")")))

(defn download-avatar [url]
  (let [{:keys [^bytes body headers]} (hato/get url {:as :byte-array})
        filename                      (str (sha256-hex body) "." (file-extension (get headers "content-type")))
        target                        (io/file (config/value :uploads/dir) filename)]
    (io/make-parents target)
    (with-open [f (io/output-stream target)]
      (.write f ^bytes body))
    filename))

(defn assigned-ticket [user]
  (first (:tito.ticket/_assigned-to user)))

(defn admin? [user]
  (when-let [ticket (assigned-ticket user)]
    (= "crew" (:tito.release/slug (:tito.ticket/release ticket)))))

(comment
  (def r (hato/get "https://cdn.discordapp.com/avatars/758588684177768469/8b32119c1ae262544e2952ea60aaf9a7.png" {:as :byte-array}))

  (download-avatar  "https://cdn.discordapp.com/avatars/758588684177768469/8b32119c1ae262544e2952ea60aaf9a7.png"))