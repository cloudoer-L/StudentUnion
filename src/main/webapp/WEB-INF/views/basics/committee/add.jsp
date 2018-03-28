<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/26
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="committee_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <form id="committee_add_form">
            <input type="hidden" id="committee_add_id" name="committee.id">
            <table class="table1" style="width: 100%">
                <tr>
                    <th>班级：</th>
                    <td>
                        <input id="committee_add_classes" name="classesNumber" />
                    </td>
                </tr>
                <tr>
                    <th>学生：</th>
                    <td>
                        <input id="committee_add_student" name="studentNumber" />
                    </td>
                </tr>
                <tr>
                    <th>职务：</th>
                    <td>
                        <input id="committee_add_position" name="positionNumber" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
        <div style="position: absolute; right: 20px">
            <a href="javascript:;" id="committee_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
            <a href="javascript:;" id="committee_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var _committee_add_js = {
        _qp:null,
        load_category:function () {
            var _columns = [[
                {field:'name',title:'班级名称',width:150},
                {field:'number',title:'班级编号',width:150}
            ]];
            var _url = bs.base_url + 'classes/getAll.action';
            bs.load_category('committee_add_classes', _columns, _url, 'number', 'name');
            _columns = [[
                {field:'name',title:'姓名',width:150},
                {field:'number',title:'学号',width:150}
            ]];
            _url = bs.base_url + 'student/getAll.action';
            bs.load_category('committee_add_student', _columns, _url, 'number', 'name');
            _columns = [[
                {field:'name',title:'职务名称',width:150},
                {field:'number',title:'职务编号',width:150}
            ]];
            _url = bs.base_url + 'position/getAll.action';
            bs.load_category('committee_add_position', _columns, _url, 'number', 'name');
        },
        init:function () {
            _committee_add_js._qp = $('#committee_dialog').dialog('options').queryParams;
            if (_committee_add_js._qp.flag == 'add'){

            }else if (_committee_add_js._qp.flag == 'update'){
                if (_committee_add_js._qp.row != null){
                    var _row = _committee_add_js._qp.row
                    $('#committee_add_id').val(_row.id);
                    $("#committee_add_classes").combogrid('setValue',_row.student.classes.number);
                    $("#committee_add_student").combogrid('setValue',_row.student.number);
                    $("#committee_add_position").combogrid('setValue',_row.positionNumber);
                }
            }
            if (_committee_add_js._qp.classesNumber){
                $("#committee_add_classes").combogrid('setValue',_committee_add_js._qp.classesNumber);
            }
        },
        btn_init:function () {
            $('#committee_add_cancel').on('click',function () {
                $("#committee_dialog").dialog('destroy');
            });
            $('#committee_add_ok').on('click',function () {
                var _url = '';
                if (_committee_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'committee/add.action';
                }else if (_committee_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'committee/update.action';
                }
                bs.formSubmit_datagrid(_url,'committee_add_form','committee_dialog',_committee_add_js._qp.dgId);
            });
        }
    }

    var committee_add_app = (function () {
        $(function () {
            _committee_add_js.load_category();
            _committee_add_js.init();
            _committee_add_js.btn_init();
        });
    })();
</script>