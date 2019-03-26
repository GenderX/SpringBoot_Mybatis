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
    <title>入库单</title>
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
            $('#dlg').dialog('open').dialog('setTitle', '编辑入库单');
            $('#fm').form('load', row);
            url = '/inbound/updateMaster'
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

    function formatOper(val,row,index){
        return '<a href="#" onclick="downloads('+row.number+')">下载</a>';
    }


    function downloads(id) {
        var url = "/inbound/downloadPlan?num="+id;
        window.location=url;
    }

</script>

<table id="dg" class="easyui-datagrid" title="入库计划" style="width:100%;height:auto"
       data-options="
       pagination:true,
       singleSelect:true,
       collapsible:true,
       url:'/inbound/getAll',
       method:'get',
       striped:true,
       toolbar:'#toolbar' "
>
    <thead>

    <tr>
        <th data-options="field:'number',width:80">入库编号</th>
        <th data-options="field:'suppliernumber',width:180,hidden:true">种类编号</th>
        <th data-options="field:'suppliername',width:180">供应商</th>
        <th data-options="field:'createtime',width:180">创建时间</th>
        <th data-options="field:'completetime',width:180">完成时间</th>
        <th data-options="field:'recipient',width:100,hidden:true">接收人ID</th>
        <th data-options="field:'recipientname',width:100">接收人</th>
        <th data-options="field:'isfinish',width:120,align:'left'">是否完成</th>
        <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">下载</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editUser()">编辑产品</a>
    <span>产品编号:</span>
    <input id="Number" style="line-height:26px;border:1px solid #ccc">
    <a href="#" class="easyui-linkbutton" plain="false" onclick="doSearch()">Search</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:600px"
     data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
    <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <h3>产品信息</h3>
        <div class="div-leftform">
            <div style="margin-bottom:10px">
                <input name="number" class="easyui-textbox"  label="产品编号:" readonly="readonly" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="name" class="easyui-textbox" required="true" label="产品名:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input label="职位：" id="categorynumber" name="categorynumber" class="easyui-combobox" data-options="
         valueField: 'number', textField: 'name',
         url: '/product/getCategorynumber'"/>
                <%--<input name="categoryname" class="easyui-textbox" label="分类:" style="width:100%">--%>
            </div>
            <div style="margin-bottom:10px">
                <input name="barcode" class="easyui-textbox" label="条码:" style="width:100%">
            </div>

        </div>
        <div class="div-rightform">
            <div style="margin-bottom:10px">
                <input name="price" class="easyui-textbox" label="价格:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="unit" class="easyui-textbox" label="计量单位:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="spec" class="easyui-textbox" label="产品型号:" style="width:100%">
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