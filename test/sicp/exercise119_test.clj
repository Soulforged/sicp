(ns sicp.exercise119-test
  (:require [clojure.test :refer :all]
            [sicp.exercise119 :refer :all]))

(deftest should-work
  (testing "Exercise 1.19: ..."
    (is (= 0 (run 0)))
    (is (= 1 (run 1)))
    (is (= 1 (run 2)))
    (is (= 3 (run 4)))
    (is (= 21 (run 8)))))
