package com.payrollapp.validation;

public class EmailValidationException extends ValidationException{
    public EmailValidationException(String message){
        super(message);
    }
}
