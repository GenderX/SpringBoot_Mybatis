package com.wms.service;

import com.wms.mapper.supplierMapper;
import com.wms.model.supplier;
import com.wms.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierService {
    @Autowired
    supplierMapper mapper;

    public List<supplier> selectAllOrID(String name, String number) {
        return mapper.selectAll(name,number);
    }

    public Integer insert(supplier sp) {
        sp.setNumber(UUIDGenerator.createID());
        int i = mapper.insertSelective(sp);
        return i;
    }

    public Integer updateStaff(supplier sp) {
        return mapper.updateByPrimaryKeySelective(sp);
    }

    public void deleteCustomer(String number) {
        mapper.deleteByPrimaryKey(number);
    }
}
