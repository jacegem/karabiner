(ns two-key.part.row-2
  (:require [two-key.common :as co]))



(def rules
  [{:description "=+BSPC ➡️ delete forward"
    :manipulators [{:from (co/sim :eq :bspc),
                    :to [(co/key-mods :del)]}]}])
