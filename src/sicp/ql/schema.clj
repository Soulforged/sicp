(ns sicp.ql.schema
  (:require
    [sicp.edn.file-loader :as loader]
    [com.walmartlabs.lacinia.schema :as schema]
    [com.walmartlabs.lacinia.util :as util]))


(defn load-and-compile [schema-res resolver-map]
  (-> (loader/load schema-res)
      (util/attach-resolvers resolver-map)
      schema/compile))