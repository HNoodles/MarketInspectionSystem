package com.fudan.market_inspection.service;

import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;

import java.util.List;

public interface DataService {
    /**
     * This method should return all experts in the system.
     * @return a list of all experts in the system.
     */
    List<Expert> getAllExperts();

    /**
     * This method should return all markets in the system.
     * @return a list of markets in the system.
     */
    List<Market> getAllMarkets();

    /**
     * This method should return all products in the system.
     * @return a list of products in the system.
     */
    List<Product> getAllProducts();
}
