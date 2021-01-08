package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.ExpertInspectionTask;
import com.fudan.market_inspection.dao.GradeInfo;
import com.fudan.market_inspection.dao.SelfInspectionTask;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface GradingService {
    /**
     * This method should summarize grade information for each expert according to the existing tasks
     * and the current date in the system.
     * @param tasks existing ExpertInspectionTasks in the system
     * @param currentDate current date of the system
     * @return a map of experts to their grade information, each grade information contains a total
     * grade and detailed grade info.
     */
    Map<Expert, GradeInfo> getExpertGradeInfo(List<ExpertInspectionTask> tasks, Date currentDate);

    /**
     * This method should summarize grade information for each market according to the existing tasks
     * and the current date in the system.
     * @param tasks existing SelfInspectionTasks in the system
     * @param currentDate current date of the system
     * @return a map of markets to their grade information, each grade information contains a total
     * grade and detailed grade info.
     */
    Map<Market, GradeInfo> getMarketGradeInfo(List<SelfInspectionTask> tasks, Date currentDate);
}
