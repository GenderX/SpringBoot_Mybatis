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
                    name: "菜单", open: true,
                    children: [
                        {
                            name: "人员管理",
                            children: [
                                {name: "客户管理", click: "javascript:openTab('客户管理','/page/CustomerGrid','icon-man')"},
                                {name: "供应商管理", click: "javascript:openTab('供应商管理','/page/supplierGrid','icon-man')"},
                                {name: "员工管理", click: "javascript:openTab('员工管理','/page/StaffGrid','icon-man')"},
                                {name: "用户管理", click: "javascript:openTab('用户管理','/page/userGrid','icon-man')"}
                            ]
                        },
                        {
                            name: "仓库基础资料",
                            children: [
                                {name: "仓库规划", click: "javascript:openTab('仓库规划','/page/warehouseGrid','icon-man')"},
                                {name: "供应商管理", click: "javascript:openTab('产品列表','/page/productGrid','icon-man')"},
                            ]
                        },
                        {
                            name: "入库作业", children: [
                                {name: "入库单制定", click: "javascript:openTab('入库单制定','/page/inbound','icon-man'"},
                                {name: "入库计划", click: "javascript:openTab('入库计划','/page/inboundReview','icon-man')"},
                            ]
                        },
                        {
                            name: "库内作业", children: [
                                {name: "入库单制定", click: "javascript:openTab('入库单制定','/page/inbound','icon-man')"},
                                {name: "库存查询", click: "javascript:openTab('库存查询','/page/inventory','icon-man')"},
                            ]
                        },
                        {
                            name: "出库作业", children: [
                                {name: "入库单制定", click: "javascript:openTab('出库单制定','/page/outbound','icon-man')"},
                                {name: "出库作业", click: "javascript:openTab('出库作业','/page/outboundReview','icon-man')"},
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
                    <font color="green" size="10">欢迎使用</font>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>
