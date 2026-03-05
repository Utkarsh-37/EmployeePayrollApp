package com.payrollapp.payroll;

import com.payrollapp.registration.Employee;

/*
 * Payslip represents a monthly salary statement.
 * Aggregates Employee and SalaryComponents.
 */
public class Payslip  implements Cloneable{

    private Employee employee;          // Aggregation
    private SalaryComponents components; // Composition
    private String month;

    public Payslip(Employee employee, SalaryComponents components, String month) {
        this.employee = employee;
        this.components = components;
        this.month = month;
    }

    @Override
    public String toString() {

        return "\n========= PAYSLIP =========\n" +
                "Month : " + month + "\n\n" +

                "Employee Details\n" +
                "----------------\n" +
                employee + "\n\n" +

                "Earnings\n" +
                "-------\n" +
                "Base Salary : " + components.baseSalary + "\n" +
                "HRA         : " + components.hra + "\n" +
                "Allowances  : " + components.allowances + "\n\n" +

                "Deductions\n" +
                "----------\n" +
                "PF  : " + components.pf + "\n" +
                "Tax : " + components.tax + "\n\n" +

                "Net Pay : " + components.netPay + "\n" +
                "===========================\n";
    }
    
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}