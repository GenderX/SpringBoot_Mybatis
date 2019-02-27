package com.wms.mapper;

import com.wms.model.product;

public interface productMapper {
    int deleteByPrimaryKey(String number);

    int insert(product record);

    int insertSelective(product record);

    product selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(product record);

    int updateByPrimaryKey(product record);
}