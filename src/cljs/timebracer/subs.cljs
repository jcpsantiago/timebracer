(ns timebracer.subs
  (:require
   [re-frame.core :as rf]
   [cljs-time.core :as ct]))

;; Layer 1 -- getting the data

(rf/reg-sub
 ::start-time
 (fn [db _]
  (:start-time db)))

(rf/reg-sub
 ::end-time
 (fn [db _]
  (:end-time db)))

(rf/reg-sub
 ::duration
 (fn [db _]
  (:duration db)))

(rf/reg-sub
  ::button-state
  (fn [db _]
    (:button-state db)))  

;; layer 2 -- doing stuff

(rf/reg-sub
  ::ongoing-duration
  :<- [::start-time] 
  (fn [start-time _]
    (if (= start-time 0)
      0  
      (ct/in-seconds (ct/interval start-time (ct/now))))))    
