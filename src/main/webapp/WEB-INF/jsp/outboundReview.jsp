<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>出库单</title>
</head>
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
<body>
<script type="text/javascript">
    var url;

    function doSearch() {
        $('#dg').datagrid('load', {
            Number: $('#Number').val(),
        });
    }


    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '审核');
            $('#fm').form('load', row);
            url = '/outbound/done'
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

    function formatOper(val, row, index) {
        return '<a href="#" onclick="downloads(' + row.number + ')">下载</a>';
    }


    function downloads(id) {
        var url = "/outbound/downloadPlan?num=" + id;
        window.location = url;
    }

    function isFinish(value, row, index) {
        if (row.isfinish) {
            return "是";
        } else {
            return "否";
        }
    }

</script>

<table id="dg" class="easyui-datagrid" title="出库计划" style="width:100%;height:auto"
       data-options="
       pagination:true,
       singleSelect:true,
       collapsible:true,
       url:'/outbound/getAll',
       method:'get',
       striped:true,
       toolbar:'#toolbar' "
>
    <thead>

    <tr>
        <th data-options="field:'number',width:180">出库编号</th>
        <th data-options="field:'customernumber',width:180,hidden:true">客户编号</th>
        <th data-options="field:'customerName',width:180">客户名</th>
        <th data-options="field:'createtime',width:180">创建时间</th>
        <th data-options="field:'completetime',width:180">完成时间</th>
        <th data-options="field:'recipient',width:100,hidden:true">接收人ID</th>
        <th data-options="field:'recipientname',width:100">接收人</th>
        <th data-options="field:'isfinish',formatter:isFinish,width:120,align:'left'">是否完成</th>
        <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">下载出库单</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editUser()">审核</a>
    <span>产品编号:</span>
    <input id="Number" style="line-height:26px;border:1px solid #ccc">
    <a href="#" class="easyui-linkbutton" plain="false" onclick="doSearch()">Search</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:600px"
     data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
    <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <h3>出库</h3>
        <div style="margin-bottom:10px">
            <input name="number" class="easyui-textbox" label="出库编号:" readonly="readonly" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="customerName" class="easyui-textbox" required="true" label="供应商:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input required="true" label="审核人:" name="approver" class="easyui-combogrid" style="width:250px"
                   data-options="
            panelWidth: 500,
			idField: 'number',
			textField: 'name',
			url: '/staff/getAllCombo',
			method: 'get',
			columns: [[
				{field:'number',title:'员工编号',width:200},
				{field:'name',title:'员工名',width:120},
				{field:'type',title:'员工类型',width:120,align:'right'},
			]],
			fitColumns: true
		">
        </div>
        <div style="margin-bottom:10px">
            <input required="true" label="送货人:" name="deliverer" class="easyui-combogrid" style="width:250px"
                   data-options="
            panelWidth: 500,
			idField: 'number',
			textField: 'name',
			url: '/staff/getAllCombo',
			method: 'get',
			columns: [[
				{field:'number',title:'员工编号',width:200},
				{field:'name',title:'员工名',width:120},
				{field:'type',title:'员工类型',width:120,align:'right'},
			]],
			fitColumns: true
		">
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