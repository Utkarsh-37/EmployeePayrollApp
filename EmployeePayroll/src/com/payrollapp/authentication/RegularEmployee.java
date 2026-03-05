package com.payrollapp.authentication;

public class RegularEmployee extends User {
    public RegularEmployee(String username, String password) {
        super(username, password, "EMPLOYEE");
    }

    @Override
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.passwordHash.equals(PasswordUtil.hash(password));
    }
}