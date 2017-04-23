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
    
    <title>活动详情</title>
    
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
  	 				项目名：${project.pro_Title}<br>
  	 				项目类型：${project.pro_Type==1?"捐钱":"物品" }<br>
   					项目描述：${project.pro_Des }<br>
   					主办单位：${project.pro_Sponsor }<br>
		  			项目状态：${project.pro_Status }<br>
		  			开始时间：${project.pro_StartTime }<br>
		  			结束时间：${project.pro_EndTime }<br>
		  			目标数：${project.pro_TargetNumber }<br>
		  			已募捐数：${project.pro_CurNumber }<br>
		  			参与总人次：${project.pro_CurPeoples }<br>
   			</c:if>
  	 	</c:forEach>
   		
  </body>
</html>
