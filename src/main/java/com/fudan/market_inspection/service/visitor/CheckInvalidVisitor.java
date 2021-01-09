package com.fudan.market_inspection.service.visitor;

import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class CheckInvalidVisitor extends AbstractVisitor {
    public Map<Product, Integer> result;

    public CheckInvalidVisitor() {
        result = new HashMap<>();
    }

    public Map<Product, Integer> getResult() {
        return result;
    }

    @Override
    public <T> void visit(CheckTask checkTask) {
        //TODO
    }
}
