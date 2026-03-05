package com.payrollapp.notifications;

import java.util.ArrayList;
import java.util.List;

/*
 * Manages notification subscribers.
 */

public class NotificationService {

    private List<EmployeeObserver> observers = new ArrayList<>();

    public void addObserver(EmployeeObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {

        for (EmployeeObserver obs : observers) {
            obs.notify(message);
        }
    }
}