package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.dao.AbstractInspectionTask;
import com.fudan.market_inspection.dao.CheckResult;
import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.dao.ITask;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.TaskService;
import com.fudan.market_inspection.service.TimeService;
import com.fudan.market_inspection.service.visitor.AbstractVisitor;
import com.fudan.market_inspection.service.visitor.CheckUnfinishedVisitor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskServiceImpl implements TaskService {
    TimeService timeService;

    public TaskServiceImpl(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public Map<CheckTask, List<Product>> getUnfinishedContents(AbstractInspectionTask task) {
        CheckUnfinishedVisitor visitor = new CheckUnfinishedVisitor();
        task.accept(visitor);
        return visitor.getResult();
    }

    @Override
    public void addCheckResult(AbstractInspectionTask task, Market market, Product product, int invalidNum) {
        CheckTask checkTask = task.getMarketCheckTaskMap().get(market);
        checkTask.addCheckResult(product, new CheckResult(product, invalidNum, timeService.getCurrentDate()));
    }

    @Override
    public void markTaskFinished(ITask task) {
        boolean isFinished = task.markFinish(timeService.getCurrentDate());
        if (!isFinished)
            throw new RuntimeException("Task unfinished: " + task.getClass());
    }
}
