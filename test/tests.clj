(ns core2)
(use 'clojure.test)

(with-test
  ; test(s) are packaged with the respective function it tests(they test) 
  ; so as to be able to select them simply by the hook in the meta-data 
  ; of the function
  (defn ^:dep1-hook ret0 []
    0)
  (deftest dep1-dep-test
    "sample failing test whose testing function has `:dep1-hook` 
	set to true in its meta-data"
    (prn "dep1 setup goes here")
    (is (= 1 (ret0))); fails
    (prn "dep1 teardown goes here")))

(deftest ^:dep2-hook dep2-dep-test
  "sample failing test whose meta-data has `:dep2-hook` set to true"
  (is (= 1 0)))

(deftest indep-test
  (is (= 1 0)))

;(run-tests) 
  ; executes them all (unit and integration tests)