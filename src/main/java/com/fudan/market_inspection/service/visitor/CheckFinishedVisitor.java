package com.fudan.market_inspection.service.visitor;

import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckFinishedVisitor extends AbstractVisitor {
    private final Map<Market, List<Product>> result;

    public CheckFinishedVisitor() {
        result = new HashMap<>();
    }

    public Map<Market, List<Product>> getResult() {
        return result;
    }

    @Override
    public void visit(CheckTask checkTask) {
        //TODO
    }
}
