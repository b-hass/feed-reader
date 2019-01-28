(ns feed-reader.core
  (:gen-class)
  (:import (com.rometools.rome.io SyndFeedInput XmlReader)
           (java.net URL)))

(defn get-synd
  [url]
  (-> (SyndFeedInput.)
     (.build (XmlReader. (URL. url)))))

(defn get-entries
  [synd]
  (-> synd (.getEntries)))

(defn extract-info
  [feed-entry]
  {:title (-> feed-entry (.getTitle))
   :link  (-> feed-entry (.getLink))
   :summary (-> feed-entry (.getDescription) (.getValue))})

(defn parse-entries
  [synd]
  (map extract-info synd))

(defn -main
  [& args]
  (print (->
           (get-synd "http://feeds.bbci.co.uk/news/world/africa/rss.xml")
           get-entries
           parse-entries)))

(-main)