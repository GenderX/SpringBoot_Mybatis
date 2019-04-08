package com.wms.mapper;

import com.wms.model.staff;
import com.wms.model.staffVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface staffMapper {
    int deleteByPrimaryKey(String number);

    int insert(staff record);

    int insertSelective(staff record);

    staff selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(staff record);

    int updateByPrimaryKey(staff record);

    List<staffVO> selectAllOrID(@Param("name") String name, @Param("number") String number);
}