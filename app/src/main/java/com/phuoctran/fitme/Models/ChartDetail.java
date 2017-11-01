package com.phuoctran.fitme.Models;

public class ChartDetail {
    public String EU;
    public String INT;
    public String JP;
    public String KR;
    public String UK;
    public String US;

    public ChartDetail(String INT, String US, String UK, String EU, String KR, String JP) {
        this.INT = INT;
        this.US = US;
        this.UK = UK;
        this.EU = EU;
        this.KR = KR;
        this.JP = JP;
    }

    public ChartDetail(String EU, String US, String UK, String KR, String JP) {
        this.US = US;
        this.UK = UK;
        this.EU = EU;
        this.KR = KR;
        this.JP = JP;
    }

    public ChartDetail(String INT, String US, String UK) {
        this.INT = INT;
        this.US = US;
        this.UK = UK;
    }
}
