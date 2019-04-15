package com.wms.service;

import com.wms.mapper.inventoryMapper;
import com.wms.model.inventory;
import com.wms.model.inventoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    @Autowired
    inventoryMapper mapper ;
    public List<inventoryVO> getAllCombo( String Name) {
     return  mapper.getAll(Name);
    }

    public inventory selectById(Integer id) {
      return   mapper.selectByPrimaryKey(id);
    }
}
