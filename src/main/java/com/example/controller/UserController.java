package com.example.controller;

import com.example.mapper.employeesMapper;
import com.example.model.employees;
import com.example.model.employeesWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：LiCan
 * @date ：Created in 2019/1/9 14:35
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
public class UserController {
    @Autowired
    employeesMapper employeesMapper;
     @RequestMapping("/user/login")
    public String login(Integer employeeid, String password, HttpServletRequest request){
         employees employees = employeesMapper.selectByPrimaryKey(employeeid);
         if (password!=null&&password!=""&&employees.getPassword().equals(password)){
             request.getSession().setAttribute("user",employees);
             return "index";

         }
         return "login";
     }

}
