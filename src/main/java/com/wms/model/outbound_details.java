package com.wms.model;

public class outbound_details {
    private Integer id;

    private String outstocknumber;

    private Integer productnumber;

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

    public Integer getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(Integer productnumber) {
        this.productnumber = productnumber;
    }
}