package com.payrollapp.validation;

import java.util.regex.Pattern;
public class ValidationService {
    private static String sanitize(String input){
        return input.trim();
    }
    public static void validateEmail(String email) throws EmailValidationException{
        email = sanitize(email);
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(!Pattern.matches(regex,email)){
            throw new EmailValidationException("Invalid email format");
        }
    }
    public static void validatePhone(String phone) throws PhoneValidationException{
        phone = sanitize(phone);
        String regex = "^[6-9]\\d{9}$";
        if(!Pattern.matches(regex,phone)){
            throw new PhoneValidationException("Invalid phone number");
        }
    }
    public static void validatePassword(String password) throws PasswordValidationException{
        password = sanitize(password);
        if(password.length() < 6){
            throw new PasswordValidationException("Password must contain at least 6 characters");
        }
    }
    public static void validateEmployeeId(String empId) throws EmployeeIdValidationException{
        empId = sanitize(empId);
        String regex = "^EMP-[0-9]{4}$";
        if(!Pattern.matches(regex,empId)){
            throw new EmployeeIdValidationException("Employee ID must follow EMP-XXXX format");
        }
    }
}
