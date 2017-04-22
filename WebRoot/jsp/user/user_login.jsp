<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_login.jsp' starting page</title>
    

	<script src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
		alert(${requestScope.flush_msg});
			alert_hide();
			function alert_hide(){
				$("#alert").hide();
			}
			if(${param.login_msg=="false"} && ${requestScope.flush_msg!="true"}){
				$("#alert").show();
			}	
			$("input").click(alert_hide);		
		});
	</script>

  </head>
  
  <body>
  	  <c:set var="flush_msg" value="true" scope="request"></c:set>
	  <div id="alert" style="display: none;">
	  	用户名或密码错误！！！
	  </div>
    <form action="user/login" method="post">
    	<input name="user_name" value="${user.user_Name}"/>
    	<input name="user_pass" value="${user.user_Pass}"/>
    	<button type="submit">提交</button>
    </form>
  </body>
</html>
