package com.wms.service;

import com.wms.mapper.staffMapper;
import com.wms.mapper.userMapper;
import com.wms.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class userService {
    @Autowired
    userMapper mapper;

    public user selectByPrimaryKey(String username) {
        user data = mapper.selectByPrimaryKey(username);

        return data;
    }

    public int insert(user record){
        int insert = mapper.insert(record);
        return insert;
    }



    public int insertSelective(user record){
        int data = mapper.insertSelective(record);

        return data;
    }

    public int updateByPrimaryKeySelective(user record){
        int data = mapper.updateByPrimaryKeySelective(record);

        return data;
    }

    public int updateByPrimaryKey(user record){
        int data = mapper.updateByPrimaryKey(record);

        return data;
    }

    public int deleteByPrimaryKey(String username) {
        int data = mapper.deleteByPrimaryKey(username);

        return data;
    }
}
