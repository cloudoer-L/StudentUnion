<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/2/16
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>student_union</title>
    <jsp:include page="../common/jscss.jsp" flush="true"></jsp:include>
</head>
<body>
<div id="cc" class="easyui-layout" data-options="fit:true" style="">
    <div data-options="region:'north'" style="height: 80px;"></div>
    <div data-options="region:'south'" style="height: 50px;"></div>
    <div data-options="region:'west',href:'westUI.action'" style="width: 200px;"></div>
    <div data-options="region:'center'" style="">
        <div id="index_tabs" class="easyui-tabs" data-options="fit:true, border:false"></div>
    </div>

</div>
</body>
</html>
