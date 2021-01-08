package com.fudan.market_inspection.entity;

import com.fudan.market_inspection.dao.Market;
import com.fudan.market_inspection.dao.Product;

import java.util.Date;
import java.util.List;

public class SelfInspectionTask extends AbstractInspectionTask {
    public SelfInspectionTask(List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine) {
        super(interestedMarkets, interestedProducts, deadLine);
    }
}
