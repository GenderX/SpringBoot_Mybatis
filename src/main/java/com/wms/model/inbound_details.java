package com.wms.model;

import java.util.Date;

public class inbound_details {
    private Integer id;

    private String instocknumber;

    private String productnumber;

    private Float amount;

    private Integer storehousenumber;

    private Date completetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstocknumber() {
        return instocknumber;
    }

    public void setInstocknumber(String instocknumber) {
        this.instocknumber = instocknumber == null ? null : instocknumber.trim();
    }

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber == null ? null : productnumber.trim();
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getStorehousenumber() {
        return storehousenumber;
    }

    public void setStorehousenumber(Integer storehousenumber) {
        this.storehousenumber = storehousenumber;
    }

    public Date getCompletetime() {
        return completetime;
    }

    public void setCompletetime(Date completetime) {
        this.completetime = completetime;
    }
}