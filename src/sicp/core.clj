(ns sicp.core
  (:require [sicp.http.server :as server]))

(defn run [] (server/start))