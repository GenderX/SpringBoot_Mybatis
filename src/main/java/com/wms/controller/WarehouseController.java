package com.wms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wms.model.warehouse;
import com.wms.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @RequestMapping("/getAll")
    public Object getAll(int page, int rows, String Number, String Name) {
        Page<warehouse> rowpage = PageHelper.startPage(page, rows);
        List<warehouse> list = warehouseService.getAll(Number, Name);
        return list;
    }
}