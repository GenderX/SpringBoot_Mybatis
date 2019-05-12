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

    /**
     * 查询本周七天每天的入库数量
     *
     * @return weeklyAmountBO的List
     * +------------+---------+--------+
     * | date       | weekday | Amount |
     * +------------+---------+--------+
     * | 2019-04-22 |       1 |    900 |
     * +------------+---------+--------+
     */
    List<weeklyAmountBO> selectWeeklyInboundAmount();

    /**
     * 查询本周七天每天的出库数量
     *
     * @return weeklyAmountBO的list
     */
    List<weeklyAmountBO> selectWeeklyOutboundAmount();

    /**
     * 查询库位库存数量
     *
     * @return WHPieChartBO的list
     */
    List<WHPieChartBO> selectWHPieChartBO();

}