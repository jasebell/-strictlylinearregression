(ns strictlylinearregression.core
  (:require [clojure.pprint :as pp]))

;; Round a double to the given precision (number of significant digits)
(defn round2 [precision d]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/round (* d factor)) factor)))

;; vectors of scores, [craig, len, bruno, darcey's actual score]
(def wk14-scores [[6 8 7 8]
                  [5 7 6 6]
                  [4 7 7 6]
                  [7 8 8 7]
                  [8 8 9 8]
                  [2 6 4 6]
                  [9 9 9 9]
                  [8 8 8 8]
                  [8 9 9 9]
                  [8 8 9 7]])

(defn predict-score-from-craig [x-score]
  (+ 3.031 (* 0.6769 x-score)))

(defn predict-score-from-all [x-score]
  (- (* 0.2855 x-score) 1.2991))

(defn predict-from-craig [scores]
  (map (fn [score]
         (let [craig (first score)
               expected (last score)
               predicted (round2 0 (predict-score-from-craig (first score)))]
           (println "Craig:     " craig
                    "Predicted: " predicted
                    "Actual:    " expected
                    "Correct:   " (if (= (int predicted) expected)
                                    true
                                    false)))) scores))

(defn predict-from-judges [scores]
  (map (fn [score]
         (let [judges (reduce + (take 3 score))
               expected (last score)
               predicted (round2 0 (predict-score-from-all judges))]
           (println "Judges:     " judges
                    "Predicted: " predicted
                    "Actual:    " expected
                    "Correct:   " (if (= (int predicted) expected)
                                    true
                                    false)))) scores))
