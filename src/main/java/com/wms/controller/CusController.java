package com.wms.controller;

import com.wms.mapper.customerMapper;
import com.wms.model.customer;
import com.wms.service.CustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author ：LiCan
 * @date ：Created in 2019/1/4 23:21
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping(value = "/customer")
public class CusController {
    @Autowired
    customerMapper customersMapper;
    @Autowired
    CustomerService customerService;

    @RequestMapping("updateCustomer")
    @ResponseBody
    public Object updateCustomer(customer customers) {
        HashMap<String, Object> map = new HashMap<>();
        int i = customersMapper.updateByPrimaryKeySelective(customers);
        if (i == 0) {
            String errorMes = "更新失败";
            map.put("errorMsg", errorMes);
        }
        map.put("success", true);
        return map;
    }


    @GetMapping("getAll")
    @ResponseBody
    public Object getAllCus(int page, int rows, String Number, String Name) {
        Page<customer> rowpage = PageHelper.startPage(page, rows);
        List<customer> customers = customersMapper.selectAllOrID(Number, Name);
        HashMap<String, Object> data = new HashMap<>();
        data.put("rows", customers);
        data.put("total", rowpage.getTotal());
        return data;
    }

    @RequestMapping("/insertCus")
    @ResponseBody
    public Object insertCus(customer customers) {
        Boolean flag = false;
        Integer insert = 0;
        String errorMsg;
        HashMap<String, Object> map = new HashMap<>();
        try {
          insert =customerService.insert(customers);
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

    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public Object deleteCustomer(String number){
        int i = customerService.deleteCustomer(number);
        HashMap<String, Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }


}
