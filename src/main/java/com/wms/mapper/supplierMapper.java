package com.wms.mapper;

import com.wms.model.supplier;

public interface supplierMapper {
    int deleteByPrimaryKey(String number);

    int insert(supplier record);

    int insertSelective(supplier record);

    supplier selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(supplier record);

    int updateByPrimaryKey(supplier record);
}