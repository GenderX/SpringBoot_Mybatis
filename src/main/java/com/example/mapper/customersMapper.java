package com.example.mapper;

import com.example.model.customers;
import com.example.model.customersExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface customersMapper {
    int countByExample(customersExample example);

    int deleteByPrimaryKey(String customerid);

    int insert(customers record);

    int insertSelective(customers record);

    List<customers> selectByExample(customersExample example);

    customers selectByPrimaryKey(String customerid);

    int updateByPrimaryKeySelective(customers record);

    int updateByPrimaryKey(customers record);

    void selectByExample();

    List<customers> selectAll();

    List<customers> selectAllOrID(String customerid, String companyname);
}