; Exercise 1.3: Define a procedure that takes three numbers
; as arguments and returns the sum of the squares of the two
; larger numbers.
(ns sicp.solutions.exercise13
  (:require [sicp.math :as math :refer [square]]))

(defn ^{:private true} sum-of-squares [a b] (+ (square a) (square b)))

(defn ^{:private true} max [a b] (cond (> a b) a :else b))

(defn run [a b c]
  (sum-of-squares (max a b) (max c b)))
