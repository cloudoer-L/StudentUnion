<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/22
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="position_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <form id="position_add_form">
            <input type="hidden" id="position_add_id" name="position.id">
            <table class="table1" style="width: 100%">
                <tr>
                    <th>职务名称：</th>
                    <td>
                        <input id="position_add_name" class="easyui-textbox" name="position.name" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>职务编号：</th>
                    <td>
                        <input id="position_add_number" class="easyui-textbox" name="position.number" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>职务说明：</th>
                    <td>
                        <input id="position_add_introduce" class="easyui-textbox" name="position.introduce" data-options="required:false">
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <input id="position_add_state" class="easyui-textbox" name="position.state" data-options="required:false">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
        <div style="position: absolute; right: 20px">
            <a href="javascript:;" id="position_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
            <a href="javascript:;" id="position_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var _position_add_js = {
        _qp:null,
        init : function () {
            _position_add_js._qp  = $('#position_dialog').dialog('options').queryParams;
            if (_position_add_js._qp.flag == 'add'){

            }else if (_position_add_js._qp.flag == 'update'){
                if (_position_add_js._qp.row != null){
                    var _row = _position_add_js._qp.row
                    $('#position_add_id').val(_row.id);
                    $('#position_add_name').textbox('setValue',_row.name);
                    $('#position_add_number').textbox('setValue',_row.number);
                    $('#position_add_introduce').textbox('setValue',_row.phone);
                    $('#position_add_state').textbox('setValue',_row.state);
                }
            }
        },
        btn_init:function () {
            $('#position_add_ok').on('click',function () {
                var _url = '';
                if (_position_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'position/add.action';
                }else if (_position_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'position/update.action';
                }
                bs.formSubmit_datagrid(_url,'position_add_form','position_dialog','position_index_dg');
            });
            $('#position_add_cancel').on('click',function () {
                $("#position_dialog").dialog('destroy');
            });
        }
    }

    var position_add_app = (function () {
        $(function () {
            _position_add_js.init();
            _position_add_js.btn_init();
        })
    })();
</script>