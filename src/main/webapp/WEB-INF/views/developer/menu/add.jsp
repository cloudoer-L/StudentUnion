<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/8
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="menu_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <form id="menu_add_form">
            <input type="hidden" id="menu_add_id" name="id">
            <table class="table1" style="width: 100%">
                <tr>
                    <th>菜单名：</th>
                    <td>
                        <input id="menu_add_name" class="easyui-textbox" name="menu.name" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>英文名：</th>
                    <td>
                        <input id="menu_add_value" class="easyui-textbox" name="menu.value" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>菜单编号：</th>
                    <td>
                        <input id="menu_add_number" class="easyui-textbox" name="menu.number" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <th>菜单地址：</th>
                    <td>
                        <input id="menu_add_url" class="easyui-textbox" name="menu.url" data-options="required:false">
                    </td>
                </tr>
                <tr>
                    <th>菜单图标：</th>
                    <td>
                        <input id="menu_add_ico" class="easyui-textbox" name="menu.ico" data-options="required:false">
                    </td>
                </tr>
                <tr>
                    <th>菜单说明：</th>
                    <td>
                        <input id="menu_add_introduce" class="easyui-textbox" name="menu.introduce" data-options="required:false">
                    </td>
                </tr>
                <tr>
                    <th>菜单状态：</th>
                    <td>
                        <input id="menu_add_state" class="easyui-textbox" name="menu.state" data-options="required:false">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south'" style="height: 50px;padding-top: 10px;">
        <div style="position: absolute; right: 20px">
            <a href="javascript:;" id="menu_add_ok" class="easyui-linkbutton" data-options="iconCls:'fa fa-check'" style="margin-right: 10px">确 定</a>
            <a href="javascript:;" id="menu_add_cancel" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'">关 闭</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var _menu_add_js = {
        _qp:null,
        data_init:function () {
            _menu_add_js._qp  = $('#menu_dialog').dialog('options').queryParams;
            if (_menu_add_js._qp.flag == 'add'){

            }else if (_menu_add_js._qp.flag == 'addNext'){
                $('#menu_add_id').val(_menu_add_js._qp.id);
            }else if (_menu_add_js._qp.flag == 'update'){
                if (_menu_add_js._qp.row != null){
                    var _row = _menu_add_js._qp.row
                    $('#menu_add_id').val(_row.id);
                    $('#menu_add_name').textbox('setValue',_row.name);
                    $('#menu_add_value').textbox('setValue',_row.value);
                    $('#menu_add_number').textbox('setValue',_row.number);
                    $('#menu_add_url').textbox('setValue',_row.url);
                    $('#menu_add_ico').textbox('setValue',_row.ico);
                    $('#menu_add_introduce').textbox('setValue',_row.introduce);
                    $('#menu_add_state').textbox('setValue',_row.state);
                }
            }
        },
        btn_init:function () {
            $('#menu_add_ok').on('click',function () {
                var Iurl = '';
                if (_menu_add_js._qp.flag == 'add'){
                    _url = bs.base_url + 'menu/add.action';
                }else if (_menu_add_js._qp.flag == 'addNext'){
                    _url = bs.base_url + 'menu/add.action';
                }else if (_menu_add_js._qp.flag == 'update'){
                    _url = bs.base_url + 'menu/update.action';
                }
                bs.formSubmit_treegrid(_url,'menu_add_form','menu_dialog','menu_index_treegrid');
            });
            $('#menu_add_cancel').on('click',function () {
                $("#menu_dialog").dialog('destroy');
            });
        }
    }

    var menu_add_app = (function () {
        $(function () {
            _menu_add_js.data_init();
            _menu_add_js.btn_init();
        });
    })();
</script>