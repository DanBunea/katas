(ns cljs-kata.account
  (:require
    [cljs-kata.transaction-repository :refer [add-deposit add-withdraw all-transactions]]
    [cljs-kata.statement-printer :refer [print-full-statement]])
  )




(defn deposit [account amount]
  (add-deposit account amount)
  )

(defn withdraw [account amount]
  (add-withdraw account amount))

(defn print-statement [account]
  (print-full-statement (:transactions account))
  )



