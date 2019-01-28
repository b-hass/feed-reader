(ns feed-reader.core
  (:gen-class)
  (:import (com.rometools.rome.io SyndFeedInput XmlReader)
           (java.net URL)))

(defn get-rss
  [url]
  (-> (SyndFeedInput.)
     (.build (XmlReader. (URL. "http://feeds.bbci.co.uk/news/world/africa/rss.xml")))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print (get-rss "")))

(-main)