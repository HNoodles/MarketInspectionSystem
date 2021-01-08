package com.fudan.market_inspection.dao;

import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.List;

public class ExpertInspectionTask extends AbstractInspectionTask {
    private final Expert expert;

    public ExpertInspectionTask(String name, List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine, Expert expert) {
        super(name, interestedMarkets, interestedProducts, deadLine);
        this.expert = expert;
    }

    public Expert getExpert() {
        return expert;
    }
}
