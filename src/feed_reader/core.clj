(ns feed-reader.core
  (:gen-class)
  (:require [feed-reader.synd :refer [get-rss-entries]]
            [feed-reader.db :refer [insert-article]]
            [clojure.string :as str]))

(def url-list (str/split-lines (slurp "url_list")))

(defn do-url
  [url]
  (dorun
    (map #(insert-article %) (get-rss-entries url))))

(defn -main
  [& args]
  (dorun
    (pmap do-url url-list)))

