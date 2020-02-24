(defproject sicp "0.1.0-SNAPSHOT"
  :main sicp.core/run
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                [org.clojure/math.numeric-tower "0.0.4"]
                [clojure-interop/java.lang "1.0.5"]
                [com.walmartlabs/lacinia-pedestal "0.13.0"]
                [io.aviso/logging "0.3.2"]]
  :repl-options {:init-ns sicp.core})
