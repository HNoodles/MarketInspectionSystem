package com.fudan.market_inspection.entity;

import com.fudan.market_inspection.dao.Product;

import java.util.Date;

public class CheckResult {
    private final Product product;
    private final int invalidCount;
    private final Date uploadDate;

    public CheckResult(Product product, int invalidCount, Date uploadDate) {
        this.product = product;
        this.invalidCount = invalidCount;
        this.uploadDate = uploadDate;
    }

    public Product getProduct() {
        return product;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public Date getUploadDate() {
        return uploadDate;
    }
}
