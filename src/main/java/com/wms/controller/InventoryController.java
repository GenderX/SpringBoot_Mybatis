package com.wms.controller;

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
    @RequestMapping("/getAllCombo")
    public Object getAllInventory(){
     List<inventoryVO>  inventory= inventoryService.getAllCombo();
        System.out.println(inventory.toString());
     return inventory;

    }


}
