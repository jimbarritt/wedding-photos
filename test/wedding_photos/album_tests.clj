(ns wedding-photos.album-tests
  (:use wedding-photos.album
        midje.sweet)
  (:require [net.cgrand.enlive-html :as html]
            [clojure.set :as set]
            [clojure.java.io :as io]
            [clojure.string :as string]))



(fact "Can create an album item"
   (html-album-page-image 23 "http://foo") => #(println %))