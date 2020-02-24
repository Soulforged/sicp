(ns sicp.solutions.exercise13-test
  (:require [clojure.test :refer :all]
            [sicp.solutions.exercise13 :refer :all]))

(deftest should-return-sum-of-squares-of-highests
  (testing "Exercise 1.3: Define a procedure that takes three numbers
            as arguments and returns the sum of the squares of the two
            larger numbers."
    (is (= 13 (run 1 2 3)))
    (is (= 25 (run 4 2 3)))
    (is (= 25 (run 4 3 2)))))
