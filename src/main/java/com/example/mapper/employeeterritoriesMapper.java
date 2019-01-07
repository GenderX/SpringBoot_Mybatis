package com.example.mapper;

import com.example.model.employeeterritoriesKey;

public interface employeeterritoriesMapper {
    int deleteByPrimaryKey(employeeterritoriesKey key);

    int insert(employeeterritoriesKey record);

    int insertSelective(employeeterritoriesKey record);
}