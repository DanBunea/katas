(ns cljs-kata.statement-printer
  (:require
    [cljs-kata.console :refer [console-print]]
    )
  )

(def HEADER "DATE | AMOUNT | BALANCE")


(defn transaction->statement [transaction balance]
  (str
    (:date transaction)
    " | "
    (:amount transaction)
    ".00 | "
    balance
    ".00"
    )
  )


(defn print-full-statement [transactions]
  (concat [(console-print HEADER)]
          (doall (->> transactions
                      (sort #(< (:date %1) (:date %2)))
                      (reduce
                        (fn [accumulator transaction]
                          (let [balance (+ (:balance accumulator) (:amount transaction))]
                            (-> accumulator
                                (assoc :balance balance)
                                (update :statements #(conj %
                                                           (transaction->statement
                                                             transaction
                                                             balance))))
                            ))
                        {
                          :balance 0
                          :statements []}
                        )
                      :statements
                      (reverse)
                      (map console-print)
                      ))))

