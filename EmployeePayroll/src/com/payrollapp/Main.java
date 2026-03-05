/*
 * =============================================================
 * USE CASE 6: INPUT VALIDATION
 * =============================================================
 *
 * Goal of this Use Case:
 * - Validate user input before it enters the system
 * - Centralize validation logic
 * - Learn how exceptions are used to handle invalid data
 *
 * New ideas introduced in UC6:
 * - Exception hierarchy
 * - Custom checked exceptions
 * - Fail-fast validation
 *
 * This use case brings together lessons from:
 * - UC1: Input handling
 * - UC2: Controlled program flow
 * - UC3–UC5: Clean separation of responsibilities
*/
package com.payrollapp;

import com.payrollapp.registration.*;
import com.payrollapp.payslipdownload.*;
import com.payrollapp.payroll.*;
import com.payrollapp.authentication.*;
import com.payrollapp.notifications.*;
import com.payrollapp.audit.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Employee emp = null;
        Payslip payslip = null;

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

            // UC6 LOG
            AuditService.log("Employee registered: " + empId);

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

        // UC6 LOG
        AuditService.log("User logged in: " + session);

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

            sc.nextLine();

            PayrollService service = new PayrollService();

            payslip = service.generatePayslip(emp, base, hra, allowances, month);

            System.out.println(payslip);

            // UC6 LOG
            AuditService.log("Payslip generated for employee.");

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

                // UC6 LOG
                AuditService.log("Payslip downloaded: " + filename);

            } catch (Exception e) {
                System.out.println("Error downloading payslip.");
            }

        } else {
            System.out.println("Download token expired.");
        }

        /*
         * =============================================================
         * USE CASE 5: EMPLOYEE NOTIFICATION
         * =============================================================
         */

        System.out.println("\n=== USE CASE 5: EMPLOYEE NOTIFICATION ===");

        NotificationService notificationService = new NotificationService();

        EmployeeObserver observer = new EmployeeObserver(emp.toString());

        notificationService.addObserver(observer);

        notificationService.notifyObservers(
                "Your payslip for this month has been generated and is ready for download."
        );

        // UC6 LOG
        AuditService.log("Notification sent to employee.");

        sc.close();
    }
}