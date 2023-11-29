(ns layers.main
  (:require [clojure.data.json :as json]
            [layers.flip :as flip]
            [layers.part.row-1 :as row-1]
            [layers.part.row-12 :as row-12]
            [layers.part.row-2 :as row-2]
            [layers.part.row-23 :as row-23]
            [layers.part.row-3 :as row-3]
            [layers.part.row-4 :as row-4]
            [layers.part.row-fn :as row-fn]))
#_(remove-ns 'layers.main)

#_(defn update-type [manipulator]
    (update manipulator :type (fnil identity "basic")))



(comment
  rules


  :rcf)

(defn update-profiles [name rules profiles]
  (map (fn [profile]
         (if  (= name (:name profile))
           (assoc-in profile [:complex_modifications :rules] rules)
           profile))
       profiles))

(defn update-type [manipulators]
  (map #(update % :type (fnil identity "basic"))
       manipulators))


(def rules
  (let [left-rules (concat row-fn/rules
                           row-1/sim-rules
                           row-2/sim-rules
                           row-3/sim-rules
                           row-4/sim-rules
                           row-12/sim-rules
                           row-23/sim-rules)
        right-rules (->> left-rules
                         (filter #(= true (:copy-flip %)))
                         flip/copy-flip)
        single-rules (concat row-1/rules
                             row-2/rules
                             row-12/rules
                             row-4/rules
                             row-23/rules
                             row-3/rules
                             row-4/rules)]
    (->> (concat left-rules right-rules single-rules)
         (map #(update % :manipulators update-type))
         (map #(dissoc % :copy-flip)))))

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
  rules
  (set-configs)
  :rcf)
