package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.*;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.Impl.DataServiceImpl;
import com.fudan.market_inspection.service.Impl.InspectionServiceImpl;
import com.fudan.market_inspection.service.Impl.TaskServiceImpl;
import com.fudan.market_inspection.service.Impl.TimeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InspectionServiceTest {
    List<Expert> experts;
    List<Market> markets;
    List<Product> products;

    SelfInspectionTask marketTask1;
    SelfInspectionTask marketTask2;
    ExpertInspectionTask expertTask1;
    ExpertInspectionTask expertTask2;

    DataService dataService;
    TaskService taskService;
    InspectionService inspectionService;
    TimeServiceMockImpl timeService;

    @BeforeEach
    void setUp() {
        dataService = new DataServiceImpl();
        timeService = new TimeServiceMockImpl();
        taskService = new TaskServiceImpl(timeService);
        inspectionService = new InspectionServiceImpl(timeService);

        experts = dataService.getAllExperts();
        markets = dataService.getAllMarkets();
        products = dataService.getAllProducts();

        System.out.println("Experts: " + Arrays.toString(experts.toArray()));
        System.out.println("Markets: " + Arrays.toString(markets.toArray()));
        System.out.println("Products: " + Arrays.toString(products.toArray()));

        marketTask1 = new SelfInspectionTask(
                "Market Task1",
                Arrays.asList(markets.get(0), markets.get(1), markets.get(2)),
                Arrays.asList(products.get(0), products.get(1)),
                timeService.getCurrentDate()
        );
        timeService.setCurrentDateLaterByNDays(12);
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(1), marketTask1.getInterestedProducts().get(0), 1);
        timeService.setCurrentDateLaterByNDays(2);
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(0), marketTask1.getInterestedProducts().get(1), 2);
        timeService.setCurrentDateLaterByNDays(5);
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(1), marketTask1.getInterestedProducts().get(0), 3);

        /////////////////////////////////////////////////////////////////////////////

        marketTask2 = new SelfInspectionTask(
                "Market Task2",
                Arrays.asList(markets.get(1), markets.get(2)),
                Arrays.asList(products.get(0), products.get(1), products.get(2)),
                timeService.getCurrentDate()
        );
        timeService.setCurrentDateLaterByNDays(0);
        taskService.addCheckResult(marketTask2, marketTask2.getInterestedMarkets().get(0), marketTask2.getInterestedProducts().get(0), 1);
        timeService.setCurrentDateLaterByNDays(14);
        taskService.addCheckResult(marketTask2, marketTask2.getInterestedMarkets().get(1), marketTask2.getInterestedProducts().get(0), 2);
        timeService.setCurrentDateLaterByNDays(4);
        taskService.addCheckResult(marketTask2, marketTask2.getInterestedMarkets().get(1), marketTask2.getInterestedProducts().get(1), 5);
        timeService.setCurrentDateLaterByNDays(8);
        taskService.addCheckResult(marketTask2, marketTask2.getInterestedMarkets().get(1), marketTask2.getInterestedProducts().get(2), 7);

    }

    @AfterEach
    void tearDown() {
        dataService = null;
        taskService = null;
        inspectionService = null;
        timeService = null;

        experts = null;
        markets = null;
        products = null;

        marketTask1 = null;
    }

    @Test
    void testGetExpertGradeInfo() { // todo
    }

    @Test
    void testGetMarketGradeInfo() { // todo
//        Map<Market, GradeInfo> gradeInfoMap = inspectionService.getMarketGradeInfo()
    }

    /**
     * 测试：查看某个农贸产品类别在某个时间范围内的总的不合格数（时间以抽检日期为准）
     */
    @Test
    void testGetProductTotalInvalidCountInRange() {
        Map<Product, Integer> counts = inspectionService.getProductTotalInvalidCountInRange(Collections.singletonList(marketTask1),
                timeService.getDateOfNDaysLater(3), timeService.getDateOfNDaysLater(14));
        assertEquals(1, counts.size());
        assertEquals(4, counts.get(marketTask1.getInterestedProducts().get(0)));

        counts = inspectionService.getProductTotalInvalidCountInRange(Collections.singletonList(marketTask2),
                timeService.getDateOfNDaysLater(-1), timeService.getDateOfNDaysLater(9));
        assertEquals(3, counts.size());
        assertEquals(1, counts.get(marketTask2.getInterestedProducts().get(0)));
        assertEquals(5, counts.get(marketTask2.getInterestedProducts().get(1)));
        assertEquals(7, counts.get(marketTask2.getInterestedProducts().get(2)));

        counts = inspectionService.getProductTotalInvalidCountInRange(Arrays.asList(marketTask1, marketTask2),
                timeService.getDateOfNDaysLater(1), timeService.getDateOfNDaysLater(10));
        assertEquals(3, counts.size());
        assertEquals(3, counts.get(marketTask2.getInterestedProducts().get(0)));
        assertEquals(7, counts.get(marketTask2.getInterestedProducts().get(1)));
        assertEquals(7, counts.get(marketTask2.getInterestedProducts().get(2)));
    }

    /**
     * 测试：一次监管任务中，某种分类产品的不合格总数
      */
    @Test
    void testGetProductInvalidCount() {
        Map<Product, Integer> counts = inspectionService.getProductInvalidCount(marketTask1);
        assertEquals(2, counts.size());
        assertEquals(4, counts.get(marketTask1.getInterestedProducts().get(0)));
        assertEquals(2, counts.get(marketTask1.getInterestedProducts().get(1)));

        counts = inspectionService.getProductInvalidCount(marketTask2);
        assertEquals(3, counts.size());
        assertEquals(3, counts.get(marketTask2.getInterestedProducts().get(0)));
        assertEquals(5, counts.get(marketTask2.getInterestedProducts().get(1)));
        assertEquals(7, counts.get(marketTask2.getInterestedProducts().get(2)));
    }
}