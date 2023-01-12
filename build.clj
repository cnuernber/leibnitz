(ns build
  (:require [clojure.tools.build.api :as b]
            [clojure.edn :as edn])
  (:refer-clojure :exclude [compile]))

(def deps-data (edn/read-string (slurp "deps.edn")))
(def lib (symbol "leibniz"))
(def version "1.0")
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def jar-file (format "target/%s.jar" (name lib)))
(def uber-file (format "target/uber-%s.jar" (name lib)))

(defn clean [_]
  (b/delete {:path "target"}))

(defn compile [_]
  (b/javac {:src-dirs ["java"]
            :class-dir class-dir
            :basis basis
            :javac-opts ["-Xlint:unchecked"
                         "--add-modules" "jdk.incubator.vector"]}))


(defn uberjar [arg]
  (compile arg)
  (let [basis (b/create-basis {:project "deps.edn" :aliases [:jdk-17]})]
    (b/compile-clj {:basis basis
                    :src-dirs ["src"]
                    :class-dir class-dir
                    :java-opts ["--add-modules" "jdk.incubator.vector"]})
    (b/uber {:class-dir class-dir
             :uber-file uber-file
             :basis basis
             :main 'leibniz.main})))
