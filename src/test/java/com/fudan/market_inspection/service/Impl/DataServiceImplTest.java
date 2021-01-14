package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.DataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceImplTest {
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
    void getAllExperts() {
        List<Expert> experts = dataService.getAllExperts();
        assertEquals(experts.size(), 3);
    }

    @Test
    void getMarkets() {
    }

    @Test
    void getAllProducts() {
    }
}