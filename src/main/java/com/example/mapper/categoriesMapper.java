package com.example.mapper;

import com.example.model.categories;
import com.example.model.categoriesWithBLOBs;

public interface categoriesMapper {
    int deleteByPrimaryKey(Integer categoryid);

    int insert(categoriesWithBLOBs record);

    int insertSelective(categoriesWithBLOBs record);

    categoriesWithBLOBs selectByPrimaryKey(Integer categoryid);

    int updateByPrimaryKeySelective(categoriesWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(categoriesWithBLOBs record);

    int updateByPrimaryKey(categories record);
}