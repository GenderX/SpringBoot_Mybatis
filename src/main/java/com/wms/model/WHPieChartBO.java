package com.wms.model;

public class WHPieChartBO {
    private String Name;
    private Integer Amount;
    private Integer StoreHouseNumber;


    public Integer getStoreHouseNumber() {
        return StoreHouseNumber;
    }

    public void setStoreHouseNumber(Integer storeHouseNumber) {
        StoreHouseNumber = storeHouseNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }
}
