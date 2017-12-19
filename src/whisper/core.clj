(ns whisper.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults
                                              site-defaults]]
            [ring.middleware.params :refer [wrap-params]]

            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]

            [whisper.routes :as routes]
            [whisper.model :as model]))

(def db "jdbc:postgresql://localhost/whisper")

(defn wrap-db [hdlr]
  (fn [req]
    (hdlr (assoc req :whisper/db db))))

(def app
  (wrap-defaults
    (wrap-params
      (wrap-db
        routes/app-routes))
    (assoc site-defaults :security false)))

(defn -main [port]
  (model/create-table db)
  (jetty/run-jetty app {:port (Integer. port)}))
