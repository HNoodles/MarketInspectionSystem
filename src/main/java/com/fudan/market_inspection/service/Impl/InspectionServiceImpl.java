package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.dao.*;
import com.fudan.market_inspection.dao.decorator.InTimeDecorator;
import com.fudan.market_inspection.dao.decorator.LongOvertimeDecorator;
import com.fudan.market_inspection.dao.decorator.OvertimeDecorator;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.InspectionService;
import com.fudan.market_inspection.service.TimeService;
import com.fudan.market_inspection.service.visitor.CheckInvalidVisitor;
import com.fudan.market_inspection.service.visitor.MarketGradingVisitor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InspectionServiceImpl implements InspectionService {
    TimeService timeService;
    public InspectionServiceImpl(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public Map<Expert, GradeInfo> getExpertGradeInfo(List<ExpertInspectionTask> tasks) {
//        TODO: duplicated code with MarketGradingVisitor
        Map<Expert, GradeInfo> result = new HashMap<>();
        for (ExpertInspectionTask task : tasks) {
            long delayDays;
            Date deadLine = task.getDeadLine();
            if (task.isFinished()) {
                Date finishDate = task.getFinishDate();
                delayDays = (finishDate.getTime() - deadLine.getTime()) / (24 * 60 * 60 * 1000);
                if (delayDays <= 0) {
                    updateResult(result, task.getExpert(), new InTimeDecorator(task).getGrade());
                    continue;
                }
            } else { // unfinished
                delayDays = (timeService.getCurrentDate().getTime() - deadLine.getTime()) / (24 * 60 * 60 * 1000);
            }
            if (delayDays > 0 && delayDays <= 20) {
                updateResult(result, task.getExpert(), new OvertimeDecorator(task).getGrade());
            } else if (delayDays > 20) { // > 20 days
                updateResult(result, task.getExpert(), new LongOvertimeDecorator(new OvertimeDecorator(task)).getGrade());
            }
        }
        return result;
    }

    private void updateResult(Map<Expert, GradeInfo> result, Expert expert, List<GradeTerm> gradeTerms) {
        GradeInfo gradeInfo;
        if (result.containsKey(expert)) {
            gradeInfo = result.get(expert);
        } else {
            gradeInfo = new GradeInfo();
        }
        for (GradeTerm gradeTerm: gradeTerms) {
            gradeInfo.addGradeTerm(gradeTerm);
        }
        result.put(expert, gradeInfo);
    }

    @Override
    public Map<Market, GradeInfo> getMarketGradeInfo(List<SelfInspectionTask> tasks) {
        MarketGradingVisitor visitor = new MarketGradingVisitor(timeService.getCurrentDate());
        for (SelfInspectionTask task : tasks) {
            task.accept(visitor);
        }
        return visitor.getResult();
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
