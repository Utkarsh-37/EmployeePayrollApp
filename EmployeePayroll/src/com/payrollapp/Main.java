/*
 * =============================================================
 * USE CASE 2: EMPLOYEE AUTHENTICATION & LOGIN
 * =============================================================
 *
 * Goal of this Use Case:
 * - Introduce inheritance and polymorphism
 * - Show how different user types share common behavior
 * - Demonstrate a simple authentication flow
 *
 * New ideas introduced here:
 * - Abstract class
 * - Method overriding
 * - Runtime decision-making
 *
 * This use case builds directly on UC1.
 *
 * @author Developer
 * @version 2.0

 */

package com.payrollapp;
import com.payrollapp.registration.*;
import com.payrollapp.authentication.*;

import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
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

			Employee emp = new Employee(empId, name, email, phone, ua);

			emp.persist(); // save to file

			System.out.println("\nEmployee Registered Successfully!\n");
			System.out.println(emp);

		} catch (ValidationException e) {
			System.out.println("\nValidation Failed: " + e.getMessage());
			return;
		} catch (IOException e) {
			System.out.println("\nError saving employee data!");
			return;
		}


		System.out.println("=== USE CASE 2: EMPLOYEE AUTHENTICATION & LOGIN ===\n");

		AuthenticationService auth = new AuthenticationService();
		Session session = auth.login();

		if (session != null) {
			System.out.println("\n" + session);
			if (!session.isExpired()) {
				System.out.println("Session active and valid.");
			} else {
				System.out.println("Session expired. Please login again.");
			}
		}


	}

}
