package com.payrollapp.payroll;

/*
 * Represents salary structure.
 * Holds salary components used for payslip calculation.
 */
public class SalaryComponents {

    public double baseSalary;
    public double hra;
    public double allowances;

    public double pf;
    public double tax;
    public double netPay;

    public SalaryComponents(double baseSalary, double hra, double allowances) {
        this.baseSalary = baseSalary;
        this.hra = hra;
        this.allowances = allowances;
    }
}