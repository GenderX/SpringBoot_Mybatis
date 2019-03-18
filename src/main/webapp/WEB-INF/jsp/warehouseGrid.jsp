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
    <title>仓库规划</title>
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
            $.messager.confirm('确认', '是否删除货位?', function (r) {
                if (r) {
                    $.post('/warehouse/deleteHouseNumber', {number: row.number}, function (result) {
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
        $('#dlg').dialog('open').dialog('setTitle', '添加货位');
        $('#fm').form('clear');
        url = '/warehouse/insertHouseNumber';
    }

    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '编辑货位');
            $('#fm').form('load', row);
            url = '/warehouse/updateHouseNumber?Number=' + row.number;
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

<table id="dg" class="easyui-datagrid" title="货位列表" style="width:100%;height:auto"
       data-options="
       pagination:true,
       singleSelect:true,
       collapsible:true,
       url:'/warehouse/getAll',
       method:'get',
       striped:true,
       toolbar:'#toolbar' "
>
    <thead>

    <tr>
        <th data-options="field:'number',width:80">货位编号</th>
        <th data-options="field:'name',width:180">货位名</th>
        <th data-options="field:'admin',width:180,hidden:true">管理员</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加货位</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editUser()">编辑货位</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除货位</a>

    <span>货位编号:</span>
    <input id="Number" style="line-height:26px;border:1px solid #ccc">
    <span>货位名称:</span>
    <input id="Name" style="line-height:26px;border:1px solid #ccc">
    <a href="#" class="easyui-linkbutton" plain="false" onclick="doSearch()">Search</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:600px"
     data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
    <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <h3>货位信息</h3>
        <div style="margin-bottom:10px">
            <input name="number" class="easyui-textbox" required="true" label="货位编号:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="name" class="easyui-textbox" required="true" label="货位名:" style="width:100%">
        </div>
        <div style="margin-bottom:10px">
            <input name="admin" class="easyui-textbox" required="true" label="管理员:" style="width:100%">
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