package com.fudan.market_inspection.dao;

import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpertInspectionTask extends AbstractInspectionTask {
    private final List<Expert> experts;
    private final Map<Expert, CheckTask> expertCheckTaskMap;

    public ExpertInspectionTask(String name, List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine, List<Expert> experts) {
        super(name, interestedMarkets, interestedProducts, deadLine);
        this.experts = experts;
        // initialize expert * check task map
        this.expertCheckTaskMap = new HashMap<>();
        for (Market market: interestedMarkets) {
            this.expertCheckTaskMap.put(experts.get(interestedMarkets.indexOf(market)), new CheckTask(market, interestedProducts));
        }
    }

    public List<Expert> getExperts() {
        return experts;
    }

    public Map<Expert, CheckTask> getExpertCheckTaskMap() {
        return expertCheckTaskMap;
    }
}
