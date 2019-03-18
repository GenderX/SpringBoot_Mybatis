package com.wms.service;

import com.wms.mapper.warehouseMapper;
import com.wms.model.warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WarehouseService {
    @Autowired
    warehouseMapper mapper;
    public List<warehouse> getAll(String number, String name) {
      return   mapper.selectAllOrQuery(number,name);
    }
}
