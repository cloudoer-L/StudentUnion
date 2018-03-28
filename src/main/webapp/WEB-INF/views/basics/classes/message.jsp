<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/26
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="classes_message_div">
    <fieldset class="layui-elem-field">
        <legend>班级信息</legend>
        <form id="classes_message_classesForm">
            <input type="hidden" id="classes_message_classes_id" name="classes.id">
            <table class="table1" style="width: 100%;">
                <tr>
                    <th>班级名称：</th>
                    <td>
                        <input id="classes_message_classes_name" class="easyui-textbox" name="classes.name" data-options="readonly:true">
                    </td>
                    <th>班级编号：</th>
                    <td>
                        <input id="classes_message_classes_number" class="easyui-textbox" name="classes.number" data-options="readonly:true">
                    </td>
                </tr>
                <tr>
                    <th>年级：</th>
                    <td>
                        <select id="classes_message_classes_grade" class="easyui-combobox" name="classes.grade" data-options="readonly:true,editable:false">
                            <option value="2015">2015级</option>
                            <option value="2016">2016级</option>
                            <option value="2017">2017级</option>
                        </select>
                    </td>
                    <th>状态：</th>
                    <td>
                        <input id="classes_message_classes_state" class="easyui-textbox" name="classes.state" data-options="readonly:true">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>班主任信息</legend>
        <form id="classes_message_teacherForm">
            <input type="hidden" id="classes_message_teacher_id" name="teacher.id">
            <table class="table1" style="width: 100%;">
                <tr>
                    <th>姓名：</th>
                    <td>
                        <input id="classes_message_teacher_name" class="easyui-textbox" name="teacher.name" data-options="readonly:true">
                    </td>
                    <th>性别：
                    </th>
                    <td>
                        <select id="classes_message_teacher_sex" class="easyui-combobox" name="teacher.sex" data-options="readonly:true,editable:false">
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>教师编号：</th>
                    <td>
                        <input id="classes_message_teacher_number" class="easyui-textbox" name="teacher.number" data-options="readonly:true">
                    </td>
                    <th>电话：</th>
                    <td>
                        <input id="classes_message_teacher_phone" class="easyui-textbox" name="teacher.phone" data-options="readonly:true">
                    </td>
                </tr>
                <tr>
                    <th>QQ号：</th>
                    <td>
                        <input id="classes_message_teacher_qq" class="easyui-textbox" name="teacher.qq" data-options="readonly:true">
                    </td>
                    <th>邮箱：</th>
                    <td>
                        <input id="classes_message_teacher_email" class="easyui-textbox" name="teacher.email" data-options="readonly:true">
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <input id="classes_message_teacher_state" class="easyui-textbox" name="teacher.state" data-options="readonly:true">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>

<script type="text/javascript">
    var _classes_message_js = {
        init : function () {
            var _row = _classes_info_js._qp.row;
            $('#classes_message_classes_id').val(_row.id);
            $('#classes_message_classes_name').textbox('setValue',_row.name);
            $('#classes_message_classes_number').textbox('setValue',_row.number);
            $('#classes_message_classes_state').textbox('setValue',_row.state);
            $("#classes_message_classes_grade").combobox('select',_row.grade);
            if (_row.teacher != null){
                $('#classes_message_teacher_id').val(_row.teacher.id);
                $('#classes_message_teacher_name').textbox('setValue',_row.teacher.name);
                $('#classes_message_teacher_number').textbox('setValue',_row.teacher.number);
                $('#classes_message_teacher_phone').textbox('setValue',_row.teacher.phone);
                $('#classes_message_teacher_qq').textbox('setValue',_row.teacher.qq);
                $('#classes_message_teacher_email').textbox('setValue',_row.teacher.email);
                $('#classes_message_teacher_state').textbox('setValue',_row.teacher.state);
                $("#classes_message_teacher_sex").combobox('select',_row.teacher.sex==null? '男':_row.teacher.sex);
            }
        }
    }

    var classes_message_app = (function () {
        $(function () {
            _classes_message_js.init();
        });
    })();
</script>