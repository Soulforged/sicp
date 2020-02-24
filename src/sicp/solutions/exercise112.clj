; Exercise 1.12: e following paern of numbers is called
; Pascal’s triangle.
;         1
;       1   1
;     1   2   1
;   1   3   3   1
; 1   4   6   4   1
; . . .
; The numbers at the edge of the triangle are all 1, and each
; number inside the triangle is the sum of the two numbers
; above it. Write a procedure that computes elements of
; Pascal’s triangle by means of a recursive process.
(ns sicp.solutions.exercise112)

; TODO I need to return to this, it doesn't work properly.
(defn run [n]
  (defn r [count a b]
    (prn b)
    (if (= count 0)
      1
      (r (- count 1) b (r (- count 1) b (+ a b)))))
  (r n 0 1))
