package com.wms.mapper;

import com.wms.model.outbound_details;

public interface outbound_detailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(outbound_details record);

    int insertSelective(outbound_details record);

    outbound_details selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(outbound_details record);

    int updateByPrimaryKey(outbound_details record);
}