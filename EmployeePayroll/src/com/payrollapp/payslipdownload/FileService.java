package com.payrollapp.payslipdownload;

import java.io.FileWriter;
import java.io.IOException;
import com.payrollapp.payroll.Payslip;

/*
 * Handles saving payslips to files.
 */

public class FileService {

    public String savePayslipAsText(Payslip payslip) throws IOException {

        String filename = "payslip_" + System.currentTimeMillis() + ".txt";

        FileWriter fw = new FileWriter(filename);
        fw.write(payslip.toString());
        fw.close();

        return filename;
    }

}
