package com.wms.controller;

import com.wms.mapper.customerMapper;
import com.wms.model.customer;
import com.wms.service.CustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**

 */
@Controller
@RequestMapping(value = "/customer")
public class CusController {
    @Autowired
    customerMapper customersMapper;
    @Autowired
    CustomerService customerService;

    /**
     * 更新客户信息
     * @param customers
     * @return
     */
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


    /**
     * 根据条件查询客户，条件为空查询所有客户
     * @param page
     * @param rows
     * @param Number
     * @param Name
     * @return
     */
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

    /**
     * 不分页查询所有客户
     * @return
     */
    @GetMapping("getAllCombo")
    @ResponseBody
    public Object getAllCombo(String Number, String Name) {
        List<customer> customers = customersMapper.selectAllOrID(Number, Name);
        return customers;
    }

    /**
     * 插入
     * @param customers
     * @return
     */

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
            map.put("errorMsg", errorMsg);
        }
        map.put("success", flag);
        return map;
    }

    /**
     * 删除
     * @param number
     * @return
     */

    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public Object deleteCustomer(String number){
        customerService.deleteCustomer(number);
        HashMap<String, Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }


}
