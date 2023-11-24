(ns main
  (:require [clojure.data.json :as json]
            [simultaneous-rule :refer [get-simultaneous-rule
                                       get-simultaneous-rule-right]]
            [single-rule :refer [get-single-rule]]))

;; https://github.com/pqrs-org/Karabiner-Elements/issues/925

(def config-map
  {;; single 
;; https://karabiner-elements.pqrs.org/docs/json/typical-complex-modifications-examples/#open-alfred-4-if-escape-is-held-down
   :cap_lock->ctrl+opt {:rule-type :single
                        :from {:keys ["caps_lock"]
                               :mods []}
                        :to {:keys ["left_control"]
                             :mods ["left_option"]}
                        :alone-key "escapse"}

   ;; raw
;; https://karabiner-elements.pqrs.org/docs/json/typical-complex-modifications-examples/#change-right_shift-x2-to-mission_control
   :double-right-shift {:rule-type :raw
                        :description "double-right-shift to show CLOCK"
                        :manipulators [{:type "basic"
                                        :from {:key_code  "right_shift"
                                               :modifiers {:optional ["any"]}}
                                        :to [{:key_code "grave_accent_and_tilde"
                                              :modifiers ["left_control"
                                                          "left_option"]}]
                                        :conditions [{:type "variable_if"
                                                      :name "right_shift pressed"
                                                      :value 1}]}
                                       {:type "basic"
                                        :from {:key_code  "right_shift"
                                               :modifiers {:optional ["any"]}}
                                        :to [{:set_variable {:name "right_shift pressed"
                                                             :value 1}}
                                             {:key_code "right_shift"}]
                                        :to_delayed_action {:to_if_invoked [{:set_variable {:name "right_shift pressed"
                                                                                            :value 0}}]
                                                            :to_if_canceled [{:set_variable {:name "right_shift pressed"
                                                                                             :value 0}}]}}]}


;;  https://karabiner-elements.pqrs.org/docs/json/typical-complex-modifications-examples/#change-equaldelete-to-forward_delete-if-these-keys-are-pressed-simultaneously
   :equal+delete->forward-delete {:rule-type :multi
                                  :from {:keys ["equal_sign" "delete_or_backspace"]
                                         :mods []}
                                  :to {:keys ["delete_forward"]
                                       :mods []}}
   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
   ;; with A 
   :cmd+s {:from {:keys ["a" "s"]
                  :mods []}
           :to {:keys ["s"]
                :mods ["left_command"]}}

   :cmd+d {:from {:keys ["a" "d"]
                  :mods []}
           :to {:keys ["d"]
                :mods ["left_command"]}}

   :cmd+f {:from {:keys ["a" "f"]
                  :mods []}
           :to {:keys ["f"]
                :mods ["left_command" "left_shift"]}}

   :cmd+x {:from {:keys ["a" "x"]
                  :mods []}
           :to {:keys ["x"]
                :mods ["left_command"]}}

   :cmd+c {:from {:keys ["a" "c"]
                  :mods []}
           :to {:keys ["c"]
                :mods ["left_command"]}}

   :cmd+v {:from {:keys ["a" "v"]
                  :mods []}
           :to {:keys ["v"]
                :mods ["left_command"]}}

   :cmd+p {:from {:keys ["a" "p"]
                  :mods []}
           :to {:keys ["p"]
                :mods ["left_command"]}}

   ;; row 1
   :we->escape {:from {:keys ["w" "e"]
                       :mods []}
                :to {:keys ["escape"]
                     :mods []}}

   :wr->tab {:from {:keys ["w" "r"]
                    :mods []}
             :to {:keys ["tab"]
                  :mods []}}

   :qr->shift+tab {:from {:keys ["q" "r"]
                          :mods []}
                   :to {:keys ["tab"]
                        :mods ["left_shift"]}}

   :er->delete_or_backspace {:from {:keys ["e" "r"]
                                    :mods []}
                             :to {:keys ["delete_or_backspace"]
                                  :mods []}}

   ;; row 2
   :wd->ctrl+cmd {:from {:keys ["w" "d"]
                         :mods []}
                  :to {:keys ["left_control"]
                       :mods ["left_command"]}}

   :wf->ctrl+cmd+shift {:from {:keys ["w" "f"]
                               :mods []}
                        :to {:keys ["left_control"]
                             :mods ["left_command"
                                    "left_shift"]}}

   :shift+opt+cmd {:from {:keys ["e" "f"]
                          :mods []}
                   :to {:keys ["left_shift"]
                        :mods ["left_option"
                               "left_command"]}}

   :ctrl+opt+tab {:desc "마우스 이동용으로 사용한다. "
                  :from {:keys ["s" "e"]
                         :mods []}
                  :to {:keys ["tab"]
                       :mods ["left_control"
                              "left_option"]}}

   ;;  row 3
   :sd->ctrl+opt {:from {:keys ["s" "d"]
                         :mods []}
                  :to {:keys ["left_control"]
                       :mods ["left_option"]}}

   :sf->ctrl+opt+shift {:from {:keys ["s" "f"]
                               :mods []}
                        :to {:keys ["left_control"]
                             :mods ["left_option"
                                    "left_shift"]}}

   :df->opt+cmd {:from {:keys ["d" "f"]
                        :mods []}
                 :to {:keys ["left_option"]
                      :mods ["left_command"]}}


;; row 4   
   :sc->ctrl+opt+cmd {:from {:keys ["s" "c"]
                             :mods []}
                      :to {:keys ["left_command"]
                           :mods ["left_control"
                                  "left_option"]}}

   :sv->ctrl+opt+cmd+shift {:from {:keys ["s" "v"]
                                   :mods []}
                            :to {:keys ["left_shift"]
                                 :mods ["left_control"
                                        "left_option"
                                        "left_command"]}}

   :dv->opt {:from {:keys ["d" "v"]
                    :mods []}
             :to {:keys ["left_option"]
                  :mods []}}

  ;;  row 5
   :cmd+z {:from {:keys ["z" "x"]
                  :mods []}
           :to {:keys ["left_command"]
                :mods ["z"]}}

   :shift {:from {:keys ["x" "c"]
                  :mods []}
           :to {:keys ["left_shift"]
                :mods []}}

   :cmd+shift {:from {:keys ["x" "v"]
                      :mods []}
               :to {:keys ["left_command"]
                    :mods ["left_shift"]}}

   :cmd {:from {:keys ["c" "v"]
                :mods []}
         :to {:keys ["left_command"]
              :mods []}}

   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
   ;; space 
   :cmd+w {:from {:keys ["w" "spacebar"]
                  :mods []}
           :to {:keys ["w"]
                :mods ["left_command"]}}

   :opt+shift+e {:from {:keys ["e" "spacebar"]
                        :mods []}
                 :to {:keys ["e"]
                      :mods ["left_shift"
                             "left_option"]}}

   :cmd+return {:from {:keys ["r" "spacebar"]
                       :mods []}
                :to {:keys ["return_or_enter"]
                     :mods ["left_command"]}}

   :space+a {:from {:keys ["a" "spacebar"]
                    :mods []}
             :to {:keys ["a"]
                  :mods ["left_control"
                         "left_option"
                         "left_command"
                         "left_shift"]}}

   :shift+space {:from {:keys ["s" "spacebar"]
                        :mods []}
                 :to {:keys ["spacebar"]
                      :mods ["left_shift"]}}

   :ctrl+opt+space {:from {:keys ["d" "spacebar"]
                           :mods []}
                    :to {:keys ["spacebar"]
                         :mods ["left_control"
                                "left_option"]}}

   :return_or_enter_3 {:from {:keys ["f" "spacebar"]
                              :mods []}
                       :to {:keys ["return_or_enter"]
                            :mods []}}

   :ctrl+space {:from {:keys ["x" "spacebar"]
                       :mods []}
                :to {:keys ["spacebar"]
                     :mods ["left_control"]}}

   :opt+space {:from {:keys ["c" "spacebar"]
                      :mods []}
               :to {:keys ["spacebar"]
                    :mods ["left_option"]}}

   :cmd+space {:from {:keys ["v" "spacebar"]
                      :mods []}
               :to {:keys ["spacebar"]
                    :mods ["left_command"]}}

;;    end
   })


(defmulti rule-convert (fn [[_k v]] (:rule-type v)))

(defmethod rule-convert :raw [[k v]]
  [(dissoc v :rule-type)])

(defmethod rule-convert :single [config]
  [(get-single-rule config)])

(defmethod rule-convert :multi [config]
  [(get-simultaneous-rule config)
   (get-simultaneous-rule-right config)])

(defmethod rule-convert :default [config]
  [(get-simultaneous-rule config)
   (get-simultaneous-rule-right config)])

(defn generate-raw [configs]
  (let [file-name "karabiner.json"
        karabiner (json/read-str (slurp file-name))
        profile (->> (get karabiner "profiles")
                     (filter #(= "Default profile" (get % "name")))
                     first)
        new-rules (->> (mapcat rule-convert configs)
                       (remove nil?))
        new-profile (assoc-in profile ["complex_modifications" "rules"] new-rules)
        json-str (-> (assoc karabiner "profiles" [new-profile])
                     (json/write-str {:escape-unicode false}))]
    (spit file-name json-str)))

(comment
  (generate-raw config-map)
  (mapcat rule-convert config-map)
  (rule-convert [:a {:rule-type :double}])

  :rcf)

;; (defn rule [config]
;;   (let [[_ value] config
;;         from-keys (get-in value [:from :keys])
;;         keys-len (count from-keys)]
;;     (if (<= keys-len 1)
;;       (get-single-rule config)
;;       (get-simultaneous-rule config))))

;; (defn rule-right [config]
;;   (let [[_name value] config
;;         from-keys (get-in value [:from :keys])
;;         keys-len (count from-keys)
;;         right  (:right value)]
;;     (when (and (>= keys-len 2)
;;                (not= right false))
;;       (get-simultaneous-rule-right config))))

;; (defn generate [configs]
;;   (let [file-name "karabiner.json"
;;         karabiner (json/read-str (slurp file-name))
;;         profile (->> (get karabiner "profiles")
;;                      (filter #(= "Default profile" (get % "name")))
;;                      first)
;;         new-rules (->> (concat (map rule configs)
;;                                (map rule-right configs))
;;                        (remove nil?))
;;         new-profile (assoc-in profile ["complex_modifications" "rules"] new-rules)
;;         json-str (-> (assoc karabiner "profiles" [new-profile])
;;                      (json/write-str {:escape-unicode false}))]
;;     (spit file-name json-str)))


;; (comment
;;   (generate config-map)
;;   (def simple-map
;;     {;; single 
;;      :cap_lock->ctrl+opt {:from {:keys ["caps_lock"]
;;                                  :mods []}
;;                           :to {:keys ["left_control"]
;;                                :mods ["left_option"]}}})
;;   (generate simple-map)
;;   @(def data (json/read-str (slurp "karabiner.json")))
;;   @(def profiles (get data "profiles"))
;;   @(def default-profile
;;      (->> (filter #(= "Default profile" (get % "name"))
;;                   profiles)
;;           first))
;;   @(def rules
;;      (get-in default-profile ["complex_modifications" "rules"]))

;;   @(def complex_modifications
;;      (->> (filter #(= "complex_modifications" (first %))
;;                   profiles)))

;;   (keys (first profiles))
;;   (map (fn [k]
;;          (prn k)) profiles)

;;   (-> data
;;       (get "profiles")
;;       #_(get "complex_modifications")
;;       count)

;;   (spit "z_data.json" (json/write-str data {:escape-unicode false}))
;;   (spit "j.txt" (generate config-map))
;;   (spit "rules.txt" (generate config-map))
;;   :rcf)
;;    :a->cmd {:rule-type :raw
;;             :description "a->cmd"
;;             :manipulators [{:type "basic"
;;                             :from {:key_code "a"
;;                                    :modifiers {:optional ["any"]}}
;;                             :parameters {:basic.to_if_alone_timeout_milliseconds 800
;;                                          :basic.to_if_held_down_threshold_milliseconds 300}
;;                             :to_if_alone [{:key_code "a"}]
;;                             :to_if_held_down [{:key_code "left_command"}]}]}

;;    :f->shift {:rule-type :raw
;;               :description "f->cmd"
;;               :manipulators [{:type "basic"
;;                               :from {:key_code "f"
;;                                      :modifiers {:optional ["any"]}}
;;                               :parameters {:basic.to_if_alone_timeout_milliseconds 900
;;                                            :basic.to_if_held_down_threshold_milliseconds 900}
;;                               :to_if_alone [{:key_code "f"}]
;;                               :to_if_held_down [{:key_code "left_shift"}]}]}
