(ns sicp.ql.resolvers
  (:require [sicp.datasource.edn :as edn-ds]
            [sicp.datasource.db :as db]
            [clojure.java.io :as io]))

(defn read-code-from-file [file code]
  (if (code) 
    code
    (-> (io/resource file)
      slurp)))

(defn create-map 
  ([db]
    {:exercise (fn [_context args _value] (db/exercise db (:id args)))
    :exercises (fn [_context _args _value] (db/exercises db))
    :solution/user (fn [_context _args _value] (db/solution-user db))
    :exercise/possible_solutions (fn [_context _args value] (db/exercise-possible-solutions db value))
    :exercise/featured_solution (fn [_context _args value] (db/exercise-featured-solution db value))
    :solution/code (fn [_context _args value] (read-code-from-file (:file value) (:code value)))
    :solution/exercise (fn [_context _args value] (db/solution-exercise db value))
    :exercise/test (fn [_context _args value] (read-code-from-file (:file value) (:test value)))})
  ;; FIXME this is deprecated, remove
  ([] 
    {:exercise (fn [_context args _value] (edn-ds/exercise (:id args)))
    :exercises (fn [_context _args _value] (edn-ds/exercises))
    :solution/user (fn [_context _args _value] (partial edn-ds/solution-user))
    :exercise/possible_solutions (fn [_context _args _value] (partial edn-ds/exercise-possible-solutions))
    :exercise/featured_solution (fn [_context _args _value] (partial edn-ds/exercise-featured-solution))
    :solution/code (fn [_context _args value] (read-code-from-file (:file value) (:code value)))
    :solution/exercise (fn [_context _args value] (db/solution-exercise value))
    :exercise/test (fn [_context _args value] (read-code-from-file (:file value) (:test value)))}))