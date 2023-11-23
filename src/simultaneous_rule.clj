(ns simultaneous-rule
  (:require [clojure.string :as str]))

(def left-right {"w" "o"
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

(defn get-simultaneous-rule [[name {:keys [from to right?]}]]
  (let [{from-keys :keys} from
        {to-keys :keys to-mods :mods} to
        from-key-codes (map (fn [key] {:key_code key}) from-keys)
        to-key-code (first to-keys)
        desc (str (str/join "+" from-keys) " ➡️ " (str/join " + " (cons to-key-code to-mods))
                  " # " name " " (if right? "(right)" ""))
        manipulator {:type "basic"
                     :from {:simultaneous from-key-codes
                            :modifiers {:optional ["any"]}
                            :simultaneous_options {:detect_key_down_uninterruptedly false
                                                   :key_down_order "insensitive"
                                                   :key_up_order "insensitive"
                                                   :key_up_when "any"
                                                   :to_after_key_up []}}
                     :to [{:key_code to-key-code
                           :modifiers to-mods}]}]
    {:description desc
     :manipulators [manipulator]}))

(defn get-simultaneous-rule-right [[name {:keys [from] :as value}]]
  (let [{from-keys :keys} from
        right-keys (map left-right from-keys)]
    (when (every? some? right-keys)
      (->> [name (-> (assoc-in value [:from :keys] right-keys)
                     (assoc :right? true))]
           (get-simultaneous-rule)))))


(comment
  (def config
    [:shift+ctrl {:from {:keys ["c" "r"]
                         :mods []}
                  :to {:keys ["s"]
                       :mods ["left_control"]}}])
  (get-simultaneous-rule config)
  (get-simultaneous-rule-right config)
  :rcf)
