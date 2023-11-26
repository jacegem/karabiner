;; (ns one-key.part.row-2
;;   (:require [one-key.common :as co]))

;; (def rule
;;   [{:description "caps_lock ➡️ left_control + left_option # :cap_lock->ctrl+opt",
;;     :manipulators [{:type "basic",
;;                     :from (co/key-any :caps) ,
;;                     :to [(co/set-var "space-mode" 1)
;;                          (co/set-var "space->shift" 1)
;;                          (co/key-modes :ctrl :opt)]
;;                     :to_after_key_up [(co/set-var "space-mode" 0)
;;                                       (co/set-var "space->shift" 0)]}]}

;;    {:description "s+d ➡️ left_control + left_option # :sd->ctrl+opt ",
;;     :manipulators [{:type "basic",
;;                     :from {:simultaneous (co/sim-keys "s" "d"),
;;                            :modifiers {:optional ["any"]},
;;                            :simultaneous_options (co/sim-ups
;;                                                   (co/set-var "space-mode" 0)
;;                                                   (co/set-var "space->shift" 0))},
;;                     :to [(co/set-var "space-mode", 1)
;;                          (co/set-var "space->shift", 1)
;;                          (co/key-mods :ctrl :opt)]}]},

;;    {:description "spcae ➡️ shift (다른 mods가 활성화된 경우에)"
;;     :manipulators [;;  delay 가 조금 있다. 이게 사용하다 보면 느껴질 것 같은데..
;;                    {:type "basic"
;;                     :conditions [(co/var-if "space-mode" 0)]
;;                     :from (co/key-any :spc)
;;                     :to [(co/key-mods :ctrl :cmd :sft)]
;;                     :to_if_alone [(co/key-mods "spacebar")]}

;;                    {:type "basic",
;;                     :conditions [(co/var-if "space-mode" 1)
;;                                  (co/var-if "space->shift" 1)],
;;                     :from (co/key-mods :spc :any) ,
;;                     :to [(co/key-mods :rsft)]}

;;                    {:type "basic",
;;                     :conditions [(co/var-if "space-mode" 1)
;;                                  (co/var-if "space->cmd" 1)],
;;                     :from (co/key-mods :spc :any) ,
;;                     :to [(co/key-mods :rcmd)]}

;;                    #_{:type "basic",
;;                       :conditions [{:name "space->shift",
;;                                     :type "variable_if",
;;                                     :value 0}],
;;                       :from {:key_code "spacebar",
;;                              :modifiers {:optional [:any]}},
;;                       :to [{:key_code "spacebar"}]}]}
;;   ;;  빠르게 입력하는 경우엔 입력되지 않는 문제s가 있다.
;;    #_{description "s ➡️ caps_lock ➡️ left_control",
;;       :manipulators [{:type "basic",
;;                       :from (co/key-any "s")
;;                       :to [(co/set-var "ctrl+opt" 1)
;;                            (co/key-mods "left_control"
;;                                         "left_option")]
;;                       :to_after_key_up [(co/set-var "ctrl+opt" 0)]
;;                       :to_if_alone [{:key_code "s"}]}]}])
  
