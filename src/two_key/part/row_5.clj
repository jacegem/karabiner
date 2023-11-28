(ns two-key.part.row-5
  (:require [two-key.common :as co]
            [two-key.part.var :as var]))




(def rules
  [{:description "c+v ➡️ cmd"
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:c :v]
                                   :to_after_key_up [(co/set-var var/space-changed 0)
                                                     (co/set-var var/space->shift 0)]})
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :cmd)]}]}

   {:description "x+c ➡️ shift"
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:x :c]
                                   :to_after_key_up [(co/set-var var/space-changed 0)
                                                     (co/set-var var/space->command 0)]})
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->command 1)
                         (co/key-mods :sft)]}]}

   {:description "x+v ➡️ opt"
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:x :v]
                                   :to_after_key_up [(co/set-var var/space-changed 0)
                                                     (co/set-var var/space->shift 0)]})
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :opt)]}]}

   {:description "z+v ➡️ ctrl"
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:z :v]
                                   :to_after_key_up [(co/set-var var/space-changed 0)
                                                     (co/set-var var/space->shift 0)]})
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :ctrl)]}]}

   {:description "z+x ➡️ cmd+z"
    :copy-flip true
    :manipulators [{:from (co/sim :z :x)
                    :to (co/key-mods :z :cmd)}]}

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




#_{:description "z+c ➡️ opt+sft"
   :copy-flip true
   :manipulators [{:from (co/sim :z :c)
                   :to (co/key-mods :opt :sft)}]}

#_{:description "x+v ➡️ cmd+sft"
   :copy-flip true
   :manipulators [{:from (co/sim :x :v)
                   :to (co/key-mods :opt)}]}
