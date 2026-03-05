/*
 * =============================================================
 * USE CASE 4: PAYSLIP PRINT / DOWNLOAD
 * =============================================================
 *
 * Goal of this Use Case:
 * - Protect existing data from accidental modification
 * - Learn how objects can be safely copied
 * - Understand how object equality works
 *
 * New ideas introduced in UC4:
 * - Immutability
 * - Cloning objects
 * - equals() and hashCode()
 * - Simple file persistence
 *
 * This use case builds on:
 * - UC3: Payslip generation
*/
package com.payrollapp;

import com.payrollapp.registration.*;
import com.payrollapp.payslipdownload.*;
import com.payrollapp.payroll.*;
import com.payrollapp.authentication.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Employee emp = null;
        Payslip payslip = null;   // must be accessible to UC4

        /*
         * =============================================================
         * USE CASE 1: EMPLOYEE REGISTRATION
         * =============================================================
         */

        System.out.println("=== USE CASE 1: EMPLOYEE REGISTRATION ===");

        try {

            System.out.print("Enter Employee ID (EMP-XXXX): ");
            String empId = sc.nextLine();
            Validator.validateEmpId(empId);

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            Validator.validateEmail(email);

            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();
            Validator.validatePhone(phone);

            System.out.print("Create Username: ");
            String username = sc.nextLine();

            System.out.print("Create Password: ");
            String password = sc.nextLine();

            UserAccount ua = new UserAccount(username, password);

            emp = new Employee(empId, name, email, phone, ua);

            emp.persist();

            System.out.println("\nEmployee Registered Successfully!\n");
            System.out.println(emp);

        } catch (ValidationException e) {
            System.out.println("\nValidation Failed: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("\nError saving employee data!");
            return;
        }

        /*
         * =============================================================
         * USE CASE 2: EMPLOYEE AUTHENTICATION & LOGIN
         * =============================================================
         */

        System.out.println("\n=== USE CASE 2: EMPLOYEE AUTHENTICATION & LOGIN ===");

        AuthenticationService auth = new AuthenticationService();
        Session session = auth.login();

        if (session == null) {
            System.out.println("Login failed. Cannot continue.");
            return;
        }

        System.out.println("\n" + session);

        if (session.isExpired()) {
            System.out.println("Session expired. Please login again.");
            return;
        }

        System.out.println("Session active and valid.");

        /*
         * =============================================================
         * USE CASE 3: PAYSLIP GENERATION
         * =============================================================
         */

        System.out.println("\n=== USE CASE 3: PAYSLIP GENERATION ===");

        try {

            System.out.print("Enter Month: ");
            String month = sc.nextLine();

            System.out.print("Enter Base Salary: ");
            double base = sc.nextDouble();

            System.out.print("Enter HRA: ");
            double hra = sc.nextDouble();

            System.out.print("Enter Allowances: ");
            double allowances = sc.nextDouble();

            sc.nextLine(); // clear buffer

            PayrollService service = new PayrollService();

            payslip = service.generatePayslip(emp, base, hra, allowances, month);

            System.out.println(payslip);

        } catch (Exception e) {
            System.out.println("Error generating payslip");
        }

        /*
         * =============================================================
         * USE CASE 4: PAYSLIP PRINT / DOWNLOAD
         * =============================================================
         */

        System.out.println("\n=== USE CASE 4: PAYSLIP PRINT / DOWNLOAD ===");

        if (payslip == null) {
            System.out.println("No payslip available to download.");
            sc.close();
            return;
        }

        DownloadToken token = new DownloadToken();

        if (!token.isExpired()) {

            try {

                Payslip copy = (Payslip) payslip.clone();

                FileService fileService = new FileService();

                String filename = fileService.savePayslipAsText(copy);

                System.out.println("Payslip downloaded successfully.");
                System.out.println("Saved as file: " + filename);

            } catch (Exception e) {
                System.out.println("Error downloading payslip.");
            }

        } else {
            System.out.println("Download token expired.");
        }

        sc.close();
    }
}