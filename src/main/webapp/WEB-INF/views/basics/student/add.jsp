<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/24
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="student_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <form id="student_add_form">
            <input type="hidden" id="student_add_id" name="student.id">
            <table class="table1" style="width: 100%">
                <tr>
                    <th>姓名：</th>
                    <td>
                        <input id="student_add_name" class="easyui-textbox" name="student.name" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>身份证：</th>
                    <td>
                        <input id="student_add_idCard" class="easyui-textbox" name="student.idCard" data-options="required:false">
                    </td>
                </tr>
                <tr>
                    <th>性别：
                    </th>
                    <td>
                        <select id="student_add_sex" class="easyui-combobox" name="student.sex" data-options="required:true,editable:false">
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>学号：</th>
                    <td>
                        <input id="student_add_number" class="easyui-textbox" name="student.number" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>电话：</th>
                    <td>
                        <input id="student_add_phone" class="easyui-textbox" name="student.phone" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>QQ号：</th>
                    <td>
                        <input id="student_add_qq" class="easyui-textbox" name="student.qq" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>邮箱：</th>
                    <td>
                        <input id="student_add_email" class="easyui-textbox" name="student.email" data-options="required:false">
                    </td>
                </tr>
                <tr>
                    <th>班级：</th>
                    <td>
                        <input id="student_add_classesId" name="classesId" required="required"/>
                    </td>
                </tr>
                <tr>
                    <th>寝室：</th>
                    <td>
                        <input id="student_add_dormitoryId" name="dormitoryId" />
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <input id="student_add_state" class="easyui-textbox" name="student.state" data-options="required:false">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
        <div style="position: absolute; right: 20px">
            <a href="javascript:;" id="student_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
            <a href="javascript:;" id="student_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var _student_add_js = {
        _qp:null,
        load_category:function () {
            var _columns = [[
                {field:'id',title:'ID',width:100,hidden:'true'},
                {field:'name',title:'班级名称',width:150},
                {field:'number',title:'班级编号',width:150}
            ]];
            var _url = bs.base_url + 'classes/getAll.action';
            bs.load_category('student_add_classesId', _columns, _url, 'id', 'name');
            _columns = [[
                {field:'id',title:'ID',width:100,hidden:'true'},
                {field:'name',title:'寝室名称',width:150},
                {field:'number',title:'寝室编号',width:150}
            ]];
            _url = bs.base_url + 'dormitory/getAll.action';
            bs.load_category('student_add_dormitoryId', _columns, _url, 'id', 'name');
        },
        init : function () {
            _student_add_js._qp  = $('#student_dialog').dialog('options').queryParams;
            if (_student_add_js._qp.flag == 'add'){

            }else if (_student_add_js._qp.flag == 'update'){
                if (_student_add_js._qp.row != null){
                    var _row = _student_add_js._qp.row
                    $('#student_add_id').val(_row.id);
                    $('#student_add_name').textbox('setValue',_row.name);
                    $('#student_add_idCard').textbox('setValue',_row.idCard);
                    $('#student_add_number').textbox('setValue',_row.number);
                    $('#student_add_phone').textbox('setValue',_row.phone);
                    $('#student_add_qq').textbox('setValue',_row.qq);
                    $('#student_add_email').textbox('setValue',_row.email);
                    $('#student_add_state').textbox('setValue',_row.state);
                    $("#student_add_sex").combobox('select',_row.sex==null? '男':_row.sex);
                    if (_row.classes != null){
                        $("#student_add_classesId").combogrid('setValue',_row.classes.id);
                    }
                    if (_row.dormitory != null){
                        $("#student_add_dormitoryId").combogrid('setValue',_row.dormitory.id);
                    }
                }
            }
            if (_student_add_js._qp.classesId){
                $("#student_add_classesId").combogrid('setValue',_student_add_js._qp.classesId);
            }

        },
        btn_init:function () {
            $('#student_add_ok').on('click',function () {
                var _url = '';
                if (_student_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'student/add.action';
                }else if (_student_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'student/update.action';
                }
                bs.formSubmit_datagrid(_url,'student_add_form','student_dialog',_student_add_js._qp.dgId);
            });
            $('#student_add_cancel').on('click',function () {
                $("#student_dialog").dialog('destroy');
            });
        }
    }

    var student_add_app = (function () {
        $(function () {
            _student_add_js.load_category();
            _student_add_js.init();
            _student_add_js.btn_init();
        });
    })();
</script>