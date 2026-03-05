/*
 * ------------------ UserAccount Class ------------------
 *
 * This class represents login-related information.
 *
 * Why this is a separate class:
 * - Employee details and login details are different concerns
 * - Keeps responsibilities small and clear
 *
 * This introduces the idea of COMPOSITION:
 * - An Employee HAS a UserAccount
*/
package com.payrollapp.registration;

public class UserAccount {
    private String username;
    private String password;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password; 
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
