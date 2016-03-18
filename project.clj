(defproject sample-clojure-testing "0.1.0-SNAPSHOT"
  :description "A demo on Clojure unit and integration testing"
  :url "https://github.com/Prog19/sample-clojure-testing"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.taoensso/timbre "3.4.0"]]
  :test-selectors {:dep1 :dep1-hook
                   :dep2 :dep2-hook
                   :default (fn [f] (not (or (:dep1-hook f) (:dep2-hook f)))) 
				     ; picks out test function(s) in the all of folder(s) 
					 ; as set by :test-paths that do not have either :dep1-hook 
					 ; or :dep2-hook enabled in its(their) meta-data 
                   :all (constantly true)
					 ; selects test functions on all conditions i.e. selects 
					 ; all functions
					}
  :source-paths ["src/"]
  :test-paths ["src/"] ; ["test/"]
  :main core1
  )
