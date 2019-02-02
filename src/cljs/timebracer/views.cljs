(ns timebracer.views
  (:require
   [re-frame.core :as re-frame]
   [timebracer.subs :as subs]
   [timebracer.events :as events]))
   
(defn display-duration
  []
  [:div.display-duration  
    (let [duration @(re-frame/subscribe [::subs/duration])]  
      [:h1 duration])])

(defn start-button
  []
  [:div.start-button
     {:class "button"
      :on-click  #(re-frame/dispatch [::events/start-timer])}
    [:button "Start timer"]]) 

(defn stop-button
  []
  [:div.stop-button
     {:class "button"
      :on-click #(re-frame/dispatch [::events/stop-timer
                                     ::events/calc-duration])} 
    [:button "Stop timer"]])

(defn calc-button
  []
  [:div.calc-button
     {:class "button"
      :on-click #(re-frame/dispatch [::events/calc-duration])}
    [:button "duration"]]) 

(defn start-stop-button
  []
  [:div.start-stop-button
   {:class "button"
    :on-click #(re-frame/dispatch [::events/start-stop-timer
                                   ::events/calc-duration])}                               
   [:button @(re-frame/subscribe [::subs/button-state])]])

(defn main-panel []
  [:div
    [:section#timebracer
     [display-duration]
     [:span#buttons
      [start-button]
      [stop-button]
      [calc-button]]]])
