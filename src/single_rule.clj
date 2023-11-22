(ns single-rule
  (:require [clojure.pprint :as pprint]
            [clojure.string :as str]
            [string-util :refer [double-quote]]))

(def rule-format
  "{\"description\": %s, 
  \"manipulators\": [
        {
            \"from\": {
                \"key_code\": %s,
                \"modifiers\": {
                    \"optional\": [
                        \"any\"
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



(defn gen-single-rule [[name {:keys [from to]}]]
  (let [{from-keys :keys} from
        {to-keys :keys to-mods :mods} to
        from-key-code (double-quote (first from-keys))
        to-key-code (double-quote (first to-keys))
        to-modifers (str/join ", " (map double-quote to-mods))
        desc (str from-key-code " ➡️ " to-key-code " + " to-modifers)]
    (format rule-format
            (double-quote desc)
            from-key-code
            to-key-code
            to-modifers)))


(comment
  (def config
    [:ctrl+opt {:from {:keys ["caps_lock"]
                       :mods []}
                :to {:keys ["left_control"]
                     :mods ["left_option"]}}])
  (pprint/pprint (gen-single-rule config))
  (spit "some.txt" (gen-single-rule config))
  :rcf)
