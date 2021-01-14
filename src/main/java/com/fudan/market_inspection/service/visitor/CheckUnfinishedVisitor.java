package com.fudan.market_inspection.service.visitor;

import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.entity.Product;

import java.util.*;

public class CheckUnfinishedVisitor extends AbstractVisitor {
    private final Map<CheckTask, List<Product>> result;

    public CheckUnfinishedVisitor() {
        result = new HashMap<>();
    }

    public Map<CheckTask, List<Product>> getResult() {
        return result;
    }

    @Override
    public void visit(CheckTask checkTask) {
        if (checkTask.isFinished())
            return;
        Set<Product> checkedProducts = checkTask.getCheckResults().keySet();
        List<Product> unCheckedProducts = new ArrayList<>();
        for (Product product : checkTask.getInterestedProducts()) {
            if (!checkedProducts.contains(product)) {
                unCheckedProducts.add(product);
            }
        }
        result.put(checkTask, unCheckedProducts);
    }
}
