(ns feed-reader.core-test
  (:require [clojure.test :refer :all]
            [feed-reader.core :refer :all]))

(def URL "http://feeds.bbci.co.uk/news/world/africa/rss.xml")

(deftest feed-fetch
  (testing "The RSS should be fetched correctly."
    (is (= "" (-> (get-synd URL) (.getUri))))))