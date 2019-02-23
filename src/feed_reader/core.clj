(ns feed-reader.core
  (:gen-class)
  (:require [feed-reader.synd :refer [get-rss-entries]]
            [feed-reader.db :refer [insert-articles]]
            [clojure.string :as str]))

(def url-list (str/split-lines (slurp "url_list")))

(defn do-urls
  []
  (reduce
    (fn [articles url] (concat articles (get-rss-entries url)))
    [] url-list))

(defn -main
  [& args]
  (insert-articles (do-urls)))
