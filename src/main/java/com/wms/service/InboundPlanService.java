package com.wms.service;

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
@Transactional
public class InboundPlanService {
    @Autowired
    inbound_masterMapper masterMapper;
    @Autowired
    inbound_detailsMapper detailsMapper;

    @Autowired
    productMapper productMapper;
    @Autowired
    warehouseMapper warehouseMapper;
    @Autowired
    inventoryMapper inventoryMapper;
    String fileName = null;
    String id = null;

    public String makeInboundPlan(List<inbound_details> detailsList, String supplier, String recipient) {
        inbound_master master = new inbound_master();
        //保存主表
        id = UUIDGenerator.createID();
        master.setNumber(id);
        master.setSuppliernumber(supplier);
        master.setRecipient(recipient);
        master.setIsfinish(false);
        master.setCreatetime(new Date());
        masterMapper.insert(master);
        //保存从表
        for (inbound_details details : detailsList) {
            details.setId(UUIDGenerator.createID());
            details.setInstocknumber(id);
            detailsMapper.insert(details);
        }
        return id;
    }

    public HSSFWorkbook generateXls(String num) {
        List<inbound_details> detailsList = detailsMapper.selectByInStockNumber(num);
        //使用POI生成Excel文件
        String[] title = {"入库详单编号", "入库单号", "产品编号", "产品名", "数量", "库区编号", "库区名"};
        fileName = "入库单" + detailsList.get(0).getInstocknumber() + ".xls";
        String sheetName = "入库单";
        String[][] content = new String[detailsList.size()][];
        for (int i = 0; i < detailsList.size(); i++) {
            content[i] = new String[title.length];
            inbound_details details = detailsList.get(i);
            content[i][0] = details.getInstocknumber();
            content[i][1] = details.getId();
            content[i][2] = details.getProductnumber();
            product product = productMapper.selectByPrimaryKey(details.getProductnumber());
            content[i][3] = product.getName();
            content[i][4] = details.getAmount().toString();
            content[i][5] = details.getStorehousenumber().toString();
            warehouse warehouse = warehouseMapper.selectByPrimaryKey(details.getStorehousenumber());
            content[i][6] = warehouse.getName();
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        return wb;
    }
    public String getFileName(){
        return fileName;
    }

    public List<inboundVO> getAll(String number) {
      List<inboundVO> list=  masterMapper.selectByNum(number);
      return list;
    }

    /**
     * 插入主表完成参数
     * @param number 入库单号
     * @param approver 审核人号
     * @param deliverer 送货人号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int finishMaster(String number, String approver, String deliverer) {
        inbound_master master = new inbound_master();
        master.setNumber(number);
        master.setApprover(approver);
        master.setDeliverer(deliverer);
        master.setCompletetime(new Date());
        master.setIsfinish(true);
        int i = masterMapper.updateByPrimaryKeySelective(master);
        return i;
    }

    /**
     * 从表入库完成流程
     * @param number
     */
    @Transactional(rollbackFor = Exception.class)
    public void finishDetails(String number) {
        detailsMapper.updateDate(number);


    }

    /**
     * 入库单数据写入库存表
     * @param number
     */
    @Transactional(rollbackFor = Exception.class)
    public void addInventory(String number) {
        List<inbound_details> detailsList = detailsMapper.selectByInStockNumber(number);
        for (inbound_details details : detailsList) {
            inventory inventory = new inventory();
         inventory.setStorehousenumber(details.getStorehousenumber());
         inventory.setProductnumber(details.getProductnumber());
         inventory.setAmount(details.getAmount());
         inventory.setPlacenumber(details.getStorehousenumber());
         inventory.setInstocktime(new Date());
         inventoryMapper.insert(inventory);
        }

    }

    public Boolean isFinish(String number) {
        inbound_master master = masterMapper.selectByPrimaryKey(number);
        return master.getIsfinish();
    }


}
