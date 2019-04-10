package com.wms.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wms.model.inboundVO;
import com.wms.model.inbound_details;
import com.wms.service.InboundPlanService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/inbound")
public class inboundPlan {
    @Autowired
    InboundPlanService inboundPlanService;

    /**
     * 存入数据库并导出报表
     *
     * @param effectRow
     */
    @RequestMapping("/CommitBach")
    public Object commitBach(HttpServletRequest request, HttpServletResponse response,
                             String effectRow, String supplier, String Recipient) {
        List<inbound_details> detailsList = JSONObject.parseArray(effectRow, inbound_details.class);
        String id = inboundPlanService.makeInboundPlan(detailsList, supplier, Recipient);

        return id;

    }

    /**
     * XLS文件写入流输出至浏览器
     * @param request
     * @param response
     * @param num
     */

    @RequestMapping("/downloadPlan")
    public void downloadPlan(HttpServletRequest request, HttpServletResponse response, String num) {
        HSSFWorkbook wb = inboundPlanService.generateXls(num);
        try {
            this.setResponseHeader(response, inboundPlanService.getFileName());
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送响应流方法
     * @param response
     * @param fileName
     */
       public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping("/getAll")
    public Object getAll(int page, int rows, String Number) {
        Page<Object> rowPage = PageHelper.startPage(page, rows);
        List<inboundVO> list = inboundPlanService.getAll(Number);
        HashMap<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("total", rowPage.getTotal());
        return data;

    }

    /**
     * 入库单完成，写入库存
     * @param number
     * @param approver
     * @param deliverer
     * @return
     */
    @RequestMapping("/done")
    public Object done(String number, String approver, String deliverer) {
        HashMap<String, Object> map = new HashMap<>();
        Boolean isFinish=inboundPlanService.isFinish(number);
        if (isFinish){
            map.put("errorMsg", "入库失败，当前单据已审核入库！！！！");
            return map;
        }
        try {
            inboundPlanService.finishMaster(number, approver, deliverer);
            inboundPlanService.finishDetails(number);
            inboundPlanService.addInventory(number);
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            map.put("errorMsg", errorMsg);
            return map;
        }
        map.put("success", true);
        return map;
    }


}
