(ns two-key.part.row-6
  (:require [two-key.common :as co]))



(defn spc-mode [{:keys [from to after]}]
  (if (vector? from)
    (mapcat #(spc-mode {:from %
                        :to to
                        :after after}) from)
    [{:from (co/key-any from)
      :conditions [(co/var-if "spc-mode" 1)]
      :to (if (nil? after)
            [(co/set-var "spc-mode" 0)
             (apply co/key-mods to)]
            [(co/set-var "spc-mode" 0)
             (apply co/key-mods to)
             (apply co/key-mods after)])}

     {:from (co/key-any from)
      :conditions [(co/var-if "spc-mode" 0)]
      :to (co/key-mods from)}]))


(comment
  (spc-mode {:from [:e :i]
             :to [:esc]
             :after [:s :cmd]})
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

   {:description "spc-mode with flips"
    :manipulators (concat (spc-mode {:from [:w :o]
                                     :to [:w :cmd]})
                          (spc-mode {:from [:e :i]
                                     :to [:esc]
                                     :after [:s :cmd]})
                          (spc-mode {:from [:r :u]
                                     :to [:ret]})
                          (spc-mode {:from [:t :y]
                                     :to [:ret :cmd]})
                          (spc-mode {:from :p
                                     :to [:p :cmd]})

                          (spc-mode {:from [:a :semicolon]
                                     :to [:cmd]})
                          (spc-mode {:from [:s :l]
                                     :to [:ctrl :opt]})
                          (spc-mode {:from [:d :k]
                                     :to [:sft]})
                          (spc-mode {:from [:f :j]
                                     :to [:cmd]})
                          (spc-mode {:from :g
                                     :to [:open_bracket :cmd]})
                          (spc-mode {:from :h
                                     :to [:close_bracket :cmd]})

                          (spc-mode {:from [:z :slash]
                                     :to [:z :cmd]})
                          (spc-mode {:from [:x :period]
                                     :to [:x :cmd]})
                          (spc-mode {:from [:c :comma]
                                     :to [:c :cmd]})
                          (spc-mode {:from [:v :m]
                                     :to [:v :cmd]})
                          (spc-mode {:from :b
                                     :to [:opt :cmd :sft]})
                          (spc-mode {:from :b
                                     :to [:opt :cmd]}))}



  ;;  end
   ])
