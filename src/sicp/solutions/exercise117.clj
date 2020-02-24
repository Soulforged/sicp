; Exercise 1.17: e exponentiation algorithms in this sec-
; tion are based on performing exponentiation by means of
; repeated multiplication. In a similar way, one can perform
; integer multiplication by means of repeated addition. e
; following multiplication procedure (in which it is assumed
; that our language can only add, not multiply) is analogous
; to the expt procedure:
; (define (* a b)
; (if (= b 0)
; 0
; (+ a (* a (- b 1)))))
; is algorithm takes a number of steps that is linear in b .
; Now suppose we include, together with addition, opera-
; tions double , which doubles an integer, and halve , which
; divides an (even) integer by 2. Using these, design a mul-
; tiplication procedure analogous to fast-expt that uses a
; logarithmic number of steps.
(ns sicp.solutions.exercise117)

(defn run [a b]
  (defn double [a] (+ a a))
  (defn halve [a] (/ a 2))
  (defn multiply [a b c]
    (cond (= b 0) c
          (even? b) (multiply (double a) (halve b) c)
          :else (multiply a (- b 1) (+ c a))))
  (multiply a b 0))
