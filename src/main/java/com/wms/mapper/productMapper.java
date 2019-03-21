package com.wms.mapper;

import com.wms.model.product;
import com.wms.model.productVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface productMapper {
    int deleteByPrimaryKey(String number);

    int insert(product record);

    int insertSelective(product record);

    product selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(product record);

    int updateByPrimaryKey(product record);

    List<productVO> selectAll(String number, String name);
}
