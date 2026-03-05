/*
 * AuthenticationService handles login related operations. 
*/
package com.payrollapp.authentication;

import java.util.*;
import java.util.Scanner;
import java.nio.file.*;
import java.io.IOException;

public class AuthenticationService {
    private final Map<String, User> users = new HashMap<>();
    private final int maxAttempts = 3;

    public AuthenticationService() {

        loadUsersFromEmployeeFile();
    }

    private void loadUsersFromEmployeeFile() {
        Path file = Path.of("employee_data.txt");
        if (!Files.exists(file)) return;

        try {
            List<String> lines = Files.readAllLines(file);
            String username = null;
            String password = null;

            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("Username")) {
                    // line format: Username    : value
                    int idx = line.indexOf(':');
                    if (idx != -1) username = line.substring(idx + 1).trim();
                } else if (line.startsWith("Password")) {
                    int idx = line.indexOf(':');
                    if (idx != -1) password = line.substring(idx + 1).trim();
                }

                // When we have both, add a user and reset
                if (username != null && password != null) {
                    // If the same username exists, keep the first one (or overwrite; your choice)
                    users.putIfAbsent(username, new RegularEmployee(username, password));
                    username = null;
                    password = null;
                }
            }
        } catch (IOException e) {
            // Keep silent for demo; in real apps, log this
        }
    }

    public Session login() {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.print("Enter Username: ");
            String uname = sc.nextLine().trim();
            System.out.print("Enter Password: ");
            String pwd = sc.nextLine();

            User u = users.get(uname);
            if (u != null && u.authenticate(uname, pwd)) {
                System.out.println("\nLogin Successful!");
                System.out.println("Role: " + u.getRole());
                showDashboard(u.getRole());
                return new Session(u.getUsername());
            } else {
                attempts++;
                System.out.println("Invalid credentials. Attempts left: " + (maxAttempts - attempts));
            }
        }
        System.out.println("\nMax attempts reached. Login blocked.");
        return null;
    }

    private void showDashboard(String role) {
        if ("EMPLOYEE".equals(role)) {
            System.out.println("\n=== EMPLOYEE DASHBOARD ===");
            System.out.println("View Payslip | Update Profile");
        } else if ("MANAGER".equals(role)) {
            System.out.println("\n=== MANAGER DASHBOARD ===");
            System.out.println("Team Overview | Approvals");
        }
    }
}