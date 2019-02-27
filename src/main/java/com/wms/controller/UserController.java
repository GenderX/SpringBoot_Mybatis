package com.wms.controller;

import com.wms.mapper.userMapper;
import com.wms.model.user;
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
    userMapper userMapper;
     @RequestMapping("/user/login")
    public String login(String UserName, String password, HttpServletRequest request){
         user user = userMapper.selectByPrimaryKey(UserName);
         if (user==null){
             return "login";
         }
         if (password!=null&&password!=""&& user.getPassword().equals(password)){
             request.getSession().setAttribute("user",user);
             return "index";
         }
         return "login";
     }

}
