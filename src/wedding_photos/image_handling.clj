(ns wedding-photos.image-handling
  (:require [net.cgrand.enlive-html :as html]
            [clojure.set :as set]
            [clojure.java.io :as io]
            [clojure.string :as string]))


;;Permanent url   http://tinyurl.com/cg2gdvb 
(defn img-url [index]
  (format "https://www.dropbox.com/sh/ok8x0rvwu0i3eox/hu1HVNed_w#f:jr%03d.jpg" index))

;;https://www.dropbox.com/sh/ok8x0rvwu0i3eox/hu1HVNed_w#f:jr%03d.jpg

(defn fetch-url [url]
  (html/html-resource (io/as-url url)))

(defn extract-list-of-imgs [resource]
  (let [resource resource]
    (html/select resource [:div#gallery-view-container :img])))

(defn get-img-url [link-tag]
  (:data-src (:attrs link-tag)))


(defn get-image-id-from-url [img-url]
  (-> (re-find #".*(jr)(\d\d\d)(\.jpg).*" img-url)       
      (get 2)
      Integer/parseInt))

(defn get-list-of-img-urls [resource]
  (let [list-of-img-urls (map get-img-url (extract-list-of-imgs resource))]
    [(map get-image-id-from-url list-of-img-urls)
     list-of-img-urls]))

(defn make-map [two-arrays]
  (apply hash-map (flatten (map vector (first two-arrays) (second two-arrays)))))

(defn list-item [index map-of-img-urls]
  (let [img-url (get map-of-img-urls index)]
    (format "<li style='list-style:none;float:left'><img src='%s' width='100px' /><span>%d</span></li>" img-url index)))


(defn list-of-images [resource selected]
  (let [map-of-img-urls (make-map (get-list-of-img-urls resource))]
    (map #(list-item % map-of-img-urls) (seq selected))))


(defn web-page [resource selected]
  (format "<html><body><ul style='list-style:none'>%s</ul></body></html>"  (string/join (list-of-images resource selected))))

(defn replace-home-dir [path]
  (string/replace path "~" (System/getProperty "user.home")))

(defn expand-path [path]
  (-> (io/file (replace-home-dir path)) .getAbsolutePath))

(def ^:dynamic *gallery-url* "https://www.dropbox.com/sh/ok8x0rvwu0i3eox/hu1HVNed_w#/")

(defn create-page 
  "e.g (create-page (web-page (fetch-url *gallery-url*) jim-and-romina) \"selected-images.html\")"
  [content filename] 
  (let [file (io/file (expand-path "~/Desktop") filename)]    
    (spit file content)
    (println "Completed download and page generation.")))
