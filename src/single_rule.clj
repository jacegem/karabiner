(ns single-rule
  (:require [clojure.string :as str]))

(defn add-alone-key [config m]
  (let [alone-key (get-in config [:from :alone-key])]
    (if (some? alone-key)
      (assoc m :to_if_alone [{:key_code alone-key}])
      m)))

(defn get-single-rule [[name {:keys [from to] :as config}]]
  (let [{from-keys :keys} from
        {to-keys :keys to-mods :mods} to
        from-key-code (first from-keys)
        to-key-code (first to-keys)
        to-modifers (str/join ", " to-mods)
        desc (str from-key-code " ➡️ " to-key-code " + " to-modifers " # " name)
        manipulator {:type "basic"
                     :from {:key_code from-key-code
                            :modifiers {:optional ["any"]}}
                     :to [{:key_code to-key-code
                           :modifiers to-mods}]}]
    (-> {:description desc
         :manipulators [(add-alone-key config manipulator)]})))

(comment
  (def config
    [:ctrl+opt {:from {:keys ["caps_lock"]
                       :mods []
                       :alone-key "escape"}
                :to {:keys ["left_control"]
                     :mods ["left_option"]}}])

  (get-single-rule config)
  :rcf)
