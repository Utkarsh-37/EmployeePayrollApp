package com.payrollapp.notifications;

/*
 * Represents a simple observer that receives notifications.
 */

public class EmployeeObserver {

    private String username;

    public EmployeeObserver(String username) {
        this.username = username;
    }

    public void notify(String message) {
        System.out.println("\n[NOTIFICATION for " + username + "]");
        System.out.println(message);
    }
}
