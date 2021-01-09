package com.fudan.market_inspection.dao;

import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.visitor.AbstractVisitor;

import java.util.*;

public abstract class AbstractInspectionTask {
    private final String name;
    private final List<Market> interestedMarkets;
    private final List<Product> interestedProducts;
    private final Date deadLine;
    private final Map<Market, CheckTask> marketCheckTaskMap;
    private Date finishDate;

    protected AbstractInspectionTask(String name, List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine) {
        this.name = name;
        this.interestedMarkets = interestedMarkets;
        this.interestedProducts = interestedProducts;
        this.deadLine = deadLine;
        // initialize market * check task map
        this.marketCheckTaskMap = new HashMap<>();
        for (Market market: interestedMarkets) {
            this.marketCheckTaskMap.put(market, new CheckTask(market, interestedProducts));
        }
    }

    public String getName() {
        return name;
    }

    public List<Market> getInterestedMarkets() {
        return interestedMarkets;
    }

    public List<Product> getInterestedProducts() {
        return interestedProducts;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public Map<Market, CheckTask> getMarketCheckTaskMap() {
        return marketCheckTaskMap;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public boolean markFinish(Date finishDate) {
        if (marketCheckTaskMap.size() < interestedMarkets.size()) {
            return false;
        }
        this.finishDate = finishDate;
        return true;
    }

    public void accept(AbstractVisitor visitor) {
        for (CheckTask checkTask: marketCheckTaskMap.values()) {
            visitor.visit(checkTask);
        }
    }
}
