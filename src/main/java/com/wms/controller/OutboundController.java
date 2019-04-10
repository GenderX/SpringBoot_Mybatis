package com.wms.controller;

import com.alibaba.fastjson.JSONObject;
import com.wms.model.inventory;
import com.wms.model.outbound_details;
import com.wms.model.outbound_master;
import com.wms.service.InventoryService;
import com.wms.service.OutboundService;
import com.wms.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/outbound")
public class OutboundController {
    @Autowired
    OutboundService outboundService;
    @Autowired
    InventoryService inventoryService;

    @RequestMapping("/CommitBach")
    public Object CommitBach(String effectRow, String customer, String Recipient) {
        //保存主表
        outbound_master master = new outbound_master();
        String id=UUIDGenerator.createID();
        master.setNumber(id);
        master.setCustomernumber(customer);
        master.setCreatetime(new Date());
        master.setRecipient(Recipient);
        master.setIsfinish(false);
        outboundService.insertMaster(master);
        //保存从表
        List<outbound_details> details = JSONObject.parseArray(effectRow, outbound_details.class);
        for (outbound_details detail : details) {
            detail.setOutstocknumber(id);
           inventory inventory= inventoryService.selectById(detail.getId());
            detail.setPlacenumber(inventory.getPlacenumber());
            outboundService.insertDetail(detail);
        }



        return id;

    }
}
