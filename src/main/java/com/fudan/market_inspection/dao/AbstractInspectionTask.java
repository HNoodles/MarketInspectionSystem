package com.fudan.market_inspection.dao;

import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.List;

public class AbstractInspectionTask {
    private final String name;
    private final List<Market> interestedMarkets;
    private final List<Product> interestedProducts;
    private final Date deadLine;

    protected AbstractInspectionTask(String name, List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine) {
        this.name = name;
        this.interestedMarkets = interestedMarkets;
        this.interestedProducts = interestedProducts;
        this.deadLine = deadLine;
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
}
