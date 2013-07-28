(defproject wedding-photos "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [noir "1.3.0-beta3"]
                 [enlive "1.0.0-SNAPSHOT"]
                 [net.mikera/imagez "0.0.2"]]
  :profiles {:dev {:dependencies [[midje "1.5.1"]
                                  [org.clojure/tools.namespace "0.2.3"]]}})
