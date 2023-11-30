(ns hold.flip
  (:require [two-key.part.row-3 :as row-3]))



(def flip-map {"q" "p"
               "w" "o"
               "e" "i"
               "r" "u"
               "t" "y"
               "a" "semicolon"
               "s" "l"
               "d" "k"
               "f" "j"
               "g" "h"
               "z" "slash"
               "x" "period"
               "c" "comma"
               "v" "m"
               "b" "n"
               "spacebar" "spacebar"})



(defn flip-sim-keys [manipulator]
  (update-in manipulator [:from :simultaneous]
             (fn [sims]
               (map (fn [s]
                      (update s :key_code
                              (fn [k]
                                (get flip-map k)))) sims))))

(defn copy-flip [rules]
  (map (fn [rule]
         (-> rule
             (update :manipulators (fn [ms] (map flip-sim-keys ms)))
             (update :description (fn [desc] (str "[FLIP] " desc)))))
       rules))





(comment
  row-3/rules
  (copy-flip row-3/rules)
  :rcf)


#_(defn flip-manipulator [m]
    (let [key-path [:from :simultaneous]
          from-sim-keys (get-in m key-path)
          flip-keys (map (fn [m]
                           {:key_code (-> (:key_code m)
                                          flip-map)})
                         from-sim-keys)]
      (assoc-in m key-path flip-keys)))

#_(defn left-right [rule]
    (when (:copy-flip rule)
      (-> rule
          (update :manipulators (fn [ms] (map flip-manipulator ms)))
          (update :description (fn [desc] (str "[FLIP] " desc))))))
