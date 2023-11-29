(ns layers.part.row-3
  (:require [layers.common :as co]
            [layers.var :as var]))





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
  [{:description "z ",
    :manipulators (var/layer-key {:key :z}
                                 {:sym (co/key-mods :act :sft)
                                  :num (co/key-mods :0)
                                  :func (co/key-mods :f10)
                                  :mouse (co/mouse-pos :h-)})}

   {:description "x ",
    :manipulators (var/layer-key {:key :x}
                                 {:sym (co/key-mods :1 :sft)
                                  :num (co/key-mods :1)
                                  :func (co/key-mods :f10)
                                  :mouse (co/mouse-pos :h-)})}
   {:description "c ",
    :manipulators (var/layer-key {:key :c}
                                 {:sym (co/key-mods :2 :sft)
                                  :num (co/key-mods :2)
                                  :mouse (co/mouse-pos :v-)})}

   {:description "#v ",
    :manipulators (var/layer-key {:key :v}
                                 {:sym (co/key-mods :3 :sft)
                                  :num (co/key-mods :3)
                                  :mouse (co/mouse-pos :v)})}

   {:description "#b ",
    :manipulators (var/layer-key {:key :b}
                                 {:sym (co/key-mods :backslash)
                                  :num (co/key-mods :equal_sign)})}

   {:description "#n ",
    :manipulators (var/layer-key {:key :n}
                                 {:sym (co/key-mods :8 :sft)})}

   {:description "#m ",
    :manipulators (var/layer-key {:key :m}
                                 {:sym (co/key-mods :0 :sft)
                                  :mouse (co/key-mods :page_up)})}

   {:description "#comma ",
    :manipulators (var/layer-key {:key :comma}
                                 {:sym (co/key-mods :close_bracket)
                                  :mouse (co/key-mods :page_down)})}
   {:description "#period ",
    :manipulators (var/layer-key {:key :period}
                                 {:sym (co/key-mods :close_bracket :sft)
                                  :mouse (co/key-mods :page_down)})}

   {:description "#slash ",
    :manipulators (var/layer-key {:key :slash}
                                 {:sym (co/key-mods :slash)})}

   {:description "#slash "
    :manipulators [{:from (co/key-any :slash)
                    :to [(co/set-var var/layer-active 1)
                         (co/set-var var/func-pressed 1)]
                    :to_after_key_up [(co/set-var var/layer-active 0)
                                      (co/set-var var/func-pressed 0)]
                    :to_if_alone (co/key-mods :semicolon)}]}

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
