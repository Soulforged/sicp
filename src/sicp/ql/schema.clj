(ns sicp.ql.schema
  "Contains a function to provide the full schema."
  (:require
    [sicp.edn.file-loader :as loader]
    [com.walmartlabs.lacinia.schema :as schema]
    [com.walmartlabs.lacinia.util :as util]
    [sicp.ql.resolvers :as resolvers]
    [com.stuartsierra.component :as component]))


(defn load-and-compile 
  ([schema-res resolver-map]
  (-> (loader/load schema-res)
      (util/attach-resolvers resolver-map)
      schema/compile))
  ([component]
    (-> (loader/load "sicp/ql/schema.edn")
        (util/attach-resolvers (resolvers/create-map))
        schema/compile)))

(defrecord SchemaProvider [schema]

  component/Lifecycle

  (start [this]
    (assoc this :schema (load-and-compile this)))

  (stop [this]
    (assoc this :schema nil)))

(defn new-schema-provider []
  {:schema-provider (map->SchemaProvider {})})