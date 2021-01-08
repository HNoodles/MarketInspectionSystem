package com.fudan.market_inspection.entity;

public class TimeGrade {
    final private int grade;
    final private String reason;

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
