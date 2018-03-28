<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/23
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dormitory_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="dormitory_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="dormitory_index_toolbar">
    <a id="dormitory_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加寝室</a>|
    <a id="dormitory_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改寝室</a>|
    <a id="dormitory_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除寝室</a>
    <a id="dormitory_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导入</a>
    <a id="dormitory_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导出</a>
    <a id="dormitory_index_info" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">详细信息</a>
</div>

<script type="text/javascript">
    var _dormitory_index_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'admin',title:'ID',width:0,hidden:'true'},
                {field:'name',title:'寝室名称',width:200},
                {field:'buildingName',title:'寝室搂名',width:100},
                {field:'buildingNumber',title:'寝室门牌号',width:100},
                {field:'number',title:'寝室编号',width:150},
                {field:'capacity',title:'总床位数',width:100},
                {field:'adminName',title:'室长',width:200,formatter: function(value,row,index){
                        if (row.admin != null){
                            return (row.admin.name==null?"":row.admin.name);
                        }
                        return "未指定";
                    }},
                {field:'state',title:'状态',width:100},
            ]];
            var _url = bs.base_url + 'dormitory/getByPage.action';
            bs.datagrid_load('dormitory_index_dg',_columns,_url,'dormitory_index_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#dormitory_index_add').on('click', function () {
                bs.show_dialog_min('dormitory_dialog',bs.base_url+'dormitory/addUI.action','添加寝室',{flag:'add'});
            });
            $('#dormitory_index_update').on('click', function () {
                var _row = $('#dormitory_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'dormitory/addUI.action';
                    var _queryParams = {row:_row, flag:'update'};
                    bs.show_dialog_min('dormitory_dialog',_url,'修改寝室信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#dormitory_index_delete').on('click', function () {
                var _row = $('#dormitory_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'dormitory/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'dormitory_index_dg','您确认想要删除这个寝室信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#dormitory_index_import').on('click', function () {
                bs.show_dialog_min('dormitory_dialog',bs.base_url + 'dormitory/importUI.action', '批量导入', null);
            });
            $('#dormitory_index_export').on('click', function () {
                window.open(bs.base_url + 'dormitory/exportFile.action');
            });
            $('#dormitory_index_info').on('click', function () {
                var _row = $('#dormitory_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'dormitory/infoUI.action';
                    var _queryParams = {row:_row};
                    bs.show_dialog_max('dormitory_info_dialog',_url,_row.name+'寝室详细信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
        }
    }

    var dormitory_index_app = (function () {
        $(function () {
            _dormitory_index_js.datagrid_init(null);
            _dormitory_index_js.btn_init();
        });
    })();
</script>