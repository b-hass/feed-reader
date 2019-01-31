(ns feed-reader.categorizer
  (:require [opennlp.nlp :as nlp]
            [opennlp.tools.train :as train]))

(def cat-model (train/train-document-categorization "training/news.train"))
(def get-category (nlp/make-document-categorizer cat-model))
(def tokenize (nlp/make-tokenizer "model/en-token.bin"))

(defn categorize
  [text]
  (get-category (tokenize text)))
