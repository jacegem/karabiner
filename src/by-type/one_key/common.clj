;; (ns one-key.common)


;; (defn key-map [key]
;;   (get {:caps "caps_lock"
;;         :ctrl "left_control"
;;         :opt "left_opt"
;;         :cmd "left_command"
;;         :sft "left_shift"
;;         :spc "spacebar"
;;         :rctrl "right_control"
;;         :ropt "right_option"
;;         :rcmd "right_command"
;;         :rsft "right_shift"
;;         :act "grave_accent_and_tilde"} key key))

;; (defn key-mods [key & mods]
;;   (cond
;;     (nil? mods) {:key_code (key-map key)}
;;     (= :any (first mods)) {:key_code (key-map key),
;;                            :modifiers {:optional ["any"]}}
;;     :else {:key_code (key-map key)
;;            :modifiers (map key-map mods)}))

;; (defn key-any [key]
;;   (key-mods key :any))

;; (defn set-var [name val]
;;   {:set_variable {:name name,
;;                   :value val}})

;; (defn var-if [name val]
;;   {:name name
;;    :type "variable_if"
;;    :value val})

;; (defn sim-keys [& keys]
;;   (into [] (map (fn [k] {:key_code (key-map k)}) keys)))

;; (defn sim-ups [& ups]
;;   {:detect_key_down_uninterruptedly false,
;;    :key_down_order "insensitive",
;;    :key_up_order "insensitive",
;;    :key_up_when "any",
;;    :to_after_key_up ups})



;; #_(defn simul-keys [& keys]
;;     {:simultaneous (map (fn [k] {:key_code k}) keys)  ,
;;      :modifiers {:optional ["any"]},
;;      :simultaneous_options {:detect_key_down_uninterruptedly false,
;;                             :key_down_order "insensitive",
;;                             :key_up_order "insensitive",
;;                             :key_up_when "any",
;;                             :to_after_key_up []}})
;; ;; 
