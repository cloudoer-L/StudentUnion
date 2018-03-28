<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/23
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dormitory_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <form id="dormitory_add_form">
            <input type="hidden" id="dormitory_add_id" name="dormitory.id">
            <table class="table1" style="width: 100%">
                <tr>
                    <th>寝室名称：</th>
                    <td>
                        <input id="dormitory_add_name" class="easyui-textbox" name="dormitory.name" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>寝室搂名：</th>
                    <td>
                        <input id="dormitory_add_buildingName" class="easyui-textbox" name="dormitory.buildingName" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>寝室门牌号：</th>
                    <td>
                        <input id="dormitory_add_buildingNumber" class="easyui-textbox" name="dormitory.buildingNumber" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>寝室编号：</th>
                    <td>
                        <input id="dormitory_add_number" class="easyui-textbox" name="dormitory.number" data-options="required:true">
                    </td>
                </tr>
                <tr id="dormitory_add_admin_tr">
                    <th>室长：</th>
                    <td>
                        <input id="dormitory_add_admin" name="adminId" />
                    </td>
                </tr>
                <tr>
                    <th>总床位数：</th>
                    <td>
                        <input id="dormitory_add_capacity" class="easyui-numberspinner" name="dormitory.capacity"
                               required="required" data-options="min:1,max:10,editable:false">
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <input id="dormitory_add_state" class="easyui-textbox" name="dormitory.state" data-options="required:false">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
        <div style="position: absolute; right: 20px">
            <a href="javascript:;" id="dormitory_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
            <a href="javascript:;" id="dormitory_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var _dormitory_add_js = {
        _qp:null,
        load_category:function (_dormitoryId) {
            var _columns = [[
                {field:'id',title:'ID',width:100,hidden:'true'},
                {field:'name',title:'学生姓名',width:150},
                {field:'number',title:'学号',width:150}
            ]];
            var _url = bs.base_url + 'dormitory/getStudents.action?id=' + _dormitoryId;
            bs.load_category('dormitory_add_admin', _columns, _url, 'id', 'name')
        },
        init:function () {
            _dormitory_add_js._qp = $('#dormitory_dialog').dialog('options').queryParams;
            if (_dormitory_add_js._qp.flag == 'add'){
                $('#dormitory_add_admin_tr').html('');
            }else if (_dormitory_add_js._qp.flag == 'update'){
                if (_dormitory_add_js._qp.row != null){
                    var _row = _dormitory_add_js._qp.row
                    _dormitory_add_js.load_category(_row.id)
                    $('#dormitory_add_id').val(_row.id);
                    $('#dormitory_add_name').textbox('setValue',_row.name);
                    $('#dormitory_add_number').textbox('setValue',_row.number);
                    $('#dormitory_add_state').textbox('setValue',_row.state);
                    $('#dormitory_add_buildingName').textbox('setValue',_row.buildingName);
                    $('#dormitory_add_buildingNumber').textbox('setValue',_row.buildingNumber);
                    $('#dormitory_add_capacity').textbox('setValue',_row.capacity);
                    if (_row.admin != null){
                        $("#dormitory_add_admin").combogrid('setValue',_row.admin.id);
                    }
                }
            }
        },
        btn_init:function () {
            $('#dormitory_add_cancel').on('click',function () {
                $("#dormitory_dialog").dialog('destroy');
            });
            $('#dormitory_add_ok').on('click',function () {
                var _url = '';
                if (_dormitory_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'dormitory/add.action';
                }else if (_dormitory_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'dormitory/update.action';
                }
                bs.formSubmit_datagrid(_url,'dormitory_add_form','dormitory_dialog','dormitory_index_dg');
            });
        }
    }

    var dormitory_add_app = (function () {
        $(function () {
            _dormitory_add_js.init();
            _dormitory_add_js.btn_init();
        });
    })();
</script>