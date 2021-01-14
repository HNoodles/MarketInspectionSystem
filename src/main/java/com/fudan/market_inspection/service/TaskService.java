package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.AbstractInspectionTask;
import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.dao.ITask;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TaskService {
    /**
     * This method should summarize all unfinished CheckTasks for an AbstractInspectionTask. In each CheckTask, all
     * unfinished product test should be included.
     * @param task an existing AbstractInspectionTask in the system
     * @return a map of check tasks to a list of unfinished product tests each.
     */
    Map<CheckTask, List<Product>> getUnfinishedContents(AbstractInspectionTask task);

    /**
     * This method should mark that a market has finished a product test of a CheckTask, which is part of
     * an InspectionTask.
     * @param task InspectionTask to finish
     * @param market market finishing a product test
     * @param product the product of a product test
     * @param invalidNum the number of invalid product above
     */
    void addCheckResult(AbstractInspectionTask task, Market market, Product product, int invalidNum);

    void markTaskFinished(ITask task);
}
