package com.wms.controller;


import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.wms.model.WHPieChartBO;
import com.wms.model.weeklyAmountBO;
import com.wms.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/charts")
public class ChartController {
    @Autowired
    ChartService chartService;

    /**
     * 生成柱状图
     * @return
     */
    @RequestMapping("/getTest")
    public Object test() {
        // 创建Option
        // Option 无需任何依赖; GsonOption 依赖 Gson, FsonOption 依赖 FastJson
        Option option = new GsonOption();

        // 设置标题与子标题
        option.title().text("一周出库量与入库量统计").subtext("@WMS").x(X.left).y(Y.top);
        // 设置右上角工具箱
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore,
                Tool.saveAsImage).x(X.right).y(Y.top);
        // 图例
        option.legend().data("入库量", "出库量");
        // 设置 x 轴为类目轴, y 轴为值轴
        option.xAxis(new CategoryAxis().data("周一", "周二", "周三", "周四", "周五", "周六", "周日")).yAxis(new ValueAxis());
        // 气泡提示框
        option.tooltip().trigger(Trigger.axis);

        // 柱状数据, 。
        Bar bar1 = new Bar("入库量");
        List<weeklyAmountBO> invBO = chartService.selectWeeklyInboundAmount();
        int[] Inamount=new int[7];
        for (weeklyAmountBO bo : invBO) {
            switch (bo.getWeekday()){
                case 1:Inamount[0]=bo.getAmount(); break;
                case 2:Inamount[1]=bo.getAmount(); break;
                case 3:Inamount[2]=bo.getAmount(); break;
                case 4:Inamount[3]=bo.getAmount(); break;
                case 5:Inamount[4]=bo.getAmount(); break;
                case 6:Inamount[5]=bo.getAmount(); break;
                case 7:Inamount[6]=bo.getAmount(); break;
            }
        }
        bar1.data(Inamount[0],Inamount[1], Inamount[2], Inamount[3], Inamount[4], Inamount[5], Inamount[6]);
        bar1.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
                new PointData().type(MarkType.min).name("最小值"));
        bar1.markLine().data(new PointData().type(MarkType.average).name("平均值"));
        // 又一个柱状数据
        Bar bar2 = new Bar("出库量");
        List<weeklyAmountBO> outBO =chartService.selectWeeklyOutboundAmount();
        for (weeklyAmountBO bo : outBO) {
            switch (bo.getWeekday()){
                case 1:Inamount[0]=bo.getAmount(); break;
                case 2:Inamount[1]=bo.getAmount(); break;
                case 3:Inamount[2]=bo.getAmount(); break;
                case 4:Inamount[3]=bo.getAmount(); break;
                case 5:Inamount[4]=bo.getAmount(); break;
                case 6:Inamount[5]=bo.getAmount(); break;
                case 7:Inamount[6]=bo.getAmount(); break;
            }

        }
        bar2.data(Inamount[0],Inamount[1], Inamount[2], Inamount[3], Inamount[4], Inamount[5], Inamount[6]);
        bar2.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
                new PointData().type(MarkType.min).name("最小值"));
        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));
        // 向 Option 中设置数据
        option.series(bar1, bar2);
        return option.toString();
    }

    /**
     * 生成Pie图
     * @return
     */
    @RequestMapping("/getPie")
    public Object getPie(){

            //需要的数据
        List<WHPieChartBO> pieBO = chartService.selectWHPieChartBO();
        String title = "库区库存统计";
        String[] searchs = new String[pieBO.size()];
        int[] datas = new int[pieBO.size()];
        for (int i = 0; i < pieBO.size(); i++) {
            System.out.println( pieBO.get(i).getName());
            searchs[i]=pieBO.get(i).getName();
            datas[i]=pieBO.get(i).getAmount();
        }


            //创建option对象
            Option option = new GsonOption();

            //设置标题  二级标题  标题位置
            option.title().text(title).subtext("二级标题").x("center");

            //设置工具栏 展示  能标记
            option.toolbox().show(true).feature(Tool.mark);

            //设置显示工具格式
            option.tooltip().show(true).formatter("{a}</br>{b}：{c}/个");

            //设置图例  图例位置  图例对齐方式 竖列对齐
            option.legend().data(searchs).x("left").orient(Orient.vertical);

            //填充数据
            Pie pie = new Pie();//创建饼图对象

            //设置饼图的标题 半径、位置
            pie.name(title).radius("55%").center("50%","50%");

            //填充数据
            for(int i = 0; i < searchs.length; i++){
                Map<String,Object> map = new HashMap<>();
                map.put("value",datas[i]);//填充饼图数据
                map.put("name",searchs[i]);//填充饼图数据对应的搜索引擎
                pie.data(map);
            }
            option.series(pie); //设置数据

        System.out.println(option.toString());
            return option.toString();
        }


    }

