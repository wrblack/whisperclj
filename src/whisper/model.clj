(ns whisper.model
  (:require [clojure.java.jdbc :as jdbc]))

(defn create-table [db]
  (jdbc/execute! db ["CREATE TABLE IF NOT EXISTS whispers
                     (id SERIAL PRIMARY KEY,
                      pseudonym TEXT NOT NULL,
                      whisper_body TEXT NOT NULL,
                      date_created TIMESTAMPTZ NOT NULL DEFAULT now())"]))

(defn create-whisper [db name whisper-body]
  (:id (first (jdbc/query
               db
               ["INSERT INTO whispers (pseudonym, whisper_body)
                 VALUES (?, ?) RETURNING id"
                 name
                 whisper-body]))))

(defn read-whispers [db]
  (jdbc/query db ["SELECT id, pseudonym, whisper_body, date_created
                   FROM whispers
                   ORDER BY date_created"]))

(defn delete-whisper [db id]
  (= [1] (jdbc/execute! db ["DELETE FROM whispers
                             WHERE id = ?" id])))
