package com.wms.service;

import com.wms.mapper.inventoryMapper;
import com.wms.model.WHPieChartBO;
import com.wms.model.weeklyAmountBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChartService {
    @Autowired
    inventoryMapper IM;

    public List<weeklyAmountBO> selectWeeklyInboundAmount() {
        return IM.selectWeeklyInboundAmount();
    }

    public List<weeklyAmountBO> selectWeeklyOutboundAmount() {
        return IM.selectWeeklyOutboundAmount();
    }
    public List<WHPieChartBO> selectWHPieChartBO(){
        return IM.selectWHPieChartBO();
    }
}
