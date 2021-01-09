package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.CheckResult;
import com.fudan.market_inspection.dao.ExpertInspectionTask;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.List;
import java.util.Map;

public interface ExpertService {
    /**
     * This method should return all experts in the system.
     * @return a list of all experts in the system.
     */
    List<Expert> getAllExperts();

    /**
     * This method should summarize an unfinished ExpertInspectionTask for an expert. For each market in the task, all
     * unfinished product test should be included.
     * @param task an existing ExpertInspectionTask in the system
     * @return a map from market to its unfinished tasks, each unfinished task is mapped to a list of
     * unfinished product tests.
     */
    Map<Market, List<Product>> getExpertUnfinishedTasks(ExpertInspectionTask task);

    /**
     * This method should mark that an expert has finished a product test of a CheckTask, which is part of
     * an InspectionTask.
     * @param task ExpertInspectionTask to finish
     * @param market expert finishing a product test of the market
     * @param checkResult the information of a product test
     */
    void expertFinishProductTest(ExpertInspectionTask task, Market market, CheckResult checkResult);
}
