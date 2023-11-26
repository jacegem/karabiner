(ns two-key.part.row-5
  (:require [two-key.common :as co]))


(def rules
  [{:description "z+x ➡️ cmd+z",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim :z :x)
                    :to (co/key-mods :z :cmd)}]}

   {:description "x+c ➡️ shift",
    :copy-flip true
    :manipulators [{:type "basic",
                    :from (co/sim :x :c)
                    :to (co/key-mods :sft)}]}

   {:description "double-right-shift to show CLOCK"
    :manipulators [{:type "basic"
                    :from (co/key-any :rsft)
                    :conditions [(co/var-if "right-shift" 1)]
                    :to [(co/key-mods :act :ctrl :opt)]}
                   {:type "basic"
                    :from (co/key-any :rsft)
                    :to [(co/set-var "right-shift" 1)
                         (co/key-mods :rsft)]
                    :to_delayed_action (co/delayed-action "right-shift" 0)}]}]

  ;; end
  )
