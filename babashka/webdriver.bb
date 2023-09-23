(require '[babashka.deps :as deps])
(deps/add-deps '{:deps {etaoin/etaoin {:mvn/version "1.0.40"}}})

(require '[taoensso.timbre :as timbre])
(timbre/set-level! :info)

(require '[etaoin.api :as e]
         '[etaoin.keys :as k]
         '[clojure.string :as str])

;; bb nrepl-server 1667

(def driver (e/chrome))

(e/go driver "https://en.wikipedia.org/")

(e/quit driver)

(comment
  (e/back driver)
  (e/forward driver)
  (e/refresh driver)
  (e/get-title driver)

  (e/js-execute driver "console.log('foo')")
  (e/get-logs driver)

,)
