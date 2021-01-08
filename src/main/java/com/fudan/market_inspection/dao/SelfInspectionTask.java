package com.fudan.market_inspection.dao;

import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.List;

public class SelfInspectionTask extends AbstractInspectionTask {
    public SelfInspectionTask(String name, List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine) {
        super(name, interestedMarkets, interestedProducts, deadLine);
    }
}
