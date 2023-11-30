(ns hold.var
  (:require [clojure.set :as set]
            [hold.common :as co]))

(def space-changed "space-changed")

(def space-pressed "space-pressed")
(def space->shift "space->shift")
(def space->command "space->command")

(def shift-pressed "caps-pressed")
(def control-pressed "control-pressed")
(def option-pressed "option-pressed")
(def command-pressed "command-pressed")


(defn var-if [name value]
  {:name name
   :type "variable_if"
   :value value})

(defn var-unless [name value]
  {:name name
   :type "variable_unless"
   :value value})
