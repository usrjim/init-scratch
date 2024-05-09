#!/usr/bin/env bb

(ns sqlite3-script
  (:require [babashka.pods :as pods]))

(pods/load-pod 'huahaiy/datalevin "0.9.5")
(require '[pod.huahaiy.datalevin :as d])

;; bb nrepl-server 1667

(def conn (d/get-conn "/tmp/mydb"))

(d/transact! conn [{:greeting "Hello world!"}])

(println (d/q '[:find ?g :where [_ :greeting ?g]] (d/db conn)))

(d/close conn)
