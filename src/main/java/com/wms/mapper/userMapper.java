package com.wms.mapper;

import com.wms.model.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface userMapper {
    int deleteByPrimaryKey(String username);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);

    List<user> selectAllOrByParams(@Param("username") String username);
}