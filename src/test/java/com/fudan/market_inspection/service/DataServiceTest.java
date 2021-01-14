package com.fudan.market_inspection.service;

import com.fudan.market_inspection.entity.Expert;
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
        assertEquals(experts.size(), 3);
    }

    @Test
    void testGetAllMarkets() {
    }

    @Test
    void testGetAllProducts() {
    }
}