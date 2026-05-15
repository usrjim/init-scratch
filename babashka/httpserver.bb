#!/usr/bin/env bb

;; (require '[clojure.repl.deps :as deps])
;; (deps/add-libs '{org.babashka/http-server {:mvn/version "0.1.14"}})

(require '[org.httpkit.server :as server]
         '[cheshire.core :as json]
         '[clojure.string :as str])

;; Server configuration
(def port (or (some-> (System/getenv "PORT") Integer/parseInt) 3000))
(def static-dir (or (System/getenv "STATIC_DIR") "public"))
(def host "localhost")

;; Helper functions
(defn parse-query-params [query-string]
  "Parse query parameters from a query string into a map"
  (if (empty? query-string)
    {}
    (->> (str/split query-string #"&")
         (map #(str/split % #"="))
         (map #(vector (first %) (second %)))
         (into {}))))

(defn json-response [data & [status]]
  "Create a JSON response"
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defn html-response [html & [status]]
  "Create an HTML response"
  {:status (or status 200)
   :headers {"Content-Type" "text/html"}
   :body html})

(defn text-response [text & [status]]
  "Create a plain text response"
  {:status (or status 200)
   :headers {"Content-Type" "text/plain"}
   :body text})

(defn not-found []
  "Standard 404 response"
  {:status 404
   :headers {"Content-Type" "text/plain"}
   :body "Not Found"})

;; Route handlers
(defn home-handler [req]
  "Handler for the home route"
  (html-response
    "<html>
      <head><title>Babashka HTTP Server</title></head>
      <body>
        <h1>Welcome to Babashka HTTP Server</h1>
        <p>Available routes:</p>
        <ul>
          <li><a href='/'>/</a> - Home page</li>
          <li><a href='/api/hello?name=World'>/api/hello?name=World</a> - Hello API</li>
          <li><a href='/api/echo?message=test'>/api/echo?message=test</a> - Echo API</li>
          <li>/static/* - Static files</li>
        </ul>
      </body>
    </html>"))

(defn hello-handler [req]
  "Handler for the hello API route"
  (let [params (parse-query-params (get-in req [:query-string]))
        name (get params "name" "Anonymous")]
    (json-response {:message (str "Hello, " name "!")})))

(defn echo-handler [req]
  "Handler for the echo API route"
  (let [params (parse-query-params (get-in req [:query-string]))
        message (get params "message" "")]
    (json-response {:echo message
                    :timestamp (java.time.Instant/now)})))

;; Main router function
(defn route-request [req]
  "Main router function that dispatches requests to appropriate handlers"
  (let [path (:uri req)]
    (cond
      (= path "/") (home-handler req)
      (= path "/api/hello") (hello-handler req)
      (= path "/api/echo") (echo-handler req)
      ;; Serve static files
      (str/starts-with? path "/static/")
      (let [file-path (subs path 8) ; Remove "/static/" prefix
            full-path (str static-dir "/" file-path)]
        (if (.exists (java.io.File. full-path))
          {:status 200
           :body (java.io.File. full-path)}
          (not-found)))
      :else (not-found))))

;; Start the server
(defn start-server []
  "Start the HTTP server"
  (println (str "Starting server on http://" host ":" port))
  (println (str "Serving static files from: " static-dir))
  (println "Press Ctrl+C to stop the server")
  (server/run-server route-request
    {:host host
     :port port
     :join? true}))

;; Create public directory if it doesn't exist
(defn create-static-dir []
  "Create the static files directory if it doesn't exist"
  (when-not (.exists (java.io.File. static-dir))
    (.mkdirs (java.io.File. static-dir))
    (spit (str static-dir "/index.html")
          "<html><body><h1>Hello from static files!</h1></body></html>")))

;; Main entry point
(defn -main [& args]
  "Main entry point"
  (create-static-dir)
  (start-server))

;; Run main function when script is executed directly
(when (= *file* (System/getProperty "babashka.file"))
  (-main))
