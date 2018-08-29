(ns cljs-kata.transaction-repository-tests
  (:require
    [cljs.test :refer-macros [deftest testing is are use-fixtures]]
    [cljs-kata.transaction-repository :refer [add-deposit add-withdraw all-transactions today-as-string]]
    ))

(def expected-invocations (atom []))
(def account (atom {}))
(def TODAY "12/05/2015")


(use-fixtures :each
  {:before (fn [] (reset! expected-invocations []))})




(deftest create-and-store-a-deposit-transaction-test
  (testing  "create-and-store-a-deposit-transaction"
    (with-redefs [today-as-string (fn [_] TODAY)]
      ;;WHEN performing a deposit of 100
      (let [transactions (-> @account
                             (add-deposit 100)
                             (all-transactions )
                             )]
        ;;ASSERT
        (is (= 1 (count transactions)))
        (is (= [{:date "12/05/2015"
                :amount 100}]
               transactions))
        ))))



(deftest create-and-store-a-withdraw-transaction-test
  (testing  "create-and-store-a-withdraw-transaction"
    (with-redefs [today-as-string (fn [_] TODAY)]
      ;;WHEN performing a withdraw of 100
      (let [transactions (-> @account
                             (add-withdraw 100)
                             (all-transactions )
                             )]
        ;;ASSERT
        (is (= 1 (count transactions)))
        (is (= [{:date "12/05/2015"
                :amount -100}]
               transactions))
        ))))
