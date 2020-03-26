(ns sicp.ql.schema_test
  (:require [clojure.test :refer :all]
            [com.walmartlabs.lacinia :refer [execute]]
            [sicp.ql.schema :as schema]
            [sicp.ql.resolvers :as resolvers]
            [sicp.datasource.db :as db]
            [clojure.walk :as walk])
  (:import (clojure.lang IPersistentMap)))

(defn simplify
  "Converts all ordered maps nested within the map into standard hash maps, and
    sequences into vectors, which makes for easier constants in the tests, and eliminates ordering problems."
  [m]
  (walk/postwalk
    (fn [node]
      (cond
        (instance? IPersistentMap node)
        (into {} node)

        (seq? node)
        (vec node)

        :else
        node))
    m))

(def compiled-schema (schema/load-and-compile "sicp/ql/schema.edn" (resolvers/create-map)))

(defn q [query-string]
  (-> (execute compiled-schema query-string nil nil)
  simplify))

(deftest schema_resolvers
  (testing "exercise query with a certain 'id' returns a single resource."
    (is (= 
      { :data { :exercise { :name "1.2" }}} 
      (q "query { exercise(id: \"1\") { name }}"))))
  (testing "exercises query with no args resolves to all exercises."
    (is (= 
      { :data { :exercises [{ :name "1.2" } 
                            { :name "1.3" }
                            { :name "1.8" }
                            { :name "1.11" }]}} 
      (q "query { exercises { name }}")))))
