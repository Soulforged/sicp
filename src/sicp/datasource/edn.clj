(ns sicp.datasource.edn
  (:require [sicp.edn.file-loader :as loader]))

(def data (loader/load "sicp/data/exercises.edn"))
(def exercises-by-id (->> data
                      :exercises
                      (reduce #(assoc %1 (:id %2) %2) {})))
(def users-by-id (->> data
                      :users
                      (reduce #(assoc %1 (:id %2) %2) {})))
(def solutions-by-id (->> data
                      :solutions
                      (reduce #(assoc %1 (:id %2) %2) {})))

(defn exercise [id] (get exercises-by-id id))
(defn exercises [] (get data :exercises))

(defn solution-user [solution] 
  (->> solution
       :user
       (map users-by-id)))

(defn exercise-possible-solutions [exercise]
  (->> exercise
       :possible_solutions
       (map solutions-by-id)))

(defn exercise-featured-solution [exercise]
  (->> exercise
       :featured_solution
       (map solutions-by-id)))

(defn solution-exercise [solution]
  (->> solution
       :exercise
       (get exercises-by-id)))
