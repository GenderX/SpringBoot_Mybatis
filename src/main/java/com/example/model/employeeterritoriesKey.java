package com.example.model;

public class employeeterritoriesKey {
    private Integer employeeid;

    private String territoryid;

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getTerritoryid() {
        return territoryid;
    }

    public void setTerritoryid(String territoryid) {
        this.territoryid = territoryid == null ? null : territoryid.trim();
    }
}