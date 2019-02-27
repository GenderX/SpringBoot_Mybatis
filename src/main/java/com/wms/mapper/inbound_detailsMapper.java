package com.wms.mapper;

import com.wms.model.inbound_details;

public interface inbound_detailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(inbound_details record);

    int insertSelective(inbound_details record);

    inbound_details selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(inbound_details record);

    int updateByPrimaryKey(inbound_details record);
}