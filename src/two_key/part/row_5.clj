(ns two-key.part.row-5
  (:require [two-key.common :as co]))


(def rules
  [{:description "z+x ➡️ cmd+z"
    :copy-flip true
    :manipulators [{:from (co/sim :z :x)
                    :to (co/key-mods :z :cmd)}]}

   {:description "z+v ➡️ opt"
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:z :v]
                                   :to_after_key_up [(co/set-var "spc->sft" 0)]})
                    :to [(co/set-var "spc->sft" 1)
                         (co/key-mods :opt)]}]}

   {:description "z+c ➡️ opt+sft"
    :copy-flip true
    :manipulators [{:from (co/sim :z :c)
                    :to (co/key-mods :opt :sft)}]}

   {:description "x+c ➡️ shift"
    :copy-flip true
    :manipulators [{:from (co/sim :x :c)
                    :to (co/key-mods :sft)}]}

   {:description "c+v ➡️ cmd"
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:c :v]
                                   :to_after_key_up [(co/set-var "spc->sft" 0)]})
                    :to [(co/set-var "spc->sft" 1)
                         (co/key-mods :cmd)]}]}

   {:description "x+v ➡️ cmd+sft"
    :copy-flip true
    :manipulators [{:from (co/sim :x :v)
                    :to (co/key-mods :opt)}]}

   {:description "double-right-shift to show CLOCK"
    :manipulators [{:from (co/key-any :rsft)
                    :conditions [(co/var-if "right-shift" 1)]
                    :to [(co/key-mods :act :ctrl :opt)]}

                   {:from (co/key-any :rsft)
                    :to [(co/set-var "right-shift" 1)
                         (co/key-mods :rsft)]
                    :to_delayed_action (co/delayed-action "right-shift" 0)}]}]

  ;; end
  )
