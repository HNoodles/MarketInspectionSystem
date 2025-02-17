package com.fudan.market_inspection.dao;

public enum GradeTerm {
    NOT_YET(0, "尚未完成"),
    IN_TIME(10, "按时完成"),
    OVERTIME(-10, "超时完成"),
    LONG_OVERTIME(-20, "超过20天未完成");

    private final int grade;
    private final String reason;

    private GradeTerm(int grade, String reason) {
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
