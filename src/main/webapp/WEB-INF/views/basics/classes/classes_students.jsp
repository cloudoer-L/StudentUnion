<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/26
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="classes_student_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="classes_student_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="classes_student_toolbar">
    <a id="classes_student_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加学生</a>|
    <a id="classes_student_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改学生</a>|
    <a id="classes_student_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除学生</a>
</div>


<script type="text/javascript">
    var _classes_student_js = {
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
                {field:'dormitory_name',title:'寝室',width:150,formatter: function(value,row,index){
                        if (row.dormitory != null){
                            return (row.dormitory.name==null?"":row.dormitory.name);
                        }
                        return "未指定";
                    }},
                {field:'state',title:'状态',width:100},
            ]];
            var _url = bs.base_url + 'classes/getStudents.action?id=' + _classes_info_js._qp.row.id;
            bs.datagrid_load('classes_student_dg',_columns,_url,'classes_student_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#classes_student_add').on('click', function () {
                bs.show_dialog_min('student_dialog',bs.base_url+'student/addUI.action','添加学生',{flag:'add',classesId:_classes_info_js._qp.row.id,dgId:'classes_student_dg'});
            });
            $('#classes_student_update').on('click', function () {
                var _row = $('#classes_student_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'student/addUI.action';
                    var _queryParams = {row:_row, flag:'update',classesId:_classes_info_js._qp.row.id,dgId:'classes_student_dg'};
                    bs.show_dialog_min('student_dialog',_url,'修改学生信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#classes_student_delete').on('click', function () {
                var _row = $('#classes_student_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'student/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'classes_student_dg','您确认想要删除这个学生信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
        }
    }

    var classes_student_app = (function () {
        $(function () {
            _classes_student_js.datagrid_init(null);
            _classes_student_js.btn_init();
        });
    })();
</script>