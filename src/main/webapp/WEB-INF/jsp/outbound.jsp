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
    function setNum() {
        var val = $('#dg').combogrid('getValue');
        alert(val);
    }


    function bindData() {
        $('#dg').datagrid({
            title: '出库单',
            url: '/outbound/outboundPlan',
            pagination: true,
            singleSelect: true,
            rownumbers: true,
            pageNumber: 1,
            height: 420,
            pageSize: 10,
            onClickRow: onClickRow,
            pageList: [10, 15, 20, 25, 30],
            columns: [[

                {
                    field: 'Invid', width: 225, title: '批属性编号',

                    editor: {
                        type: 'combogrid',
                        options: {
                            panelWidth: 500,
                            idField: 'id',
                            textField: 'productName',
                            url: '/inventory/getAllCombo',
                            method: 'get',
                            columns: [[
                                {field: 'id', title: '产品行号', width: 225},
                                {field: 'productName', title: '名称', width: 80},
                                {field: 'amount', title: '数量', width: 80, align: 'right'},
                                {field: 'storehousenumber', title: '库位', width: 123, align: 'right'},
                                {field: 'productnumber', title: '产品编号', width: 123, align: 'right'},
                            ]],
                            fitColumns: true
                        }

                    }
                },
                /*   { field: 'productnumber', title: '产品编号',editor:"text" },*/
                {field: 'amount', width: 100, title: '数量', editor: "text"},

            ]],
            toolbar: '#tb'
        });
    }

    //编辑状态
    function endEditing() {
        if (editIndex == undefined) {
            return true
        }
        if ($('#dg').datagrid('validateRow', editIndex)) {
            var ed = $('#dg').datagrid('getEditor', {index: editIndex, field: 'productid'});
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
            $('#dg').datagrid('appendRow', {});
            editIndex = $('#dg').datagrid('getRows').length - 1;
            $('#dg').datagrid('selectRow', editIndex)
                .datagrid('beginEdit', editIndex);
        }
    }

    //删除一行
    function remove() {
        if (editIndex == undefined) {
            return
        }
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
                //  var supplier=$('#supplierNum').val();
                var customer = $("#supplierNum").combogrid('grid').datagrid('getSelected').number;
                var Recipient = $("#Recipient").combogrid('grid').datagrid('getSelected').number;
                console.log(customer);
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
        $.post("/outbound/CommitBach", {
            effectRow: effectRow.inserted,
            customer: customer,
            Recipient:Recipient
        }, function (rsp) {
            if (rsp) {
                $dg.datagrid('acceptChanges');
                bindData();
                alert(rsp)
            }
        }, "JSON").error(function (rsp) {
            alert(rsp.responseText);
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
    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
       id="delButton">删除</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'"
       id="saveButton">保存</a>
    <span>客户编号:</span>
    <select id="supplierNum" class="easyui-combogrid" style="width:250px" data-options="
			panelWidth: 800,
			idField: 'number',
			textField: 'name',
			url: '/customer/getAllCombo',
			method: 'get',
			columns: [[
				{field:'number',title:'客户编号',width:200},
				{field:'name',title:'客户名称',width:120},
				{field:'contact',title:'客户联系人',width:120,align:'right'},
				{field:'address',title:'客户地址',width:120,align:'right'},
				{field:'postcode',title:'客户邮编',width:120},
				{field:'phone',title:'客户电话',width:120,align:'center'}
			]],
			fitColumns: true
		">
    </select>

    <span>接收人:</span>
    <select id="Recipient" class="easyui-combogrid" style="width:250px" data-options="
			panelWidth: 800,
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
    </select>
</div>

</body>
</html>