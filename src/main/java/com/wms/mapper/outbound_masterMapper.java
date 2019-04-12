package com.wms.mapper;

import com.wms.model.outbound_master;
import com.wms.model.outbound_masterVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface outbound_masterMapper {
    int deleteByPrimaryKey(String number);

    int insert(outbound_master record);

    int insertSelective(outbound_master record);

    outbound_master selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(outbound_master record);

    int updateByPrimaryKey(outbound_master record);

    List<outbound_masterVO> selectAll(@Param("number") String number);
}