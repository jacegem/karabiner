(ns two-key.part.row-34
  (:require [two-key.common :as co]))

(def rules
  [;; q
   {:description "q+d ➡️ ctrl"
    :copy-flip true
    :manipulators [{:from (co/sim :q :d)
                    :to [(co/key-mods :ctrl)]}]}

   {:description "q+f ➡️ ctrl"
    :copy-flip true
    :manipulators [{:from (co/sim :q :d)
                    :to [(co/key-mods :ctrl)]}]}

  ;;  w
   {:description "w+f ➡️ ctrl+cmd+sft :: macro call"
    :copy-flip true
    :manipulators [{:from  (co/sim :w :f)
                    :to [(co/key-mods :ctrl :cmd :sft)]}]}

   {:description "e+f ➡️ ctrl+cmd :: for window"
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:e :f]
                                   :to_after_key_up [(co/set-var "spc->sft" 0)]})
                    :to [(co/set-var "spc->sft" 1)
                         (co/key-mods :ctrl :cmd)]}]}

   {:description "e+s ➡️ ctrl+opt+tab :: for mouse"
    :copy-flip true
    :manipulators [{:from (co/sim :e :s)
                    :to [(co/key-mods :tab :ctrl :opt)]}]}])
