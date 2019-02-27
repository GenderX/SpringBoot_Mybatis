package com.wms.mapper;

import com.wms.model.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface userMapper {
    int deleteByPrimaryKey(String username);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
}