package com.wms.service;

import com.alibaba.fastjson.JSONObject;
import com.wms.mapper.*;
import com.wms.model.*;
import com.wms.utils.ExcelUtil;
import com.wms.utils.UUIDGenerator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OutboundService {
    @Autowired
    outbound_masterMapper masterMapper;
    @Autowired
    outbound_detailsMapper detailsMapper;
    @Autowired
    InventoryService inventoryService;

    @Autowired
    warehouseMapper warehouseMapper;

    @Autowired
    productMapper productMapper;

    @Autowired
    inventoryMapper inventoryMapper;


    String fileName=null;

    public void insertDetail(outbound_details detail) {
        detailsMapper.insert(detail);
    }

    public void insertMaster(outbound_master master) {
        masterMapper.insert(master);
    }

    public List<outbound_masterVO> getAll(String number) {
        return masterMapper.selectAll(number);
    }

    /**
     * 制定出库计划单
     * @param effectRow
     * @param customer
     * @param recipient
     * @param id
     */
    public void savePlan(String effectRow, String customer, String recipient, String id) throws Exception {
        //保存主表
        outbound_master master = new outbound_master();
        master.setNumber(id);
        master.setCustomernumber(customer);
        master.setCreatetime(new Date());
        master.setRecipient(recipient);
        master.setIsfinish(false);
        this.insertMaster(master);
        //保存从表
        List<outBoundVO> vos = JSONObject.parseArray(effectRow, outBoundVO.class);
        for (outBoundVO vo : vos) {
            outbound_details detail = new outbound_details();
            //后台获取SKU库内信息
            inventory inventory = inventoryService.selectById(vo.getInvid());
            //出库数量校验
            if (inventory.getAmount()<vo.getAmount()){
                throw new Exception("库存行号‘"+inventory.getPlacenumber()+"’大于可出库数量！");
            }
            //减去SKU库存数量
            inventory.setAmount(inventory.getAmount()-vo.getAmount());
            inventoryMapper.updateByPrimaryKeySelective(inventory);
            detail.setProductnumber(inventory.getProductnumber());
            detail.setPlacenumber(inventory.getPlacenumber());
            detail.setOutstocknumber(id);
            detail.setId(UUIDGenerator.createID());
            detail.setAmount(vo.getAmount());
            this.insertDetail(detail);
        }
    }

    public HSSFWorkbook generateXls(String num) {
     List<outbound_details>  detailsList= detailsMapper.selectByOutBoundNumber(num);
        //使用POI生成Excel文件
        String[] title = {"出库单号", "出库详单号", "产品编号", "产品名", "数量", "库区编号", "库区名"};
        fileName = "出库单" + detailsList.get(0).getOutstocknumber() + ".xls";
        String sheetName = "出库单";
        String[][] content = new String[detailsList.size()][];
        for (int i = 0; i < detailsList.size(); i++) {
            content[i] = new String[title.length];
            outbound_details details = detailsList.get(i);
            content[i][0] = details.getOutstocknumber();
            content[i][1] = details.getId();
            content[i][2] = details.getProductnumber();
            product product = productMapper.selectByPrimaryKey(details.getProductnumber());
            content[i][3] = product.getName();
            content[i][4] = details.getAmount().toString();
            content[i][5] = details.getPlacenumber().toString();
            warehouse warehouse = warehouseMapper.selectByPrimaryKey(details.getPlacenumber());
            content[i][6] = warehouse.getName();
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        return wb;
    }

    public String getFileName() {
        return fileName;
    }

    public void finishMaster(String number, String approver, String deliverer) {
        outbound_master master = new outbound_master();
        master.setNumber(number);
        master.setApprover(approver);
        master.setSender(deliverer);
        master.setIsfinish(true);
        masterMapper.updateByPrimaryKeySelective(master);
    }

    public Boolean isFinish(String number) {
        outbound_master outbound_master = masterMapper.selectByPrimaryKey(number);
        return outbound_master.getIsfinish();
    }


}
