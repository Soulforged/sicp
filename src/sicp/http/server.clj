(ns sicp.http.server
  (:require [io.pedestal.http :as http]
            [com.walmartlabs.lacinia.pedestal :as lp]
            [clojure.java.browse :refer [browse-url]]
            [sicp.ql.schema :as schema]
            [sicp.ql.resolvers :as resolvers]))

(defonce server nil)

(defonce compiled-schema (schema/load-and-compile "sicp/ql/schema.edn" (resolvers/create-map)))

(defn start-server
  [_]
  (let [server (-> compiled-schema
                (lp/service-map {:graphiql true})
                (merge {:env :dev
                  ::http/allowed-origins {:creds true :allowed-origins (constantly true)}})
                http/create-server
                http/start)]
    (browse-url "http://localhost:8888/")
    server))

(defn stop-server
  [server]
  (http/stop server)
  nil)

(defn start
  []
  (alter-var-root #'server start-server)
  :started)

(defn stop
  []
  (alter-var-root #'server stop-server)
  :stopped)