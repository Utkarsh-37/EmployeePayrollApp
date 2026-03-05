/*
 * Abstract User Class
 * User represents a generic system user 
*/
package com.payrollapp.authentication;

public abstract class User {
    protected String username;
    protected String passwordHash;
    protected String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.passwordHash = PasswordUtil.hash(password); 
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }

    public abstract boolean authenticate(String username, String password);
}