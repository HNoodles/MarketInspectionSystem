package com.fudan.market_inspection;

import com.fudan.market_inspection.dao.AbstractInspectionTask;
import com.fudan.market_inspection.dao.SelfInspectionTask;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.DataService;
import com.fudan.market_inspection.service.Impl.DataServiceImpl;
import com.fudan.market_inspection.service.Impl.InspectionServiceImpl;
import com.fudan.market_inspection.service.Impl.TaskServiceImpl;
import com.fudan.market_inspection.service.Impl.TimeServiceImpl;
import com.fudan.market_inspection.service.InspectionService;
import com.fudan.market_inspection.service.TaskService;
import com.fudan.market_inspection.service.TimeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // A simple demo to show how to use our system.
        // More functions can be seen in test cases.
        DataService dataService = new DataServiceImpl();
        TimeService timeService = new TimeServiceImpl();
        TaskService taskService = new TaskServiceImpl(timeService);
        InspectionService inspectionService = new InspectionServiceImpl(timeService);

        List<Market> markets = dataService.getAllMarkets();
        List<Product> products = dataService.getAllProducts();

        AbstractInspectionTask task = new SelfInspectionTask(
                "Market Task",
                Collections.singletonList(markets.get(0)),
                Collections.singletonList(products.get(0)),
                timeService.getCurrentDate()
        );
        taskService.addCheckResult(task, markets.get(0), products.get(0), 1);
        Map<Product, Integer> map = inspectionService.getProductInvalidCount(task);
        for (Product product : map.keySet()) {
            System.out.println(product.getName() + " invalid number: " + map.get(product));
        }

    }
}
