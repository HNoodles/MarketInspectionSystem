package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.entity.Expert;
import com.fudan.market_inspection.entity.Market;
import com.fudan.market_inspection.entity.Product;
import com.fudan.market_inspection.service.DataService;
import com.fudan.market_inspection.utils.DataUtils;

import java.util.List;

public class DataServiceImpl implements DataService {
    @Override
    public List<Expert> getAllExperts() {
        return DataUtils.getObjects(Expert.class);
    }

    @Override
    public List<Market> getMarkets() {
        return DataUtils.getObjects(Market.class);
    }

    @Override
    public List<Product> getAllProducts() {
        return DataUtils.getObjects(Product.class);
    }
}
