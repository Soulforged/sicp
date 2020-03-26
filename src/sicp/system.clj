(ns sicp.system
  (:require [com.stuartsierra.component :as component]
            [sicp.ql.schema :as schema]
            [sicp.http.server :as server]
            [sicp.datasource.db :as db]))

(defn new-system []
  (merge (component/system-map)
         (server/new-server)
         (schema/new-schema-provider)
         (db/new-db)))