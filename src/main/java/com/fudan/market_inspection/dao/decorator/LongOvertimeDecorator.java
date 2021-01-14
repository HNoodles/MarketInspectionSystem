package com.fudan.market_inspection.dao.decorator;

import com.fudan.market_inspection.dao.GradeTerm;

import java.util.List;

public class LongOvertimeDecorator extends AbstractDecorator {
    public LongOvertimeDecorator(Component component) {
        super(component);
    }

    @Override
    public List<GradeTerm> getGrade() {
        List<GradeTerm> gradeTerms = super.getGrade();
        gradeTerms.add(GradeTerm.LONG_OVERTIME);
        return gradeTerms;
    }
}
