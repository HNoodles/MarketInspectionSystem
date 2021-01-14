package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.CheckResult;
import com.fudan.market_inspection.dao.ExpertInspectionTask;
import com.fudan.market_inspection.dao.SelfInspectionTask;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.Impl.DataServiceImpl;
import com.fudan.market_inspection.service.Impl.TaskServiceImpl;
import com.fudan.market_inspection.service.Impl.TimeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class InspectionServiceTest {
    List<Expert> experts;
    List<Market> markets;
    List<Product> products;

    SelfInspectionTask marketTask1;
    SelfInspectionTask marketTask2;
    ExpertInspectionTask expertTask;

    DataService dataService;
    TaskService taskService;
    TimeService timeService;

    @BeforeEach
    void setUp() {
        dataService = new DataServiceImpl();
        taskService = new TaskServiceImpl();
        timeService = new TimeServiceImpl();

        experts = dataService.getAllExperts();
        markets = dataService.getAllMarkets();
        products = dataService.getAllProducts();

        marketTask1 = new SelfInspectionTask(
                "Market Task",
                Arrays.asList(markets.get(0), markets.get(1), markets.get(2)),
                Arrays.asList(products.get(0), products.get(1)),
                timeService.getCurrentDate()
        );
        expertTask = new ExpertInspectionTask(
                "Expert Task",
                Arrays.asList(markets.get(0), markets.get(1)),
                Arrays.asList(products.get(0), products.get(2)),
                timeService.getCurrentDate(),
                experts.get(0)
        );

        System.out.println("Experts: " + Arrays.toString(experts.toArray()));
        System.out.println("Markets: " + Arrays.toString(markets.toArray()));
        System.out.println("Products: " + Arrays.toString(products.toArray()));

        // finish all first market's tasks
        Market firstMarket = marketTask1.getInterestedMarkets().get(0);
        for (Product product : marketTask1.getInterestedProducts()) {
            // add check result
            taskService.addCheckResult(marketTask1, firstMarket, new CheckResult(product, 1, timeService.getCurrentDate()));
        }
        // mark check task finished
        taskService.markTaskFinished(marketTask1.getMarketCheckTaskMap().get(firstMarket), timeService.getCurrentDate());

        // finish half of second market's tasks
        Market secondMarket = marketTask1.getInterestedMarkets().get(1);
        Product firstProduct = marketTask1.getInterestedProducts().get(0);
        taskService.addCheckResult(marketTask1, secondMarket, new CheckResult(firstProduct, 1, timeService.getCurrentDate()));

        // leave third market's tasks unfinished
        Market thirdMarket = marketTask1.getInterestedMarkets().get(2);
        /////////////////////////////////////////////////////////////////////////////
        marketTask1 = new SelfInspectionTask(
                "Market Task",
                Arrays.asList(markets.get(0), markets.get(1), markets.get(2)),
                Arrays.asList(products.get(0), products.get(1)),
                timeService.getCurrentDate()
        );
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(0), new CheckResult(marketTask1.getInterestedProducts().get(0), 1, timeService.getCurrentDate()));
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(0), new CheckResult(marketTask1.getInterestedProducts().get(0), 1, timeService.getCurrentDate()));
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(0), new CheckResult(marketTask1.getInterestedProducts().get(0), 1, timeService.getCurrentDate()));

        /////////////////////////////////////////////////////////////////////////////
        // finish all first market's tasks
        Market firstMarket2 = marketTask2.getInterestedMarkets().get(0);
        for (Product product : marketTask2.getInterestedProducts()) {
            // add check result
            taskService.addCheckResult(marketTask2, firstMarket2, new CheckResult(product, 1, timeService.getCurrentDate()));
        }
        // mark check task finished
        taskService.markTaskFinished(marketTask2.getMarketCheckTaskMap().get(firstMarket2), timeService.getCurrentDate());

        // finish half of second market's tasks
        Market secondMarket2 = marketTask2.getInterestedMarkets().get(1);
        Product firstProduct2 = marketTask2.getInterestedProducts().get(0);
        taskService.addCheckResult(marketTask2, secondMarket2, new CheckResult(firstProduct2, 1, timeService.getCurrentDate()));

        // leave third market's tasks unfinished
        Market thirdMarket2 = marketTask2.getInterestedMarkets().get(2);
    }

    @AfterEach
    void tearDown() {
        dataService = null;
        taskService = null;
        timeService = null;

        experts = null;
        markets = null;
        products = null;

        marketTask1 = null;
    }

    @Test
    void testGetExpertGradeInfo() {
    }

    @Test
    void testGetMarketGradeInfo() {
    }

    @Test
    void testGetProductTotalInvalidCountInRange() {
    }

    @Test
    void testGetProductInvalidCount() {
    }
}