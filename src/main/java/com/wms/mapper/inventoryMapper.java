package com.wms.mapper;

import com.wms.model.WHPieChartBO;
import com.wms.model.inventory;
import com.wms.model.inventoryVO;
import com.wms.model.weeklyAmountBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface inventoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(inventory record);

    int insertSelective(inventory record);

    inventory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(inventory record);

    int updateByPrimaryKey(inventory record);

    List<inventoryVO> getAll();

    List<inventoryVO> getAllSel(@Param("Name") String Name);

    List<weeklyAmountBO> selectWeeklyInboundAmount();

    List<weeklyAmountBO> selectWeeklyOutboundAmount();
    List<WHPieChartBO> selectWHPieChartBO();

}