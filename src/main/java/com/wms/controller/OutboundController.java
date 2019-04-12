package com.wms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wms.model.outbound_masterVO;
import com.wms.service.OutboundService;
import com.wms.utils.UUIDGenerator;
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
@RequestMapping("/outbound")
public class OutboundController {
    @Autowired
    OutboundService outboundService;


    /**
     * 创建入库单写入主表和明细表
     *
     * @param effectRow 库内行号和数量
     * @param customer  客户号
     * @param Recipient 接收人号
     * @return 出库单号
     */
    @RequestMapping("/CommitBach")
    public Object CommitBach(String effectRow, String customer, String Recipient) {
        String id = UUIDGenerator.createID();
        try {
            outboundService.savePlan(effectRow, customer, Recipient, id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "出库单制作完成，出库单编号为"+id;
    }

    /**
     * 查询出库单
     *
     * @param Number 单号
     * @return 出库单list
     */
    @RequestMapping("/getAll")
    public Object getAll(int page, int rows, String Number) {
        Page<Object> rowPage = PageHelper.startPage(page, rows);
        List<outbound_masterVO> list = outboundService.getAll(Number);
        HashMap<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("total", rowPage.getTotal());
        return data;
    }

    /**
     * 下载出库单
     * @param request
     * @param response
     * @param num 库存行号
     * @return xls出库单流
     */
    @RequestMapping("/downloadPlan")
    public Object downloadPlan(HttpServletRequest request, HttpServletResponse response, String num) {
        HSSFWorkbook wb = outboundService.generateXls(num);
        try {
            this.setResponseHeader(response, outboundService.getFileName());
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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

    @RequestMapping("/done")
    public Object done(String number,String approver,String deliverer  ){
        HashMap<String, Object> map = new HashMap<>();
        Boolean isFinish=outboundService.isFinish(number);
        if (isFinish){
            map.put("errorMsg", "出库失败，当前单据已审核出库！！！！");
            return map;
        }
        try {
            outboundService.finishMaster(number, approver, deliverer);
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            map.put("errorMsg", errorMsg);
            return map;
        }
        map.put("success", true);
        return map;

    }

}
