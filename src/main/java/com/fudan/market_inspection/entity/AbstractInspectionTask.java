package com.fudan.market_inspection.entity;

import com.fudan.market_inspection.dao.Market;
import com.fudan.market_inspection.dao.Product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractInspectionTask {
    private final List<Market> interestedMarkets;
    private final List<Product> interestedProducts;
    private final Date deadLine;
    private final Map<Market, CheckTask> marketCheckTaskMap;

    protected AbstractInspectionTask(List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine) {
        this.interestedMarkets = interestedMarkets;
        this.interestedProducts = interestedProducts;
        this.deadLine = deadLine;
        // initialize market - check task map
        this.marketCheckTaskMap = new HashMap<>();
        for (Market market: this.interestedMarkets) {
            this.marketCheckTaskMap.put(market, new CheckTask(market, this.interestedProducts));
        }
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
}
