<%--
  Created by IntelliJ IDEA.
  User: 奥巴马
  Date: 2018/3/12
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="teacher_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="teacher_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="teacher_index_toolbar">
    <a id="teacher_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加教师</a>|
    <a id="teacher_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改教师</a>|
    <a id="teacher_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除教师</a>
    <a id="teacher_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导入</a>
    <a id="teacher_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导出</a>
</div>

<script type="text/javascript">
    var _teacher_index_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'name',title:'姓名',width:100},
                {field:'number',title:'教师编号',width:100},
                {field:'sex',title:'性别',width:100},
                {field:'phone',title:'电话',width:100},
                {field:'qq',title:'QQ号',width:100},
                {field:'email',title:'邮箱',width:200},
                {field:'state',title:'状态',width:100},
            ]];
            var _url = bs.base_url + 'teacher/getByPage.action';
            bs.datagrid_load('teacher_index_dg',_columns,_url,'teacher_index_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#teacher_index_add').on('click', function () {
                bs.show_dialog_min('teacher_dialog',bs.base_url+'teacher/addUI.action','添加教师',{flag:'add'});
            });
            $('#teacher_index_update').on('click', function () {
                var _row = $('#teacher_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'teacher/addUI.action';
                    var _queryParams = {row:_row, flag:'update'};
                    bs.show_dialog_min('teacher_dialog',_url,'修改教师信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#teacher_index_delete').on('click', function () {
                var _row = $('#teacher_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'teacher/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'teacher_index_dg','您确认想要删除这个教师信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#teacher_index_import').on('click', function () {
                bs.show_dialog_min('teacher_dialog',bs.base_url + 'teacher/importUI.action', '批量导入', null);
            });
            $('#teacher_index_export').on('click', function () {
                window.open(bs.base_url + 'teacher/exportFile.action');
            });
        }
    }
    
    var teacher_index_app = (function () {
        $(function () {
            _teacher_index_js.datagrid_init(null);
            _teacher_index_js.btn_init();
        });
    })();
</script>
