(ns sicp.edn.file-loader
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn load [res-path]
  (-> (io/resource res-path)
    slurp
    edn/read-string))