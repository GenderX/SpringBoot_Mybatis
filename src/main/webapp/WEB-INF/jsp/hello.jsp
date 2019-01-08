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
    <title>NorthWind主页</title>
</head>
<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
  function openTab(text, url, iconCls) {
    if($("#tabs").tabs("exists", text)) {
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
<div id="cc" class="easyui-layout"   style="width:1690px;height:800px;">
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
    <div data-options="region:'west',title:'菜单',split:true," style="width:200px;">
        <ul>
            <li><a href="javascript:openTab('添加','/customer/hello','icon-add')">菜单1</a></li>
            <li><a href="#">菜单2</a></li>
            <li><a href="#">菜单3</a></li>
            <li><a href="#">菜单4</a></li>
            <li><a href="#">菜单5</a></li>
        </ul>
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
