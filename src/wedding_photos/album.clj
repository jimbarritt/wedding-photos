(ns wedding-photos.album
  (:require [net.cgrand.enlive-html :as html]
            [clojure.set :as set]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [wedding-photos.image-handling :as img]))


(defn html-album-page-image [id]
  (format "<li style='float:left;list-style:none;margin-right:20px'><img style='clear:both;float:none' src='https://photos-2.dropbox.com/t/0/AACNEQfHJa9S_x56ifQHoifOxlwe9QQoBGIzAy6qDang3Q/12/35623490/jpeg/32x32/3/1365267600/0/2/jr001.jpg/-6Q8N7JsYumGhHHN-CRNeDK0LuML_R9s1DQypLyuRnQ?size=1024x768' width='100px' /><p style='margin:0;padding:0;text-align:center'>Id: %s</p></li>" id))

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

(spit (img/expand-path "~/Desktop/test-album.html") (web-page "" [2]))

(def photos-on-each-page [54 56 59 [63 97] 98 106 [110 113] [79 112] 114 118 130 142 166 198 206 244 363 375 [225 233 234 237] [238 271 311] [253 254 276 300] [366 367 368 373] [357 358 360] [214 215 217 220] [156 157 159 189] [125 131 132 140] [127 151]])

