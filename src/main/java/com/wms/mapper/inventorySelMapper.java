package com.wms.mapper;

import com.wms.model.inventoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface inventorySelMapper {

    List<inventoryVO> getAll(@Param("Name") String Name);
}
