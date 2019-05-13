package com.wms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.net.httpserver.Authenticator;
import com.wms.mapper.userMapper;
import com.wms.model.user;
import com.wms.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**

 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    userMapper userMapper;
    @Autowired
    userService userService;

    /**
     * 登录
     * @param UserName 用户名
     * @param password 密码
     * @param request httprequest
     * @return index.jsp
     */
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

    /**
     * 查询所有用户
     * @param username
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectAll")
    @ResponseBody
    public Object selectByPrimaryKey(String username,int page, int rows) {
        Page<user> rowpage = PageHelper.startPage(page, rows);
        List<user> data = userMapper.selectAllOrByParams(username);
        return data;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Object  insert(user record){
        Boolean flag = false;
        Integer insert = 0;
        String errorMsg;
        HashMap<String, Object> map = new HashMap<>();
        try {
            insert = userService.insert(record);
            if (insert != 0) {
                flag = true;
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
            map.put("errorMsg",errorMsg);
        }
        map.put("success", flag);
        return map;

    }

    @RequestMapping("/updateByPrimaryKey")
    @ResponseBody
    public Object updateByPrimaryKey(user user){
        Integer update = userService.updateByPrimaryKey(user);
        HashMap<String, Object> map = new HashMap<>();
        if (update == 0) {
            String errorMes = "更新失败";
            map.put("errorMsg", errorMes);
        }
        map.put("success", true);
        return map;
    }


    @RequestMapping("/deleteByPrimaryKey")
    @ResponseBody
    public Object deleteByPrimaryKey(String username){

        HashMap<String, Object> map = new HashMap<>();
        if (username.equals("admin") ) {
            String errorMes = "删除失败";
            map.put("errorMsg", errorMes);
        }else {
            userService.deleteByPrimaryKey(username);
        }
        map.put("success", true);
        return map;
    }






    /**
     * 清除所有session并登出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return "login";

    }


}
