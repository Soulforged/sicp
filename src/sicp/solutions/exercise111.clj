; Exercise 1.11: A function f is defined by the rule that
; f(n0 if n < 3,
;   f (n − 1) + 2f (n − 2) + 3f (n − 3) if n ≥ 3.
; Write a procedure that computes f by means of a recursive
; process. Write a procedure that computes f by means of an
; iterative process.
(ns sicp.solutions.exercise111)

(defn run-recur [n]
  (if (< n 3) n
    (+ (run-recur (- n 1)) (* 2 (run-recur (- n 2))) (* 3 (run-recur (- n 3))))))

(defn run-iter [n]
  (defn run-iter-in [acc b c count]
    (if (= 0 count) acc
      (run-iter-in (+ acc (* 2 b) (* 3 c)) acc b (- count 1))))
  (if (< n 3) n
    (run-iter-in 2 1 0 (- n 2))))
