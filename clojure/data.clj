(require '[clojure.java.io :as io])
(require '[clojure.string :as str])
(require '[clojure.edn :as edn])

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

  ;;; match, https://github.com/clojure/core.match
  (add-libs '{org.clojure/core.match {:mvn/version "1.0.1"}})
  (require '[clojure.core.match :refer [match]])

  ;;; async, https://github.com/clojure/core.async/blob/master/examples/walkthrough.clj
  (add-libs '{org.clojure/core.async {:mvn/version "1.6.673"}})
  (require '[clojure.core.async :as async :refer :all])

  ;;; chime, https://github.com/jarohen/chime 
  (add-libs '{jarohen/chime {:mvn/version "0.3.3"}})
  (require '[chime.core :as chime])

  ;;; meander, https://github.com/noprompt/meander
  (add-libs '{meander/epsilon {:mvn/version "0.0.650"}})
  (require '[meander.epsilon :as m])

  ;;; specter, https://github.com/redplanetlabs/specter
  (add-libs '{com.rpl/specter {:mvn/version "1.1.4"}})
  (require '[com.rpl.specter :refer :all])
 ,)

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

