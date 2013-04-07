(ns wedding-photos.album
  (:require [net.cgrand.enlive-html :as html]
            [clojure.set :as set]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [wedding-photos.image-handling :as img]))


(defn html-album-page-image [id, url]
  (format "<li style='float:left;list-style:none;margin-right:20px'><img style='clear:both;float:none' src='%s' width='100px' /><p style='margin:0;padding:0;text-align:center'>Id: %s</p></li>" url, id))

(defn html-album-page-image-list [content]
  (format "<ul style='height: 100;margin:0;padding:0;margin-left:10px;margin-top:10px;'>%s</ul>" content))

(defn html-album-page [number content]
  (format "<div style='border: solid 2px grey'><p style='margin:0;padding:0;margin-left:10px;margin-top:10px'>Page %s</p> %s </div>" number content))

(defn html-album-pages [resource selected-images]
  (string/join 
   (html-album-page 
    1 
    (html-album-page-image-list 
     (str (html-album-page-image "1") (html-album-page-image "2"))))))

(defn web-page [resource selected]
  (format "<html><body><ul style='list-style:none'>%s</ul></body></html>"  (string/join (html-album-pages resource selected))))

;;(spit (img/expand-path "~/Desktop/test-album.html") (web-page "" [2]))

(def photos-on-each-page [54 56 59 [63 97] 98 106 [110 113] [79 112] 114 118 130 142 166 198 206 244 363 375 [225 233 234 237] [238 271 311] [253 254 276 300] [366 367 368 373] [357 358 360] [214 215 217 220] [156 157 159 189] [125 131 132 140] [127 151]])

(def gallery-resource (img/fetch-url img/*gallery-url*))

;;(def map-of-img-urls (retrieve-image-urls (flatten photos-on-each-page)))


(def map-of-urls-on-page (img/make-map (img/get-list-of-img-urls gallery-resource)))


(defn img-url [id map-of-urls]
  ((keyword (str id)) map-of-urls))

;;(map #(img-url % map-of-urls-on-page) [1 2])


(defn img [id map-of-urls]
  (html-album-page-image id (img-url id map-of-urls)))

(defn list-of-img [vector]
  (string/join (map img vector)))

(defmulti page class)
(defmethod page clojure.lang.IEditableCollection [c] (list-of-img c))
(defmethod page Long [i] (img i))

;; (page [1 2 3])
;; (page 1)

;; (map page [1 2 [4 5]])

 
;; (html-album-page-image 1 (:1 map-of-urls-on-page))