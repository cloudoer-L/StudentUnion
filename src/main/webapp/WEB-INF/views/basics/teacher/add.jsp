<%--
  Created by IntelliJ IDEA.
  User: 奥巴马
  Date: 2018/3/12
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="teacher_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
    <form id="teacher_add_form">
        <input type="hidden" id="teacher_add_id" name="teacher.id">
        <table class="table1" style="width: 100%">
            <tr>
                <th>姓名：</th>
                <td>
                    <input id="teacher_add_name" class="easyui-textbox" name="teacher.name" data-options="required:true">
                </td>
            </tr>
            <tr>
                <th>身份证：</th>
                <td>
                    <input id="teacher_add_idCard" class="easyui-textbox" name="teacher.idCard" data-options="required:false">
                </td>
            </tr>
            <tr>
                <th>性别：
                </th>
                <td>
                    <select id="teacher_add_sex" class="easyui-combobox" name="teacher.sex" data-options="required:true,editable:false">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>教师编号：</th>
                <td>
                    <input id="teacher_add_number" class="easyui-textbox" name="teacher.number" data-options="required:true">
                </td>
            </tr>
            <tr>
                <th>电话：</th>
                <td>
                    <input id="teacher_add_phone" class="easyui-textbox" name="teacher.phone" data-options="required:true">
                </td>
            </tr>
            <tr>
                <th>QQ号：</th>
                <td>
                    <input id="teacher_add_qq" class="easyui-textbox" name="teacher.qq" data-options="required:true">
                </td>
            </tr>
            <tr>
                <th>邮箱：</th>
                <td>
                    <input id="teacher_add_email" class="easyui-textbox" name="teacher.email" data-options="required:false">
                </td>
            </tr>
            <tr>
                <th>状态：</th>
                <td>
                    <input id="teacher_add_state" class="easyui-textbox" name="teacher.state" data-options="required:false">
                </td>
            </tr>
        </table>
    </form>
</div>
<div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
    <div style="position: absolute; right: 20px">
        <a href="javascript:;" id="teacher_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
        <a href="javascript:;" id="teacher_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
    </div>
</div>
</div>

<script type="text/javascript">
    var _tercher_add_js = {
        _qp:null,
        init : function () {
            _tercher_add_js._qp  = $('#teacher_dialog').dialog('options').queryParams;
            if (_tercher_add_js._qp.flag == 'add'){

            }else if (_tercher_add_js._qp.flag == 'update'){
                if (_tercher_add_js._qp.row != null){
                    var _row = _tercher_add_js._qp.row
                    $('#teacher_add_id').val(_row.id);
                    $('#teacher_add_name').textbox('setValue',_row.name);
                    $('#teacher_add_idCard').textbox('setValue',_row.idCard);
                    $('#teacher_add_number').textbox('setValue',_row.number);
                    $('#teacher_add_phone').textbox('setValue',_row.phone);
                    $('#teacher_add_qq').textbox('setValue',_row.qq);
                    $('#teacher_add_email').textbox('setValue',_row.email);
                    $('#teacher_add_state').textbox('setValue',_row.state);
                    $("#teacher_add_sex").combobox('select',_row.sex==null? '男':_row.sex);
                }
            }
        },
        btn_init:function () {
            $('#teacher_add_ok').on('click',function () {
                var _url = '';
                if (_tercher_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'teacher/add.action';
                }else if (_tercher_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'teacher/update.action';
                }
                bs.formSubmit_datagrid(_url,'teacher_add_form','teacher_dialog','teacher_index_dg');
            });
            $('#teacher_add_cancel').on('click',function () {
                $("#teacher_dialog").dialog('destroy');
            });
        }
    }

    var teacher_add_app = (function () {
        $(function () {
            _tercher_add_js.init();
            _tercher_add_js.btn_init();
        });
    })();
</script>