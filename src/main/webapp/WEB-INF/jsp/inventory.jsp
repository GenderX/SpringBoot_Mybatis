<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<style>
    .div-leftform {
        float: left;
        width: 49%;
        padding-left: 10px
    }

    .div-rightform {
        float: left;
        width: 49%;
        padding-left: 10px
    }
</style>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
    var url;

    function doSearch() {
        $('#dg').datagrid('load', {
            Name: $('#Name').val()
        });
    }

</script>

<table id="dg" class="easyui-datagrid" title="库存查询" style="width:100%;height:auto"
       data-options="
       pagination:true,
       singleSelect:true,
       collapsible:true,
       url:'/inventory/getAllCombo',
       method:'get',
       striped:true,
       toolbar:'#toolbar' "
>
    <thead>

    <tr>
        <th data-options="field:'id',width:80">库存项编号</th>
        <th data-options="field:'storehousenumber',width:80">仓库编号</th>
        <th data-options="field:'productnumber',width:80">产品编号</th>
        <th data-options="field:'productName',width:180">产品名称</th>
        <th data-options="field:'amount',width:80">数量</th>
        <th data-options="field:'areanumber',width:80">库区号</th>
        <th data-options="field:'shelfnumber',width:80">货架号</th>
        <th data-options="field:'placenumber',width:80">货位号</th>
        <th data-options="field:'instocktime',width:80">入库时间</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <span>产品名称:</span>
    <input id="Name" style="line-height:26px;border:1px solid #ccc">
    <a href="#" class="easyui-linkbutton" plain="false" onclick="doSearch()">查询</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:600px"
     data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
    <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <h3>库存信息</h3>
        <div class="div-leftform">
        <div style="margin-bottom:10px"><input name="ID" class="easyui-textbox" required="true" label="库存项编号:" style="width:100%"></div>
        <div style="margin-bottom:10px"><input name="StoreHouseNumber" class="easyui-textbox" required="true" label="仓库编号:" style="width:100%"></div>
        <div style="margin-bottom:10px"><input name="ProductNumber" class="easyui-textbox" required="true" label="产品编号:" style="width:100%"></div>
        <div style="margin-bottom:10px"><input name="Name" class="easyui-textbox" required="true" label="产品名称:" style="width:100%"></div>
        <div style="margin-bottom:10px"><input name="Amount" class="easyui-textbox" required="true" label="数量:" style="width:100%"></div>
        <div class="div-rightform">
        <div style="margin-bottom:10px"><input name="AreaNumber" class="easyui-textbox" required="true" label="库区号:" style="width:100%"></div>
        <div style="margin-bottom:10px"><input name="ShelfNumber" class="easyui-textbox" required="true" label="货架号:" style="width:100%"></div>
        <div style="margin-bottom:10px"><input name="PlaceNumber" class="easyui-textbox" required="true" label="货位号:" style="width:100%"></div>
        <div style="margin-bottom:10px"><input name="InStockTime" class="easyui-textbox" required="true" label="入库时间:" style="width:100%"></div>

    </form>
</div>



<div id="tb" style="padding:3px">

</div>

</body>
</html>