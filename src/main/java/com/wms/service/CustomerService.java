package com.wms.service;

import com.wms.mapper.customerMapper;
import com.wms.model.customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {
@Autowired
customerMapper customersMapper;
    public Integer insert(customer customers ) throws Exception {
        int insert = customersMapper.insert(customers);
        return insert;
    }

    public int deleteCustomer(String number) {
        int i = customersMapper.deleteByPrimaryKey(number);
        return i;
    }
}
