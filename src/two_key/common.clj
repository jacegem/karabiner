(ns two-key.common)

(defn key-map [key]
  (get {:esc "escape"
        :tab "tab"
        :caps "caps_lock"
        :ctrl "left_control"
        :opt "left_option"
        :cmd "left_command"
        :sft "left_shift"
        :spc "spacebar"
        :bspc "delete_or_backspace"
        :rctrl "right_control"
        :ropt "right_option"
        :rcmd "right_command"
        :rsft "right_shift"
        :eq "equal_sign"
        :ret "return_or_enter"
        :del "delete_forward"} key (name key)))

(defn key-mods [key & mods]
  (cond
    (nil? mods) {:key_code (key-map key)}
    (= :any (first mods)) {:key_code (key-map key),
                           :modifiers {:optional ["any"]}}
    :else {:key_code (key-map key)
           :modifiers (map key-map mods)}))

(comment
  (key-mods :ctrl :opt)
  :rcf)


(defn key-any [key]
  (key-mods key :any))

(defn set-var [name val]
  {:set_variable {:name name,
                  :value val}})

(defn var-if [name val]
  {:name name
   :type "variable_if"
   :value val})

(defn sim-keys [keys]
  (prn "sim-keys:" keys)
  (into [] (map (fn [k] {:key_code (key-map k)}) keys)))


(defn sim-opt [{:keys [detect_key_down_uninterruptedly
                       key_down_order
                       key_up_order
                       key_up_when
                       to_after_key_up] :as _opt
                :or {detect_key_down_uninterruptedly false
                     key_down_order "insensitive"
                     key_up_order "insensitive"
                     key_up_when "any"
                     to_after_key_up []}}]
  {:detect_key_down_uninterruptedly detect_key_down_uninterruptedly,
   :key_down_order key_down_order,
   :key_up_order key_up_order,
   :key_up_when key_up_when,
   :to_after_key_up to_after_key_up})

(defn sim
  ([{:keys [keys] :as opt}]
   {:simultaneous (sim-keys keys),
    :modifiers {:optional ["any"]},
    :simultaneous_options (sim-opt opt)})

  ([key & keys] {:simultaneous (sim-keys (conj keys key)),
                 :modifiers {:optional ["any"]},
                 :simultaneous_options (sim-opt {})}))


(comment
  (sim {:keys [:a :b]
        :opt {}})
  :rcf)

(defn delayed-action [name value]
  {:to_if_invoked [{:set_variable {:name name
                                   :value value}}]
   :to_if_canceled [{:set_variable {:name name
                                    :value value}}]})