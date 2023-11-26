(ns two-key.part.row-34
  (:require [two-key.common :as co]))

(def rules
  [{:description "w+f ➡️ ctrl+cmd :: macro call "
    :copy-flip true
    :manipulators [{:type "basic",
                    :from  (co/sim :w :f),
                    :to [(co/key-mods :ctrl :cmd)]}]}

   {:description "e+f ➡️ opt+cmd+sft :: for window"
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim :e :f),
                    :to [(co/key-mods :sft :opt :cmd)]}]}

   {:description "e+s ➡️ ctrl+opt+tab :: for mouse"
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim :e :s),
                    :to [(co/key-mods :tab :ctrl :opt)]}]}])
