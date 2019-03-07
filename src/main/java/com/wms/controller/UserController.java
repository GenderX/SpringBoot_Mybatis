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

    @RequestMapping("/selectAll")
    @ResponseBody
    public Object selectByPrimaryKey(String username,int page, int rows) {
        Page<user> rowpage = PageHelper.startPage(page, rows);
        List<user> data = userMapper.selectAllOrByParams(username);
        return data;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public int insert(user record){
        int data = userMapper.insert(record);
        return data;
    }

    @RequestMapping("/insertSelective")
    @ResponseBody
    public int insertSelective(user record){
        int data = userMapper.insertSelective(record);
        return data;
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public int updateByPrimaryKeySelective(user record){
        int data = userMapper.updateByPrimaryKeySelective(record);

        return data;
    }

    @RequestMapping("/updateByPrimaryKey")
    @ResponseBody
    public int updateByPrimaryKey(user record){
        int data = userMapper.updateByPrimaryKey(record);

        return data;
    }

    @RequestMapping("/deleteByPrimaryKey")
    @ResponseBody
    public int deleteByPrimaryKey(String username) {
        int data = userMapper.deleteByPrimaryKey(username);

        return data;
    }
}
