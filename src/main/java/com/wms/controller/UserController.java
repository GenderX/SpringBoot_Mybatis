package com.wms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wms.mapper.userMapper;
import com.wms.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * @author ：LiCan
 * @date ：Created in 2019/1/9 14:35
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    userMapper userMapper;
     @RequestMapping("/login")
    public String login(String UserName, String password, HttpServletRequest request){
         user user = userMapper.selectByPrimaryKey(UserName);
         if (user==null){
             return "login";
         }
         if (password!=null&&password!=""&& user.getPassword().equals(password)){
             request.getSession().setAttribute("user",user);
             request.getSession().setAttribute("root",false);
             if (user.getUsername().equals("admin")){
                 request.getSession().setAttribute("root",true);
             }
             return "index";
         }
         return "login";
     }

    @RequestMapping("/selectAll")
    @ResponseBody
    public Object selectByPrimaryKey(String username,int page, int rows) {
        Page<user> rowpage = PageHelper.startPage(page, rows);
        List<user> data = userMapper.selectAllOrByParams(username);
        return data;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return "login";

    }


}
