package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.dao.AbstractInspectionTask;
import com.fudan.market_inspection.dao.ExpertInspectionTask;
import com.fudan.market_inspection.dao.GradeInfo;
import com.fudan.market_inspection.dao.SelfInspectionTask;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.InspectionService;
import com.fudan.market_inspection.service.visitor.CheckInvalidVisitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class InspectionServiceImpl implements InspectionService {
    @Override
    public Map<Expert, GradeInfo> getExpertGradeInfo(List<ExpertInspectionTask> tasks, Date currentDate) {
        return null;
    }

    @Override
    public Map<Market, GradeInfo> getMarketGradeInfo(List<SelfInspectionTask> tasks, Date currentDate) {
        return null;
    }

    @Override
    public Map<Product, Integer> getProductTotalInvalidCountInRange(List<AbstractInspectionTask> tasks, Date startDate, Date endDate) {
        CheckInvalidVisitor visitor = new CheckInvalidVisitor(startDate, endDate);
        for (AbstractInspectionTask task : tasks) {
            task.accept(visitor);
        }
        return visitor.getResult();
    }

    @Override
    public Map<Product, Integer> getProductInvalidCount(AbstractInspectionTask task) {
        CheckInvalidVisitor visitor = new CheckInvalidVisitor();
        task.accept(visitor);
        return visitor.getResult();
    }
}
