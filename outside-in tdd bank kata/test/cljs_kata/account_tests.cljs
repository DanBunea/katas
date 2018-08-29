(ns cljs-kata.account-tests
  (:require
    [cljs.test :refer-macros [deftest testing is are use-fixtures]]
    [cljs-kata.console :refer [console-print]]
    [cljs-kata.transaction-repository :refer [add-deposit add-withdraw all-transactions]]
    [cljs-kata.account :refer [deposit withdraw print-statement]]
    [cljs-kata.statement-printer :refer [print-full-statement]]
    ))

(def expected-invocations (atom []))
(def account (atom {}))


(use-fixtures :each
  {:before (fn [] (do
                    (reset! expected-invocations [])
                    ;;GIVEN an account
                    (reset! account {})
                    ))})



(deftest store-a-deposit-transaction-test
  (testing  "store-a-deposit-transaction"
    (with-redefs [add-deposit #(swap! expected-invocations conj [`add-deposit %2])]
      ;;WHEN performing a deposit
      (-> @account
          (deposit 100)
          )
      ;;ASSERT it should have invoked add-deposit
      (is (= [[`add-deposit 100]] @expected-invocations))
      )))


(deftest store-a-withdrawal-transaction-test
  (testing  "store-a-withdrawal-transaction"
    (with-redefs [add-withdraw #(swap! expected-invocations conj [`add-withdraw %2])]
      ;;WHEN performing a withdraw
      (-> @account
          (withdraw 100)
          )
      ;;ASSERT it should have invoked add-withdraw
      (is (= [[`add-withdraw 100]] @expected-invocations))
      )))


(deftest print-a-statement-test
  (testing  "print-a-statement"
    (with-redefs [all-transactions #([])
                  print-full-statement #(swap! expected-invocations conj `print-full-statement)]
      ;;WHEN printing the statement
      (-> @account
          (print-statement)
          )
      ;;ASSERT it should have invoked
      (is (= [`print-full-statement] @expected-invocations))
      )))

