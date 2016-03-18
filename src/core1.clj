(ns core1
  (:require [clojure.test :refer :all]
            [core2 :refer [ret0]]
            [taoensso.timbre :refer [error]]))

(defn get-fn-by-hook [ns-sym inp-hook]
  "returns a vector of test function(s) in namespace `ns-sym`(a symbol) 
  that have `dep-hook`(a keyword) enabled in its(their) meta-data"
  (let [ns-fns (vals (ns-interns ns-sym))]
    (filter (fn[f] (inp-hook (meta f))) ns-fns)))

(deftest ^:dep1-hook dep1-test
  "sample failing test whose meta-data has `:dep1-hook` set to true"
  (is (= 1 (ret0))) 
  )

(deftest ^:dep2-hook dep2-test
  "sample failing test whose meta-data has `:dep2-hook` set to true"
  (is (= 1 (ret0)))
  )

(deftest unit-test
  "sample failing test whose meta-data has any of the dependency hooks 
   - `:dep1-hook` or `:dep2-hook`"
  (is (= 1 0)) 
  )

(defn wrap-dep1-fix []
  "runs sample setup and teardown fixtures before and 
  after the integration tests of depedancy `dep1` in `core1`"
  (try
    (prn "dep1 setup goes here")
    (test-vars (get-fn-by-hook 'core1 :dep1-hook))
    (catch Exception e (error (.getMessage e)))
    (finally (prn "dep1 tear down goes here"))))

(wrap-dep1-fix)

(defn get-fn-by-no-hook [ns-sym deps]
  "returns a vector of test function(s) in namespace `ns-sym`(a symbol) 
  that do not have any of `deps`(a vector of keywords) enabled
  in their meta-data - independant (or unit) test(s)"
  (->> (map (fn[dep] (into #{} (get-fn-by-hook ns-sym (complement dep)))) deps)
       (apply clojure.set/intersection)))

(test-vars (get-fn-by-no-hook 'core1 [:dep1-hook :dep2-hook]))
