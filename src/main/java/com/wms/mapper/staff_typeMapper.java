package com.wms.mapper;

import com.wms.model.staff_type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface staff_typeMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(staff_type record);

    int insertSelective(staff_type record);

    staff_type selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(staff_type record);

    int updateByPrimaryKey(staff_type record);

    List<staff_type> selectAll();
}