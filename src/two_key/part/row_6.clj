(ns two-key.part.row-6
  (:require [two-key.common :as co]))

(defn spc-mode [{:keys [from to]}]
  (if (vector? from)
    (mapcat #(spc-mode {:from %
                        :to to}) from)
    [{:from (co/key-any from)
      :conditions [(co/var-if "spc-mode" 1)]
      :to (remove nil?
                  [(co/set-var "spc-mode" 0)
                   (if (nil? to)
                     (co/key-mods from :ctrl :cmd :sft)
                     (apply co/key-mods to))])}

     {:from (co/key-any from)
      :conditions [(co/var-if "spc-mode" 0)]
      :to [(co/key-mods from)]}]))


(comment
  (spc-mode {:from [:p]})
  :rcf)

(def rules
  [{:description "spcae ➡️ shift (다른 mods가 활성화된 경우에)"
    :manipulators [;;  delay 가 조금 있다. 이게 사용하다 보면 느껴질 것 같은데..
                   {:from (co/key-any :spc)
                    :conditions [(co/var-if "caps-mode" 0)]
                    :to_if_alone [(co/key-mods "spacebar")]
                    :to [(co/set-var "spc-mode" 1)]
                    :to_after_key_up [(co/set-var "spc-mode" 0)]}

                   {:from (co/key-mods :spc :any)
                    :conditions [(co/var-if "caps-mode" 1)]
                    :to [(co/key-mods :rsft)]}]}

   {:description "spc-mode ➡️ ctrl+cmd+shift"
    :manipulators (concat (spc-mode {:from [:s :l]
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

  ;;  end
   ])

(comment
  rules
  :rcf)


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
