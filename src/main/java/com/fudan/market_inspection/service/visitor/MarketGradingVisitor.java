package com.fudan.market_inspection.service.visitor;

import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.dao.GradeInfo;
import com.fudan.market_inspection.entity.Market;

import java.util.HashMap;
import java.util.Map;

public class MarketGradingVisitor extends AbstractVisitor {
    private final Map<Market, GradeInfo> result;

    public MarketGradingVisitor() {
        result = new HashMap<>();
    }

    public Map<Market, GradeInfo> getResult() {
        return result;
    }

    @Override
    public void visit(CheckTask checkTask) {
        //TODO
    }
}
