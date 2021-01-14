package com.fudan.market_inspection.service;

import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.Impl.DataServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {
    DataService dataService;

    @BeforeEach
    void setUp() {
        dataService = new DataServiceImpl();
    }

    @AfterEach
    void tearDown() {
        dataService = null;
    }

    @Test
    void testGetAllExperts() {
        List<Expert> experts = dataService.getAllExperts();
        assertEquals(3, experts.size());
    }

    @Test
    void testGetAllMarkets() {
        List<Market> markets = dataService.getAllMarkets();
        assertEquals(3, markets.size());
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = dataService.getAllProducts();
        assertEquals(3, products.size());
    }
}