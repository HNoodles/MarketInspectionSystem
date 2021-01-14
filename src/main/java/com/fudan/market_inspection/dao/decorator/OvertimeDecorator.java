package com.fudan.market_inspection.dao.decorator;

import com.fudan.market_inspection.dao.GradeTerm;

import java.util.List;

public class OvertimeDecorator extends AbstractDecorator {
    public OvertimeDecorator(Component component) {
        super(component);
    }

    @Override
    public List<GradeTerm> getGrade() {
        List<GradeTerm> gradeTerms = super.getGrade();
        gradeTerms.add(GradeTerm.OVERTIME);
        return gradeTerms;
    }
}
