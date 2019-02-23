(ns feed-reader.categorizer
  (:require [opennlp.nlp :as nlp]
            [opennlp.tools.train :as train]))

(defn serialize-model
  [model]
  (with-open [outp (-> (java.io.File. "trained_model") java.io.FileOutputStream. java.io.ObjectOutputStream.)]
    (.writeObject outp model)))

(defn deserialize-model
  []
  (try
    (with-open [inp (-> (java.io.File. "trained_model") java.io.FileInputStream. java.io.ObjectInputStream.)]
      (.readObject inp))
    (catch Exception e nil)))

(defn train-model
  []
  (let [trained-model (train/train-document-categorization "training/news.train")]
    (do
      (serialize-model trained-model)
      trained-model)))

(def tokenize (nlp/make-tokenizer "model/en-token.bin"))
(def trained-model (deserialize-model))
(def get-category (nlp/make-document-categorizer (if (nil? trained-model) (train-model) trained-model)))

(defn categorize
  [text]
  (get-category (tokenize text)))
