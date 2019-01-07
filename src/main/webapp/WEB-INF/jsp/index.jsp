<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/demo/demo.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
<h2>customers DataGrid</h2>
<div style="margin:20px 0;"></div>

<table class="easyui-datagrid" title="Customers DataGrid" style="width:1000px;height:300px;"
       data-options="
       singleSelect:true,
       collapsible:true,
       url:'/customer/getAll',
       method:'get'">
    <thead>
    <tr>
        <th data-options="field:'customerId',width:80">客户ID</th>
        <th data-options="field:'companyName',width:110">companyName</th>
        <th data-options="field:'contactName',width:80,align:'right'">contactName</th>
        <th data-options="field:'contactTitle',width:80,align:'right'">contactTitle</th>
        <th data-options="field:'address',width:200">address</th>
        <th data-options="field:'city',width:60,align:'center'">city</th>
        <th data-options="field:'region',width:60,align:'center'">region</th>
        <th data-options="field:'postalCode',width:60,align:'center'">postalCode</th>
        <th data-options="field:'country',width:60,align:'center'">country</th>
        <th data-options="field:'phone',width:60,align:'center'">phone</th>
        <th data-options="field:'fax',width:60,align:'center'">fax</th>

    </tr>
    </thead>
</table>

</body>
</html>