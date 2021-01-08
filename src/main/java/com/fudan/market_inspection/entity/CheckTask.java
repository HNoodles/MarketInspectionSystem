package com.fudan.market_inspection.entity;

import com.fudan.market_inspection.dao.Market;
import com.fudan.market_inspection.dao.Product;

import java.util.ArrayList;
import java.util.List;

public class CheckTask {
    private final Market interestedMarket;
    private final List<Product> interestedProducts;
    private final List<CheckResult> checkResults;
    private boolean finished;

    public CheckTask(Market interestedMarket, List<Product> interestedProducts) {
        this.interestedMarket = interestedMarket;
        this.interestedProducts = interestedProducts;
        this.checkResults = new ArrayList<>();
        this.finished = false;
    }

    public Market getInterestedMarket() {
        return interestedMarket;
    }

    public List<Product> getInterestedProducts() {
        return interestedProducts;
    }

    public List<CheckResult> getCheckResults() {
        return checkResults;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
