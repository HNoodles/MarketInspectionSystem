package com.fudan.market_inspection.dao.decorator;

import com.fudan.market_inspection.dao.GradeTerm;

import java.util.List;

public interface Component {
    List<GradeTerm> getGrade();
}
