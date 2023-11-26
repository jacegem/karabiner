(ns two-key.flip)



(def flip-map {"w" "o"
               "e" "i"
               "r" "u"
               "q" "p"
               "a" "semicolon"
               "s" "l"
               "d" "k"
               "f" "j"
               "x" "period"
               "c" "comma"
               "v" "m"
               "z" "slash"
               "2" "9"
               "3" "8"
               "4" "7"
               "spacebar" "spacebar"})

(defn flip-manipulator [m]
  (let [key-path [:from :simultaneous]
        from-sim-keys (get-in m key-path)
        flip-keys (map (fn [m]
                         {:key_code (-> (:key_code m)
                                        flip-map)})
                       from-sim-keys)]
    (assoc-in m key-path flip-keys)))

(defn left-right [rule]
  (when (:copy-flip rule)
    (-> rule
        (update :manipulators (fn [ms] (map flip-manipulator ms)))
        (update :description (fn [desc] (str "[FLIP] " desc))))))
