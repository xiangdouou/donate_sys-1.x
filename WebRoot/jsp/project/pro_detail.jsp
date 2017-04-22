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
    
    <title>My JSP 'pro_detail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	 	<c:forEach items="${projects}" var="project">
  	 		<c:if test="${param.pro_id==project.id}">
  	 				项目名：${project.pro_Title} <br>
   					项目描述：${project.pro_Des }<br>
		  			项目状态：${project.pro_Status }<br>
		  			开始时间：${project.pro_StartTime }<br>
		  			结束时间：${project.pro_EndTime }<br>
   			</c:if>
  	 	</c:forEach>
   		
  </body>
</html>
