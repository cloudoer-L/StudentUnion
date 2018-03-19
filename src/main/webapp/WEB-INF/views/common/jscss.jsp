<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/2/17
  Time: 15:51
  To change this template use File | Settings | File Templates.
  引入所有的资源文件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 引入css文件，不限顺序 -->
<link rel="stylesheet" href="<%=basePath%>jslibs/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>jslibs/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/jslibs/layui/css/layui.css"></link>
<link rel="stylesheet" href="<%=basePath%>css/dialog_min.css" type="text/css"></link>

<!-- 引入js文件，有顺序限制 -->
<script type="text/javascript" src="<%=basePath%>jslibs/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jslibs/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jslibs/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>jslibs/layui/layui.js"></script>

<script type="text/javascript" src="<%=basePath%>js/base.js"></script>