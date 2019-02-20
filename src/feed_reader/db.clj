(ns feed-reader.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

(def conn (mg/connect))
(def db (mg/get-db conn "test"))

(defn insert-articles
  [articles]
  (mc/insert-batch db "articles" articles))
