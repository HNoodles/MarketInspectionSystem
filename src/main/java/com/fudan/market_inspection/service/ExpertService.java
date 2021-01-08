package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.CheckResult;
import com.fudan.market_inspection.dao.ExpertInspectionTask;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Product;

import java.util.List;
import java.util.Map;

public interface ExpertService {
    /**
     * This method should summarize all unfinished ExpertInspectionTasks for each market. In each task, all
     * unfinished product test should be included.
     * @param tasks existing ExpertInspectionTasks in the system
     * @return a map from market to its unfinished tasks, each unfinished task is mapped to a list of
     * unfinished product tests.
     */
    Map<Expert, Map<ExpertInspectionTask, List<Product>>> getExpertUnfinishedTasks(List<ExpertInspectionTask> tasks);

    /**
     * This method should mark that an expert has finished a product test of a CheckTask, which is part of
     * an InspectionTask.
     * @param task ExpertInspectionTask to finish
     * @param expert expert finishing a product test
     * @param checkResult the information of a product test
     */
    void expertFinishProductTest(ExpertInspectionTask task, Expert expert, CheckResult checkResult);
}
