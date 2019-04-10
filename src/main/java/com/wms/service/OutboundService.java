package com.wms.service;

import com.wms.mapper.outbound_detailsMapper;
import com.wms.mapper.outbound_masterMapper;
import com.wms.model.outbound_details;
import com.wms.model.outbound_master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OutboundService {
    @Autowired
    outbound_masterMapper masterMapper;
    @Autowired
    outbound_detailsMapper detailsMapper;
    public void insertDetail(outbound_details detail) {
        detailsMapper.insert(detail);
    }

    public void insertMaster(outbound_master master) {
        masterMapper.insert(master);
    }
}
