(require '[babashka.http-client.websocket :as ws])

(defn send [data]
  (let [conn (ws/websocket {:uri "ws://localhost:8008/ws"})]
    (ws/send! conn (json/generate-string data))
    (ws/close! conn)))

(defn tap-fn [v]
  (send v))

(comment
  (add-tap tap-fn)

  (remove-tap tap-fn)

  ,)

