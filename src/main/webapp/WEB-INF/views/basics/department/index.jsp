<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/29
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="department_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="department_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="department_index_toolbar">
    <a id="department_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加部门</a>|
    <a id="department_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改部门</a>|
    <a id="department_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除部门</a>
    <a id="department_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导入</a>
    <a id="department_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导出</a>
</div>


<script type="text/javascript">
    var _department_index_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'superior',title:'ID',width:0,hidden:'true'},
                {field:'leader',title:'ID',width:0,hidden:'true'},
                {field:'name',title:'部门名称',width:100},
                {field:'number',title:'部门编号',width:100},
                {field:'leaderName',title:'部长姓名',width:150,formatter: function(value,row,index){
                        if (row.leader != null){
                            return (row.leader.name==null?"":row.leader.name);
                        }
                        return "未指定";
                    }},
                {field:'superiorName',title:'上级部门名称',width:200,formatter: function(value,row,index){
                        if (row.superior != null){
                            return (row.superior.name==null?"":row.superior.name);
                        }
                        return "未指定";
                    }},
                {field:'introduce',title:'部门介绍',width:300},
                {field:'state',title:'状态',width:100},
            ]];
            var _url = bs.base_url + 'department/getByPage.action';
            bs.datagrid_load('department_index_dg',_columns,_url,'department_index_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#department_index_add').on('click', function () {
                bs.show_dialog_min('department_dialog',bs.base_url+'department/addUI.action','添加部门',{flag:'add'});
            });
            $('#department_index_update').on('click', function () {
                var _row = $('#department_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'department/addUI.action';
                    var _queryParams = {row:_row, flag:'update'};
                    bs.show_dialog_min('department_dialog',_url,'修改部门信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#department_index_delete').on('click', function () {
                var _row = $('#department_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'department/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'department_index_dg','您确认想要删除这个部门信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#department_index_import').on('click', function () {
                bs.show_dialog_min('department_dialog',bs.base_url + 'department/importUI.action', '批量导入', null);
            });
            $('#department_index_export').on('click', function () {
                window.open(bs.base_url + 'department/exportFile.action');
            });
        }
    }

    var department_index_app = (function () {
        $(function () {
            _department_index_js.datagrid_init(null);
            _department_index_js.btn_init();
        });
    })();
</script>
