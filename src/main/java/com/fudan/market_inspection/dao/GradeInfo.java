package com.fudan.market_inspection.dao;

import java.util.ArrayList;
import java.util.List;

public class GradeInfo {
    private int totalGrade;
    private final List<GradeTerm> gradeTermList;

    public GradeInfo() {
        this.totalGrade = 0;
        this.gradeTermList = new ArrayList<>();
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public List<GradeTerm> getGradeTermList() {
        return gradeTermList;
    }

    public void addGradeTerm(GradeTerm gradeHistory) {
        this.gradeTermList.add(gradeHistory);
        this.totalGrade += gradeHistory.getGrade();
    }
}
