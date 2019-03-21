package com.wms.controller;


import com.github.pagehelper.PageHelper;
import com.wms.mapper.productMapper;
import com.wms.model.product;
import com.wms.model.productVO;
import com.wms.model.product_category;
import com.wms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    productMapper productsMapper;
    @Autowired
    ProductService productService;
    /**
     *  根据调价查询所有商品并分页，条件为查询所有商品
     * @param Number
     * @param Name
     * @return 所有商品
     */

    @RequestMapping("/getAll")
    public List<productVO> selectAllProduct(int page, int rows, String Number, String Name) {
        PageHelper.startPage(page, rows);
        List<productVO> products = productService.slectAll(Number, Name);
        return products;
    }

    /**
     * 查询产品种类字典值
     * @return 产品种类字典
     */
    @RequestMapping("/getCategorynumber")
    public List<product_category> slectCategory() {
        List<product_category> list = productService.selectCategory();
        return list;
    }

    /**
     * 插入产品
     * @param pro
     * @return
     */
     @RequestMapping("/insertPro")
    public Object insertPro(product pro){
         Boolean flag = false;
         Integer insert = 0;
         String errorMsg;
         HashMap<String, Object> map = new HashMap<>();
         try {
             insert = productService.insert(pro);
             if (insert != 0) {
                 flag = true;
             }
         } catch (Exception e) {
             errorMsg = e.getMessage();
             map.put("errorMsg", errorMsg);
         }
         map.put("success", flag);
         return map;

     }

    /**
     * 更新产品信息
     * @param pro
     * @return
     */
     @RequestMapping("/updateProduct")
     public Object updatePro(product pro){
         HashMap<String, Object> map = new HashMap<>();
         int i = productService.updateByPrimaryKeySelective(pro);
         if (i == 0) {
             String errorMes = "更新失败";
             map.put("errorMsg", errorMes);
         }
         map.put("success", true);
         return map;
     }

    /**
     * 删除产品信息
     * @param number
     * @return
     */

     @RequestMapping("/deleteProduct")
     public Object deletePro(String number){
         productService.deleteCustomer(number);
         HashMap<String, Object> result = new HashMap<>();
         result.put("success",true);
         return result;
     }
}
