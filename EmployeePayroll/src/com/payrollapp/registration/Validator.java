/*
 * ------------------- Validator Class -------------------
 *
 * This class is responsible only for checking input correctness.
 *
 * Why we separate validation:
 * - Keeps main() clean and readable
 * - Avoids repeating validation logic
 *
 * Important idea:
 * - Validation logic does not belong to employee
 * - Validation happens before objects are created
 */
package com.payrollapp.registration;
import java.util.regex.*;

public class Validator {


	public static void validateEmail(String email) throws ValidationException {
		if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			throw new ValidationException("Invalid Email Format!");
		}
	}

	public static void validatePhone(String phone) throws ValidationException {
		if (!phone.matches("^[6-9][0-9]{9}$")) {
			throw new ValidationException("Invalid Phone Number! Must be 10 digits starting with 6-9.");
		}
	}

	public static void validateEmpId(String empId) throws ValidationException {
		if (!empId.matches("^EMP-[0-9]{4}$")) {
			throw new ValidationException("Invalid Employee ID format! Expected EMP-XXXX");
		}
	}


}
