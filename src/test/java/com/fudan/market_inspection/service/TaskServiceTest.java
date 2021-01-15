package com.fudan.market_inspection.service;

import com.fudan.market_inspection.dao.CheckTask;
import com.fudan.market_inspection.dao.ExpertInspectionTask;
import com.fudan.market_inspection.dao.SelfInspectionTask;
import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.Impl.DataServiceImpl;
import com.fudan.market_inspection.service.Impl.TaskServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class TaskServiceTest {
    List<Expert> experts;
    List<Market> markets;
    List<Product> products;

    SelfInspectionTask marketTask;
    ExpertInspectionTask expertTask;

    DataService dataService;
    TaskService taskService;
    TimeService timeService;

    @BeforeEach
    void setUp() {
        dataService = new DataServiceImpl();
        timeService = new TimeServiceMockImpl();
        taskService = new TaskServiceImpl(timeService);

        experts = dataService.getAllExperts();
        markets = dataService.getAllMarkets();
        products = dataService.getAllProducts();

        marketTask = new SelfInspectionTask(
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
    }

    @AfterEach
    void tearDown() {
        dataService = null;
        taskService = null;
        timeService = null;

        experts = null;
        markets = null;
        products = null;

        marketTask = null;
        expertTask = null;
    }

    @Test
    void testMarketGetUnfinishedContents() {
        Map<CheckTask, List<Product>> unfinishedContents = taskService.getUnfinishedContents(marketTask);
        // assert have equal number of check tasks
        assertEquals(marketTask.getMarketCheckTaskMap().size(), unfinishedContents.size());
        for (CheckTask checkTask : unfinishedContents.keySet()) {
            // assert checkTask.interestedMarket is contained in task.interestedMarkets
            assertTrue(marketTask.getInterestedMarkets().contains(checkTask.getInterestedMarket()));
            // assert interested products are equal
            List<Product> actualProducts = checkTask.getInterestedProducts();
            List<Product> expectedProducts = marketTask.getInterestedProducts();
            assertTrue(actualProducts.containsAll(expectedProducts));
            assertTrue(expectedProducts.containsAll(actualProducts));
        }
    }

    @Test
    void testMarkMarketTaskFinished() {
        // finish check tasks first
        for (Market market : marketTask.getInterestedMarkets()) {
            for (Product product : marketTask.getInterestedProducts()) {
                // add check result
                taskService.addCheckResult(marketTask, market, product, 1);
            }
            // mark check task finished
            taskService.markTaskFinished(marketTask.getMarketCheckTaskMap().get(market));
        }
        // mark inspection task finished
        taskService.markTaskFinished(marketTask);
        // check if all check tasks finished
        assertEquals(0, taskService.getUnfinishedContents(marketTask).size());
    }

    @Test
    void testExpertGetUnfinishedContents() {
        Map<CheckTask, List<Product>> unfinishedContents = taskService.getUnfinishedContents(expertTask);
        // assert have equal number of check tasks
        assertEquals(expertTask.getMarketCheckTaskMap().size(), unfinishedContents.size());
        for (CheckTask checkTask : unfinishedContents.keySet()) {
            // assert checkTask.interestedMarket is contained in task.interestedMarkets
            assertTrue(expertTask.getInterestedMarkets().contains(checkTask.getInterestedMarket()));
            // assert interested products are equal
            List<Product> actualProducts = checkTask.getInterestedProducts();
            List<Product> expectedProducts = expertTask.getInterestedProducts();
            assertTrue(actualProducts.containsAll(expectedProducts));
            assertTrue(expectedProducts.containsAll(actualProducts));
        }
    }

    @Test
    void testMarkExpertTaskFinished() {
        // finish check tasks first
        for (Market market : expertTask.getInterestedMarkets()) {
            for (Product product : expertTask.getInterestedProducts()) {
                // add check result
                taskService.addCheckResult(expertTask, market, product, 1);
            }
            // mark check task finished
            taskService.markTaskFinished(expertTask.getMarketCheckTaskMap().get(market));
        }
        // mark inspection task finished
        taskService.markTaskFinished(expertTask);
        // check if all check tasks finished
        assertEquals(0, taskService.getUnfinishedContents(expertTask).size());
    }

    @Test
    void testMarketTaskHalfFinished() {
        // finish all first market's tasks
        Market firstMarket = marketTask.getInterestedMarkets().get(0);
        for (Product product : marketTask.getInterestedProducts()) {
            // add check result
            taskService.addCheckResult(marketTask, firstMarket, product, 1);
        }
        // mark check task finished
        taskService.markTaskFinished(marketTask.getMarketCheckTaskMap().get(firstMarket));

        // finish half of second market's tasks
        Market secondMarket = marketTask.getInterestedMarkets().get(1);
        Product firstProduct = marketTask.getInterestedProducts().get(0);
        taskService.addCheckResult(marketTask, secondMarket, firstProduct, 1);

        // leave third market's tasks unfinished
        Market thirdMarket = marketTask.getInterestedMarkets().get(2);

        // check
        Map<CheckTask, List<Product>> unfinishedContents = taskService.getUnfinishedContents(marketTask);

        // assert have equal number of check tasks
        assertEquals(2, unfinishedContents.size());

        // assert can get those markets that have not completed tasks
        List<Market> expectedMarkets = Arrays.asList(secondMarket, thirdMarket);
        List<Market> actualMarkets = new ArrayList<>();
        for (CheckTask checkTask : unfinishedContents.keySet()) {
            actualMarkets.add(checkTask.getInterestedMarket());
        }
        assertTrue(actualMarkets.containsAll(expectedMarkets));
        assertTrue(expectedMarkets.containsAll(actualMarkets));

        // assert second market's task
        CheckTask secondTask = marketTask.getMarketCheckTaskMap().get(secondMarket);
        assertTrue((unfinishedContents.containsKey(secondTask)));
        List<Product> actualProductsSecond = unfinishedContents.get(secondTask);
        List<Product> expectedProductsSecond = Collections.singletonList(products.get(1));
        assertTrue(actualProductsSecond.containsAll(expectedProductsSecond));
        assertTrue(expectedProductsSecond.containsAll(actualProductsSecond));

        // assert third market's task
        CheckTask thirdTask = marketTask.getMarketCheckTaskMap().get(thirdMarket);
        assertTrue((unfinishedContents.containsKey(thirdTask)));
        List<Product> actualProducts = thirdTask.getInterestedProducts();
        List<Product> expectedProducts = marketTask.getInterestedProducts();
        assertTrue(actualProducts.containsAll(expectedProducts));
        assertTrue(expectedProducts.containsAll(actualProducts));
    }
}