package com.fudan.market_inspection.service.visitor;

import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.dao.GradeInfo;
import com.fudan.market_inspection.dao.GradeTerm;
import com.fudan.market_inspection.dao.decorator.InTimeDecorator;
import com.fudan.market_inspection.dao.decorator.LongOvertimeDecorator;
import com.fudan.market_inspection.dao.decorator.OvertimeDecorator;
import com.fudan.market_inspection.entity.Market;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketGradingVisitor extends AbstractVisitor {
    private final Map<Market, GradeInfo> result;
    private final Date currentDate;

    public MarketGradingVisitor(Date currentDate) {
        this.result = new HashMap<>();
        this.currentDate = currentDate;
    }

    public Map<Market, GradeInfo> getResult() {
        return result;
    }

    @Override
    public void visit(CheckTask checkTask) {
        long delayDays;
        Date deadLine = checkTask.getDeadLine();
        if (checkTask.isFinished()) {
            Date finishDate = checkTask.getFinishDate();
            delayDays = (finishDate.getTime() - deadLine.getTime()) / (24 * 60 * 60 * 1000);
            if (delayDays <= 0) {
                updateResult(checkTask.getInterestedMarket(), new InTimeDecorator(checkTask).getGrade());
                return;
            }
        } else { // unfinished
            delayDays = (currentDate.getTime() - deadLine.getTime()) / (24 * 60 * 60 * 1000);
        }
        if (delayDays > 0 && delayDays <= 20) {
            updateResult(checkTask.getInterestedMarket(), new OvertimeDecorator(checkTask).getGrade());
        } else if (delayDays > 20) { // > 20 days
            updateResult(checkTask.getInterestedMarket(), new LongOvertimeDecorator(new OvertimeDecorator(checkTask)).getGrade());
        }
    }

    private void updateResult(Market market, List<GradeTerm> gradeTerms) {
        GradeInfo gradeInfo;
        if (result.containsKey(market)) {
            gradeInfo = result.get(market);
        } else {
            gradeInfo = new GradeInfo();
        }
        for (GradeTerm gradeTerm: gradeTerms) {
            gradeInfo.addGradeTerm(gradeTerm);
        }
        result.put(market, gradeInfo);
    }
}
