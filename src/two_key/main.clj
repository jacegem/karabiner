(ns two-key.main
  (:require [clojure.data.json :as json]
            [two-key.flip :as flip]
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
  (let [lefts (concat row-1/rules
                      row-2/rules
                      row-3/rules
                      row-3-4/rules
                      row-4/rules
                      row-4-5/rules
                      row-5/rules
                      row-6/rules)

        rights (map flip/left-right lefts)]
    (->> (concat lefts rights)
         (remove nil?)
         (map #(update % :manipulators
                       (fn [manipulators]
                         (map update-type manipulators)))))))

(comment
  (rules)
  :rcf)


(defn set-profile [rules name profile]
  (when (= name (get profile "name"))
    (assoc-in profile ["complex_modifications" "rules"] rules)))

(defn set-configs
  ([] (set-configs rules))
  ([rules]
   (let [file-name "karabiner.json"
         karabiner (json/read-str (slurp file-name))
         profiles (map #(set-profile rules "Default profile" %)
                       (get karabiner "profiles"))
         json-str (-> (assoc karabiner "profiles" profiles)
                      (json/write-str {:escape-unicode false}))]
     #_profiles
     (spit file-name json-str))))

(comment
  #_(rules)
  (set-configs)
  :rcf)
