(ns feed-reader.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

(def conn (mg/connect))
(def db (mg/get-db conn "test"))

(defn insert-article
  [art]
  (mc/insert-and-return db "articles" art))
