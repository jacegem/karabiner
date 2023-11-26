(ns two-key.part.row-45
  (:require [two-key.common :as co]))

(def rules
  [{:description "a+s ➡️ cmd+s",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "a" "s")
                    :to (co/key-mods "s" :cmd)}]}

   {:description "a+d ➡️ cmd+d",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "a" "d")
                    :to (co/key-mods "d" :cmd)}]}

   {:description "a+f ➡️ cmd+f",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "a" "f")
                    :to (co/key-mods "f" :cmd)}]}

   {:description "a+z ➡️ cmd+z",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "a" "z")
                    :to (co/key-mods "z" :cmd)}]}

   {:description "a+x ➡️ cmd+x",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "a" "x")
                    :to (co/key-mods "x" :cmd)}]}

   {:description "a+c ➡️ cmd+c",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "a" "c")
                    :to (co/key-mods "c" :cmd)}]}

   {:description "a+v ➡️ cmd+v",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "a" "v")
                    :to (co/key-mods "v" :cmd)}]}

   {:description "s+c ➡️ ctrl+opt+cmd",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "s" "c")
                    :to (co/key-mods :ctrl :opt :cmd)}]}

   {:description "s+v ➡️ ctrl+opt+cmd",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim "s" "v")
                    :to (co/key-mods :ctrl :opt :cmd :sft)}]}
;;    end
   ])
