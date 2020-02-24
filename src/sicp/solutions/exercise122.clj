(ns sicp.solutions.exercise122
  (:require [sicp.util :as util :refer [runtime]]
            [sicp.math :as math :refer [sdiv]]))

; Exercise 1.22: Most Lisp implementations include a prim-
; itive called runtime that returns an integer that specifies
; the amount of time the system has been running (mea-
; sured, for example, in microseconds). e following timed-
; prime-test procedure, when called with an integer n, prints
; n and checks to see if n is prime. If n is prime, the procedure
; prints three asterisks followed by the amount of time used
; in performing the test.
; Using this procedure, write a procedure search-for-primes
; that checks the primality of consecutive odd integers in a
; specified range. Use your procedure to find the three small-
; est primes larger than 1000; larger than 10,000; larger than
; 100,000; larger than 1,000,000. Note the time needed to test
; each prime. Since the testing algorithm has order of growth
; √
; of Θ( n), you should expect
; √ that testing for primes around
; 10,000 should take about 10 times as long as testing for
; primes around 1000. Do your timing data bear this out?
; How well do the data for 100,000 and 1,000,000 support the
; √
; Θ( n) prediction? Is your result compatible with the notion
; that programs on your machine run in time proportional to
; the number of steps required for the computation?
(defn run [n]
  (defn prime? [n] (= (sdiv n) n))
  (defn timed-prime-test [n]
    (defn report-prime [elapsed-time]
      (prn " *** ")
      (prn elapsed-time))
    (defn start-prime-test [n start-time]
      (if (prime? n) (report-prime (- (runtime) start-time)) false))
    (newline)
    (prn n)
    (start-prime-test n (runtime)))
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

;Exercise 1.23: The smallest-divisor procedure shown at
; the start of this section does lots of needless testing: Aer it
; checks to see if the number is divisible by 2 there is no point
; in checking to see if it is divisible by any larger even num-
; bers. is suggests that the values used for test-divisor
; should not be 2, 3, 4, 5, 6, . . ., but rather 2, 3, 5, 7, 9, . . ..
; To implement this change, define a procedure next that re-
; turns 3 if its input is equal to 2 and otherwise returns its in-
; put plus 2. Modify the smallest-divisor procedure to use
; (next test-divisor) instead of (+ test-divisor 1) .
; With timed-prime-test incorporating this modified version of smallest-divisor ,
; run the test for each of the 12 primes found in Exercise 1.22.
; Since this modification halves the number of test steps, you should expect it
; to run about twice as fast. Is this expectation confirmed? If not, what is
; the observed ratio of the speeds of the two algorithms, and
; how do you explain the fact that it is different from 2?
; sicp.core=> (e/run 1000)
; "time: " 2
; sicp.core=> (e/run 10000)
; "time: " 2
; sicp.core=> (e/run 100000)
; "time: " 3
; sicp.core=> (e/run 1000000)
; "time: " 6
; sicp.core=> (e/run 10000000)
; "time: " 30
;
; sicp.core=> (e/run 1000)
; "Elapsed time: 1.065731 msecs"
; sicp.core=> (e/run 10000)
; "Elapsed time: 2.00636 msecs"
; sicp.core=> (e/run 100000)
; "Elapsed time: 2.491206 msecs"
; sicp.core=> (e/run 1000000)
; "Elapsed time: 5.258913 msecs"
; sicp.core=> (e/run 10000000)
; "Elapsed time: 15.188774 msecs"
; sicp.core=> (e/run 100000000)
; "Elapsed time: 29.189801 msecs"
;
; The ratio seems to be close to 2 but not exactly two and it becomes unnoticeable
; for numbers smaller than 10000000. There's always an extra step for the first
; test number, therefore the closest theoretical ratio is: (n + 1)/2, where n is
; the number of steps for the original function without optimization.
