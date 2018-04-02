<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/4/2
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="member_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="member_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="member_index_toolbar">
    <a id="member_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加部员</a>|
    <a id="member_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改部员</a>|
    <a id="member_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除部员</a>
    <a id="member_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导入</a>
    <a id="member_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导出</a>
</div>

<script type="text/javascript">
    var _member_index_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'person',title:'person',width:0,hidden:'true'},
                {field:'department',title:'department',width:0,hidden:'true'},
                {field:'name',title:'姓名',width:100},
                {field:'number',title:'编号',width:100},
                {field:'sex',title:'性别',width:100,formatter: function(value,row,index){
                        if (row.person != null){
                            return (row.person.sex==null?"":row.person.sex);
                        }
                        return "未指定";
                    }},
                {field:'phone',title:'电话',width:100,formatter: function(value,row,index){
                        if (row.person != null){
                            return (row.person.phone==null?"":row.person.phone);
                        }
                        return "未指定";
                    }},
                {field:'qq',title:'QQ号',width:100,formatter: function(value,row,index){
                        if (row.person != null){
                            return (row.person.qq==null?"":row.person.qq);
                        }
                        return "未指定";
                    }},
                {field:'email',title:'邮箱',width:150,formatter: function(value,row,index){
                        if (row.person != null){
                            return (row.person.email==null?"":row.person.email);
                        }
                        return "未指定";
                    }},
                {field:'departmentName',title:'所在部门',width:100,formatter: function(value,row,index){
                        if (row.department != null){
                            return (row.department.name==null?"":row.department.name);
                        }
                        return "未指定";
                    }},
                {field:'departmentNumber',title:'部门编号',width:100,formatter: function(value,row,index){
                        if (row.department != null){
                            return (row.department.number==null?"":row.department.number);
                        }
                        return "未指定";
                    }},
                {field:'place',title:'职务',width:100},
                {field:'state',title:'状态',width:100},
            ]];
            var _url = bs.base_url + 'member/getByPage.action';
            bs.datagrid_load('member_index_dg',_columns,_url,'member_index_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#member_index_add').on('click', function () {
                bs.show_dialog_min('member_dialog',bs.base_url+'member/addUI.action','添加学生',{flag:'add',dgId:'member_index_dg'});
            });
            $('#member_index_update').on('click', function () {
                var _row = $('#member_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'member/addUI.action';
                    var _queryParams = {row:_row, flag:'update',dgId:'member_index_dg'};
                    bs.show_dialog_min('member_dialog',_url,'修改学生信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#member_index_delete').on('click', function () {
                var _row = $('#member_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'member/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'member_index_dg','您确认想要删除这个学生信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#member_index_import').on('click', function () {
                bs.show_dialog_min('member_dialog',bs.base_url + 'member/importUI.action', '批量导入', null);
            });
            $('#member_index_export').on('click', function () {
                window.open(bs.base_url + 'member/exportFile.action');
            });
        }
    }

    var member_index_app = (function () {
        $(function () {
            _member_index_js.datagrid_init(null);
            _member_index_js.btn_init();
        });
    })();
</script>