<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<style>
    .div-leftform{ float:left;width:49%;padding-left: 10px}
    .div-rightform{ float:left;width:49%;padding-left: 10px}
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
      Number: $('#Number').val(),
      Name: $('#Name').val()
    });
  }
  function destroyUser() {
      var row = $('#dg').datagrid('getSelected');
      if (row){
          console.log(row);
          $.messager.confirm('确认','是否删除客户?',function(r){
              if (r){
                  $.post('/customer/deleteCustomer',{number:row.number},function(result){
                      if (result.success){
                          $('#dg').datagrid('reload');	// reload the user data
                      } else {
                          $.messager.show({	// show error message
                              title: 'Error',
                              msg: result.errorMsg
                          });
                      }
                  },'json');
              }
          });
      }
  }

  function newUser() {
    //打开一个对话框
    $('#dlg').dialog('open').dialog('setTitle', '添加客户');
    $('#fm').form('clear');
    url = '/customer/insertCus';
  }

  function editUser() {
    var row = $('#dg').datagrid('getSelected');
    if (row) {
      $('#dlg').dialog('open').dialog('setTitle', '编辑客户');
      $('#fm').form('load', row);
      url = '/customer/updateCustomer?Number=' + row.Number;
    }
  }

  function saveUser() {
    $('#fm').form('submit', {
      url: url,
      onSubmit: function () {
        return $(this).form('validate');
      },
      success: function (result) {
        var result = eval('(' + result + ')');
        if (result.errorMsg) {
          $('#dlg').dialog('close');
          $('#dg').datagrid('reload');
          $.messager.alert({
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

<table id="dg" class="easyui-datagrid" title="客户列表" style="width:100%;height:auto"
       data-options="
       pagination:true,
       fit:true,
       singleSelect:true,
       collapsible:true,
       url:'/customer/getAll',
       method:'get',
       striped:true,
       toolbar:'#toolbar' "
>
    <thead>

    <tr>
        <th data-options="field:'number',width:80">客户编号</th>
        <th data-options="field:'name',width:180">客户名称</th>
        <th data-options="field:'contact',width:100,align:'left'">客户联系人</th>
        <th data-options="field:'address',width:140,align:'left'">客户地址</th>
        <th data-options="field:'postcode',width:100">客户邮编</th>
        <th data-options="field:'phone',width:120,align:'left'">客户电话</th>
        <th data-options="field:'fax',width:120,align:'center'">客户传真</th>
        <th data-options="field:'bank',width:140,align:'center'">客户开户银行</th>
        <th data-options="field:'account',width:140,align:'center'">客户银行账号</th>

    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加用户</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editUser()">编辑用户</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除用户</a>

    <span>客户编号:</span>
    <input id="Number" style="line-height:26px;border:1px solid #ccc">
    <span>客户名称:</span>
    <input id="Name" style="line-height:26px;border:1px solid #ccc">
    <a href="#" class="easyui-linkbutton" plain="false" onclick="doSearch()">Search</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:600px"
     data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
    <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <h3>客户信息</h3>
        <div class="div-leftform">
        <div style="margin-bottom:10px">
            <input name="number" class="easyui-textbox" readonly="readonly"  label="客户编号:" style="width: 100%;background-color:red">
        </div>
        <div style="margin-bottom:10px">
            <input name="name" class="easyui-textbox" required="true" label="姓名:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="contact" class="easyui-textbox" label="联系人名:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="address" class="easyui-textbox" label="地址:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="postcode" class="easyui-textbox" label="邮编:" style="width:100%">
        </div>
        </div>
        <div class="div-rightform">
        <div style="margin-bottom:10px">
            <input name="phone" class="easyui-textbox" label="电话:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="fax" class="easyui-textbox" label="传真:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="bank" class="easyui-textbox" label="开户行:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="account" class="easyui-textbox" label="开户行账号:" style="width:100%">
        </div>
        </div>

    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>


<div id="tb" style="padding:3px">

</div>

</body>
</html>