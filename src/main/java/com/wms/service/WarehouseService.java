package com.wms.service;

import com.wms.mapper.warehouseMapper;
import com.wms.model.warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class WarehouseService {
    @Autowired
    warehouseMapper mapper;

    public List<warehouse> getAll(String number, String name) {
        return mapper.selectAllOrQuery(number, name);
    }

    public Integer insert(warehouse wh) {
        return mapper.insert(wh);
    }

    public Integer updateStaff(warehouse wh) {
        return mapper.updateByPrimaryKeySelective(wh);
    }

    public Object deleteWH(Integer number) {
        mapper.deleteByPrimaryKey(number);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
