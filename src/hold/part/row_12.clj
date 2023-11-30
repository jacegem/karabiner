(ns hold.part.row-12
  (:require [hold.common :as co]
            [hold.var :as var]))

(def sim-rules
  [{:description "w+f ➡️ ctrl+cmd+sft :: macro call"
    :copy-flip true
    :manipulators [{:from  (co/sim :w :f)
                    :to [(co/key-mods :ctrl :cmd :sft)]}]}

   {:description "e+f ➡️ ctrl+cmd :: for window"
    :copy-flip true
    :manipulators [{:from (co/sim {:keys [:e :f]
                                   :to_after_key_up [(co/set-var  var/space-changed 0)
                                                     (co/set-var  var/space->shift 0)]})
                    :to [(co/set-var var/space-changed 1)
                         (co/set-var var/space->shift 1)
                         (co/key-mods :ctrl :cmd)]}]}

   {:description "e+s ➡️ ctrl+opt+tab :: for mouse"
    :copy-flip true
    :manipulators [{:from (co/sim :e :s)
                    :to [(co/key-mods :tab :ctrl :opt)]}]}])

(def rules
  [;;  w



  ;;  end
   ])



#_{:description "d+f ➡️ opt+cmd :: symbol",
   :copy-flip true
   :manipulators [{:from (co/sim {:keys [:d :f]
                                  :to_after_key_up [(co/set-var  var/space-changed 0)
                                                    (co/set-var  var/space->shift 0)]})
                   :to [(co/set-var var/space-changed 1)
                        (co/set-var var/space->shift 1)
                        (co/key-mods :opt :cmd)]}]}
;; q
  ;;  {:description "q+d ➡️ ctrl"
  ;;   :copy-flip true
  ;;   :manipulators [{:from (co/sim :q :d)
  ;;                   :to [(co/key-mods :ctrl)]}]}

  ;;  {:description "q+f ➡️ ctrl"
  ;;   :copy-flip true
  ;;   :manipulators [{:from (co/sim :q :d)
  ;;                   :to [(co/key-mods :ctrl)]}]}
