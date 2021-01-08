package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.AbstractInspectionTask;
import com.fudan.market_inspection.entity.Product;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProductService {
    /**
     * This method should summarize the total invalid product counts for each product checked in system tasks
     * among a period of time.
     * @param tasks inspection tasks in the system
     * @param startDate count start date
     * @param endDate count end date
     * @return a map of product to total invalid count
     */
    Map<Product, Integer> getProductTotalInvalidCount(List<AbstractInspectionTask> tasks, Date startDate, Date endDate);
}
