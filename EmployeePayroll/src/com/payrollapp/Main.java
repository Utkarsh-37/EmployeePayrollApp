/*
 * ================================================================
 * USE CASE 1: EMPLOYEE REGISTRATION
 * ================================================================
 *
 * Goal of this Use Case:
 * - Understand how multiple classes work together
 * - Learn how objects are created and used
 * - See how a real-world problem is broken into small parts
 *
 * At this stage, focus on:
 * - What each class represents
 * - How main() coordinates the flow
 * 

 * --------------------- Main Class ---------------------
 *
 * Entry point of Use Case 1.
 *
 * Execution Flow:
 * 1. Take input from user
 * 2. Validate input
 * 3. Create objects
 * 4. Persist data
 * 5. Display confirmation
 *
 * @author Developer
 * @version 1.0

 */

package com.payrollapp;
import com.payrollapp.registration.*;

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
		} catch (IOException e) {
			System.out.println("\nError saving employee data!");
		}

	}

}
