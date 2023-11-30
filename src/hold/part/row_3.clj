(ns hold.part.row-3
  (:require [hold.common :as co]
            [hold.var :as var]))





(def sim-rules
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
                    :to (co/key-mods :z :cmd)}]}])




(def rules
  [{:description "left-shift ➡️ show modal "
    :manipulators [{:from (co/key-any :sft)
                    :conditions [(co/var-if var/shift-pressed 1)]
                    :to [(co/key-mods :spc :sft)]}

                   {:from (co/key-any :sft)
                    :to [(co/set-var var/shift-pressed 1)
                         (co/key-mods :sft)]
                    :to_delayed_action (co/delayed-action var/shift-pressed 0)}]}

   #_{:description "#comma ",
      :manipulators [{:from (co/key-any :comma)
                      :to [(co/set-var var/space-changed 1)
                           (co/set-var var/space->shift 1)
                           (co/key-mods :cmd)]
                      :to_after_key_up [(co/set-var var/space-changed 0)
                                        (co/set-var var/space->shift 0)]
                      :to_if_alone (co/key-mods :comma)}]}

   #_{:description "#period ",
      :manipulators [{:from (co/key-any :period)
                      :to [(co/set-var var/space-changed 1)
                           (co/set-var var/space->shift 1)
                           (co/key-mods :opt)]
                      :to_after_key_up [(co/set-var var/space-changed 0)
                                        (co/set-var var/space->shift 0)]
                      :to_if_alone (co/key-mods :period)}]}

   {:description "#slash ",
    :manipulators [{:from (co/key-any :slash)
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :ctrl :opt :cmd)]
                    :to_after_key_up [(co/set-var var/space-changed 0)
                                      (co/set-var var/space->shift 0)]
                    :to_if_alone (co/key-mods :slash)}]}


   {:description "right-shift ➡️ enter"
    :manipulators [{:from (co/key-any :rsft)
                    :to (co/key-code {:key :rsft
                                      :lazy? true})
                    :to_if_alone (co/key-mods :ret)}]}]

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

#_{:description "right-shift ➡️ show clock "
   :manipulators [{:from (co/key-any :rsft)
                   :conditions [(co/var-if "right-shift" 1)]
                   :to [(co/key-mods :act :ctrl :opt)]}

                  {:from (co/key-any :rsft)
                   :to [(co/set-var "right-shift" 1)
                        (co/key-mods :rsft)]
                   :to_delayed_action (co/delayed-action "right-shift" 0)}]}
