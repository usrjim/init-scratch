#!/usr/bin/env bb

(comment
  (require '[babashka.deps :as deps])
  (deps/add-deps '{:deps {medley/medley {:mvn/version "1.3.0"}}})

  ;; bb nrepl-server 1667
  ,)
