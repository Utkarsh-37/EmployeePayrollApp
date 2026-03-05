/*
 * Session represents a logged-in user state. 
*/
package com.payrollapp.authentication;

public class Session {
    private final String username;
    private final long loginTime;
    private final long timeoutMillis;

    public Session(String username) {
        this.username = username;
        this.loginTime = System.currentTimeMillis();
        this.timeoutMillis = 5 * 60 * 1000; 
    }

    public boolean isExpired() {
        return (System.currentTimeMillis() - loginTime) > timeoutMillis;
    }

    @Override
    public String toString() {
        return "Session active for user: " + username;
    }
}