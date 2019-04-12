package com.wms.mapper;

import com.wms.model.outbound_details;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface outbound_detailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(outbound_details record);

    int insertSelective(outbound_details record);

    outbound_details selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(outbound_details record);

    int updateByPrimaryKey(outbound_details record);

    List<outbound_details> selectByOutBoundNumber(@Param("number") String num);
}