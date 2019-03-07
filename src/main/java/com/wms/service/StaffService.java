package com.wms.service;

import com.wms.mapper.staffMapper;
import com.wms.mapper.staff_typeMapper;
import com.wms.model.staff;
import com.wms.model.staffVO;
import com.wms.model.staff_type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffService {
    @Autowired
    staff_typeMapper staff_typeMapper ;

    @Autowired
    staffMapper  staffMapper;

    public List<staff_type> getTypeName() {
        List<staff_type> staff_type = staff_typeMapper.selectAll();
        return staff_type;
    }

    public List<staffVO> selectAllOrID(String name, String number) {
      List<staffVO> list=  staffMapper.selectAllOrID(name,number);
      return  list;
    }

    public Integer insert(staff st) {
        int insert = staffMapper.insert(st);
        return insert;
    }

    public Integer updateStaff(staff st) {
        int i = staffMapper.updateByPrimaryKeySelective(st);
        return i;
    }
}
