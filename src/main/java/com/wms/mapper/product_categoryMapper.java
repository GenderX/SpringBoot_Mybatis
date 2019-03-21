package com.wms.mapper;

import com.wms.model.product_category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface product_categoryMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(product_category record);

    int insertSelective(product_category record);

    product_category selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(product_category record);

    int updateByPrimaryKey(product_category record);

    List<product_category> selectAll();
}