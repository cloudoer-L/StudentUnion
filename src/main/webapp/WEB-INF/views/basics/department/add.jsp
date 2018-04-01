<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/29
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="department_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <form id="department_add_form">
            <input type="hidden" id="department_add_id" name="department.id">
            <table class="table1" style="width: 100%">
                <tr>
                    <th>班级名称：</th>
                    <td>
                        <input id="department_add_name" class="easyui-textbox" name="department.name" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>班级编号：</th>
                    <td>
                        <input id="department_add_number" class="easyui-textbox" name="department.number" data-options="required:true">
                    </td>
                </tr>
                <tr id="department_leader_tr">
                    <th>部长：</th>
                    <td>
                        <input id="department_add_leader" name="leaderId" />
                    </td>
                </tr>
                <tr>
                    <th>上级部门：</th>
                    <td>
                        <input id="department_add_superior" name="superiorId" />
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <input id="department_add_introduce" class="easyui-textbox" name="department.introduce" data-options="required:false">
                    </td>
                </tr>
                <tr>
                    <th>部门介绍：</th>
                    <td>
                        <input id="department_add_state" class="easyui-textbox" name="department.state" data-options="required:false">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
        <div style="position: absolute; right: 20px">
            <a href="javascript:;" id="department_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
            <a href="javascript:;" id="department_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var _department_add_js = {
        _qp:null,
        leader_category:function (_departmentId) {
            var _columns = [[
                {field:'id',title:'ID',width:100,hidden:'true'},
                {field:'name',title:'部员姓名',width:150},
                {field:'number',title:'部员编号',width:150}
            ]];
            var _url = bs.base_url + 'department/getMembers.action?id=' + _departmentId;
            bs.load_category('department_add_leader', _columns, _url, 'id', 'name')
        },
        superior_superior:function (_departmentId) {
            var _columns = [[
                {field:'id',title:'ID',width:100,hidden:'true'},
                {field:'name',title:'部门名称',width:150},
                {field:'number',title:'部门编号',width:150}
            ]];
            var _url = bs.base_url + 'department/getAll.action';
            bs.load_category('department_add_superior', _columns, _url, 'id', 'name')
        },
        init:function () {
            _department_add_js._qp = $('#department_dialog').dialog('options').queryParams;
            _department_add_js.superior_superior();
            if (_department_add_js._qp.flag == 'add'){
                $('#department_leader_tr').html('');
            }else if (_department_add_js._qp.flag == 'update'){
                if (_department_add_js._qp.row != null){
                    var _row = _department_add_js._qp.row
                    _department_add_js.leader_category(_row.id)
                    $('#department_add_id').val(_row.id);
                    $('#department_add_name').textbox('setValue',_row.name);
                    $('#department_add_number').textbox('setValue',_row.number);
                    $('#dormitory_add_state').textbox('setValue',_row.state);
                    $('#department_add_introduce').textbox('setValue',_row.introduce);
                    if (_row.superior != null){
                        $("#department_add_superior").combogrid('setValue',_row.superior.id);
                    }
                    if (_row.leader != null){
                        $("#department_add_leader").combogrid('setValue',_row.leader.id);
                    }
                }
            }
        },
        btn_init:function () {
            $('#department_add_cancel').on('click',function () {
                $("#department_dialog").dialog('destroy');
            });
            $('#department_add_ok').on('click',function () {
                var _url = '';
                if (_department_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'department/add.action';
                }else if (_department_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'department/update.action';
                }
                bs.formSubmit_datagrid(_url,'department_add_form','department_dialog','department_index_dg');
            });
        }
    }

    var department_add_app = (function () {
        $(function () {
            _department_add_js.init();
            _department_add_js.btn_init();
        });
    })();
</script>