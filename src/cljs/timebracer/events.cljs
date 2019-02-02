(ns timebracer.events
  (:require
   [re-frame.core :as rf]
   [cljs-time.core :as ct]
   [timebracer.db :as db]))

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/app-db))

(rf/reg-event-db
 ::start-timer
 (fn [db _]
   (assoc db :start-time (ct/now))))

(rf/reg-event-db
 ::stop-timer
 (fn [db _]
   (assoc db :end-time (ct/now))))

(rf/reg-event-db
  ::calc-duration
  (fn [db _]
    (assoc db :duration
     (ct/in-seconds
       (ct/interval
        (:start-time db)
        (:end-time db)))))) 

(rf/reg-event-db
  ::change-button-state
  (fn [db _]
    (if (= "Start timer" (:button-state db))
      (assoc db :button-state "Stop timer")
      (assoc db :button-state "Start timer"))))

(rf/reg-event-db
  ::start-stop-timer
  (fn [db _]
    (if (= "Start timer" (:button-state db))
     (do 
      (rf/dispatch [::start-timer])
      (rf/dispatch [::change-button-state]))
     (do
      (rf/dispatch [::stop-timer])
      (rf/dispatch [::change-button-state])))))