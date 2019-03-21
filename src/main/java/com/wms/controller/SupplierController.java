package com.wms.controller;

import com.github.pagehelper.PageHelper;
import com.wms.model.supplier;
import com.wms.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    /**
     * 根据条件查询供应商，条件为空查询所有供应商
     *
     * @param page
     * @param rows
     * @param Number
     * @param Name
     * @return
     */

    @RequestMapping("/getAll")
    public List<supplier> getAll(int page, int rows, String Number, String Name) {
        PageHelper.startPage(page, rows);
        List<supplier> supplierList = supplierService.selectAllOrID(Name, Number);
        return supplierList;
    }

    /**
     * 插入供应商
     *
     * @param sp
     * @return
     */
    @RequestMapping("/insertSupplier")
    public Object insertSupplier(supplier sp) {
        Boolean flag = false;
        Integer insert = 0;
        String errorMsg;
        HashMap<String, Object> map = new HashMap<>();
        try {
            insert = supplierService.insert(sp);
            if (insert != 0) {
                flag = true;
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
            map.put("errorMsg", errorMsg);
        }
        map.put("success", flag);
        return map;
    }

    /**
     * 更新供应商
     *
     * @param sp
     * @return
     */
    @RequestMapping("/updateSupplier")
    public Object updateSupplier(supplier sp) {
        Integer update = supplierService.updateStaff(sp);
        HashMap<String, Object> map = new HashMap<>();
        if (update == 0) {
            String errorMes = "更新失败";
            map.put("errorMsg", errorMes);
        }
        map.put("success", true);
        return map;
    }
    @RequestMapping("/deleteSupplier")
    public Object deleteSupplier(String number){
        supplierService.deleteCustomer(number);
        HashMap<String, Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }
}
