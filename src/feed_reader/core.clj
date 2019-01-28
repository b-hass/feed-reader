(ns feed-reader.core
  (:gen-class)
  (:require [feed-reader.synd :refer [get-rss-entries]]
            [feed-reader.db :refer [insert-article]]))

(defn -main
  [& args]
  (print (map #(insert-article %) (get-rss-entries ""))))

(-main)