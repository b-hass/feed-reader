(ns feed-reader.synd
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

(defn get-rss-entries
  [url]
  (->
    (get-synd url)
    get-entries
    parse-entries))