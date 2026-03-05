/*
 * ------------------ Employee Class ------------------
 *
 * This class represents an Employee entity.
 *
 * Core OOP concept introduced here:
 * - Encapsulation
 *
 * Data is kept private and controlled through the class.
*/
package com.payrollapp.registration;

import java.io.FileWriter;
import java.io.IOException;

public class Employee {
    private String empId;
    private String name;
    private String email;
    private String phone;

    private UserAccount account; // Composition

    public Employee(String empId, String name, String email, String phone, UserAccount account) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Employee ID : " + empId +
               "\nName        : " + name +
               "\nEmail       : " + email +
               "\nPhone       : " + phone +
               "\nUsername    : " + account.getUsername();
    }

    // Save employee details in a simple text file
    public void persist() throws IOException {
        FileWriter fw = new FileWriter("employee_data.txt", true);
        fw.write("\n----------------------\n");
        fw.write(this.toString() + "\n");
        fw.write("----------------------\n");
        fw.close();
    }
}
