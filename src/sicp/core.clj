(ns sicp.core
  (:require [sicp.system :as system]
            [com.stuartsierra.component :as component]))

(defn start
  []
  (alter-var-root #'system component/start-system)
  (browse-url "http://localhost:8888/")
  :started)

(defn stop
  []
  (alter-var-root #'system component/stop-system)
  :stopped)

(defn run [] (start))