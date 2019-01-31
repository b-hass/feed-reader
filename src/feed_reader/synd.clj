(ns feed-reader.synd
  (:require
           [feed-reader.categorizer :refer [categorize]])
  (:import (com.rometools.rome.io SyndFeedInput)
           (org.jsoup.safety Whitelist)
           (com.rometools.rome.io XmlReader)
           (org.jsoup Jsoup)
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
  (let [title (-> feed-entry (.getTitle))
        link  (-> feed-entry (.getLink))
        summary (-> feed-entry (.getDescription) (.getValue))]
    {:title   title
     :link    link
     :summary (Jsoup/clean summary (Whitelist/none))
     :category ((categorize summary) :best-category)}))

(defn parse-entries
  [synd]
  (map extract-info synd))

(defn get-rss-entries
  [url]
  (->
    (get-synd url)
    get-entries
    parse-entries))