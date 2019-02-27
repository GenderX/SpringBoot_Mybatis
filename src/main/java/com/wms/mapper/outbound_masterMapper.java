package com.wms.mapper;

import com.wms.model.outbound_master;

public interface outbound_masterMapper {
    int deleteByPrimaryKey(String number);

    int insert(outbound_master record);

    int insertSelective(outbound_master record);

    outbound_master selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(outbound_master record);

    int updateByPrimaryKey(outbound_master record);
}