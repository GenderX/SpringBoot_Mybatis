package com.wms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wms.mapper.inventoryMapper;
import com.wms.model.inventoryVO;
import com.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @RequestMapping("/getAll")
    public Object getAllInventory(String Name,int page, int rows){
        Page<inventoryVO> inventoryVO = PageHelper.startPage(page, rows);
        List<inventoryVO> inventory = mapper.getAllSel(Name);
        HashMap<String, Object> data = new HashMap<>();
        data.put("rows", inventory);
        data.put("total", inventoryVO.getTotal());
        return data;
    }

}
