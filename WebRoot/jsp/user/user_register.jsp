<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
		//alert(${params.reg_status});
		//注册失败显示提示信息
			if(${reg_status==false})
			$("#alert").show();	
		});
	</script>
  </head>
  
  <body>
  		<div id="alert" style="display: none;"> 注册的用户名已存在！！</div>
    	<form action="user/register" method="post">
    		user_name:<input name="user_name"/><br>
    		user_pass:<input name="user_pass"/><br>
    		user_age:<input name="user_age"/><br>
    		user_phone:<input name="user_phone"/><br>
    		user_email:<input name="user_email"/><br>
    		user_address:<input name="user_address"/><br>
    		<button type="submit">注册</button>
    	</form>
  </body>
</html>
