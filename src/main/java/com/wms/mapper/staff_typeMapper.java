package com.wms.mapper;

import com.wms.model.staff_type;

public interface staff_typeMapper {
    int deleteByPrimaryKey(String number);

    int insert(staff_type record);

    int insertSelective(staff_type record);

    staff_type selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(staff_type record);

    int updateByPrimaryKey(staff_type record);
}