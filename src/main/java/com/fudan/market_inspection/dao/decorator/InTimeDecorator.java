package com.fudan.market_inspection.dao.decorator;

import com.fudan.market_inspection.dao.GradeTerm;

import java.util.List;

public class InTimeDecorator extends AbstractDecorator {
    public InTimeDecorator(Component component) {
        super(component);
    }

    @Override
    public List<GradeTerm> getGrade() {
        List<GradeTerm> gradeTerms = super.getGrade();
        gradeTerms.add(GradeTerm.IN_TIME);
        return gradeTerms;
    }
}
