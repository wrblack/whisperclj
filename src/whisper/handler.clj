(ns whisper.handler
  (:require [whisper.model :refer [create-whisper
                                   read-whispers
                                   delete-whisper]]

            [whisper.views :refer [whispers-page]]))

;; the goal here is to do views/some-page as :body

(defn handle-index-whispers [req]
  (let [db (:whisper/db req)
        whispers (read-whispers db)]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (whispers-page whispers)}))

(defn handle-create-whisper [req]
  (let [name (get-in req [:params :pseudonym])
        whisper-body (get-in req [:params :whisper-body])
        db (:whisper/db req)
        id (create-whisper db name whisper-body)]
    {:status 302
     :headers {"Location" "/whispers"}
     :body ""}))
