(ns cljs-kata.statement-printer-tests
  (:require
    [cljs.test :refer-macros [deftest testing is are use-fixtures]]
    [cljs-kata.statement-printer :refer [print-full-statement]]
    [cljs-kata.console :refer [console-print]]
    ))

(def expected-invocations (atom []))


(use-fixtures :each
  {:before (fn [] (reset! expected-invocations []))})



(deftest always-print-the-header-test
  (testing  "always-print-the-header"
    (with-redefs [console-print #(swap! expected-invocations conj %)]
      ;;WHEN printing
      (print-full-statement [])
      ;;ASSERT the header is displayed
      (is (= ["DATE | AMOUNT | BALANCE"] @expected-invocations))
      )))



(deftest print-transactions-in-reverse-chronological-order-test
  (testing  "print-transactions-in-reverse-chronological-order"
    (with-redefs [console-print #(do
                                   (swap! expected-invocations conj %)
                                   %)
                                   ]
      ;;GIVEN a list of transactions
      (let [transactions  [
                            {:date "01/04/2014" :amount 1000}
                            {:date "02/04/2014" :amount -100}
                            {:date "10/04/2014" :amount 500}
                            ]]

        ;;WHEN printing
        (print-full-statement transactions)
        ;;ASSERT it should print on the console the following lines
        (is (= [
                 "DATE | AMOUNT | BALANCE"
                 "10/04/2014 | 500.00 | 1400.00"
                 "02/04/2014 | -100.00 | 900.00"
                 "01/04/2014 | 1000.00 | 1000.00"
                 ]
               @expected-invocations))
        ))))

