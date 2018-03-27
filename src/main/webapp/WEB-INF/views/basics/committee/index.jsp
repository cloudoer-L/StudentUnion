<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/26
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="committee_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="committee_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="committee_index_toolbar">
    <a id="committee_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加班委</a>|
    <a id="committee_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改班委</a>|
    <a id="committee_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除班委</a>
    <a id="committee_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导入</a>
    <a id="committee_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量导出</a>
</div>

<script type="text/javascript">
    var _committee_index_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'student',title:'student',width:0,hidden:'true'},
                {field:'positionNumber',title:'positionNumber',width:0,hidden:'true'},
                {field:'classesName',title:'班级名称',width:200,formatter: function(value,row,index){
                        if (row.student != null && row.student.classes != null){
                            return (row.student.classes.name==null?"":row.student.classes.name);
                        }
                        return "未指定";
                    }},
                {field:'classesNumber',title:'班级编号',width:150,formatter: function(value,row,index){
                        if (row.student != null && row.student.classes != null){
                            return (row.student.classes.number==null?"":row.student.classes.number);
                        }
                        return "未指定";
                    }},
                {field:'studentName',title:'姓名',width:100},
                {field:'studentNumber',title:'学号',width:150,formatter: function(value,row,index){
                        if (row.student != null){
                            return (row.student.number==null?"":row.student.number);
                        }
                        return "未指定";
                    }},
                {field:'studentPhone',title:'电话',width:150,formatter: function(value,row,index){
                        if (row.student != null){
                            return (row.student.phone==null?"":row.student.phone);
                        }
                        return "未指定";
                    }},
                {field:'studentQQ',title:'QQ号码',width:150,formatter: function(value,row,index){
                        if (row.student != null){
                            return (row.student.qq==null?"":row.student.qq);
                        }
                        return "未指定";
                    }},
                {field:'positionName',title:'职务',width:100},
            ]];
            var _url = bs.base_url + 'committee/getByPage.action';
            bs.datagrid_load('committee_index_dg',_columns,_url,'committee_index_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#committee_index_add').on('click', function () {
                bs.show_dialog_min('committee_dialog',bs.base_url+'committee/addUI.action','添加班级',{flag:'add'});
            });
            $('#committee_index_update').on('click', function () {
                var _row = $('#committee_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'committee/addUI.action';
                    var _queryParams = {row:_row, flag:'update'};
                    bs.show_dialog_min('committee_dialog',_url,'修改班级信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#committee_index_delete').on('click', function () {
                var _row = $('#committee_index_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'committee/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'committee_index_dg','您确认想要删除这个班级信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#committee_index_import').on('click', function () {
                bs.show_dialog_min('committee_dialog',bs.base_url + 'committee/importUI.action', '批量导入', null);
            });
            $('#committee_index_export').on('click', function () {
                window.open(bs.base_url + 'committee/exportFile.action');
            });
        }
    }

    var committee_index_app = (function () {
        $(function () {
            _committee_index_js.datagrid_init(null);
            _committee_index_js.btn_init();
        });
    })()
</script>