package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.dao.AbstractInspectionTask;
import com.fudan.market_inspection.dao.CheckResult;
import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.dao.ITask;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.TaskService;
import com.fudan.market_inspection.service.visitor.AbstractVisitor;
import com.fudan.market_inspection.service.visitor.CheckUnfinishedVisitor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskServiceImpl implements TaskService {
    @Override
    public Map<CheckTask, List<Product>> getUnfinishedContents(AbstractInspectionTask task) {
        if (task.isFinished())
            return null;
        CheckUnfinishedVisitor visitor = new CheckUnfinishedVisitor();
        task.accept(visitor);
        return visitor.getResult();
    }

    @Override
    public void addCheckResult(AbstractInspectionTask task, Market market, CheckResult checkResult) {
        CheckTask checkTask = task.getMarketCheckTaskMap().get(market);
        checkTask.addCheckResult(checkResult.getProduct(), checkResult);
    }

    @Override
    public void markTaskFinished(ITask task, Date finishDate) {
        boolean isFinished = task.markFinish(finishDate);
        if (!isFinished)
            throw new RuntimeException("Task unfinished: " + task.getClass());
    }
}
