package com.fudan.market_inspection.dao;

public class GradeHistory {
    private final int grade;
    private final String reason;

    public GradeHistory(int grade, String reason) {
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
