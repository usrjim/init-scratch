#!/usr/bin/env bb

(ns sqlite3-script
  (:require [babashka.pods :as pods]))

(pods/load-pod 'org.babashka/go-sqlite3 "0.2.3")
(require '[pod.babashka.go-sqlite3 :as sqlite])

;; bb nrepl-server 1667

(sqlite/query "sakila.db" ["select name from sqlite_master where type='table'"])
