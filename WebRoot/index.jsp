<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
		//alert(${login_status});
			if(${login_status==true}){
				//用户登陆成功，处在登陆状态
				$("#logtrue").show();
				$("#logfalse").hide();
			}
			else{
				//未处在登陆状态
				$("#logtrue").hide();
				$("#logfalse").show();
			}
			
		});
	</script>
  </head>
  
  <body>
  		<a href="jsp/user/user_detail.jsp"  id="logtrue">${user.user_Name}</a>
  		<div id="logfalse">
  			<a href="jsp/user/user_login.jsp" >登陆</a>
  			<a href="jsp/user/user_register.jsp" >注册</a>
  		</div>
  		<c:forEach items="${projects}" var="project">
  			<a href="jsp/project/pro_detail.jsp?pro_id=${project.id }">项目名：${project.pro_Title }</a><br>
  			项目描述：${project.pro_Des }<br>
  			项目状态：${project.pro_Status }<br>
  			开始时间：${project.pro_StartTime }<br>
  			结束时间：${project.pro_EndTime }<br>
  			<br>
  		</c:forEach>
  		募捐到的总钱数：${total_money}
  		募捐到的总物品：${total_goods }
    	
<!--     	adasdasdasd -->
    	
  </body>
</html>
