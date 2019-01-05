package com.example.controller;

import com.example.dao.CusDao;
import com.example.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    CusDao cusDao;
    @ResponseBody
    @RequestMapping("/{id}")
  public  Customers select(@PathVariable String id){
        Customers customers = cusDao.slectById(id);
        return customers;
    }

}
