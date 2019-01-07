package com.example.mapper;

import com.example.model.employees;
import com.example.model.employeesWithBLOBs;

public interface employeesMapper {
    int deleteByPrimaryKey(Integer employeeid);

    int insert(employeesWithBLOBs record);

    int insertSelective(employeesWithBLOBs record);

    employeesWithBLOBs selectByPrimaryKey(Integer employeeid);

    int updateByPrimaryKeySelective(employeesWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(employeesWithBLOBs record);

    int updateByPrimaryKey(employees record);
}