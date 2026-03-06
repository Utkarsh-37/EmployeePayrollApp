package com.payrollapp.validation;

public class EmployeeIdValidationException extends ValidationException{
    public EmployeeIdValidationException(String message){
        super(message);
    }
}