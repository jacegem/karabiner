(ns hold.part.row-2
  (:require [hold.common :as co]
            [hold.var :as var]))


(def sim-rules
  [{:description "a+s ➡️ cmd + s",
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:a :s]})
                    :to [(co/key-mods :s :cmd)]}]}

   {:description "s+d ➡️ ctrl+opt",
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:s :d]
                                   :to_after_key_up [(co/set-var var/space-changed 0)
                                                     (co/set-var var/space->shift 0)]})
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :ctrl :opt)]}]}

   {:description "s+f ➡️ ctrl+opt+sft",
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:s :f]
                                   :to_after_key_up [(co/set-var  var/space-changed 0)
                                                     (co/set-var  var/space->command 0)]})
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->command 1)
                         (co/key-mods :ctrl :opt :sft)]}]}

   {:description "d+f ➡️ opt+cmd :: symbol",
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:d :f]
                                   :to_after_key_up [(co/set-var  var/space-changed 0)
                                                     (co/set-var  var/space->shift 0)]})
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :opt :cmd)]}]}])





(def rules
  [;; capslock
   {:description "#caps_lock ➡️ ctrl+opt / space 제외",
    :manipulators [{:from (co/key-any :caps)
                    :to [(co/key-mods :ctrl :opt)]
                    :to_if_alone [(co/key-mods :s :cmd)
                                  (co/key-mods :esc)]}]}


   #_{:description "#semicolon "
      :manipulators [{:from (co/key-any :semicolon)
                      :to [(co/set-var var/space-changed 1)
                           (co/set-var var/space->shift 1)
                           (co/key-mods :ctrl :opt)]
                      :to_after_key_up [(co/set-var var/space-changed 0)
                                        (co/set-var var/space->shift 0)]
                      :to_if_alone (co/key-mods :semicolon)}]}


   {:description "#quote",
    :manipulators [{:from (co/key-any :quote)
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :ctrl :cmd)]
                    :to_after_key_up [(co/set-var var/space-changed 0)
                                      (co/set-var var/space->shift 0)]
                    :to_if_alone (co/key-mods :quote)}]}



  ;;  end :;:;:;
   ])


(comment
  {:from (co/from-key-code {:key :semicolon
                            :mods [:sft]})
   :to [(co/key-mods :semicolon)]}

  {:description "s+d ➡️ ctrl+opt",
   :manipulators [{:from (co/sim {:keys [:s :d]
                                  :to_after_key_up [(co/set-var "caps-mode" 0)]})
                   :to [(co/set-var "caps-mode" 1)
                        (co/key-mods :ctrl :opt)]}]}

  :rcf)
#_{:description "#quote shift",
   :manipulators [{:from (co/from-key-code {:key :quote
                                            :mods [:shift]})
                   :to [(co/key-mods :quote)]}]}
#_{:description "' ➡️ return"
   :manipulators [{:from (co/key-any :quote)
                   :to (co/key-mods :ret)}]}

#_{:description "#semicolon shift "
   :manipulators [{:from (co/from-key-code {:key :semicolon
                                            :mods [:shift]})
                   :to [(co/key-mods :semicolon)]}]}

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
