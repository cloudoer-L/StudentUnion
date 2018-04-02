<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/4/2
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="member_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <form id="member_add_form">
            <input type="hidden" id="member_add_id" name="member.id">
            <table class="table1" style="width: 100%">
                <tr>
                    <th>部员编号：</th>
                    <td>
                        <input id="member_add_number" class="easyui-textbox" name="member.number" data-options="required:false">
                    </td>
                </tr>
                <tr>
                    <th>关联学生：</th>
                    <td>
                        <input id="member_add_personNumber" name="personNumber" required="required"/>
                    </td>
                </tr>
                <tr>
                    <th>部门：</th>
                    <td>
                        <input id="member_add_departmentNumber" name="departmentNumber" required="required"/>
                    </td>
                </tr>
                <tr>
                    <th>职务：</th>
                    <td>
                        <select id="member_add_place" class="easyui-combobox" name="member.place" data-options="required:true,editable:false">
                            <option value="主席" selected>主席</option>
                            <option value="副主席">副主席</option>
                            <option value="团副">团副</option>
                            <option value="团副助理">团副助理</option>
                            <option value="部长">部长</option>
                            <option value="副部长">副部长</option>
                            <option value="干事">干事</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <input id="member_add_state" class="easyui-textbox" name="member.state" data-options="required:false">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
        <div style="position: absolute; right: 20px">
            <a href="javascript:;" id="member_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
            <a href="javascript:;" id="member_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var _member_add_js = {
        _qp:null,
        load_category:function () {
            var _columns = [[
                {field:'id',title:'ID',width:100,hidden:'true'},
                {field:'name',title:'学生姓名',width:150},
                {field:'number',title:'学生学号',width:150}
            ]];
            var _url = bs.base_url + 'student/getAll.action';
            bs.load_category('member_add_personNumber', _columns, _url, 'number', 'name');
            _columns = [[
                {field:'id',title:'ID',width:100,hidden:'true'},
                {field:'name',title:'部门名称',width:150},
                {field:'number',title:'部门编号',width:150}
            ]];
            _url = bs.base_url + 'department/getAll.action';
            bs.load_category('member_add_departmentNumber', _columns, _url, 'number', 'name');
        },
        init : function () {
            _member_add_js._qp  = $('#member_dialog').dialog('options').queryParams;
            if (_member_add_js._qp.flag == 'add'){

            }else if (_member_add_js._qp.flag == 'update'){
                if (_member_add_js._qp.row != null){
                    var _row = _member_add_js._qp.row
                    $('#member_add_id').val(_row.id);
                    $('#member_add_number').textbox('setValue',_row.number);
                    $('#member_add_place').combobox('select',_row.place);
                    $('#member_add_state').textbox('setValue',_row.state);
                    if (_row.person != null){
                        $("#member_add_personNumber").combogrid('setValue',_row.person.number);
                    }
                    if (_row.department != null){
                        $("#member_add_departmentNumber").combogrid('setValue',_row.department.number);
                    }
                }
            }
            // if (_member_add_js._qp.classesId){
            //     $("#member_add_classesId").combogrid('setValue',_member_add_js._qp.classesId);
            // }

        },
        btn_init:function () {
            $('#member_add_ok').on('click',function () {
                var _url = '';
                if (_member_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'member/add.action';
                }else if (_member_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'member/update.action';
                }
                bs.formSubmit_datagrid(_url,'member_add_form','member_dialog',_member_add_js._qp.dgId);
            });
            $('#member_add_cancel').on('click',function () {
                $("#member_dialog").dialog('destroy');
            });
        }
    }

    var member_add_app = (function () {
        $(function () {
            _member_add_js.load_category();
            _member_add_js.init();
            _member_add_js.btn_init();
        });
    })();
</script>