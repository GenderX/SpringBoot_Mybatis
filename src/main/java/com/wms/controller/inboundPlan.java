package com.wms.controller;

import com.alibaba.fastjson.JSONObject;
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

    @RequestMapping("/downloadPlan")
    public void downloadPlan(HttpServletRequest request, HttpServletResponse response, String num) {
        num = "1000000588447497";
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

    //发送响应流方法
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
    public Object getAll(int page, int rows,String Number){
        PageHelper.startPage(page, rows);
       List<inboundVO> list= inboundPlanService.getAll(Number);
       return list;

    }


}
