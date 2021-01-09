package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.CheckResult;
import com.fudan.market_inspection.dao.SelfInspectionTask;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.List;
import java.util.Map;

public interface MarketService {
    /**
     * This method should return all markets in the system.
     * @return a list of markets in the system.
     */
    List<Market> getMarkets();

    /**
     * This method should summarize all unfinished SelfInspectionTasks for each market. In each task, all
     * unfinished product test should be included.
     * @param tasks existing SelfInspectionTasks in the system
     * @return a map from market to its unfinished tasks, each unfinished task is mapped to a list of
     * unfinished product tests.
     */
    Map<Market, Map<SelfInspectionTask, List<Product>>> getMarketUnfinishedTasks(List<SelfInspectionTask> tasks);

    /**
     * This method should mark that a market has finished a product test of a CheckTask, which is part of
     * an InspectionTask.
     * @param task InspectionTask to finish
     * @param market market finishing a product test
     * @param checkResult the information of a product test
     */
    void marketFinishProductTest(SelfInspectionTask task, Market market, CheckResult checkResult);
}
