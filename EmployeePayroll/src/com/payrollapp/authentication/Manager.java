package com.payrollapp.authentication;

public class Manager extends User {
    public Manager(String username, String password) {
        super(username, password, "MANAGER");
    }

    @Override
    public boolean authenticate(String username, String password) {
        return this.username.equals(username)
                && this.passwordHash.equals(PasswordUtil.hash(password));
    }
}
