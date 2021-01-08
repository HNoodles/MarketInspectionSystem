package com.fudan.market_inspection.entity;

import com.fudan.market_inspection.dao.Market;
import com.fudan.market_inspection.dao.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckTask {
    private final Market interestedMarket;
    private final List<Product> interestedProducts;
    private final Map<Product, CheckResult> checkResults;
    private boolean finished;

    public CheckTask(Market interestedMarket, List<Product> interestedProducts) {
        this.interestedMarket = interestedMarket;
        this.interestedProducts = interestedProducts;
        this.checkResults = new HashMap<>();
        this.finished = false;
    }

    public Market getInterestedMarket() {
        return interestedMarket;
    }

    public List<Product> getInterestedProducts() {
        return interestedProducts;
    }

    public Map<Product, CheckResult> getCheckResults() {
        return checkResults;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
