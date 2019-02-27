package com.wms.model;

public class staff_type {
    private String number;

    private String name;

    private Integer typenumber;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTypenumber() {
        return typenumber;
    }

    public void setTypenumber(Integer typenumber) {
        this.typenumber = typenumber;
    }
}