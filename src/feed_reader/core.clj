(ns feed-reader.core
  (:gen-class)
  (:require [feed-reader.synd :refer [get-rss-entries]]))

(defn -main
  [& args]
  (print
    (get-rss-entries "http://feeds.bbci.co.uk/news/world/africa/rss.xml")))

(-main)