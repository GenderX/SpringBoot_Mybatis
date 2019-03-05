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
<link rel="stylesheet" type="text/css" href="/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    //加载标签到center
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
<div id="cc" class="easyui-layout"   style="width:1800px;height:850px;">
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
    <div data-options="region:'west',title:'菜单',split:true," style="width:200px;">

         <ul id="tt" class="easyui-tree">
             <li>
                 <span>Folder</span>
                 <ul>
                     <li>
                         <span>人员管理</span>
                         <ul>
                             <li><span><a href="javascript:openTab('客户管理','/page/CustomerGrid','icon-man')">客户管理</a></span></li>
                            <li><span><a href="javascript:openTab('供应商管理','/page/CustomerGrid','icon-man')">供应商管理</a></span></li>
                            <li><span><a href="javascript:openTab('员工管理','/page/StaffGrid','icon-man')">员工管理</a></span></li>
                             <li><span><a href="javascript:openTab('用户管理','/page/userGrid','icon-man')">用户管理</a></span></li>
                         </ul>
                     </li>
                     <li><span>File 2</span></li>
                     <li><span>File 3</span></li>
                 </ul>
             </li>
             <li><span>File21</span></li>
         </ul>

  <%--      <div style="width:200px;height:auto;border:1px solid #ccc;">
            <ul id="tt" class="easyui-tree" url="/static/tree_data.json" method="get"></ul>
        </div>--%>
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
