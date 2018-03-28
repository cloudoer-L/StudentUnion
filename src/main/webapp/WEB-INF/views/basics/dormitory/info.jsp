<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/26
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dormitory_info_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north'" style="height: 200px;">
        <form id="dormitory_student_form">
            <input type="hidden" id="dormitory_student_id" name="dormitory.id">
            <table class="table1" style="width: 100%;">
                <tr>
                    <th>寝室名称：</th>
                    <td>
                        <input id="dormitory_student_name" class="easyui-textbox" name="dormitory.name" data-options="readonly:true">
                    </td>
                    <th>寝室编号：</th>
                    <td>
                        <input id="dormitory_student_number" class="easyui-textbox" name="dormitory.number" data-options="readonly:true">
                    </td>
                </tr>
                <tr>
                    <th>寝室搂名：</th>
                    <td>
                        <input id="dormitory_student_buildingName" class="easyui-textbox" name="dormitory.buildingName" data-options="readonly:true">
                    </td>
                    <th>寝室门牌号：</th>
                    <td>
                        <input id="dormitory_student_buildingNumber" class="easyui-textbox" name="dormitory.buildingNumber" data-options="readonly:true">
                    </td>
                </tr>
                <tr>
                    <th>寝室室长：</th>
                    <td>
                        <input id="dormitory_student_admin" class="easyui-textbox" name="adminId" data-options="readonly:true">
                    </td>
                    <th>总床位数：</th>
                    <td>
                        <input id="dormitory_student_capacity" class="easyui-numberspinner" name="dormitory.capacity"
                               data-options="min:1,max:10,editable:false,readonly:true">
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <input id="dormitory_student_state" class="easyui-textbox" name="dormitory.state" data-options="readonly:false">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',fit:true">
        <table id="dormitory_student_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="dormitory_student_toolbar">
    <a href="javascript:;" id="dormitory_student_appointAdmin" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">设为室长</a>
</div>

<script type="text/javascript">
    var _dormitory_student_js = {
        _qp:null,
        data_init:function () {
            _dormitory_student_js._qp = $('#dormitory_info_dialog').dialog('options').queryParams;
            var _row = _dormitory_student_js._qp.row
            $('#dormitory_student_id').val(_row.id);
            $('#dormitory_student_name').textbox('setValue',_row.name);
            $('#dormitory_student_number').textbox('setValue',_row.number);
            $('#dormitory_student_buildingName').textbox('setValue',_row.buildingName);
            $('#dormitory_student_buildingNumber').textbox('setValue',_row.buildingNumber);
            $('#dormitory_student_capacity').textbox('setValue',_row.capacity);
            $('#dormitory_student_state').textbox('setValue',_row.state);
            if (_row.admin != null){
                $("#dormitory_student_admin").textbox('setValue',_row.admin.name);
            }
        },
        datagrid_init : function (_queryParams) {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'classes',title:'ID',width:0,hidden:'true'},
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
                {field:'state',title:'状态',width:100},
            ]];
            var _url = bs.base_url + 'dormitory/getStudents.action?id='+_dormitory_student_js._qp.row.id;
            bs.datagrid_load('dormitory_student_dg',_columns,_url,'dormitory_student_toolbar',_queryParams);
        },
        btn_init:function () {
            $('#dormitory_student_appointAdmin').on('click', function () {
                var _row = $('#dormitory_student_dg').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'dormitory/appointAdmin.action?id='+_dormitory_student_js._qp.row.id + '&adminId=' + _row.id;
                    bs.base_ajax_datagrid(_url,'dormitory_index_dg','确定将该学生设为该寝室室长？');
                    $("#dormitory_student_admin").textbox('setValue',_row.name);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
        }
    }

    var dormitory_student_app = (function () {
        $(function () {
            _dormitory_student_js.data_init();
            _dormitory_student_js.datagrid_init(null);
            _dormitory_student_js.btn_init();
        });
    })();
</script>