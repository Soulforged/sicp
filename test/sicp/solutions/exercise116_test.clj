(ns sicp.solutions.exercise116-test
  (:require [clojure.test :refer :all]
            [sicp.solutions.exercise116 :refer :all]))

(deftest should-work
  (testing "Exercise 1.16: ..."
    (is (= 2 (run 2 1)))
    (is (= 16 (run 2 4)))
    (is (= 1099511627776 (run 2 40)))
    (is (= 2199023255552 (run 2 41)))))
