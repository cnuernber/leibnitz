(ns leibniz.main
  (:import [leibniz JL])
  (:gen-class))


(defn -main [& args]
  (println "Java original")
  (dotimes [idx 10] (JL/PiOrig 100000000))
  (time (JL/PiOrig 100000000))


  (println "Java vectorized")
  (dotimes [idx 10] (JL/PiVec 100000000))
  (time (JL/PiVec 100000000))

  (println "done"))
