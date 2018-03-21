<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/20
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="classes_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="classes_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="classes_index_toolbar">
    <a id="classes_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加班级</a>|
    <a id="classes_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改班级</a>|
    <a id="classes_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除班级</a>
    <a id="classes_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量班级</a>
    <a id="classes_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量班级</a>
</div>

<script type="text/javascript">
    var _classes_index_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'name',title:'班级名称',width:200},
                {field:'number',title:'班级编号',width:150},
                {field:'grade',title:'年级',width:100},
                {field:'teacher_name',title:'班主任姓名',width:150,formatter: function(value,row,index){
                        if (row.teacher != null){
                            return (row.teacher.name==null?"":row.teacher.name);
                        }
                        return "未指定";
                    }},
                {field:'teacher_phone',title:'班主任电话',width:200,formatter: function(value,row,index){
                        if (row.teacher != null){
                            return (row.teacher.phone==null?"":row.teacher.phone);
                        }
                        return "未指定";
                    }},
                {field:'teacher',title:'班主任',width:0,hidden:'true'},
                {field:'state',title:'状态',width:100},
            ]];
            var _url = bs.base_url + 'classes/getByPage.action';
            bs.datagrid_load('classes_index_dg',_columns,_url,'classes_index_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#classes_index_add').on('click', function () {
                bs.show_dialog_min('classes_dialog',bs.base_url+'classes/addUI.action','添加班级',{flag:'add'});
            });
            $('#classes_index_update').on('click', function () {
                var _row = $('#classes_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'classes/addUI.action';
                    var _queryParams = {row:_row, flag:'update'};
                    bs.show_dialog_min('classes_dialog',_url,'修改班级信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#classes_index_delete').on('click', function () {
                var _row = $('#classes_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'classes/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'classes_index_dg','您确认想要删除这个教师信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
        }
    }

    var classes_index_app = (function () {
        $(function () {
            _classes_index_js.datagrid_init(null);
            _classes_index_js.btn_init();
        });
    })();
</script>