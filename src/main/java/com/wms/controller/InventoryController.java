package com.wms.controller;

import com.wms.mapper.inventoryMapper;
import com.wms.model.inventoryVO;
import com.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    inventoryMapper mapper ;
    @RequestMapping("/getAllCombo")
    public Object getAllInventory(){
        List<inventoryVO> data = inventoryService.getAllCombo();
        return data;
    }


}
