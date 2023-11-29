(ns layers.part.row-4
  (:require [layers.common :as co]
            [two-key.part.var :as var]))

(defn spc-mode [{:keys [from to]}]
  (if (vector? from)
    (mapcat #(spc-mode {:from %
                        :to to}) from)
    [{:from (co/key-any from)
      :conditions [(co/var-if var/space-pressed 1)]
      :to (if (nil? to)
            [(co/key-mods from :ctrl :opt :cmd :sft)]
            [(apply co/key-mods to)])}

     {:from (co/key-any from)
      :conditions [(co/var-if var/space-pressed 0)]
      :to [(co/key-mods from)]}]))

#_{:description "double-right-shift to show CLOCK"
   :manipulators [{:from (co/key-any :rsft)
                   :conditions [(co/var-if "right-shift" 1)]
                   :to [(co/key-mods :act :ctrl :opt)]}

                  {:from (co/key-any :rsft)
                   :to [(co/set-var "right-shift" 1)
                        (co/key-mods :rsft)]
                   :to_delayed_action (co/delayed-action "right-shift" 0)}]}



(def sim-rules
  [{:description "spc+q ➡️ with :all",
    :manipulators [{:from (co/sim {:keys [:spc :q]})
                    :to [(co/key-mods :q :all)]}]}

   {:description "spc+w ➡️ with :all :: memory call",
    :manipulators [{:from (co/sim {:keys [:spc :w]})
                    :to [(co/key-mods :w :all)]}]}

   {:description "spc+e ➡️ ctrl+cmd :: window move"
    :manipulators [{:from (co/sim {:keys [:spc :e]})
                    :to [(co/key-mods :ctrl :cmd)]}]}

   {:description "spc+r ➡️ opt+cmd+sft",
    :manipulators [{:from (co/sim {:keys [:spc :r]})
                    :to [(co/key-mods :ctrl :opt :sft)]}]}

   {:description "spc+t ➡️ ctrl+cmd+sft",
    :manipulators [{:from (co/sim {:keys [:spc :t]})
                    :to [(co/key-mods :ctrl :cmd :sft)]}]}

   {:description "spc+a ➡️ all + a",
    :manipulators [{:from (co/sim {:keys [:spc :a]})
                    :to [(co/key-mods :a :all)]}]}

   {:description "spc+s ➡️ ctrl+opt",
    :manipulators [{:from (co/sim {:keys [:spc :s]})
                    :to [(co/key-mods :ctrl :opt)]}]}

   {:description "spc+d ➡️ ctrl+opt+sft",
    :manipulators [{:from (co/sim {:keys [:spc :d]})
                    :to [(co/key-mods :ctrl :opt :sft)]}]}

   {:description "spc+f ➡️ opt+cmd",
    :manipulators [{:from (co/sim {:keys [:spc :f]})
                    :to [(co/key-mods :opt :cmd)]}]}

   {:description "spc+g ➡️ cmd+sft",
    :manipulators [{:from (co/sim {:keys [:spc :g]})
                    :to [(co/key-mods :cmd :sft)]}]}

   {:description "spc+z ➡️ opt",
    :manipulators [{:from (co/sim {:keys [:spc :z]})
                    :to [(co/key-mods :opt)]}]}

   {:description "spc+x ➡️ opt",
    :manipulators [{:from (co/sim {:keys [:spc :x]})
                    :to [(co/key-mods :sft)]}]}

   {:description "spc+c ➡️ ctrl+opt+cmd",
    :manipulators [{:from (co/sim {:keys [:spc :c]})
                    :to [(co/key-mods :ctrl :opt :cmd)]}]}

   {:description "spc+v ➡️ cmd"
    :manipulators [{:from (co/sim {:keys [:spc :v]})
                    :to [(co/key-mods :cmd)]}]}

   {:description "spc+b ➡️ opt+sft"
    :manipulators [{:from (co/sim {:keys [:spc :b]})
                    :to [(co/key-mods :opt :sft)]}]}

   {:description "rctrl + f13 ➡️ 화면 잠금"
    :manipulators [{:from (co/sim {:keys [:rctrl :f13]})
                    :to [(co/key-mods :q :ctrl :cmd)]
                    :parameters {:basic.simultaneous_threshold_milliseconds 1000}}]}

   {:description "ropt + f13 ➡️ 화면 잠금"
    :manipulators [{:from (co/sim {:keys [:ropt :f13]})
                    :to [(co/key-mods :q :ctrl :cmd)]
                    :parameters {:basic.simultaneous_threshold_milliseconds 1000}}]}])

(def rules
  [{:description "spc->sft (다른 mods가 활성화된 경우에) 1"
    :manipulators [{:from (co/key-any :spc)
                    :conditions [(co/var-if var/space-pressed 1)]
                    :to [(co/key-mods :spc :sft)]}

                   {:from (co/key-any :spc)
                    :conditions [(co/var-if var/space-changed 0)]
                    :to [(co/set-var var/space-pressed 1)
                         (co/key-mods :spc)]
                    :to_delayed_action (co/delayed-action var/space-pressed 0)}

                   {:from (co/key-any :spc)
                    :conditions [(co/var-if var/space-changed 1)
                                 (co/var-if var/space->shift 1)]
                    :to [(co/key-mods :sft)]}

                   {:from (co/key-any :spc)
                    :conditions [(co/var-if var/space-changed 1)
                                 (co/var-if var/space->command 1)]
                    :to [(co/key-mods :cmd)]}]}


  ;;  end
   ])



(comment
  rules
  :rcf)


  ;;  {:description "space first ➡️ ctrl+cmd"
  ;;   :copy-flip true
  ;;   :manipulators (concat (spc-mode {:from [:s]
  ;;                                    :to [:ctrl :opt]}))}

  ;;  {:description "spc+q ➡️ esc",
  ;;   :manipulators (spc-mode {:from [:q]
  ;;                            :to [:esc]})}

  ;;  {:description "spc+w ➡️ ctrl+cmd+sft :: memory call",
  ;;   :manipulators (spc-mode {:from [:w]})}

  ;;  {:description "spc+e ➡️ ctrl+cmd :: window move"
  ;;   :manipulators (spc-mode {:from [:e]
  ;;                            :to [:ctrl :cmd]})}

  ;;  {:description "spc+r ➡️ opt+cmd+sft",
  ;;   :manipulators (spc-mode {:from [:r]
  ;;                            :to [:ctrl :opt :sft]})}

#_{:description "double-right-shift to show CLOCK"
   :manipulators [{:from (co/key-any :rsft)
                   :conditions [(co/var-if "right-shift" 1)]
                   :to [(co/key-mods :act :ctrl :opt)]}

                  {:from (co/key-any :rsft)
                   :to [(co/set-var "right-shift" 1)
                        (co/key-mods :rsft)]
                   :to_delayed_action (co/delayed-action "right-shift" 0)}]}
#_{:description "spcae ➡️ shift (다른 mods가 활성화된 경우에)"
   :manipulators [;;  delay 가 조금 있다. 이게 사용하다 보면 느껴질 것 같은데..
                  {:from (co/key-any :spc)
                   :conditions [(co/var-if "caps-mode" 0)]
                   :to_if_alone [(co/key-mods "spacebar")]
                   :to [(co/set-var "spc-mode" 1)]
                   :to_after_key_up [(co/set-var "spc-mode" 0)]}

                  {:from (co/key-mods :spc :any)
                   :conditions [(co/var-if "caps-mode" 1)]
                   :to [(co/key-mods :rsft)]}]}

#_{:description "spc-mode ➡️ ctrl+cmd+shift"
   :manipulators (concat #_(spc-mode {:from [:s :l]
                                      :to [:ctrl :opt]})
                  (spc-mode {:from [:d :k]
                             :to [:sft]})
                         (spc-mode {:from [:f :j]
                                    :to [:cmd]})
                         (spc-mode {:from [:g]
                                    :to [:ctrl]})
                         (spc-mode {:from [:h]
                                    :to [:opt]})

                         (spc-mode {:from [:1 :2 :3 :4 :5 :6 :7 :8 :9 :0 :hyphen :eq :bspc :page_up]})
                         (spc-mode {:from [:q :w :e :r :t :y :u :i :o :p :open_bracket :close_bracket :backslash :page_down]})
                         (spc-mode {:from [:a :semicolon :quote :open_bracket :return_or_enter :home]})
                         (spc-mode {:from [:z :x :c :v :b :n :m :comma :period :slash :backslash :up_arrow :end]}))}




;; #_{:description "right cmd (alone) ➡️ F13",
;;    :copy-flip true
;;    :manipulators [{:type "basic" {:description "spc+c ➡️ ctrl+cmd",
;;                                   :manipulators [{:from (co/sim {:keys [:spc :c]})
;;                                                   :to [(co/key-mods :ctrl :cmd)]}]}
;;                    {:description "spc+c ➡️ ctrl+cmd",
;;                     :manipulators [{:from (co/sim {:keys [:spc :c]})
;;                                     :to [(co/key-mods :ctrl :cmd)]}]}
;;                    {:description "spc+c ➡️ ctrl+cmd",
;;                     :manipulators [{:from (co/sim {:keys [:spc :c]})
;;                                     :to [(co/key-mods :ctrl :cmd)]}]}
;;                    {:description "spc+c ➡️ ctrl+cmd",
;;                     :manipulators [{:from (co/sim {:keys [:spc :c]})
;;                                     :to [(co/key-mods :ctrl :cmd)]}]},
;;                    :from (co/key-mods :rsft)
;;                    :to (co/key-mods :rsft)
;;                    :to_if_alone (co/key-code :F13)}]}

; (concat (spc-mode {:from [:q]
;;                    :to [:tab]})
;;         (spc-mode {:from [:w :o]
;;                    :to [:w :cmd]})
;;         (spc-mode {:from [:e :i]
;;                    :to [:esc]})
;;         (spc-mode {:from [:r :u]
;;                    :to [:ret]})
;;         (spc-mode {:from [:t :y]
;;                    :to [:tab :cmd]})
;;         (spc-mode {:from [:y]
;;                    :to [:tab :sft]})s
;;         (spc-mode {:from :p
;;                    :to [:p :cmd]})

;;         (spc-mode {:from [:a :semicolon]
;;                    :to [:cmd]
;;                    :modifier? true})
;;         (spc-mode {:from [:s :l]
;;                    :to [:ctrl :opt]})
;;         (spc-mode {:from [:d :k]
;;                    :to [:sft]})
;;         (spc-mode {:from [:f :j]
;;                    :to [:cmd]})
;;         (spc-mode {:from :g
;;                    :to [:open_bracket :cmd]})
;;         (spc-mode {:from :h
;;                    :to [:close_bracket :cmd]})

;;         (spc-mode {:from [:z :slash]
;;                    :to [:z :cmd]})
;;         (spc-mode {:from [:x :period]
;;                    :to [:x :cmd]})
;;         (spc-mode {:from [:c :comma]
;;                    :to [:c :cmd]})
;;         (spc-mode {:from [:v :m]
;;                    :to [:v :cmd]})
;;         (spc-mode {:from :b
;;                    :to [:opt :cmd :sft]})
;;         (spc-mode {:from :n
;;                    :to [:opt :cmd]}))
;; #_{:description "spc+quote ➡️ cmd+ret",
;;    :manipulators [{:from (co/sim {:keys [:spc :quote]})
;;                    :to [(co/key-mods :ret :cmd)]}]}

;; #_{:description "spc+ret ➡️ cmd+ret",
;;    :manipulators [{:from (co/sim {:keys [:spc :ret]})
;;                    :to [(co/key-mods :ret :cmd)]}]}
