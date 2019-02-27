package com.wms.mapper;

import com.wms.model.warehouse;

public interface warehouseMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(warehouse record);

    int insertSelective(warehouse record);

    warehouse selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(warehouse record);

    int updateByPrimaryKey(warehouse record);
}