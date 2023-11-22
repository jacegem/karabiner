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
                                \"name\": \"superduper-mode\",
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




(comment

  (def config
    [:shift+ctrl {:from {:keys ["3" "r"]
                         :mods []}
                  :to {:keys ["left_shift"]
                       :mods ["left_control"]}}])
  (gen-simultaneous-rule config)
  :rcf) 
