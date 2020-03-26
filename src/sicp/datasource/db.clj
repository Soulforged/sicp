(ns sicp.datasource.db
  (:require
    [clojure.edn :as edn]
    [sicp.edn.file-loader :as loader]
    [com.stuartsierra.component :as component]))

(defrecord SICPExercisesDB [data]

  component/Lifecycle

  (start [this]
    (assoc this :data (-> (loader/load "sicp/data/exercises.edn")
                          atom)))

  (stop [this]
    (assoc this :data nil)))

(defn new-db
  []
  {:db (map->SICPExercisesDB {})})

(defn exercise [db id]
  (->> db
       :data
       deref
       :exercises
       (filter #(= id (:id %)))
       first))

(defn exercises [db]
  (->> db
       :data
       deref
       :exercises))

(defn solution-user [db solution]
  (let [user (:user solution)]
    (->> db
        :data
        deref
        :users
        (get #(contains? user (:id %))))))

(defn exercise-possible-solutions [db exercise]
  (let [possible_solutions (:possible_solutions exercise)]
    (->> db
        :data
        deref
        :solutions
        (filter #(contains? possible_solutions (:id %))))))
  
(defn exercise-featured-solution [db exercise]
  (let [featured_solution (:featured_solution exercise)]
    (->> db
         :data
         deref
         :solutions
         (filter #(= featured_solution (:id %)))
         first)))

(defn solution-exercise [db solution]
  (let [exercise-id (:exercise solution)]
    (->> db
         :data
         deref
         :exercises
         (filter #(= exercise-id (:exercise %)))
         first)))

(defn upsert-solution
  "Adds a new game rating, or changes the value of an existing game rating."
  [db exercise-id user-id solution-code]
  (defn ^:private apply-solution [solutions]
    (->> solutions
        (remove #(and (= exercises-id (:exercise %))
                      (= user-id (:user %))))
        (cons {:game_id game-id
                :member_id member-id
                :rating rating})))
  (-> db
      :data
      (swap! update :ratings apply-game-rating)))