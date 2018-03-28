<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/24
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="student_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="student_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="student_index_toolbar">
    <a id="student_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加学生</a>|
    <a id="student_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改学生</a>|
    <a id="student_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除学生</a>
    <a id="student_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导入</a>
    <a id="student_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导出</a>
</div>

<script type="text/javascript">
    var _student_index_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'classes',title:'ID',width:0,hidden:'true'},
                {field:'dormitory',title:'ID',width:0,hidden:'true'},
                {field:'name',title:'姓名',width:100},
                {field:'number',title:'学号',width:100},
                {field:'sex',title:'性别',width:100},
                {field:'phone',title:'电话',width:100},
                {field:'qq',title:'QQ号',width:100},
                {field:'email',title:'邮箱',width:200},
                {field:'classes_name',title:'班级',width:150,formatter: function(value,row,index){
                        if (row.classes != null){
                            return (row.classes.name==null?"":row.classes.name);
                        }
                        return "未指定";
                    }},
                {field:'dormitory_name',title:'寝室',width:150,formatter: function(value,row,index){
                        if (row.dormitory != null){
                            return (row.dormitory.name==null?"":row.dormitory.name);
                        }
                        return "未指定";
                    }},
                {field:'state',title:'状态',width:100},
            ]];
            var _url = bs.base_url + 'student/getByPage.action';
            bs.datagrid_load('student_index_dg',_columns,_url,'student_index_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#student_index_add').on('click', function () {
                bs.show_dialog_min('student_dialog',bs.base_url+'student/addUI.action','添加学生',{flag:'add',dgId:'student_index_dg'});
            });
            $('#student_index_update').on('click', function () {
                var _row = $('#student_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'student/addUI.action';
                    var _queryParams = {row:_row, flag:'update',dgId:'student_index_dg'};
                    bs.show_dialog_min('student_dialog',_url,'修改学生信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#student_index_delete').on('click', function () {
                var _row = $('#student_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'student/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'student_index_dg','您确认想要删除这个学生信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#student_index_import').on('click', function () {
                bs.show_dialog_min('student_dialog',bs.base_url + 'student/importUI.action', '批量导入', null);
            });
            $('#student_index_export').on('click', function () {
                window.open(bs.base_url + 'student/exportFile.action');
            });
        }
    }

    var student_index_app = (function () {
        $(function () {
            _student_index_js.datagrid_init(null);
            _student_index_js.btn_init();
        });
    })();
</script>