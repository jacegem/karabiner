(ns layers.part.row-2
  (:require [layers.common :as co]
            [layers.var :as var]))


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
   {:description "#caps_lock ➡️ [nav-pressed] ➡️ ctrl+opt",
    :manipulators [{:from (co/key-any :caps)
                    :to [(co/set-var var/layer-active 1)
                         (co/set-var var/nav-pressed 1)]
                    :to_after_key_up [(co/set-var var/layer-active 0)
                                      (co/set-var var/nav-pressed 0)]
                    :to_if_alone [(co/key-mods :s :cmd)
                                  (co/key-mods :esc)]}]}

   {:description "#a "
    :manipulators (var/layer-key {:key :a}
                                 {:nav (co/key-mods :a :cmd)
                                  :sym (co/key-mods :act)
                                  :num (co/key-mods :+)
                                  :func (co/key-mods :f11)
                                  :held-mode var/nav-pressed
                                  :held-to (co/key-mods :sft)})}

   {:description "#s ",
    :manipulators (var/layer-key {:key :s}
                                 {:nav (co/key-mods :s :cmd)
                                  :sym (co/key-mods :act :sft)
                                  :num (co/key-mods :4)
                                  :func (co/key-mods :f4)
                                  :mouse (co/mouse-pos :y-)})}

   {:description "#d ",
    :manipulators (var/layer-key {:key :d}
                                 {:nav (co/key-mods :d :cmd)
                                  :sym (co/key-mods :1 :sft)
                                  :num (co/key-mods :5)
                                  :func (co/key-mods :f5)
                                  :mouse (co/mouse-pos :x-)})}

   {:description "#f ",
    :manipulators (var/layer-key {:key :f}
                                 {:nav (co/key-mods :f :cmd)
                                  :sym (co/key-mods :act :sft)
                                  :num (co/key-mods :4)
                                  :func (co/key-mods :f4)
                                  :mouse (co/mouse-pos :x)})}

   {:description "#f + shift",
    :manipulators (var/layer-key {:key :f
                                  :mods [:sft]}
                                 {:nav (co/key-mods :f :cmd :sft)
                                  :sym (co/key-mods :act :sft)
                                  :mouse (co/mouse-pos :x {:fast 2})})}

   {:description "#h ",
    :manipulators (var/layer-key {:key :h}
                                 {:nav (co/key-mods :home)
                                  :sym (co/key-mods :equal_sign :sft)
                                  :mouse (co/mouse-screen-center 1)})}

   {:description "#j "
    :manipulators (var/layer-key {:key :j}
                                 {:nav (co/key-mods :left_arrow)
                                  :sym (co/key-mods :9 :sft)
                                  :mouse (co/key-mods :left_arrow)})}

   {:description "#k "
    :manipulators (var/layer-key {:key :k}
                                 {:nav (co/key-mods :down_arrow)
                                  :sym (co/key-mods :open_bracket)})}

   {:description "#l "
    :manipulators (var/layer-key {:key :l}
                                 {:nav (co/key-mods :right_arrow)
                                  :sym (co/key-mods :open_bracket :sft)})}

   {:description "#semicolon "
    :manipulators (var/layer-key {:key :semicolon}
                                 {:nav (co/key-mods :end)
                                  :sym (co/key-mods :quote :sft)})}

   {:description "#quote",
    :manipulators [{:from (co/key-any :quote)
                    :to [(co/set-var var/layer-active 1)
                         (co/set-var var/nav-pressed 1)]
                    :to_after_key_up [(co/set-var var/layer-active 0)
                                      (co/set-var var/nav-pressed 0)]
                    :to_if_alone (co/key-mods :quote)}]}

  ;;  end
   ])


(comment


  {:description "s+d ➡️ ctrl+opt",
   :manipulators [{:from (co/sim {:keys [:s :d]
                                  :to_after_key_up [(co/set-var "caps-mode" 0)]})
                   :to [(co/set-var "caps-mode" 1)
                        (co/key-mods :ctrl :opt)]}]}

  :rcf)

#_{:description "' ➡️ return"
   :manipulators [{:from (co/key-any :quote)
                   :to (co/key-mods :ret)}]}
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
