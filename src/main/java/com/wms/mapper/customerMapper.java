package com.wms.mapper;

import com.wms.model.customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface customerMapper {
    int deleteByPrimaryKey(String number);

    int insert(customer record);

    int insertSelective(customer record);

    customer selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(customer record);

    int updateByPrimaryKey(customer record);

    List<customer> selectAllOrID(String Number, String Name);
}