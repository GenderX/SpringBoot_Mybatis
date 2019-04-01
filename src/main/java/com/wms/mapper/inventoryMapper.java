package com.wms.mapper;

import com.wms.model.inventory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface inventoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(inventory record);

    int insertSelective(inventory record);

    inventory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(inventory record);

    int updateByPrimaryKey(inventory record);
}