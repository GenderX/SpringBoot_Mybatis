package com.example.controller;

import com.example.dao.CusDao;
import com.example.mapper.customersMapper;
import com.example.model.customers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.zip.CheckedOutputStream;

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
    @RequestMapping("/insertCus")
    @ResponseBody
    public Object insertCus(customers customers){
        Boolean flag=false;
        Integer insert=0;
        String errorMsg;
        HashMap<String, Object> map = new HashMap<>();
        try {
           insert = customersMapper.insert(customers);
            if (insert!=0){
                flag=true;
            }
        }catch (Exception e)
        {
            errorMsg = e.getMessage();
            map.put("errorMsg",errorMsg);
        }

        map.put("success",true);

        return map;
    }

}
