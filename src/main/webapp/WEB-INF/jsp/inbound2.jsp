<%--
  Created by IntelliJ IDEA.
  User: wolei
  Date: 2019/3/21
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Build CRUD DataGrid with jQuery EasyUI - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.edatagrid.js"></script>
</head>
<script type="text/javascript">
    var editIndex = undefined;
    $(function () {
        bindData();
        bindAddButton();
        bindDelButton();
        bindSaveButton();
    });


    function bindData() {
        $('#dg').datagrid({
            title: '部门管理',
            url: '/Home/GetBranch',
            pagination: true,
            singleSelect: true,
            rownumbers: true,
            pageNumber: 1,
            height: 420,
            pageSize: 10,
            onClickRow: onClickRow,
            pageList: [10, 15, 20, 25, 30],
            columns: [[
                { field: 'ID', title: 'ID', hidden: true },
                { field: 'BrcName', title: '部门名称' ,editor:"text"},
                { field: 'BrcComment', title: '备注', editor: "text" }
            ]],
            toolbar: '#tb'
        });
    }

    //编辑状态
    function endEditing() {
        if (editIndex == undefined) { return true }
        if ($('#dg').datagrid('validateRow', editIndex)) {
            var ed = $('#dg').datagrid('getEditor', { index: editIndex, field: 'productid' });
            $('#dg').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    //单击某行进行编辑
    function onClickRow(index) {
        if (editIndex != index) {
            if (endEditing()) {
                $('#dg').datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
                editIndex = index;
            } else {
                $('#dg').datagrid('selectRow', editIndex);
            }
        }
    }

    //添加一行
    function append() {
        if (endEditing()) {
            $('#dg').datagrid('appendRow', {  });
            editIndex = $('#dg').datagrid('getRows').length - 1;
            $('#dg').datagrid('selectRow', editIndex)
                .datagrid('beginEdit', editIndex);
        }
    }
    //删除一行
    function remove() {
        if (editIndex == undefined) { return }
        $('#dg').datagrid('cancelEdit', editIndex)
            .datagrid('deleteRow', editIndex);
        editIndex = undefined;
    }
    //撤销编辑
    function reject() {
        $('#dg').datagrid('rejectChanges');
        editIndex = undefined;
    }

    //获得编辑后的数据,并提交到后台
    function saveChanges() {
        if (endEditing()) {
            var $dg = $('#dg');
            var rows = $dg.datagrid('getChanges');
            if (rows.length) {
                var inserted = $dg.datagrid('getChanges', "inserted");
                var deleted = $dg.datagrid('getChanges', "deleted");
                var updated = $dg.datagrid('getChanges', "updated");
                var effectRow = new Object();
                if (inserted.length) {
                    effectRow["inserted"] = JSON.stringify(inserted);
                }
                if (deleted.length) {
                    effectRow["deleted"] = JSON.stringify(deleted);
                }
                if (updated.length) {
                    effectRow["updated"] = JSON.stringify(updated);
                }
            }
        }
        $.post("/Home/Commit", effectRow, function (rsp) {
            if (rsp) {
                $dg.datagrid('acceptChanges');
                bindData();
            }
        }, "JSON").error(function () {
            $.messager.alert("提示", "提交错误了！");
        });

    }

    function bindSaveButton() {
        $("#saveButton").click(function () {
            saveChanges();
        });
    }
    function bindAddButton() {
        $("#addButton").click(function () {
            append();
        });
    }
    function bindDelButton() {
        $("#delButton").click(function () {
            remove();
        });
    }
</script>

<body>
<table id="dg">
</table>
<div id="tb">
    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="addButton">新增</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delButton">删除</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" id="saveButton">保存</a>
</div>

</body>
</html>