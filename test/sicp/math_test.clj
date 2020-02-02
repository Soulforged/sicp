(ns sicp.math-test
  (:require [clojure.test :refer :all]
            [sicp.math :refer :all]))

(deftest should-square-root
  (testing "Square root."
    (is (= 2.0 (sqrt 4)))
    (is (= 5.0 (sqrt 25)))
    (is (= 10.0 (sqrt 100)))))
