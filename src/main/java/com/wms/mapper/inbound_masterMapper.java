package com.wms.mapper;

import com.wms.model.inboundVO;
import com.wms.model.inbound_master;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface inbound_masterMapper {
    int deleteByPrimaryKey(String number);

    int insert(inbound_master record);

    int insertSelective(inbound_master record);

    inbound_master selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(inbound_master record);

    int updateByPrimaryKey(inbound_master record);

    List<inboundVO> selectByNum(@Param("number") String number);

}