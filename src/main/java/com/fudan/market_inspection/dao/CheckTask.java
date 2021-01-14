package com.fudan.market_inspection.dao;

import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckTask implements ITask {
    private final Market interestedMarket;
    private final List<Product> interestedProducts;
    private final Map<Product, CheckResult> checkResults;
    private Date finishDate;

    public CheckTask(Market interestedMarket, List<Product> interestedProducts) {
        this.interestedMarket = interestedMarket;
        this.interestedProducts = interestedProducts;
        this.checkResults = new HashMap<>();
        this.finishDate = null;
    }

    public Market getInterestedMarket() {
        return interestedMarket;
    }

    public List<Product> getInterestedProducts() {
        return interestedProducts;
    }

    public void addCheckResult(Product product, CheckResult checkResult) {
        // todo what if product is not in interestedProducts?
        checkResults.put(product, checkResult); // if using the same key, update automatically
    }

    public Map<Product, CheckResult> getCheckResults() {
        return checkResults;
    }

    @Override
    public boolean markFinish(Date finishDate) {
        if (checkResults.size() < interestedProducts.size()) {
            return false;
        }
        this.finishDate = finishDate;
        return true;
    }

    @Override
    public boolean isFinished() {
        return finishDate != null;
    }

    @Override
    public Date getFinishDate() {
        return finishDate;
    }
}
