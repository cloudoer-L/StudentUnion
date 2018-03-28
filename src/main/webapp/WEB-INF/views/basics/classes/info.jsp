<%--
  Created by IntelliJ IDEA.
  User: lixiaodong
  Date: 2018/3/26
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="classes_info_dialog" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',fit:true">
        <div id="classes_info_tabs" class="easyui-tabs" data-options="fit:true"></div>
    </div>
</div>

<script type="text/javascript">
    var _classes_info_js = {
        _qp:null,
        init:function () {
            _classes_info_js._qp = $('#classes_info_dialog').dialog('options').queryParams;
            $('#classes_info_tabs').tabs('add', {
                title: '班级信息',
                href: bs.base_url+'classes/messageUI.action',
                closable: false,
                loadingMessage: '正在加载中......'
            });
            $('#classes_info_tabs').tabs('add', {
                title: '学生信息',
                href: bs.base_url+'classes/classes_studentsUI.action',
                closable: false,
                loadingMessage: '正在加载中......'
            });
            $('#classes_info_tabs').tabs('add', {
                title: '班委信息',
                href: bs.base_url+'classes/classes_committeeUI.action',
                closable: false,
                loadingMessage: '正在加载中......'
            });
        }
    }
    
    var classes_info_app = (function () {
        $(function () {
            _classes_info_js.init();
            $('#classes_info_tabs').tabs('select', 0);
        });
    })();
</script> 