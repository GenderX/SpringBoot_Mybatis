package com.wms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wms.model.warehouse;
import com.wms.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    /**
     * 不分页查询库位
     *
     * @param Number
     * @param Name
     * @return
     */
    @RequestMapping("/getAllCombo")
    public Object getAll(String Number, String Name) {
        List<warehouse> list = warehouseService.getAll(Number, Name);
        return list;
    }

    @RequestMapping("/insertHouseNumber")
    public Object insertHousenumber(warehouse wh) {
        Boolean flag = false;
        Integer insert = 0;
        String errorMsg;
        HashMap<String, Object> map = new HashMap<>();
        try {
            insert = warehouseService.insert(wh);
            if (insert != 0) {
                flag = true;
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
            map.put("errorMsg", "插入失败，请检查编号是否重复");
        }
        map.put("success", flag);
        return map;
    }

    @RequestMapping("/updateHouseNumber")
    public Object updateHouseNumber(warehouse wh) {
        Integer update = warehouseService.updateStaff(wh);
        HashMap<String, Object> map = new HashMap<>();
        if (update == 0) {
            String errorMes = "更新失败";
            map.put("errorMsg", errorMes);
        }
        map.put("success", true);
        return map;
    }

    @RequestMapping("/deleteHouseNumber")
    public Object delectWh(Integer number) {
        Object o = warehouseService.deleteWH(number);
        System.out.println(o.toString());
        return o;
    }
}