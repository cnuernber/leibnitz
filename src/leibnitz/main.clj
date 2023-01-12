(ns leibnitz.main
  (:require [criterium.core :as crit])
  (:import [leibniz JL])
  (:gen-class))


(defn -main [& args]
  (println "Java original")
  (crit/quick-bench (JL/PiOrig 100000000))


  (println "Java vectorized")
  (crit/quick-bench (JL/PiVec 100000000)))
