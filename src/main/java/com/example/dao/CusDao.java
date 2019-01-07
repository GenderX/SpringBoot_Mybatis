package com.example.dao;

import com.example.model.customers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：LiCan
 * @date ：Created in 2019/1/4 23:15
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Mapper
@Repository
public interface CusDao {
    customers slectById(String id);

    List<customers> selectAllCustomers();

}

