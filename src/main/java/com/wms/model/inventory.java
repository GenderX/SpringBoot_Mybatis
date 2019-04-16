package com.wms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class inventory {
    private Integer id;

    private Integer storehousenumber;

    private String productnumber;

    private Float amount;

    private Integer areanumber;

    private Integer shelfnumber;

    private Integer placenumber;

    private Date instocktime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStorehousenumber() {
        return storehousenumber;
    }

    public void setStorehousenumber(Integer storehousenumber) {
        this.storehousenumber = storehousenumber;
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

    public Integer getAreanumber() {
        return areanumber;
    }

    public void setAreanumber(Integer areanumber) {
        this.areanumber = areanumber;
    }

    public Integer getShelfnumber() {
        return shelfnumber;
    }

    public void setShelfnumber(Integer shelfnumber) {
        this.shelfnumber = shelfnumber;
    }

    public Integer getPlacenumber() {
        return placenumber;
    }

    public void setPlacenumber(Integer placenumber) {
        this.placenumber = placenumber;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getInstocktime() {
        return instocktime;
    }

    public void setInstocktime(Date instocktime) {
        this.instocktime = instocktime;
    }
}