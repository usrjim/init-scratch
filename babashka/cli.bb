#!/usr/bin/env bb

(comment
  (require '[babashka.deps :as deps])
  (deps/add-deps '{:deps {medley/medley {:mvn/version "1.3.0"}}})

  ;; bb nrepl-server 1667

  (require '[babashka.process :as bp])
  (bp/shell "ls -l")

  ,)

(require '[babashka.cli :as cli])

(defn hello [opt]
  (println "hello" opt))

(defn ping [_]
  (println "pong"))

(def cli-opts
  {:entry {:alias   :e
           :desc    "Your dreams."
           :require true}})

(defn help [_]
  (println
   (str "ping\nhi\n"
    (cli/format-opts {:spec cli-opts}))))

(def table
  [{:cmds ["hi"] :fn #(hello (:opts %)) :spec cli-opts}
   {:cmds ["ping"] :fn ping}
   {:cmds [] :fn help}])

(cli/dispatch table *command-line-args*)


