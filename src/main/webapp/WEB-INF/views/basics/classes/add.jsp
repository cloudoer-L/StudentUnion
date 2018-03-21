<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/20
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="classes_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <form id="classes_add_form">
            <input type="hidden" id="classes_add_id" name="classes.id">
            <table class="table1" style="width: 100%">
                <tr>
                    <th>班级名称：</th>
                    <td>
                        <input id="classes_add_name" class="easyui-textbox" name="classes.name" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>班级编号：</th>
                    <td>
                        <input id="classes_add_number" class="easyui-textbox" name="classes.number" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>年级：</th>
                    <td>
                        <select id="classes_add_grade" class="easyui-combobox" name="classes.grade" data-options="required:true,editable:false">
                            <option value="2015">2015级</option>
                            <option value="2016">2016级</option>
                            <option value="2017">2017级</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>班主任：</th>
                    <td>
                        <input id="classes_add_teacher" name="teacherId" />
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <input id="classes_add_state" class="easyui-textbox" name="classes.state" data-options="required:false">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
        <div style="position: absolute; right: 20px">
            <a href="javascript:;" id="classes_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
            <a href="javascript:;" id="classes_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var _classes_add_js = {
        _qp:null,
        load_category:function () {
            var _columns = [[
                {field:'id',title:'ID',width:100,hidden:'true'},
                {field:'name',title:'教师姓名',width:150},
                {field:'number',title:'教师编号',width:150}
            ]];
            var _url = bs.base_url + 'teacher/getAll.action';
            $('#classes_add_teacher').combogrid({
                url: _url,
                panelWidth:300,
                columns: _columns,
                idField:'id',
                textField:'name',
                multiple:false,
                separator:',',
                editable:false,
                loadMsg:'加载中',
                required:true
            });
        },
        init:function () {
            _classes_add_js._qp = $('#classes_dialog').dialog('options').queryParams;
            if (_classes_add_js._qp.flag == 'add'){

            }else if (_classes_add_js._qp.flag == 'update'){
                if (_classes_add_js._qp.row != null){
                    var _row = _classes_add_js._qp.row
                    $('#classes_add_id').val(_row.id);
                    $('#classes_add_name').textbox('setValue',_row.name);
                    $('#classes_add_number').textbox('setValue',_row.number);
                    $('#teacher_add_state').textbox('setValue',_row.state);
                    $("#classes_add_grade").combobox('select',_row.grade);
                    if (_row.teacher != null){
                        $("#classes_add_teacher").combogrid('setValue',_row.teacher.id);
                    }
                }
            }
        },
        btn_init:function () {
            $('#classes_add_cancel').on('click',function () {
                $("#classes_dialog").dialog('destroy');
            });
            $('#classes_add_ok').on('click',function () {
                var _url = '';
                if (_classes_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'classes/add.action';
                }else if (_classes_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'classes/update.action';
                }
                bs.formSubmit_datagrid(_url,'classes_add_form','classes_dialog','classes_index_dg');
            });
        }
    }
    
    var classes_add_app = (function () {
        $(function () {
            _classes_add_js.load_category();
            _classes_add_js.init();
            _classes_add_js.btn_init();
        });
    })();
</script> 