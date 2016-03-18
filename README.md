# A demo on Clojure unit and integration testing

`clojure.test/use-fixtures` can only choose between running fixture fucntions for each test or once for all the tests. 

This project demonstrates ways of choosing tests, to run, by their dependency. That external component must be setup 
for the tests to run upon and toredown later with fixtures to restore the original state.

## Configuring Leiningen for unit and integration testing 
1.   `cd` to `sample-clojure-testing`, and run:

	 `$ lein test [optional - :dep1/:dep2/:all]`

	All tests defined in the code files of `src/` (as set by the option `:test-paths` in the project 
	configuration file `project.clj`) are selected, by the dependenc(ies)y hooked in their metadata, to run.

	 `$ lein test`

	This simply runs unit tests i.e. those that are independent of(doesn't involve operating on) 
	external components meaning those that don't have any dependency hook in their metadata.


2.   The tests may be moved to a seperate folder (Here, from `src/` to `test/`). Hence, the option :test-paths 
	is set to `["test/"]` in project.clj.

	 `$ lein test some-ns [optional - :dep1/:dep2/:all]`

	This runs tests in a namespace `some-ns`

Alternatively, evaluate the namespace `core1` to see a replication of the above two via code.

## Contact

Pragathi (pragathi.prabaharan@gmail.com)

