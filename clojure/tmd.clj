;;
;; start repl: clojure -M:add-libs:repl/nrepl
;;

(require '[clojure.tools.deps.alpha.repl :refer [add-libs]])

;;; https://github.com/techascent/tech.ml.dataset
(add-libs '{techascent/tech.ml.dataset {:mvn/version "7.012"}})


(require 'tech.v3.dataset)

(->> (System/getProperties)
     (map (fn [[k v]] {:k k :v (apply str (take 40 (str v)))}))
     (tech.v3.dataset/->>dataset {:dataset-name "System Props"}))


