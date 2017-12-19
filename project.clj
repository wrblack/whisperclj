(defproject whisper "0.1.0-SNAPSHOT"

  :description "Whisper: a webapp to share anonymous thoughts"

  :url "tba"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.0.0"

  :uberjar-name "whisper.jar"

  :main whisper.core

  :profiles {:dev
             {:source-paths ["src" "dev"]
              :main whisper.dev}}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [listora/ring-cache-buster "0.2.0"]
                 [compojure "1.6.0"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [org.postgresql/postgresql "42.1.4"]])
