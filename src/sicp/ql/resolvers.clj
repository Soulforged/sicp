(ns sicp.ql.resolvers
  (:require [sicp.datasource.edn :as edn-ds]))

(defn create-map []
  {:exercise (fn [_context args _value] (edn-ds/exercise (:id args)))
  :exercises (fn [_context _args _value] (edn-ds/exercises))})