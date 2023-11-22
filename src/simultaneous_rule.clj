(ns simultaneous-rule
  (:require [clojure.string :as str]
            [string-util :refer [double-quote]]))


(def rule-format
  "{
\"description\": %s,
    \"manipulators\": [
        {
            \"from\": {
                \"simultaneous\": [ %s ],
                \"simultaneous_options\": {
                    \"detect_key_down_uninterruptedly\": true,
                    \"key_down_order\": \"insensitive\",
                    \"key_up_order\": \"insensitive\",
                    \"key_up_when\": \"any\",
                    \"to_after_key_up\": [
                        {
                            \"set_variable\": {
                                \"name\": \"super-mode\",
                                \"value\": 0
                            }
                        }
                    ]
                }
            },
            \"to\": [
                {
                    \"key_code\": %s,
                    \"modifiers\": [ %s ]
                }
            ],
            \"type\": \"basic\"
        }
    ]
}")

(defn simul-key-code [key-code]
  (str "{\"key_code\": \"" key-code "\" }"))

(defn gen-simultaneous-rule [[name {:keys [from to]}]]
  (let [{from-keys :keys} from
        {to-keys :keys to-mods :mods} to
        from-key-code (str/join ", " (map simul-key-code from-keys))
        to-key-code (double-quote (first to-keys))
        to-modifers (str/join ", " (map double-quote to-mods))
        desc (str (str/join "+" from-keys) " ➡️ " (str/join " + " (concat [to-key-code] to-mods)))]
    (format rule-format
            (double-quote desc)
            from-key-code
            to-key-code
            to-modifers)))


(def left-right {"w" "o"
                 "e" "i"
                 "r" "u"
                 "a" "semicolon"
                 "s" "l"
                 "d" "k"
                 "f" "j"
                 "x" "period"
                 "c" "comma"
                 "q" "p"
                 "v" "m"
                 "z" "slash"
                 "2" "9"
                 "3" "8"
                 "4" "7"
                 "spacebar" "spacebar"})


(defn gen-simultaneous-rule-right [[name {:keys [from to] :as value}]]
  (let [{from-keys :keys} from
        right-keys (map left-right from-keys)]
    (->> [name (assoc-in value [:from :keys] right-keys)]
         (gen-simultaneous-rule))))


(comment
  (def config
    [:shift+ctrl {:from {:keys ["c" "r"]
                         :mods []}
                  :to {:keys ["s"]
                       :mods ["left_control"]}}])
  (gen-simultaneous-rule config)
  (gen-simultaneous-rule-right config)
  :rcf) 
