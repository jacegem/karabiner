(ns two-key.main
  (:require [clojure.data.json :as json]
            [two-key.part.row-1 :as row-1]
            [two-key.part.row-2 :as row-2]
            [two-key.part.row-3 :as row-3]
            [two-key.part.row-34 :as row-3-4]
            [two-key.part.row-4 :as row-4]
            [two-key.part.row-45 :as row-4-5]
            [two-key.part.row-5 :as row-5]
            [two-key.part.row-6 :as row-6]))


(defn update-type [manipulator]
  (update manipulator :type (fnil identity "basic")))

(def rules
  (->> (concat row-1/rules
               row-2/rules
               row-3/rules
               row-3-4/rules
               row-4/rules
               row-4-5/rules
               row-5/rules
               row-6/rules)
       (map #(update % :manipulators
                     (fn [manipulators]
                       (map update-type manipulators))))))

(defn update-profiles [name rules profiles]
  (map (fn [profile]
         (if  (= name (:name profile))
           (assoc-in profile [:complex_modifications :rules] rules)
           profile))
       profiles))

(defn set-configs
  ([] (set-configs rules))
  ([rules]
   (let [file-name "karabiner.json"
         karabiner (json/read-str (slurp file-name)
                                  :key-fn keyword)
         config (update karabiner :profiles (partial update-profiles "Default profile" rules))
         json-str (json/write-str config {:escape-unicode false})]
     #_config
     (spit file-name json-str))))

(comment
  #_(rules)
  (set-configs)
  :rcf)
