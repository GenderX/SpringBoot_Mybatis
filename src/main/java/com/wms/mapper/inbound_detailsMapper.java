package com.wms.mapper;

import com.wms.model.inbound_details;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface inbound_detailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(inbound_details record);

    int insertSelective(inbound_details record);

    inbound_details selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(inbound_details record);

    int updateByPrimaryKey(inbound_details record);
}