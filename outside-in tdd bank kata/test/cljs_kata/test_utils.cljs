(ns cljs-kata.test-utils
  (:require
   [cljs.test :refer-macros [deftest testing is are]]

   )
  )



(defn ==? [x y]
  (is (= x y)))
