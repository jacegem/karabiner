(ns two-key.part.row-4
  (:require [two-key.common :as co]))



(def rules
  [{:description "caps_lock ➡️ left_control + left_option # :cap_lock->ctrl+opt",
    :manipulators [{:type "basic",
                    :from (co/key-any :caps) ,
                    :to [(co/set-var "caps-mode" 1)
                         (co/key-mods :ctrl :opt)]
                    :to_if_alone (co/key-mods :esc)
                    :to_after_key_up [(co/set-var "caps-mode" 0)]}]}

   {:description "s+d ➡️ ctrl+opt",
    :manipulators [{:type "basic",
                    :from (co/sim {:keys [:s :d]
                                   :to_after_key_up [(co/set-var "caps-mode" 0)]})
                    :to [(co/set-var "caps-mode" 1)
                         (co/key-mods :ctrl :opt)]}]}

   {:description "l+k ➡️ ctrl+opt",
    :manipulators [{:type "basic",
                    :from (co/sim {:keys [:l :k]
                                   :to_after_key_up [(co/set-var "caps-mode" 0)]})
                    :to [(co/set-var "caps-mode", 1)
                         (co/key-mods :ctrl :opt)]}]}

   {:description "s+f ➡️ ctrl+opt+sft",
    :manipulators [{:type "basic",
                    :from (co/sim :s :f)
                    :to (co/key-mods :ctrl :opt :sft)}]}

   {:description "l+j ➡️ ctrl+opt+sft",
    :manipulators [{:type "basic",
                    :from (co/sim :l :j)
                    :to (co/key-mods :ctrl :opt :sft)}]}

   {:description "d+f ➡️ opt+cmd",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim :d :f),
                    :to (co/key-mods :opt :cmd)}]}

   {:description "k+j ➡️ opt+cmd",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim :k :j),
                    :to (co/key-mods :opt :cmd)}]}
  ;;  end
   ])


(comment
  {:description "s+d ➡️ ctrl+opt",
   :manipulators [{:type "basic",
                   :from (co/sim {:keys [:s :d]
                                  :to_after_key_up [(co/set-var "caps-mode" 0)]})
                   :to [(co/set-var "caps-mode" 1)
                        (co/key-mods :ctrl :opt)]}]}
  :rcf)

  ;;  빠르게 입력하는 경우엔 입력되지 않는 문제s가 있다.
  ;;  #_{description "s ➡️ caps_lock ➡️ left_control",
  ;;     :manipulators [{:type "basic",
  ;;                     :from (co/key-any "s")
  ;;                     :to [(co/set-var "ctrl+opt" 1)
  ;;                          (co/key-mods "left_control"
  ;;                                       "left_option")]
  ;;                     :to_after_key_up [(co/set-var "ctrl+opt" 0)]
  ;;                     :to_if_alone [{:key_code "s"}]}]}])

  ;;  #_{:type "basic",
  ;;     :conditions [{:name "space->shift",
  ;;                   :type "variable_if",
  ;;                   :value 0}],
  ;;     :from {:key_code "spacebar",
  ;;            :modifiers {:optional [:any]}},
  ;;     :to [{:key_code "spacebar"}]}
