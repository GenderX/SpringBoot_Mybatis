package com.example.mapper;

import com.example.model.products;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface productsMapper {
    int deleteByPrimaryKey(Integer productid);

    int insert(products record);

    int insertSelective(products record);

    products selectByPrimaryKey(Integer productid);

    int updateByPrimaryKeySelective(products record);

    int updateByPrimaryKey(products record);
}