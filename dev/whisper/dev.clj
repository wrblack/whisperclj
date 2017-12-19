(ns whisper.dev
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [whisper.core :as core]
            [whisper.model :as model]))

(defn -main [port]
  (model/create-table core/db)
  (jetty/run-jetty (wrap-reload #'core/app) {:port (Integer. port)}))
