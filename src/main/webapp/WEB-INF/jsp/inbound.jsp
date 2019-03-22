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
    <link rel="stylesheet" type="text/css" href="/easyui/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
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
            title: '入库单',
            url: '/inbound/inboundPlan',
            pagination: true,
            singleSelect: true,
            rownumbers: true,
            pageNumber: 1,
            height: 420,
            pageSize: 10,
            onClickRow: onClickRow,
            pageList: [10, 15, 20, 25, 30],
            columns: [[

                { field: 'productnumber',width:225,title: '产品编号',
                    editor:{ type:'combogrid',
                             options:{
                                 panelWidth: 500,
                                 idField: 'number',
                                 textField: 'name',
                                 url: '/product/getAllCombo',
                                 method: 'get',
                                 columns: [[
                                     {field:'number',title:'产品编号',width:225},
                                     {field:'name',title:'名称',width:80},
                                     {field:'categoryname',title:'种类',width:80,align:'right'},
                                     {field:'barcode',title:'条码',width:123,align:'right'},
                                     {field:'spec',title:'产品型号',width:100},
                                     {field:'price',title:'价格',width:60,align:'center'},
                                     {field:'unit',title:'单位',width:60,align:'center'}
                                 ]],
                                 fitColumns: true
                             }

                    } },
             /*   { field: 'productnumber', title: '产品编号',editor:"text" },*/
                { field: 'amount', width:100,title: '数量' ,editor:"text"},
                { field: 'storehousenumber', width:150,title: '库位', editor:{ type:'combogrid',
                        options:{
                            panelWidth: 500,
                            idField: 'number',
                            textField: 'name',
                            url: '/warehouse/getAllCombo',
                            method: 'get',
                            columns: [[
                                {field:'number',title:'产品编号',width:225},
                                {field:'name',title:'名称',width:80},
                                {field:'admin',title:'管理员',width:80,align:'right'},

                            ]],
                            fitColumns: true
                        }

                    }  }
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
        $.post("/inbound/CommitBach", effectRow, function (rsp) {
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