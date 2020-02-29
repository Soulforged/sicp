(ns sicp.http.server
  (:require [io.pedestal.http :as http]
            [com.walmartlabs.lacinia.pedestal :as lp]
            [clojure.java.browse :refer [browse-url]]
            [sicp.ql.schema :as schema]
            [sicp.ql.resolvers :as resolvers]
            [com.stuartsierra.component :as component]))

(defrecord Server [schema-provider server]
  component/Lifecycle
  (start [this]
    (assoc this :server (-> schema-provider
                            :schema
                            (lp/service-map {:graphiql true})
                            (merge {:env :dev
                              ::http/allowed-origins {:creds true :allowed-origins (constantly true)}})
                            http/create-server
                            http/start)))
  (stop [this]
    (http/stop server)
    (assoc this :server nil))

  (defn new-server []
    {:server (component/using (map->Server {})
                              [:schema-provider])}))