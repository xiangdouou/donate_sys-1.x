<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员头部</title>   
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">	
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
	<script type="text/javascript">
		$(function(){
    		//如果用户不为空
    		if(${user!=null}){
    			//将用户名显示在页面
    			$("#login").text('${user.user_Name}');
    			 			
    			//将“注销”文字显示在页面
    			$("#logout").show();
    			
    			//将用户名的链接设置为用户详情页面
    			$("#login").attr('href','admin/projectlist?pro_status=all&page=1');
    		}
    	});
	</script>
  </head>
  
  <body>
     <div class="navbar navbar-default navbar-fixed-top">
        <div class="navbar-header">
           　        		<a href="##" class="navbar-brand">爱心捐助平台 </a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<%=basePath%>admin/projectlist?pro_status=all&page=1">活动列表</a></li>
            <li><a href="<%=basePath%>admin/projectoperate?type=add">添加活动</a></li> 
            <li><a href="<%=basePath%>admin/recordlist?page=1">捐赠记录</a></li>    
        </ul>
   
         <div class="navbar-right text-danger">
        	欢迎，<span id="user_Name"></span><a href="<%=basePath%>jsp/user/user_login.jsp" id="login">请登录</a>&nbsp;&nbsp;<span><a href="user/logout" class="text-danger" id="logout" style="display: none;">注销</a></span>
        </div>
    </div>
  </body>
</html>
