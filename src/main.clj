(ns main
  (:require [clojure.string :as str]
            [simultaneous-rule :refer [gen-simultaneous-rule
                                       gen-simultaneous-rule-right]]
            [single-rule :refer [gen-single-rule]]))

;; https://github.com/pqrs-org/Karabiner-Elements/issues/925

(def config-map
  {;; single 
   :cap_lock->ctrl+opt {:from {:keys ["caps_lock"]
                               :mods []}
                        :to {:keys ["left_control"]
                             :mods ["left_option"]}}

   ;; column 1 
   :cmd+s {:from {:keys ["a" "s"]
                  :mods []}
           :to {:keys ["s"]
                :mods ["left_command"]}}

   :cmd+x {:from {:keys ["a" "x"]
                  :mods []}
           :to {:keys ["x"]
                :mods ["left_command"]}}

   :cmd+c {:from {:keys ["a" "c"]
                  :mods []}
           :to {:keys ["c"]
                :mods ["left_command"]}}

   :cmd+v {:from {:keys ["a" "v"]
                  :mods []}
           :to {:keys ["v"]
                :mods ["left_command"]}}


   ;;  line 1
   :shift+ctrl {:from {:keys ["2" "e"]
                       :mods []}
                :to {:keys ["left_shift"]
                     :mods ["left_control"]}}

   :shift+opt {:from {:keys ["2" "r"]
                      :mods []}
               :to {:keys ["left_shift"]
                    :mods ["left_option"]}}

   :shift+cmd {:from {:keys ["3" "r"]
                      :mods []}
               :to {:keys ["left_shift"]
                    :mods ["left_command"]}}

   ;; line 2
   :escape {:from {:keys ["w" "e"]
                   :mods []}
            :to {:keys ["escape"]
                 :mods []}
            :right false}

   :tab {:from {:keys ["w" "r"]
                :mods []}
         :to {:keys ["tab"]
              :mods []}}

   :delete_or_backspace {:from {:keys ["e" "r"]
                                :mods []}
                         :to {:keys ["delete_or_backspace"]
                              :mods []}}

   :return_or_enter {:from {:keys ["w" "d"]
                            :mods []}
                     :to {:keys ["return_or_enter"]
                          :mods []}}

   :return_or_enter_2 {:from {:keys ["w" "f"]
                              :mods []}
                       :to {:keys ["return_or_enter"]
                            :mods []}}

   :shift+opt+cmd {:from {:keys ["e" "f"]
                          :mods []}
                   :to {:keys ["left_shift"]
                        :mods ["left_command"]}}

   ;;  line 3
   :caps/ctrl+opt {:from {:keys ["s" "d"]
                          :mods []}
                   :to {:keys ["left_control"]
                        :mods ["left_option"]}}

   :caps/ctrl+opt+cmd {:from {:keys ["s" "f"]
                              :mods []}
                       :to {:keys ["left_control"]
                            :mods ["left_option"
                                   "left_command"]}}

   :opt+cmd {:from {:keys ["d" "f"]
                    :mods []}
             :to {:keys ["left_option"]
                  :mods ["left_command"]}}

   :caps/shift+ctrl+opt {:from {:keys ["s" "c"]
                                :mods []}
                         :to {:keys ["left_shift"]
                              :mods ["left_control"
                                     "left_option"]}}

   :caps/shift+ctrl+opt+cmd {:from {:keys ["s" "v"]
                                    :mods []}
                             :to {:keys ["left_shift"]
                                  :mods ["left_control"
                                         "left_option"
                                         "left_command"]}}

   :ctrl+cmd {:from {:keys ["d" "v"]
                     :mods []}
              :to {:keys ["left_control"]
                   :mods ["left_command"]}}

  ;;  line 4
   :shift {:from {:keys ["x" "c"]
                  :mods []}
           :to {:keys ["left_shift"]
                :mods []}}

   :shift+ctrl+cmd {:from {:keys ["x" "v"]
                           :mods []}
                    :to {:keys ["left_shift"]
                         :mods ["left_control"
                                "left_command"]}}

   :cmd {:from {:keys ["c" "v"]
                :mods []}
         :to {:keys ["left_command"]
              :mods []}}})

(comment
  (spit "rules.txt" (generate config-map))
  :rcf)


(defn rule [config]
  (let [[_ value] config
        from-keys (get-in value [:from :keys])
        keys-len (count from-keys)]
    (if (<= keys-len 1)
      (gen-single-rule config)
      (gen-simultaneous-rule config))))

(defn rule-right [config]
  (let [[_name value] config
        from-keys (get-in value [:from :keys])
        keys-len (count from-keys)
        right  (:right value)]
    (when (and (>= keys-len 2)
               (not= right false))
      (gen-simultaneous-rule-right config))))

(defn generate [configs]
  (->> (concat (map rule configs)
               (map rule-right configs))
       (remove nil?)
       (str/join ", ")))
