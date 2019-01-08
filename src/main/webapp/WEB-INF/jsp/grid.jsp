<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
  var url;

  function doSearch(){
    $('#dg').datagrid('load',{
      customerid: $('#customerid').val(),
      companyname: $('#companyname').val()
    });
  }

  function newUser() {
    //打开一个对话框
    $('#dlg').dialog('open').dialog('setTitle', 'New User');
    $('#fm').form('clear');
    url = '/customer/insertCus';
  }
  function  editUser() {
    var row = $('#dg').datagrid('getSelected');
    if (row){
      $('#dlg').dialog('open').dialog('setTitle','Edit User');
      $('#fm').form('load',row);
      url = '/customer/updateCustomer?id='+row.customerid;
    }

  }

  function saveUser() {
    $('#fm').form('submit', {
      url: url,
      onSubmit: function () {
        return $(this).form('validate');
      },
      success: function(result){
        var result = eval('('+result+')');
        if (result.errorMsg){
          $('#dlg').dialog('close');
          $('#dg').datagrid('reload');
          $.messager.show({
            title: 'Error',
            msg: result.errorMsg
          });
        } else {
          $('#dlg').dialog('close');        // close the dialog
          $('#dg').datagrid('reload');    // reload the user data
        }
      }

    });
  }

</script>

<table id="dg" class="easyui-datagrid" title="Customers DataGrid" style="width:100%;height:auto"
       data-options="
       pagination:true,
       singleSelect:true,
       collapsible:true,
       url:'/customer/getAll',
       method:'get',
       striped:true,
       toolbar:'#toolbar' "
>
    <thead>

    <tr>
        <th data-options="field:'customerid',width:80">客户ID</th>
        <th data-options="field:'companyname',width:230">companyName</th>
        <th data-options="field:'contactname',width:140,align:'left'">contactName</th>
        <th data-options="field:'contacttitle',width:140,align:'left'">contactTitle</th>
        <th data-options="field:'address',width:200">address</th>
        <th data-options="field:'city',width:90,align:'left'">city</th>
        <th data-options="field:'region',width:60,align:'center'">region</th>
        <th data-options="field:'postalCode',width:90,align:'center'">postalCode</th>
        <th data-options="field:'country',width:90,align:'center'">country</th>
        <th data-options="field:'phone',width:120,align:'left'">phone</th>
        <th data-options="field:'fax',width:120,align:'left'">fax</th>

    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加用户</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editUser()">编辑用户</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除用户</a>

    <span>Customer ID:</span>
    <input id="customerid" style="line-height:26px;border:1px solid #ccc">
    <span>Company Name:</span>
    <input id="companyname" style="line-height:26px;border:1px solid #ccc">
    <a href="#" class="easyui-linkbutton" plain="false" onclick="doSearch()">Search</a>
</div>


<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User Information</div>
    <form id="fm" method="post">
        <div class="fitem">
            <label>客户ID:</label>
            <input name="customerid" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>公司名:</label>
            <input name="companyname" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>联系人名:</label>
            <input name="contactname">
        </div>
        <div class="fitem">
            <label>国家:</label>
            <input name="country" class="easyui-validatebox">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>


<div id="tb" style="padding:3px">

</div>

</body>
</html>