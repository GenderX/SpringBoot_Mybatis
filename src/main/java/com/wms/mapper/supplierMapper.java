package com.wms.mapper;

import com.wms.model.supplier;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface supplierMapper {
    int deleteByPrimaryKey(String number);

    int insert(supplier record);

    int insertSelective(supplier record);

    supplier selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(supplier record);

    int updateByPrimaryKey(supplier record);

    List<supplier> selectAll(String name, String number);
}