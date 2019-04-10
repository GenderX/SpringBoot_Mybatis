package com.wms.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public class outbound_details {
    private Integer id;

    private String outstocknumber;

    private String productnumber;

    private Integer amount;

    private Integer placenumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutstocknumber() {
        return outstocknumber;
    }

    public void setOutstocknumber(String outstocknumber) {
        this.outstocknumber = outstocknumber == null ? null : outstocknumber.trim();
    }

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber == null ? null : productnumber.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPlacenumber() {
        return placenumber;
    }

    public void setPlacenumber(Integer placenumber) {
        this.placenumber = placenumber;
    }
}