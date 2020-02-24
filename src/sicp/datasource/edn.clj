(ns sicp.datasource.edn
  (:require [sicp.edn.file-loader :as loader]))

(def data (loader/load "sicp/data/exercises.edn"))
(def map-by-id (->> data
                :exercises
                (reduce #(assoc %1 (:id %2) %2) {})))

(defn exercise [id] (get map-by-id id))
(defn exercises [] (get data :exercises))
