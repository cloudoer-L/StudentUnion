<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/26
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="classes_committee_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="classes_committee_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="classes_committee_toolbar">
    <a id="classes_committee_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加班委</a>|
    <a id="classes_committee_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改班委</a>|
    <a id="classes_committee_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除班委</a>
</div>

<script type="text/javascript">
    var _classes_committee_js = {
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'student',title:'student',width:0,hidden:'true'},
                {field:'positionNumber',title:'positionNumber',width:0,hidden:'true'},
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
            bs.datagrid_load('classes_committee_dg',_columns,_url,'classes_committee_toolbar',_queryParams);
        },
        btn_init : function () {
            $('#classes_committee_add').on('click', function () {
                var _queryParams = {flag:'add',classesNumber:_classes_info_js._qp.row.number,dgId:'classes_committee_dg'};
                bs.show_dialog_min('committee_dialog',bs.base_url+'committee/addUI.action','添加班级',_queryParams);
            });
            $('#classes_committee_update').on('click', function () {
                var _row = $('#classes_committee_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'committee/addUI.action';
                    var _queryParams = {row:_row, flag:'update',classesNumber:_classes_info_js._qp.row.number,dgId:'classes_committee_dg'};
                    bs.show_dialog_min('committee_dialog',_url,'修改班级信息',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#classes_committee_delete').on('click', function () {
                var _row = $('#classes_committee_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'committee/delete.action?id='+_row.id;
                    bs.base_ajax_datagrid(_url,'classes_committee_dg','您确认想要删除这个班级信息吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
        }
    }

    var classes_committee_app = (function () {
        $(function () {
            _classes_committee_js.datagrid_init(null);
            _classes_committee_js.btn_init();
        });
    })()
</script>