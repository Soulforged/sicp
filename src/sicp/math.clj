(ns sicp.math
  (:require [clojure.math.numeric-tower :as math]))

; Computes the square of x
(defn square [x] (math/expt x 2))

; Computes the square root of x using Newton's successive aproximation method
(defn sqrt [x]
  (defn good-enough-guess? [guess-diff margin]
    (if (and (>= guess-diff 0) (<= guess-diff margin)) true false))

  (defn next-guess [guess]
    (/ (+ guess (/ x guess)) 2))

  (defn sqrt-by-newton-aprox [guess prev-guess]
    (if (good-enough-guess? (- prev-guess guess) 0.000001) guess
      (sqrt-by-newton-aprox (next-guess guess) guess)))
  (double (sqrt-by-newton-aprox (/ x 2) 0)))

; Computes the cube root of x using Newton's successive aproximation method
(defn cbrt [x]
  (defn good-enough-guess? [guess-diff margin]
    (if (and (>= guess-diff 0) (<= guess-diff margin)) true false))

  (defn next-guess [guess]
    (/ (+ (* 2 guess) (/ x (* guess guess))) 3))

  (defn cbrt-by-newton-aprox [guess prev-guess]
    (if (good-enough-guess? (- prev-guess guess) 0.00000001) guess
      (cbrt-by-newton-aprox (next-guess guess) guess)))
  (double (cbrt-by-newton-aprox (/ x 2) 0)))

; Computes Ackermann's function of x and y
(defn A [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
  :else (A (- x 1) (A x (- y 1)))))

; Exponentiation of base b to the power of n
(defn pow [b n]
  (defn iter [b n a]
    (cond (= n 0) a
          (even? n) (iter (* b b) (/ n 2) a)
          :else (iter b (- n 1) (* a b))))
  (iter b n 1))

; Get the nth Fibonacci number
(defn fib [n]
  (defn fib-iter [a b p q count]
    (cond (= count 0) b
          (even? count) (fib-iter a b (+ (* p p) (* q q)) (* (+ (* 2 p) q) q) (/ count 2))
          :else (fib-iter (+ (* b q) (* a q) (* a p)) (+ (* b p) (* a q)) p q
                  (- count 1))))
  (fib-iter 1 0 0 1 n))

; Get the greates common divisor of a and b
(defn gcd [a b]
  (if (= b 0) a
    (gcd b (mod a b))))

; Find the smallest divisor of n
(defn sdiv [n]
  (defn _next [test]
    (if (= test 2) (+ 1 test)
      (+ 2 test)))
  (defn find-sdiv [n test]
    (cond (> (square test) n) n
          (= (mod n test) 0) test
          :else (find-sdiv n (+ 1 test))))
  (find-sdiv n 2))

; Fermat’s Little test: If n is a prime number and a
; is any positive integer less than n, then a raised to the n th
; power is congruent to a modulo n.
(defn fermat-test [n]
  (defn expmod [base exp]
    (cond (= exp 0) 1
          (even? exp) (mod (square (expmod base (/ exp 2))) n)
          :else (mod (* base (expmod base (- exp 1))) n)))
  (defn try-it [a]
    (= (expmod a n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

; Miller-Rabin test: If n is a prime number and a is
; any positive integer less than n, then a raised to the (n−1)-st
; power is congruent to 1 modulo n.
(defn miller-rabin-test [n]
  (defn is-non-trivial-square? [base exp]
    (let [x (expmod base (/ exp 2)) y (mod (square x) n)]
      (if (and (not= x 1) (not= x (- n 1)) (= y (mod 1 n))) 0 y)))
  (defn expmod [base exp]
    (cond (= exp 0) 1
          (even? exp) (is-non-trivial-square? base exp)
          :else (mod (* base (expmod base (- exp 1))) n)))
  (defn try-it [a]
      (= (expmod a n) a))
  (try-it (+ 1 (rand-int (- n 2)))))

; Finds primality of a number
(defn prime? [n]
  (defn fast-prime? [n times]
    (cond (= times 0) true
          (miller-rabin-test n) (fast-prime? n (- times 1))
          :else false))
  (fast-prime? n 4))
