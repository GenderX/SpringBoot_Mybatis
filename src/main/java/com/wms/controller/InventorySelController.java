package com.wms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wms.mapper.inventoryMapper;
import com.wms.mapper.inventorySelMapper;
import com.wms.model.inventoryVO;
import com.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventorySelController {


    @Autowired
    inventorySelMapper mapper ;
    @RequestMapping("/getAll")
    public Object getAllInventory(String Name,int page, int rows){
        Page<inventoryVO> inventoryVO = PageHelper.startPage(page, rows);
        List<inventoryVO> inventory = mapper.getAll(Name);

        HashMap<String, Object> data = new HashMap<>();
        data.put("rows", inventory);
        data.put("total", inventoryVO.getTotal());
        return data;
    }
}
