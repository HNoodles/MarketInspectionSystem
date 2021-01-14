package com.fudan.market_inspection.dao.decorator;

import com.fudan.market_inspection.dao.GradeTerm;

import java.util.List;

public abstract class AbstractDecorator implements Component {
    private final Component component;

    protected AbstractDecorator(Component component) {
        this.component = component;
    }

    @Override
    public List<GradeTerm> getGrade() {
        return component.getGrade();
    }
}
