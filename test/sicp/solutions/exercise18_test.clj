(ns sicp.solutions.exercise18-test
  (:require [clojure.test :refer :all]
            [sicp.solutions.exercise18 :refer :all]))

(deftest should-return-cube-root
  (testing "Exercise 1.8: ..."
    (is (= 3.0 (run 27)))
    (is (= 2.0 (run 8)))))
