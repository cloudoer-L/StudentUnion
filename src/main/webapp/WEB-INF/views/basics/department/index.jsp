<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/29
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="department_index_div" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="">
        <table id="department_index_dg" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="department_index_toolbar">
    <a id="department_index_add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加部门</a>|
    <a id="department_index_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改部门</a>|
    <a id="department_index_delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除部门</a>
    <a id="department_index_import" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量部门</a>
    <a id="department_index_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">批量部门</a>
</div>