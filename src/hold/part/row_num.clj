(ns hold.part.row-num
  (:require [hold.common :as co]))



(def rules
  [{:description "=+BSPC ➡️ delete forward"
    :manipulators [{:from (co/sim :eq :bspc),
                    :to [(co/key-mods :del)]}]}])
