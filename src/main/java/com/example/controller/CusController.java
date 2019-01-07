package com.example.controller;

import com.example.dao.CusDao;
import com.example.mapper.customersMapper;
import com.example.model.customers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    @RequestMapping("/{id}")
  public customers select(@PathVariable String id){
        customers customers = customersMapper.selectByPrimaryKey(id);
        //   customers customers = cusDao.slectById(id);
        return customers;
    }
    @RequestMapping("/hello")
    public String hello(){
        return "index";
    }
    @GetMapping("getAll")
    @ResponseBody
    public Object getAllCus(int page,int rows){
        Page<customers> rowpage = PageHelper.startPage(page, rows);
       List<customers> customers = customersMapper.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("rows",customers);
        data.put("total",rowpage.getTotal());
        return data;
    }

}
