package com.payrollapp.audit;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/*
 * Records important system events.
 */

public class AuditService {

    private static final String LOG_FILE = "system_audit_log.txt";

    public static void log(String message) {

        try {

            FileWriter fw = new FileWriter(LOG_FILE, true);

            fw.write(LocalDateTime.now() + " : " + message + "\n");

            fw.close();

        } catch (IOException e) {
            System.out.println("Audit logging failed.");
        }
    }
}