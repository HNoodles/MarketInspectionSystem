package com.fudan.market_inspection.dao;

import java.util.ArrayList;
import java.util.List;

public class GradeInfo {
    private int totalGrade;
    private final List<GradeHistory> gradeHistoryList;

    public GradeInfo() {
        this.totalGrade = 0;
        this.gradeHistoryList = new ArrayList<>();
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public List<GradeHistory> getGradeHistoryList() {
        return gradeHistoryList;
    }

    public void addGradeHistory(GradeHistory gradeHistory) {
        this.gradeHistoryList.add(gradeHistory);
        this.totalGrade += gradeHistory.getGrade();
    }
}
