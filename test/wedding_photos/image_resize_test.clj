(ns wedding-photos.image-resize-test
  (:use [clojure.java.io :only [as-file]]
        midje.sweet)
  (:import [javax.imageio.ImageIO] 
           [java.awt.image.BufferedImage]
           [java.io.ByteArrayOutputStream]))

