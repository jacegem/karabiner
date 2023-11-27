(ns two-key.part.row-4
  (:require [two-key.common :as co]))




(def rules
  [;; capslock
   {:description "caps_lock ➡️ ctrl+opt",
    :manipulators [{:from (co/key-any :caps)
                    :to [(co/set-var "spc->sft" 1)
                         (co/key-mods :ctrl :opt)]
                    :to_if_alone (co/key-mods :esc)
                    :to_after_key_up [(co/set-var "spc->sft" 0)]}]}

   {:description "s+d ➡️ ctrl+opt",
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:s :d]
                                   :to_after_key_up [(co/set-var "spc->sft" 0)]})
                    :to [(co/set-var "spc->sft" 1)
                         (co/key-mods :ctrl :opt)]}]}

   {:description "s+f ➡️ ctrl+opt+sft",
    :manipulators [{:from (co/sim {:keys [:s :f]})
                    :to (co/key-mods :ctrl :opt :sft)}]}

   {:description "d+f ➡️ opt+cmd :: symbol",
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:d :f]
                                   :to_after_key_up [(co/set-var "spc->sft" 0)]})
                    :to [(co/set-var "spc->sft" 1)
                         (co/key-mods :opt :cmd)]}]}
    ;; '를 enter 로 변경한다.    
   {:description "' ➡️ return"
    :manipulators [{:from (co/key-any :quote)
                    :to (co/key-mods :ret)}]}

  ;;  end
   ])


(comment


  {:description "s+d ➡️ ctrl+opt",
   :manipulators [{:from (co/sim {:keys [:s :d]
                                  :to_after_key_up [(co/set-var "caps-mode" 0)]})
                   :to [(co/set-var "caps-mode" 1)
                        (co/key-mods :ctrl :opt)]}]}

  :rcf)

  ;;  빠르게 입력하는 경우엔 입력되지 않는 문제s가 있다.
  ;;  #_{description "s ➡️ caps_lock ➡️ left_control",
  ;;     :manipulators [{
  ;;                     :from (co/key-any "s")
  ;;                     :to [(co/set-var "ctrl+opt" 1)
  ;;                          (co/key-mods "left_control"
  ;;                                       "left_option")]
  ;;                     :to_after_key_up [(co/set-var "ctrl+opt" 0)]
  ;;                     :to_if_alone [{:key_code "s"}]}]}])

  ;;  #_{
  ;;     :conditions [{:name "space->shift",
  ;;                   :type "variable_if",
  ;;                   :value 0}],
  ;;     :from {:key_code "spacebar",
  ;;            :modifiers {:optional [:any]}},
  ;;     :to [{:key_code "spacebar"}]}
