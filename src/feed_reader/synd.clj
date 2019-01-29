(ns feed-reader.synd
  (:import (com.rometools.rome.io SyndFeedInput XmlReader)
           (java.net URL)
           (org.jsoup.safety Whitelist)
           (org.jsoup Jsoup)))

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
     :summary (Jsoup/clean summary (Whitelist/none))}))

(defn parse-entries
  [synd]
  (map extract-info synd))

(defn get-rss-entries
  [url]
  (->
    (get-synd url)
    get-entries
    parse-entries))