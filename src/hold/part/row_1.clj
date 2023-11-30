(ns hold.part.row-1
  (:require [hold.common :as co]
            [hold.var :as var]))
#_(remove-ns 'hold.part.row-1)


(def sim-rules
  [;  w
   {:description "w+e ➡️ escape"
    :copy-flip true
    :manipulators [{:from (co/sim :w :e)
                    :to [(co/key-mods :s :cmd)
                         (co/key-mods :esc)]}]}

   {:description "w+r ➡️ tab"
    :copy-flip true
    :manipulators [{:from (co/sim :w :r),
                    :to [(co/key-mods :tab)]}]}

   {:description "q+r ➡️ shift+tab"
    :copy-flip true
    :manipulators [{:from (co/sim :q :r),
                    :to [(co/key-mods :tab :sft)]}]}

   {:description "e+r ➡️ backspace"
    :copy-flip true
    :manipulators [{:from (co/sim :e :r),
                    :to [(co/key-mods :bspc)]}]}

   {:description "q+r ➡️ backspace"
    :copy-flip true
    :manipulators [{:from (co/sim :q :r),
                    :to [(co/key-mods :del)]}]}])


(def rules
  [{:description "#tab",
    :manipulators [{:from (co/key-any :tab)
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :opt :cmd)]
                    :to_after_key_up [(co/set-var var/space-changed 0)
                                      (co/set-var var/space->shift 0)]
                    :to_if_alone (co/key-mods :tab)}]}

   {:description "#open_bracket",
    :manipulators [{:from (co/key-any :open_bracket)
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :opt :cmd)]
                    :to_after_key_up [(co/set-var var/space-changed 0)
                                      (co/set-var var/space->shift 0)]
                    :to_if_alone (co/key-mods :open_bracket)}]}

   {:description "#close_bracket",
    :manipulators [{:from (co/key-any :close_bracket)
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :ctrl :opt :cmd)]
                    :to_after_key_up [(co/set-var var/space-changed 0)
                                      (co/set-var var/space->shift 0)]
                    :to_if_alone (co/key-mods :close_bracket)}]}



  ;;  end
   ])

(comment
  (co/key-mods :esc)
  {:description "w+e ➡️ escape"
   :copy-flip true
   :manipulators [{:from (co/sim :w :e),
                   :to [(co/key-mods :esc)]}]}

  :rcf)
#_{:description "f + shift",
   :manipulators (var/layer-key {:key :f
                                 :mods [:sft]}
                                {:nav (co/key-mods :f :cmd :sft)
                                 :sym (co/key-mods :act :sft)
                                 :mouse (co/mouse-pos :x {:fast 2})})}
