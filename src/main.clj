(ns main
  (:require [clojure.string :as str]
            [simultaneous-rule :refer [gen-simultaneous-rule]]
            [single-rule :refer [gen-single-rule]]))

;; https://github.com/pqrs-org/Karabiner-Elements/issues/925

(def config-map
  {;; single 
   :cap_lock->ctrl+opt {:from {:keys ["caps_lock"]
                               :mods []}
                        :to {:keys ["left_control"]
                             :mods ["left_option"]}}

   ;;  line 1
   :shift+ctrl {:from {:keys ["3" "r"]
                       :mods []}
                :to {:keys ["left_shift"]
                     :mods ["left_control"]}}
   ;; line 2
   :escape {:from {:keys ["w" "e"]
                   :mods []}
            :to {:keys ["escape"]
                 :mods []}}

   :tab {:from {:keys ["w" "r"]
                :mods []}
         :to {:keys ["tab"]
              :mods []}}

   :delete_forward {:from {:keys ["w" "e" "r"]
                           :mods []}
                    :to {:keys ["delete_forward"]
                         :mods []}}

   :delete_or_backspace {:from {:keys ["e" "r"]
                                :mods []}
                         :to {:keys ["delete_or_backspace"]
                              :mods []}}

   :return_or_enter {:from {:keys ["w" "d"]
                            :mods []}
                     :to {:keys ["return_or_enter"]
                          :mods []}}

   :return_or_enter/r {:from {:keys ["w" "r"]
                              :mods []}
                       :to {:keys ["return_or_enter"]
                            :mods []}}

   :shift+ctrl+cmd {:from {:keys ["w" "e" "f"]
                           :mods []}
                    :to {:keys ["left_shift"]
                         :mods ["left_control"
                                "left_command"]}}

   :shift+opt {:from {:keys ["e" "f"]
                      :mods []}
               :to {:keys ["left_shift"]
                    :mods ["left_option"]}}

  ;;  line 3
   :caps/ctrl+opt {:from {:keys ["s" "d"]
                          :mods []}
                   :to {:keys ["left_control"]
                        :mods ["left_option"]}}

   :caps/ctrl+opt+cmd {:from {:keys ["s" "f"]
                              :mods []}
                       :to {:keys ["left_control"]
                            :mods ["left_option" "left_command"]}}

   :caps/ctrl+opt+shift+cmd {:from {:keys ["s" "d" "f"]
                                    :mods []}
                             :to {:keys ["left_control"]
                                  :mods ["left_option"
                                         "left_shift"
                                         "left_command"]}}

   :opt+cmd {:from {:keys ["d" "f"]
                    :mods []}
             :to {:keys ["left_option"]
                  :mods ["left_command"]}}

   :caps/ctrl+opt+shift {:from {:keys ["s" "c"]
                                :mods []}
                         :to {:keys ["left_control"]
                              :mods ["left_option"
                                     "left_shift"]}}

   :caps/ctrl+opt+shift_v {:from {:keys ["s" "v"]
                                  :mods []}
                           :to {:keys ["left_control"]
                                :mods ["left_option"
                                       "left_shift"]}}

   :shift+opt+cmd {:from {:keys ["s" "d" "v"]
                          :mods []}
                   :to {:keys ["left_shift"]
                        :mods ["left_option"
                               "left_command"]}}

   :shift+cmd {:from {:keys ["d" "v"]
                      :mods []}
               :to {:keys ["left_shift"]
                    :mods ["left_command"]}}

  ;;  line 4
   :shift {:from {:keys ["x" "v"]
                  :mods []}
           :to {:keys ["left_shift"]
                :mods []}}

   :ctrl+cmd {:from {:keys ["x" "v"]
                     :mods []}
              :to {:keys ["left_control"]
                   :mods ["left_command"]}}

   :opt {:from {:keys ["x" "c" "v"]
                :mods []}
         :to {:keys ["left_option"]
              :mods []}}

   :cmd {:from {:keys ["c" "v"]
                :mods []}
         :to {:keys ["left_command"]
              :mods []}}})

(comment
  (spit "config.txt" (generate config-map))
  :rcf)


(defn rule [config]
  (let [[_ value] config
        from-keys (get-in value [:from :keys])
        keys-len (count from-keys)]
    (if (<= keys-len 1)
      (gen-single-rule config)
      (gen-simultaneous-rule config))))


(defn generate [configs]
  (str/join ", " (map rule configs)))
