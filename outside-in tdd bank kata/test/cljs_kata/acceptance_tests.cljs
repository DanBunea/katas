(ns cljs-kata.acceptance-tests
  (:require
    [cljs.test :refer-macros [deftest testing is are use-fixtures]]
    [cljs-kata.console :refer [console-print]]
    [cljs-kata.account :refer [deposit withdraw print-statement]]
    [cljs-kata.transaction-repository :refer [today-as-string]]
    )
  )

(def dates (atom ["01/04/2014" "02/04/2014" "10/04/2014"]))


(deftest print-statement-containing-all-transactions-test
  (testing  "print-statement-containing-all-transactions-test"
    (let [expected-invocations (atom [])]
      (with-redefs [console-print #(do
                                     (swap! expected-invocations conj %)
                                     %)
                    today-as-string (fn [_]
                                      (let [result (first @dates)]
                                        (reset! dates (rest @dates))
                                        result)
                                      )]

        ;;GIVEN an account
        (let [account {}]

          ;;WHEN performing operations on it
          (-> account
                       (deposit  1000)
                       (withdraw  100)
                       (deposit  500)
                       (print-statement)
                       )
          ;;ASSERT it should print on the console the following lines
          (is (= [
                   "DATE | AMOUNT | BALANCE"
                   "10/04/2014 | 500.00 | 1400.00"
                   "02/04/2014 | -100.00 | 900.00"
                   "01/04/2014 | 1000.00 | 1000.00"
                   ]
                 @expected-invocations))
          )))))
