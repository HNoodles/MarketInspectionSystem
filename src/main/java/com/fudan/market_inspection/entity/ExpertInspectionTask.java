package com.fudan.market_inspection.entity;

import com.fudan.market_inspection.dao.Expert;
import com.fudan.market_inspection.dao.Market;
import com.fudan.market_inspection.dao.Product;

import java.util.Date;
import java.util.List;

public class ExpertInspectionTask extends AbstractInspectionTask {
    final private Expert expert;

    public ExpertInspectionTask(List<Market> interestedMarkets, List<Product> interestedProducts, Date deadLine, Expert expert) {
        super(interestedMarkets, interestedProducts, deadLine);
        this.expert = expert;
    }

    public Expert getExpert() {
        return expert;
    }
}
