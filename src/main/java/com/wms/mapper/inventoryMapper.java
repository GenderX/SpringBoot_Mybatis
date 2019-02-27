package com.wms.mapper;

import com.wms.model.inventory;

public interface inventoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(inventory record);

    int insertSelective(inventory record);

    inventory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(inventory record);

    int updateByPrimaryKey(inventory record);
}