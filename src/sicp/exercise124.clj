(ns sicp.exercise124
  (:require [sicp.math :as math :refer [prime?]]))

; Exercise 1.24: Modify the timed-prime-test procedure of
; Exercise 1.22 to use fast-prime? (the Fermat method), and
; test each of the 12 primes you found in that exercise. Since
; the Fermat test has Θ(log n) growth, how would you expect
; the time to test primes near 1,000,000 to compare with the
; time needed to test primes near 1000? Do your data bear
; this out? Can you explain any discrepancy you find?
(defn run [n]
  (defn search-for-primes [n count]
    (defn report-prime [n count]
      (prn n)
      (- count 1))
    (defn start-prime-test [n count]
      (cond (= 0 count) 0
            (prime? n) (start-prime-test (+ n 1) (report-prime n count))
            :else (start-prime-test (+ n 1) count)))
    (newline)
    (prn n)
    (start-prime-test n count))
  (time (search-for-primes n 3)))

; sicp.core=> (e1/run 1000)
; "Elapsed time: 1.546308 msecs"
; sicp.core=> (e1/run 10000)
; "Elapsed time: 3.449508 msecs"
; sicp.core=> (e1/run 100000)
; "Elapsed time: 3.74529 msecs"
; sicp.core=> (e1/run 1000000)
; "Elapsed time: 3.17802 msecs"
; sicp.core=> (e1/run 10000000)
; "Elapsed time: 8.597787 msecs"
; sicp.core=> (e1/run 100000000)
; "Elapsed time: 5.133169 msecs"
; sicp.core=> (e1/run 1000000000)
; "Elapsed time: 3.400264 msecs"
;
; The order of growth when using the optimized prime? function should be log n,
; and the data proves that this is roughly true, since
; search-for-primes(1000) = aprox. 1.5
; and the result for search-for-primes(1000000) = aprox. 3.2
; If the growth is logarithmic then
; search-for-primes(1000000)/search-for-primes(1000) = log(1000000/1000)
; which it roughly is.
