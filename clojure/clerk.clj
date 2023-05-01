(ns hello
  (:require [nextjournal.clerk :as clerk]))

(clerk/serve! {:browse? true})

(clerk/show! "notebooks/hello.clj")

(comment
  ;; examples
  ;; https://github.com/nextjournal/clerk/tree/main/notebooks

  (clerk/serve! {:watch-paths ["notebooks" "src"]})

  (clerk/build! {:paths ["notebooks/hello.clj"]})
 ,)

