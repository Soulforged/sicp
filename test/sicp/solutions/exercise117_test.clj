(ns sicp.solutions.exercise117-test
  (:require [clojure.test :refer :all]
            [sicp.solutions.exercise117 :refer :all]))

(deftest should-work
  (testing "Exercise 1.17: ..."
    (is (= 2 (run 2 1)))
    (is (= 8 (run 2 4)))
    (is (= 1152 (run 24 48)))))
