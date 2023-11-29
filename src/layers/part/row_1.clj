(ns layers.part.row-1
  (:require [layers.common :as co]
            [layers.var :as var]))
#_(remove-ns 'layers.part.row-1)


(def sim-rules
  [;  w
   {:description "w+e ➡️ escape"
    :copy-flip true
    :manipulators [{:from (co/sim :w :e)
                    :to [(co/key-mods :s :cmd)
                         (co/key-mods :esc)]}]}

   {:description "w+r ➡️ tab"
    :copy-flip true
    :manipulators [{:from (co/sim :w :r),
                    :to [(co/key-mods :tab)]}]}

   {:description "q+r ➡️ shift+tab"
    :copy-flip true
    :manipulators [{:from (co/sim :q :r),
                    :to [(co/key-mods :tab :sft)]}]}

   {:description "e+r ➡️ backspace"
    :copy-flip true
    :manipulators [{:from (co/sim :e :r),
                    :to [(co/key-mods :bspc)]}]}

   {:description "q+r ➡️ backspace"
    :copy-flip true
    :manipulators [{:from (co/sim :q :r),
                    :to [(co/key-mods :del)]}]}])


(def rules
  [;
  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ;; single

   {:description "#tab",
    :manipulators [{:from (co/key-any :tab)
                    :to [(co/set-var var/layer-active 1)
                         (co/set-var var/sym-pressed 1)]
                    :to_after_key_up [(co/set-var var/layer-active 0)
                                      (co/set-var var/sym-pressed 0)]
                    :to_if_alone (co/key-mods :tab)}]}

   {:description "#q"
    :manipulators (var/layer-key {:key :q}
                                 {:sym (co/key-mods :tab :sft)
                                  :num (co/key-mods :hyphen)
                                  :func (co/key-mods :f12)
                                  :mouse (co/mouse-button :middle)
                                  :held-mode var/sym-pressed
                                  :held-to (co/key-mods :sft)})}
   {:description "w"
    :manipulators (var/layer-key {:key :w}
                                 {:sym (co/key-mods :7 :sft)
                                  :num (co/key-mods :7)
                                  :func (co/key-mods :f7)
                                  :mouse (co/mouse-button :left)})}

   {:description "e"
    :manipulators (var/layer-key {:key :e}
                                 {:sym (co/key-mods :8 :sft)
                                  :num (co/key-mods :8)
                                  :func (co/key-mods :f8)
                                  :mouse (co/mouse-pos :y)})}

   {:description "r"
    :manipulators (var/layer-key {:key :r}
                                 {:sym (co/key-mods :4 :sft)
                                  :num (co/key-mods :9)
                                  :func (co/key-mods :f9)
                                  :mouse (co/mouse-button :right)})}

   {:description "y"
    :manipulators (var/layer-key {:key :r}
                                 {:sym (co/key-mods :5 :sft)
                                  :num (co/key-mods :slash)
                                  :mouse (co/mouse-button :middle)})}

   {:description "u "
    :manipulators (var/layer-key {:key :u}
                                 {:sym (co/key-mods :open_bracket)
                                  :num (co/key-mods :hyphen)})}
   {:description "i "
    :manipulators (var/layer-key {:key :i}
                                 {:sym (co/key-mods :hyphen)})}

   {:description "o "
    :manipulators (var/layer-key {:key :o}
                                 {:sym (co/key-mods :equal_sign)})}

   {:description "p "
    :manipulators (var/layer-key {:key :p}
                                 {:sym (co/key-mods :equal_sign :sft)
                                  :held-mode var/sym-pressed
                                  :held-to (co/key-mods :sft)})}
   {:description "#open_bracket "
    :manipulators [{:from (co/key-any :open_bracket)
                    :to [(co/set-var var/layer-active 1)
                         (co/set-var var/sym-pressed 1)]
                    :to_after_key_up [(co/set-var var/layer-active 0)
                                      (co/set-var var/sym-pressed 0)]
                    :to_if_alone (co/key-mods :open_bracket)}]}

  ;;  end
   ])

(comment
  (co/key-mods :esc)
  {:description "w+e ➡️ escape"
   :copy-flip true
   :manipulators [{:from (co/sim :w :e),
                   :to [(co/key-mods :esc)]}]}

  :rcf)
#_{:description "f + shift",
   :manipulators (var/layer-key {:key :f
                                 :mods [:sft]}
                                {:nav (co/key-mods :f :cmd :sft)
                                 :sym (co/key-mods :act :sft)
                                 :mouse (co/mouse-pos :x {:fast 2})})}
