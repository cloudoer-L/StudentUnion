<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/6
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="menu_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="menu_index_treegrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="menu_index_toolbar">
    <a id="menu_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加一级菜单</a>|
    <a id="menu_index_addNext" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">添加下级菜单</a>|
    <a id="menu_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改菜单</a>|
    <a id="menu_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除菜单</a>
</div>


<script type="text/javascript">
    _menu_index_js = {
        treegrid_init : function () {
            var _columns = [[
                {field:'id',title:'ID',width:0,hidden:'true'},
                {field:'name',title:'中文名',width:200},
                {field:'number',title:'序号',width:100},
                {field:'value',title:'英文名',width:180},
                {field:'ico',title:'图标',width:0,hidden:'true'},
                {field:'url',title:'请求地址',width:180},
                {field:'state',title:'状态',width:100},
                {field:'introduce',title:'说明',width:400},
                {field:'grade',title:'级别',width:0,hidden:'true'}
            ]];
            var _url = bs.base_url+'menu/getAll.action';
            bs.treegrid_init(_url,'menu_index_toolbar',_columns);
        },
        btn_init:function () {
            $('#menu_index_add').on('click',function () {
                bs.show_dialog_min('menu_dialog',bs.base_url+'menu/addUI.action','添加菜单',{flag:'add'});
            });
            $('#menu_index_addNext').on('click',function () {
                var _row = $('#menu_index_treegrid').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'menu/addUI.action';
                    var _queryParams = {id:_row.id, flag:'addNext'};
                    bs.show_dialog_min('menu_dialog',_url,'添加菜单',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#menu_index_update').on('click',function () {
                var _row = $('#menu_index_treegrid').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url+'menu/addUI.action';
                    var _queryParams = {row:_row, flag:'update'};
                    bs.show_dialog_min('menu_dialog',_url,'修改菜单',_queryParams);
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
            $('#menu_index_delete').on('click',function () {
                var _row = $('#menu_index_treegrid').datagrid('getSelected');
                if (_row != null){
                    var _url = bs.base_url + 'menu/delete.action?id='+_row.id;
                    bs.base_ajax_treegrid(_url,'menu_index_treegrid','您确认想要删除这个菜单和它的所有下级菜单吗？');
                }else {
                    $.messager.alert('警告','请选择一条数据');
                }
            });
        }
    }

    menu_index_app = (function () {
        $(function () {
            _menu_index_js.treegrid_init();
            _menu_index_js.btn_init();
        });
    })();
</script>