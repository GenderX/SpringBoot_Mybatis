<%--
  Created by IntelliJ IDEA.
  User: Li Tsan
  Date: 2019/1/8
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WMS主页</title>
</head>
<link rel="stylesheet" type="text/css" href="/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/metroStyle/metroStyle.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.ztree.all.js"></script>
<script type="text/javascript" src="/easyui/jquery.ztree.exhide.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/echarts.js"></script>

<script type="text/javascript">
    //加载标签到center
    function openTab(text, url, iconCls) {
        if ($("#tabs").tabs("exists", text)) {
            //如果已经存在，则使之处于选中的状态
            $("#tabs").tabs("select", text);
        } else {
            //如果不存在则新增加一个
            var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
            $("#tabs").tabs("add", {
                title: text, //标题
                iconCls: iconCls, //图标
                closable: true, //可以关闭
                content: content //内容，内嵌一个 iframe！
            });
        }
    }


</script>
<body>
<div id="cc" class="easyui-layout" style="width:1800px;height:850px;">
    <div data-options="region:'north',title:'仓储管理系统',split:true" style="height:100px;">
        <a href="/user/logout">退出登录</a>
    </div>
    <div data-options="region:'west',title:'菜单',split:true," style="width:200px;">
        <ul id="tree" class="ztree"></ul>
        <SCRIPT type="text/javascript">
            var setting = {};
            var zNodes = [
                {
                    name: "菜单", open: true,icon:"/images/diy/1_close.png",
                    children: [
                        {
                            name: "人员管理",
                            open: true,
                            children: [
                                {name: "客户管理", click: "javascript:openTab('客户管理','/page/CustomerGrid','icon-man')",icon:"/images/diy/2.png",},
                                {name: "供应商管理", click: "javascript:openTab('供应商管理','/page/supplierGrid','icon-man')",icon:"/images/diy/2.png",},
                                {name: "员工管理", click: "javascript:openTab('员工管理','/page/StaffGrid','icon-man')",icon:"/images/diy/2.png",},
                                {name: "用户管理", click: "javascript:openTab('用户管理','/page/userGrid','icon-man')",icon:"/images/diy/2.png",}
                            ]
                        },
                        {
                            name: "仓库基础资料",
                            open: true,
                            children: [
                                {name: "仓库规划", click: "javascript:openTab('仓库规划','/page/warehouseGrid','icon-man')",icon:"/images/diy/3.png",},
                                {name: "产品列表", click: "javascript:openTab('产品列表','/page/productGrid','icon-man')",icon:"/images/diy/3.png",},
                            ]
                        },
                        {
                            name: "入库作业",
                            open: true,
                            children: [
                                {name: "入库单制定", click: "javascript:openTab('入库单制定','/page/inbound','icon-man')",icon:"/images/diy/7.png",},
                                {name: "入库计划", click: "javascript:openTab('入库计划','/page/inboundReview','icon-man')",icon:"/images/diy/6.png",},
                            ]
                        },
                        {
                            name: "库内作业",
                            open: true,
                            children: [

                                {name: "库存查询", click: "javascript:openTab('库存查询','/page/inventory','icon-man')",icon:"/images/diy/8.png",},
                            ]
                        },
                        {
                            name: "出库作业",
                            open: true,
                            children: [
                                {name: "出库单制定", click: "javascript:openTab('出库单制定','/page/outbound','icon-man')",icon:"/images/diy/7.png",},
                                {name: "出库作业", click: "javascript:openTab('出库作业','/page/outboundReview','icon-man')",icon:"/images/diy/6.png",},
                            ]
                        }
                    ]
                },


            ];

            $(document).ready(function () {
                //生成树
                $.fn.zTree.init($("#tree"), setting, zNodes);
                //非admin用户隐藏人员管理功能
                var isRoot = "<%=session.getAttribute("root")%>";
                console.log(isRoot);
                if (isRoot=='false') {
                    var treeObj = $.fn.zTree.getZTreeObj("tree");
                    var nodes = treeObj.getNodes();
                    treeObj.hideNode(nodes[0].children[0]);
                }
            });
            //-->
        </SCRIPT>


    </div>
    <div region="center">
        <div class="easyui-tabs" fit="true" border="false" id="tabs">
            <div title="首页" data-options="iconCls:'icon-help'">
                <div align="center" style="padding-top: 100px">
                    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                    <div id="main" style="width: 600px;height:400px;"></div>
                    <script type="text/javascript">
                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('main'));

                        // 指定图表的配置项和数据
                        var option = {
                            title: {
                                text: 'ECharts 入门示例'
                            },
                            tooltip: {},
                            legend: {
                                data:['销量']
                            },
                            xAxis: {
                                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                            },
                            yAxis: {},
                            series: [{
                                name: '销量',
                                type: 'bar',
                                data: [5, 20, 36, 10, 10, 20]
                            }]
                        };

                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);
                    </script>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>
