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
    <title>产品档案</title>
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
        if (row) {
            console.log(row);
            $.messager.confirm('确认', '是否删除产品?', function (r) {
                if (r) {
                    $.post('/product/deleteProduct', {number: row.number}, function (result) {
                        if (result.success) {
                            $('#dg').datagrid('reload');	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        }
                    }, 'json');
                }
            });
        }
    }

    function newUser() {
        //打开一个对话框
        $('#dlg').dialog('open').dialog('setTitle', '添加产品');
        $('#fm').form('clear');
        url = '/product/insertPro';
    }

    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '编辑产品');
            $('#fm').form('load', row);
            url = '/product/updateProduct'
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

<table id="dg" class="easyui-datagrid" title="产品列表" style="width:100%;height:auto"
       data-options="
       pagination:true,
       singleSelect:true,
       collapsible:true,
       url:'/product/getAll',
       method:'get',
       striped:true,
       toolbar:'#toolbar' "
>
    <thead>

    <tr>
        <th data-options="field:'number',width:80">产品编号</th>
        <th data-options="field:'categorynumber',width:180,hidden:true">种类编号</th>
        <th data-options="field:'categoryname',width:180">种类</th>
        <th data-options="field:'barcode',width:180">条码</th>
        <th data-options="field:'name',width:100,align:'left'">名称</th>
        <th data-options="field:'spec',width:140,align:'left'">产品型号</th>
        <th data-options="field:'price',width:100">价格</th>
        <th data-options="field:'unit',width:120,align:'left'">单位</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加产品</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editUser()">编辑产品</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除用户</a>

    <span>产品编号:</span>
    <input id="Number" style="line-height:26px;border:1px solid #ccc">
    <span>产品名称:</span>
    <input id="Name" style="line-height:26px;border:1px solid #ccc">
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