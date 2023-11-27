(ns two-key.part.row-3
  (:require [two-key.common :as co]))
#_(remove-ns 'two-key.part.row-3)


(def rules
  [;;  w
   {:description "w+e ➡️ escape"
    :copy-flip true
    :manipulators [{:from (co/sim :w :e),
                    :to [(co/key-mods :esc)]}]}

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
                    :to [(co/key-mods :del)]}]}

  ;;  end
   ])

(comment
  (co/key-mods :esc)
  {:description "w+e ➡️ escape"
   :copy-flip true
   :manipulators [{:from (co/sim :w :e),
                   :to [(co/key-mods :esc)]}]}
  :rcf)
