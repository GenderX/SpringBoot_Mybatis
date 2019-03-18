package com.wms.controller;

import com.github.pagehelper.PageHelper;
import com.wms.model.staff;
import com.wms.model.staffVO;
import com.wms.model.staff_type;
import com.wms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @RequestMapping("/getTypeName")
    @ResponseBody
    public List<staff_type> getTypeName() {
        List<staff_type> typelist = staffService.getTypeName();
        return typelist;
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object getAllStaff(int page, int rows, String Number, String Name) {
        PageHelper.startPage(page, rows);
        List<staffVO> staff = staffService.selectAllOrID(Name, Number);
        return staff;
    }

    @RequestMapping("/insertStaff")
    @ResponseBody
    public Object insertStaff(staff st) {
        Boolean flag = false;
        Integer insert = 0;
        String errorMsg;
        HashMap<String, Object> map = new HashMap<>();
        try {
            insert = staffService.insert(st);
            if (insert != 0) {
                flag = true;
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
            map.put("errorMsg", "插入失败，请检查编号是否重复");
        }
        map.put("success", flag);
        return map;
    }


    @RequestMapping("/updateStaff")
    @ResponseBody
    public Object updateStaff(staff st) {
        Integer update = staffService.updateStaff(st);
        HashMap<String, Object> map = new HashMap<>();
        if (update == 0) {
            String errorMes = "更新失败";
            map.put("errorMsg", errorMes);
        }
        map.put("success", true);
        return map;
    }

}
