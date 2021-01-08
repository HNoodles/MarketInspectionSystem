package com.fudan.market_inspection.dao;

import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckTask {
    private final Market interestedMarket;
    private final List<Product> interestedProducts;
    private final Map<Product, CheckResult> checkResults;
    private boolean finished;
    private Date finishDate;

    public CheckTask(Market interestedMarket, List<Product> interestedProducts) {
        this.interestedMarket = interestedMarket;
        this.interestedProducts = interestedProducts;
        this.checkResults = new HashMap<>();
        this.finished = false;
        this.finishDate = null;
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

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinished(boolean finished, Date finishDate) {
        this.finished = finished;
        this.finishDate = finishDate;
    }
}
