package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.*;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.Impl.DataServiceImpl;
import com.fudan.market_inspection.service.Impl.InspectionServiceImpl;
import com.fudan.market_inspection.service.Impl.TaskServiceImpl;
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
    ExpertInspectionTask expertTask3;

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
                timeService.getDateOfNDaysLater(6) // deadLine
        );
        timeService.setCurrentDateLaterByNDays(12);
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(0), marketTask1.getInterestedProducts().get(0), 1);
        timeService.setCurrentDateLaterByNDays(2);
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(0), marketTask1.getInterestedProducts().get(1), 2);
        timeService.setCurrentDateLaterByNDays(5);
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(1), marketTask1.getInterestedProducts().get(0), 3);

        /////////////////////////////////////////////////////////////////////////////

        marketTask2 = new SelfInspectionTask(
                "Market Task2",
                Arrays.asList(markets.get(1), markets.get(2)),
                Arrays.asList(products.get(0), products.get(1), products.get(2)),
                timeService.getDateOfNDaysLater(10) // deadLine
        );
        timeService.setCurrentDateLaterByNDays(0);
        taskService.addCheckResult(marketTask2, marketTask2.getInterestedMarkets().get(0), marketTask2.getInterestedProducts().get(0), 1);
        timeService.setCurrentDateLaterByNDays(14);
        taskService.addCheckResult(marketTask2, marketTask2.getInterestedMarkets().get(1), marketTask2.getInterestedProducts().get(0), 2);
        timeService.setCurrentDateLaterByNDays(4);
        taskService.addCheckResult(marketTask2, marketTask2.getInterestedMarkets().get(1), marketTask2.getInterestedProducts().get(1), 5);
        timeService.setCurrentDateLaterByNDays(8);
        taskService.addCheckResult(marketTask2, marketTask2.getInterestedMarkets().get(1), marketTask2.getInterestedProducts().get(2), 7);

        /////////////////////////////////////////////////////////////////////////////

        expertTask1 = new ExpertInspectionTask(
                "Expert Task1",
                Arrays.asList(markets.get(0), markets.get(1), markets.get(2)),
                Arrays.asList(products.get(0), products.get(1)),
                timeService.getDateOfNDaysLater(6), // deadLine
                experts.get(0)
        );
        timeService.setCurrentDateLaterByNDays(2);
        taskService.addCheckResult(expertTask1, expertTask1.getInterestedMarkets().get(0), expertTask1.getInterestedProducts().get(0), 1);
        taskService.addCheckResult(expertTask1, expertTask1.getInterestedMarkets().get(0), expertTask1.getInterestedProducts().get(1), 2);
        taskService.markTaskFinished(expertTask1.getMarketCheckTaskMap().get(expertTask1.getInterestedMarkets().get(0)));
        timeService.setCurrentDateLaterByNDays(5);
        taskService.addCheckResult(expertTask1, expertTask1.getInterestedMarkets().get(1), expertTask1.getInterestedProducts().get(0), 3);

        /////////////////////////////////////////////////////////////////////////////

        expertTask2 = new ExpertInspectionTask(
                "Expert Task2",
                Collections.singletonList(markets.get(1)),
                Collections.singletonList(products.get(0)),
                timeService.getDateOfNDaysLater(10), // deadLine
                experts.get(0)
        );
        timeService.setCurrentDateLaterByNDays(5);
        taskService.addCheckResult(expertTask2, markets.get(1), products.get(0), 0);
        taskService.markTaskFinished(expertTask2.getMarketCheckTaskMap().get(markets.get(1)));
        taskService.markTaskFinished(expertTask2);

        /////////////////////////////////////////////////////////////////////////////

        expertTask3 = new ExpertInspectionTask(
                "Expert Task3",
                Collections.singletonList(markets.get(1)),
                Collections.singletonList(products.get(2)),
                timeService.getDateOfNDaysLater(15), // deadLine
                experts.get(1)
        );
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
        marketTask2 = null;
        expertTask1 = null;
        expertTask2 = null;
        expertTask3 = null;
    }

    /**
     * 测试：专家按时完成和未按时完成的抽检的场景，获取评估总得分和评估得/扣分的记录
     */
    @Test
    void testGetExpertGradeInfo() { // todo
        timeService.setCurrentDateLaterByNDays(30);

        // test case 1: check single task1 which is long-overtime
        Map<Expert, GradeInfo> gradeInfoMap = inspectionService.getExpertGradeInfo(Collections.singletonList(expertTask1));
        assertEquals(1, gradeInfoMap.size());
        GradeInfo info = gradeInfoMap.get(expertTask1.getExpert());
        assertEquals(-30, info.getTotalGrade());
        assertEquals(2, info.getGradeTermList().size());
        assertTrue(info.getGradeTermList().contains(GradeTerm.OVERTIME));
        assertTrue(info.getGradeTermList().contains(GradeTerm.LONG_OVERTIME));

        // test case 2: check single task2 which is in-time
        gradeInfoMap = inspectionService.getExpertGradeInfo(Collections.singletonList(expertTask2));
        assertEquals(1, gradeInfoMap.size());
        info = gradeInfoMap.get(expertTask2.getExpert());
        assertEquals(10, info.getTotalGrade());
        assertEquals(1, info.getGradeTermList().size());
        assertTrue(info.getGradeTermList().contains(GradeTerm.IN_TIME));

        // test case 3: check a list of tasks
        // the experts in task1 and task2 are the same, different from the one in task3
        // grade of task1 is -30, grade of task2 is 10, grade of task3 is -10
        gradeInfoMap = inspectionService.getExpertGradeInfo(Arrays.asList(expertTask1, expertTask2, expertTask3));
        assertEquals(2, gradeInfoMap.size());
        info = gradeInfoMap.get(expertTask1.getExpert());
        assertEquals(-20, info.getTotalGrade()); // -20 -10 + 10
        assertEquals(3, info.getGradeTermList().size());
        assertTrue(info.getGradeTermList().contains(GradeTerm.OVERTIME));
        assertTrue(info.getGradeTermList().contains(GradeTerm.LONG_OVERTIME));
        assertTrue(info.getGradeTermList().contains(GradeTerm.IN_TIME));
        info = gradeInfoMap.get(expertTask3.getExpert());
        assertEquals(-10, info.getTotalGrade());
        assertEquals(1, info.getGradeTermList().size());
        assertTrue(info.getGradeTermList().contains(GradeTerm.OVERTIME));
    }

    /**
     * 测试：农贸市场按时完成和未按时完成的抽检的场景，获取评估总得分和评估得/扣分的记录
     */
    @Test
    void testGetMarketGradeInfo() {
        timeService.setCurrentDateLaterByNDays(12);
        taskService.markTaskFinished(marketTask1.getMarketCheckTaskMap().get(marketTask1.getInterestedMarkets().get(0))); // finished, overtime
        timeService.setCurrentDateLaterByNDays(4);
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(1), marketTask1.getInterestedProducts().get(1), 0);
        taskService.markTaskFinished(marketTask1.getMarketCheckTaskMap().get(marketTask1.getInterestedMarkets().get(1))); // finished, in time
        timeService.setCurrentDateLaterByNDays(30);
        taskService.addCheckResult(marketTask1, marketTask1.getInterestedMarkets().get(2), marketTask1.getInterestedProducts().get(0), 0);
        // for marketTask1.getInterestedMarkets().get(2), its task is not finished, and long overtime

        // test case 1
        Map<Market, GradeInfo> gradeInfoMap = inspectionService.getMarketGradeInfo(Collections.singletonList(marketTask1));
        assertEquals(-10, gradeInfoMap.get(marketTask1.getInterestedMarkets().get(0)).getTotalGrade());
        assertTrue(gradeInfoMap.get(marketTask1.getInterestedMarkets().get(0)).getGradeTermList().contains(GradeTerm.OVERTIME));
        assertEquals(10, gradeInfoMap.get(marketTask1.getInterestedMarkets().get(1)).getTotalGrade());
        assertTrue(gradeInfoMap.get(marketTask1.getInterestedMarkets().get(1)).getGradeTermList().contains(GradeTerm.IN_TIME));
        assertEquals(-30, gradeInfoMap.get(marketTask1.getInterestedMarkets().get(2)).getTotalGrade());
        assertTrue(gradeInfoMap.get(marketTask1.getInterestedMarkets().get(2)).getGradeTermList().contains(GradeTerm.OVERTIME));
        assertTrue(gradeInfoMap.get(marketTask1.getInterestedMarkets().get(2)).getGradeTermList().contains(GradeTerm.LONG_OVERTIME));

        // test case 2
        timeService.setCurrentDateLaterByNDays(8);
        gradeInfoMap = inspectionService.getMarketGradeInfo(Collections.singletonList(marketTask2)); // not finished, in time
        assertEquals(0, gradeInfoMap.size());

        // test case 3
        timeService.setCurrentDateLaterByNDays(12);
        gradeInfoMap = inspectionService.getMarketGradeInfo(Collections.singletonList(marketTask2)); // not finished, overtime
        assertEquals(2, gradeInfoMap.size());
        assertEquals(-10, gradeInfoMap.get(marketTask2.getInterestedMarkets().get(0)).getTotalGrade());
        assertTrue(gradeInfoMap.get(marketTask2.getInterestedMarkets().get(0)).getGradeTermList().contains(GradeTerm.OVERTIME));
        assertEquals(-10, gradeInfoMap.get(marketTask2.getInterestedMarkets().get(1)).getTotalGrade());
        assertTrue(gradeInfoMap.get(marketTask2.getInterestedMarkets().get(1)).getGradeTermList().contains(GradeTerm.OVERTIME));

        // test case 4: check for inspection task list
        timeService.setCurrentDateLaterByNDays(16);
        gradeInfoMap = inspectionService.getMarketGradeInfo(Arrays.asList(marketTask1, marketTask2));
        assertEquals(3, gradeInfoMap.size());
        assertEquals(-10, gradeInfoMap.get(markets.get(0)).getTotalGrade());
        assertEquals(1, gradeInfoMap.get(markets.get(0)).getGradeTermList().size());
        assertTrue(gradeInfoMap.get(markets.get(0)).getGradeTermList().contains(GradeTerm.OVERTIME));
        assertEquals(0, gradeInfoMap.get(markets.get(1)).getTotalGrade());
        assertEquals(2, gradeInfoMap.get(markets.get(1)).getGradeTermList().size());
        assertTrue(gradeInfoMap.get(markets.get(1)).getGradeTermList().contains(GradeTerm.IN_TIME));
        assertTrue(gradeInfoMap.get(markets.get(1)).getGradeTermList().contains(GradeTerm.OVERTIME));
        assertEquals(-20, gradeInfoMap.get(markets.get(2)).getTotalGrade());
        assertEquals(2, gradeInfoMap.get(markets.get(2)).getGradeTermList().size());
        assertEquals(GradeTerm.OVERTIME, gradeInfoMap.get(markets.get(2)).getGradeTermList().get(0));
        assertEquals(GradeTerm.OVERTIME, gradeInfoMap.get(markets.get(2)).getGradeTermList().get(1));
    }

    /**
     * 测试：查看某个农贸产品类别在某个时间范围内的总的不合格数（时间以抽检日期为准）
     */
    @Test
    void testGetProductTotalInvalidCountInRange() {
        // test case 1
        Map<Product, Integer> counts = inspectionService.getProductTotalInvalidCountInRange(Collections.singletonList(marketTask1),
                timeService.getDateOfNDaysLater(3), timeService.getDateOfNDaysLater(14));
        assertEquals(1, counts.size());
        assertEquals(4, counts.get(marketTask1.getInterestedProducts().get(0)));

        // test case 2
        counts = inspectionService.getProductTotalInvalidCountInRange(Collections.singletonList(marketTask2),
                timeService.getDateOfNDaysLater(-1), timeService.getDateOfNDaysLater(9));
        assertEquals(3, counts.size());
        assertEquals(1, counts.get(marketTask2.getInterestedProducts().get(0)));
        assertEquals(5, counts.get(marketTask2.getInterestedProducts().get(1)));
        assertEquals(7, counts.get(marketTask2.getInterestedProducts().get(2)));

        // test case 3
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
        // test case 1
        Map<Product, Integer> counts = inspectionService.getProductInvalidCount(marketTask1);
        assertEquals(2, counts.size());
        assertEquals(4, counts.get(marketTask1.getInterestedProducts().get(0)));
        assertEquals(2, counts.get(marketTask1.getInterestedProducts().get(1)));

        // test case 2
        counts = inspectionService.getProductInvalidCount(marketTask2);
        assertEquals(3, counts.size());
        assertEquals(3, counts.get(marketTask2.getInterestedProducts().get(0)));
        assertEquals(5, counts.get(marketTask2.getInterestedProducts().get(1)));
        assertEquals(7, counts.get(marketTask2.getInterestedProducts().get(2)));
    }
}