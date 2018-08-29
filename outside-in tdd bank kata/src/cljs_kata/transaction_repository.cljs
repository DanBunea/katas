(ns cljs-kata.transaction-repository)




(defn today-as-string [account]
  (.toLocaleDateString (js/Date. ) "en-GB"))



(defn add-deposit [account amount]
  (update account :transactions #(conj % {
                                           :amount amount
                                           :date (today-as-string account)})))

(defn add-withdraw [account amount]
  (update account :transactions #(conj % {
                                           :amount (* -1 amount)
                                           :date (today-as-string account)})))


(defn all-transactions [account]
  (:transactions account)
  )

