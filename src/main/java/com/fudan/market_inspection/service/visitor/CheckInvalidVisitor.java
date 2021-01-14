package com.fudan.market_inspection.service.visitor;

import com.fudan.market_inspection.dao.CheckResult;
import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CheckInvalidVisitor extends AbstractVisitor {
    private final Map<Product, Integer> result;
    private final Date startDate;
    private final Date endDate;

    public CheckInvalidVisitor() {
        this.startDate = new Date(Long.MIN_VALUE);
        this.endDate = new Date(Long.MAX_VALUE);
        result = new HashMap<>();
    }

    public CheckInvalidVisitor(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        result = new HashMap<>();
    }

    public Map<Product, Integer> getResult() {
        return result;
    }

    @Override
    public void visit(CheckTask checkTask) {
        Map<Product, CheckResult> checkResults = checkTask.getCheckResults();
        for (CheckResult checkResult : checkResults.values()) {
            if (isDateInRange(checkResult.getUploadDate())) {
                updateResult(checkResult.getProduct(), checkResult.getInvalidCount());
            }
        }
    }

    private boolean isDateInRange(Date thisDate) {
        return thisDate.compareTo(startDate) > 0 && thisDate.compareTo(endDate) < 0;
    }

    private void updateResult(Product product, Integer integer) {
        result.put(product, result.containsKey(product) ? integer + result.get(product) : integer);
    }
}
