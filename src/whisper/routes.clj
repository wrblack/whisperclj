(ns whisper.routes
  (:require [compojure.core :refer [defroutes ANY GET POST]]
            [compojure.route :as route]
            [whisper.views :refer [index-page
                                   about-page
                                   whispers-page]]
            [whisper.handler :refer [handle-create-whisper
                                     handle-index-whispers]]
            [ring.handler.dump :refer [handle-dump]]))

;; TODO: replace the view functions with handler function
(defroutes app-routes
  (GET "/" [] index-page)
  (POST "/" [] handle-create-whisper)
  (GET "/whispers" [] handle-index-whispers)
  (GET "/about" [] about-page)
  (route/resources "/")
  (route/not-found "Page not found."))
