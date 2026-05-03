(ns usrj
  (:require
   [scicloj.kindly.v4.kind :as kind]
   [tablecloth.api :as tc]
   [scicloj.tableplot.v1.plotly :as plotly]
   [clojure.java.io :as io])
  (:import [javax.imageio ImageIO]
           [java.net URL]))

(comment

  ;; export to :base-target-path
  (clay/make! {:source-path "notebooks/usrj.clj"
               :format [:quarto :html]})

  ,)
