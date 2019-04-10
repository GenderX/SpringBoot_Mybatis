package com.wms.model;

public class inventoryVO extends inventory {
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "inventoryVO{" +
                "productName='" + productName + '\'' +
                '}';
    }
}
