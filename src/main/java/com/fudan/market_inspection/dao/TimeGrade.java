package com.fudan.market_inspection.dao;

public class TimeGrade {
    private final int grade;
    private final String reason;

    public TimeGrade(int grade, String reason) {
        this.grade = grade;
        this.reason = reason;
    }

    public int getGrade() {
        return grade;
    }

    public String getReason() {
        return reason;
    }
}
