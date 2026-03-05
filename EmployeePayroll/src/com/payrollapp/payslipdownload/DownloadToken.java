package com.payrollapp.payslipdownload;

/*
 * Simulates a download authorization token.
 * Token expires after a short time.
 */

public class DownloadToken {

    private long createdTime;
    private long expiryMillis;

    public DownloadToken() {
        this.createdTime = System.currentTimeMillis();
        this.expiryMillis = 60 * 1000; // valid for 1 minute
    }

    public boolean isExpired() {
        long now = System.currentTimeMillis();
        return (now - createdTime) > expiryMillis;
    }
}