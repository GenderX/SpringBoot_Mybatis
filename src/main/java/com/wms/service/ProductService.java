package com.wms.service;


import com.wms.mapper.productMapper;
import com.wms.mapper.product_categoryMapper;
import com.wms.model.product;
import com.wms.model.productVO;
import com.wms.model.product_category;
import com.wms.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    productMapper productMapper;
    @Autowired
    product_categoryMapper productCategoryMapper;

    /**
     *  根据调价查询所有商品并分页，条件为查询所有商品
     * @param number
     * @param name
     * @return 所有商品
     */

    public List<productVO> slectAll(String number, String name) {

        List<productVO> vo = productMapper.selectAll(number, name);
        return vo;

    }

    /**
     * 查询产品种类字典值
     * @return 产品种类字典
     */
    public List<product_category> selectCategory() {
        return productCategoryMapper.selectAll();
    }

    public Integer insert(product pro) {
        pro.setNumber(UUIDGenerator.createID());
        return productMapper.insert(pro);
    }

    public int updateByPrimaryKeySelective(product pro) {
        return productMapper.updateByPrimaryKeySelective(pro);
    }

    public void deleteCustomer(String number) {
        productMapper.deleteByPrimaryKey(number);
    }
}
