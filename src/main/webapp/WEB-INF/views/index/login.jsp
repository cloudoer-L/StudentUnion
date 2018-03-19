<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>login</title>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/jslibs/css/login.css" />

  </head>
  
  <body>
    <div class="container">
		<section id="content">
			<form action="../user/login.action" method="post">
				<h1>用户登录</h1>
				<div>
					<input type="text" placeholder="账号" required id="username"
						name="user.userName" />
				</div>
				<div>
					<input type="password" placeholder="密码" required id="password"
						name="user.password" onkeydown="if(event.keyCode==32) return false" />
				</div>
				<div>
					<input type="submit" value="登录" /> 
					<a href="#">忘记密码?</a>
					
				</div>
			</form>
		</section>
	</div>
  </body>
</html>
