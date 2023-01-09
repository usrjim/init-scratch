(require '[clojure.java.io :as io])
(require '[clojure.string :as str])
(require '[clojure.edn :as edn])

;;
;; docs
;;
(comment
  (require '[clojure.repl :as repl])
  (repl/dir str)
  (repl/doc str/split)
  (repl/apropos "reduce")
  (repl/find-doc "data structure")
  ,)

;;
;; add-libs
;;
(comment
  (require '[clojure.tools.deps.alpha.repl :refer [add-libs]])

  ;;; medley
  (add-libs '{dev.weavejester/medley {:mvn/version "1.5.0"}})
  (require '[medley.core :as m])

  ;;; csv, https://github.com/clojure/data.csv
  (add-libs '{org.clojure/data.csv {:mvn/version "1.0.1"}})
  (require '[clojure.data.csv :as csv])

  ;;; json, https://github.com/dakrone/cheshire
  (add-libs '{cheshire/cheshire {:mvn/version "5.11.0"}})
  (require '[cheshire.core :as json])

  ;;; fs, https://github.com/babashka/fs
  (add-libs '{babashka/fs {:mvn/version "0.2.12"}})
  (require '[babashka.fs :as fs])

  ;;; curl, https://github.com/babashka/babashka.curl
  (add-libs '{babashka/babashka.curl {:mvn/version "0.1.2"}})
  (require '[babashka.curl :as curl])
 ,)

