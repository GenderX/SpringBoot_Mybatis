package com.wms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wms.model.staffVO;
import com.wms.model.staff_type;
import com.wms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    @RequestMapping("/getTypeName")
    @ResponseBody
    public List<staff_type> getTypeName(){
      List<staff_type> typelist=staffService.getTypeName();
      return typelist;
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object getAllStaff(int page, int rows, String Number, String Name){
        Page<staffVO> rowpage = PageHelper.startPage(page, rows);
        List<staffVO> staff=   staffService.selectAllOrID(Name,Number);
        return staff;
    }
}
