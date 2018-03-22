<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/22
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="position_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="position_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="position_index_toolbar">
    <a id="position_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加职务</a>|
    <a id="position_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改职务</a>|
    <a id="position_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除职务</a>
    <a id="position_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导入</a>
    <a id="position_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导出</a>
</div>

<script type="text/javascript">
    var _position_index_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'name',title:'职务名称',width:200},
                {field:'number',title:'职务编号',width:150},
                {field:'introduce',title:'职务说明',width:400},
                {field:'state',title:'状态',width:100}
            ]];
            var _url = bs.base_url + 'position/getByPage.action';
            bs.datagrid_load('position_index_dg',_columns,_url,'position_index_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#position_index_add').on('click', function () {
                bs.show_dialog_min('position_dialog', bs.base_url + 'position/addUI.action', '添加职务', {flag: 'add'});
            });
            $('#position_index_update').on('click', function () {
                var _row = $('#position_index_dg').datagrid('getSelected');
                if (_row != null) {
                    var _url = bs.base_url + 'position/addUI.action';
                    var _queryParams = {row: _row, flag: 'update'};
                    bs.show_dialog_min('position_dialog', _url, '修改职务信息', _queryParams);
                } else {
                    $.messager.alert('警告', '请选择一条数据');
                }
            });
            $('#position_index_delete').on('click', function () {
                var _row = $('#position_index_dg').datagrid('getSelected');
                if (_row != null) {
                    var _url = bs.base_url + 'position/delete.action?id=' + _row.id;
                    bs.base_ajax_datagrid(_url, 'position_index_dg', '您确认想要删除这个职务信息吗？');
                } else {
                    $.messager.alert('警告', '请选择一条数据');
                }
            });
            $('#position_index_import').on('click', function () {
                bs.show_dialog_min('position_dialog', bs.base_url + 'position/importUI.action', '批量导入', null);
            });
            $('#position_index_export').on('click', function () {
                window.open(bs.base_url + 'position/exportFile.action');
            });
        }
    }

    var position_index_app = (function () {
        $(function () {
            _position_index_js.datagrid_init(null);
            _position_index_js.btn_init();
        })
    })();
</script>
