<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/myrecord.css">
 	 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/alterperson.css">		
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
    <script src="<%=basePath%>js/changecolor.js" ></script>

  </head>
   <style type="text/css">

    </style>
<body onload="load()">
	 <c:import url="../top.jsp"/>
<div class="container">
    <div class="jumbotron">
        <img src="<%=basePath%>img/personaltop.jpg">
    </div>
    <div class="row">
	    <div class="col-xs-11 col-xs-offset-1">
	    	<div class="col-xs-3"  >
	            <ul class="nav nav-tabs nav-stacked text-center" id="sidebarMenu">
	                <li id="li1" class="active"><a href="<%=basePath%>person/personal.jsp?list=li1">我献出的爱心</a></li>
	                <li id="li2"><a href="<%=basePath%>person/mygoods.jsp?list=li2">我捐出的物资</a></li>
	                <li id="li3"><a href="<%=basePath%>person/myrecord.jsp?list=li3">我的捐款记录</a></li>
	                <li id="li4"><a>修改个人信息</a></li>
	            </ul>
	        </div>
	        <div class="col-xs-9">
	        	<div class="row">
		        	<div class="content col-xs-10 col-xs-offset-1">
						<div class="title">
							 <span class="listtitle h2">
							 	基本资料					
							 </span>	
						</div>
					</div>
	        	</div>
	        	<div class="row col-xs-10 col-xs-offset-1">        		 	
						<div class="panel ">
						  <div class="panel-body">
						    <form action="#">
								用户名:<input type="text" class="form-control"><br>
								密码:<input type="password" class="form-control"><br>
								手机号:<input type="text" class="form-control"><br>
					
								性别:<select class="form-control">
								        <option>男</option>
								        <option>女</option>
							      	</select><br>
							            邮箱:<input type="text" class="form-control"><br>
								地址:<input type="text" class="form-control"><br>
								<button class="btn btn-success pull-right" >修改</button>

							</form>
						  </div>	
					</div>
	        	
	        	</div>	        	

			</div>
	    </div>
        
    </div>
</div>
<footer>
    <div class="layout partner">
		<div class="hd"><h2>联系我们</h2></div>
		<div class="bd"></div>
		<div class="text-center">@版权归</div>
	</div>
</footer> 
</body>

</html>
