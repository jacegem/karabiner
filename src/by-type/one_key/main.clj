;; (ns one-key.main
;;   (:require [clojure.data.json :as json]
;;             [one-key.part.row-2 :as row-2]))




;; (def rules
;;   (concat row-2/rule))


;; (defn set-profile [rules name profile]
;;   (when (= name (get profile "name"))
;;     (assoc-in profile ["complex_modifications" "rules"] rules)))


 

;; (defn set-configs [configs]
;;   (let [file-name "karabiner.json"
;;         karabiner (json/read-str (slurp file-name))
;;         profiles (map #(set-profile configs "Default profile" %)
;;                       (get karabiner "profiles"))
;;         json-str (-> (assoc karabiner "profiles" profiles)
;;                      (json/write-str {:escape-unicode false}))]
;;     #_profiles
;;     (spit file-name json-str)))

;; (comment
;;   (set-configs rules)
;;   :rcf)
