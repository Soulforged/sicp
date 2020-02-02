(ns sicp.exercise111-test
  (:require [clojure.test :refer :all]
            [sicp.exercise111 :refer :all]))

(deftest should-work
  (testing "Exercise 1.11: ..."
    (is (= 2 (run-recur 2)))
    (is (= 11 (run-recur 4)))
    (is (= 25 (run-recur 5)))
    (is (= 59 (run-recur 6)))
    (is (= 2 (run-iter 2)))
    (is (= 4 (run-iter 3)))
    (is (= 11 (run-iter 4)))
    (is (= 25 (run-iter 5)))
    (is (= 59 (run-iter 6)))))
