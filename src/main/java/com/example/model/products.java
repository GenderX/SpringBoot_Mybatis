package com.example.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
@Repository
public class products {
    private Integer productid;

    private String productname;

    private Integer supplierid;

    private Integer categoryid;

    private String quantityperunit;

    private BigDecimal unitprice;

    private Short unitsinstock;

    private Short unitsonorder;

    private Short reorderlevel;

    private Boolean discontinued;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getQuantityperunit() {
        return quantityperunit;
    }

    public void setQuantityperunit(String quantityperunit) {
        this.quantityperunit = quantityperunit == null ? null : quantityperunit.trim();
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public Short getUnitsinstock() {
        return unitsinstock;
    }

    public void setUnitsinstock(Short unitsinstock) {
        this.unitsinstock = unitsinstock;
    }

    public Short getUnitsonorder() {
        return unitsonorder;
    }

    public void setUnitsonorder(Short unitsonorder) {
        this.unitsonorder = unitsonorder;
    }

    public Short getReorderlevel() {
        return reorderlevel;
    }

    public void setReorderlevel(Short reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }
}