package com.wms.mapper;

import com.wms.model.warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface warehouseMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(warehouse record);

    int insertSelective(warehouse record);

    warehouse selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(warehouse record);

    int updateByPrimaryKey(warehouse record);

    List<warehouse> selectAllOrQuery(String number, String name);
}