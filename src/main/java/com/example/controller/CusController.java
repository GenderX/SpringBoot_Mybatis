package com.example.controller;

import com.example.mapper.customersMapper;
import com.example.model.customers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    customersMapper customersMapper;

    @RequestMapping("updateCustomer")
    @ResponseBody
    public Object updateCustomer(customers customers) {
        HashMap<String, Object> map = new HashMap<>();
        int i = customersMapper.updateByPrimaryKeySelective(customers);
        if (i==0){
            String errorMes="更新失败";
            map.put("errorMsg", errorMes);
        }
        map.put("success", true);
        return map;
    }


    @GetMapping("getAll")
    @ResponseBody
    public Object getAllCus(int page, int rows, String customerid, String companyname) {
        Page<customers> rowpage = PageHelper.startPage(page, rows);
        //  List<customers> customers = customersMapper.selectAll();
        List<customers> customers = customersMapper.selectAllOrID(customerid, companyname);
        HashMap<String, Object> data = new HashMap<>();
        data.put("rows", customers);
        data.put("total", rowpage.getTotal());
        return data;
    }

    @RequestMapping("/insertCus")
    @ResponseBody
    public Object insertCus(customers customers) {
        Boolean flag = false;
        Integer insert = 0;
        String errorMsg;
        HashMap<String, Object> map = new HashMap<>();
        try {
            insert = customersMapper.insert(customers);
            if (insert != 0) {
                flag = true;
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
            map.put("errorMsg", errorMsg);
        }

        map.put("success", true);

        return map;
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
