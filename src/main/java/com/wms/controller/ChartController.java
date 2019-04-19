package com.wms.controller;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/charts")
public class ChartController {
    @RequestMapping("/getTest")
    public Object test() {
        // 创建Option
        // Option 无需任何依赖; GsonOption 依赖 Gson, FsonOption 依赖 FastJson
        Option option = new GsonOption();

        // 设置标题与子标题
        option.title().text("某地区蒸发量和降水量").subtext("纯属虚构").x(X.left).y(Y.top);
        // 设置右上角工具箱
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore,
                Tool.saveAsImage);
        // 图例
        option.legend().data("蒸发量", "降水量");
        // 设置 x 轴为类目轴, y 轴为值轴
        option.xAxis(new CategoryAxis().data("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月",
                "10月", "11月", "12月")).yAxis(new ValueAxis().max(200));
        // 气泡提示框
        option.tooltip().trigger(Trigger.axis);

        // 柱状数据, 实验嘛，就用模拟数据了。
        Bar bar1 = new Bar("蒸发量");
        bar1.data(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3);
        bar1.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
                new PointData().type(MarkType.min).name("最小值"));
        bar1.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        // 又一个柱状数据
        Bar bar2 = new Bar("降水量");
        bar2.data(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3);
        bar2.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
                new PointData().type(MarkType.min).name("最小值"));
        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        // 向 Option 中设置数据
        option.series(bar1, bar2);

        // GsonOption 重写了 toString() 方法, 返回 Json（用 Gson 实现的）
        System.out.println(option.toString());
        return option.toString();
    }



}
