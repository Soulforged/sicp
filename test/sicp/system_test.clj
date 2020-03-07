(ns sicp.system-test
  (:require [clojure.test :refer :all]
            [sicp.system :as system]
            [com.stuartsierra.component :as component]))

(defonce system (system/new-system))

(deftest general-system-test
  (testing "System boots correctly. And can be shutdown"
    (alter-var-root #'system component/start-system)
    (alter-var-root #'system component/stop-system)))