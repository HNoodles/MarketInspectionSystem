package com.fudan.market_inspection.dao;

import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelfInspectionTask extends AbstractInspectionTask {
    private final Map<Market, CheckTask> marketCheckTaskMap;

    public SelfInspectionTask(String name, List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine) {
        super(name, interestedMarkets, interestedProducts, deadLine);
        // initialize market * check task map
        this.marketCheckTaskMap = new HashMap<>();
        for (Market market: interestedMarkets) {
            this.marketCheckTaskMap.put(market, new CheckTask(market, interestedProducts));
        }
    }

    public Map<Market, CheckTask> getMarketCheckTaskMap() {
        return marketCheckTaskMap;
    }
}
