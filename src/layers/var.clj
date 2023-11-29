(ns layers.var
  (:require [clojure.set :as set]
            [layers.common :as co]))


(defn var-if [name value]
  {:name name
   :type "variable_if"
   :value value})

(defn var-unless [name value]
  {:name name
   :type "variable_unless"
   :value value})


(def space-pressed "space-pressed")
(def space-changed "space-changed")

(def space->shift "space->shift")
(def space->command "space->command")




(def layer-active "layer-active")
(def nav-pressed "nav-pressed")
(def sym-pressed "sym-pressed")
(def num-pressed "num-pressed")
(def func-pressed "func-pressed")
(def mouse-mode "mouse-mode")


(defn layer-mode "layer 를 변경하는 키" [])




(def mods-map
  {nav-pressed [:ctrl :opt]
   sym-pressed [:opt :cmd]
   num-pressed [:ctrl :cmd]
   func-pressed [:ctrl :cmd]})



(defn normal-state [key mods]
  {:from  (co/from-key-code {:key key
                             :mods mods})
   :conditions [(var-unless layer-active 1)]
   :to [(co/to-key-code {:key key
                         :mods mods})]})
(defn by-state [key mods held-mode held-to state to]

  (let [base {:from (co/from-key-code {:key key
                                       :mods mods})
              :conditions [(var-if layer-active 1)
                           (var-if state 1)]}]
    (if (nil? to)
      (merge base {:to (co/to-key-code {:key key
                                        :mods (concat mods (get mods-map state))})})
      (merge base  (if (= held-mode state)
                     {:to_if_alone to
                      :to_if_held_down held-to}
                     {:to to})))))



(defn layer-key [{:keys [key mods]}
                 {:keys [nav sym num func mouse
                         held-mode held-to]}]
  (let [with-key (partial by-state key mods held-mode held-to)]
    (->> [(normal-state key mods)
          (with-key nav-pressed nav)
          (with-key sym-pressed sym)
          (with-key num-pressed num)
          (with-key func-pressed func)
          (with-key mouse-mode mouse)]
         (remove nil?))))

(comment
  (layer-key {:key :q}
             {:nav (co/key-mods :delete_or_backspace)
              ;; :sym (co/key-mods :tab :sft)
              ;; :num (co/key-mods :hyphen)
              ;; :func (co/key-mods :f12)
              ;; :mouse (co/mouse-button :middle)
              ;; :held-mode sym-pressed
              ;; :held-to (co/key-mods :sft)
              })
  :rcf)


(comment
  (set/union #{1 2} #{2 3})
  (concat [1 2] nil)
  :rcf)
;; (defn layer-key "layer 변경에 반응하는 키"
;;   [{:keys [key mods] :as keys} opt]
;;   (let [left-from (co/from-key-code {:key key
;;                                      :mods mods})
;;         right-from (when mods
;;                      (co/from-key-code {:key key
;;                                         :mods (left->right-mods mods)}))]
;;     (->> (concat (layer-side keys opt left-from)
;;                  (layer-side keys opt right-from))
;;          (remove nil?))))
#_(defn left->right-mods [mods]
    (map {:sft :rsft
          :cmd :rcmd
          :ctrl :rctrl
          :opt :ropt} mods))
#_(let [from-cond {:from from
                   :conditions [(var-if layer-active 1)
                                (var-if state 1)]
                   :to to}
        layer-key (co/to-key-code {:key key
                                   :mods (concat mods (get mods-map state))})]
    (merge from-cond
           (when (= held-mode state)
             {:to_if_alone layer-key
              :to_if_held_down held-to})))
#_(merge {:from (co/from-key-code {:key key
                                   :mods mods})
          :conditions [(var-if layer-active 1)
                       (var-if state 1)]
          :to to}
         (when (= held-mode state)
           {:to_if_alone to
            :to_if_held_down held-to}))
