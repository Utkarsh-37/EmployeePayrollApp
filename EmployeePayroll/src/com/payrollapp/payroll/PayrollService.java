package com.payrollapp.payroll;

import com.payrollapp.registration.Employee;

/*
 * PayrollService performs salary calculations.
 * Separates business logic from data classes.
 */
public class PayrollService {

    public Payslip generatePayslip(Employee employee,
                                   double base,
                                   double hra,
                                   double allowances,
                                   String month) {

        SalaryComponents sc = new SalaryComponents(base, hra, allowances);

        // Gross Salary
        double gross = base + hra + allowances;

        // Deductions
        sc.pf = base * 0.12;   // 12% PF
        sc.tax = gross * 0.10; // 10% tax

        // Net Pay
        sc.netPay = gross - (sc.pf + sc.tax);

        return new Payslip(employee, sc, month);
    }
}