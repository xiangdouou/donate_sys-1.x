<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript">
    	$(function(){
    		//如果登陆成功
    		if(${login_status==true}){
    			//将用户名显示在页面
    			$("#login").text('${user.user_Name}');
    			//将“注销”文字显示在页面
    			$("#logout").show();
    			//将用户名的链接设置为用户详情页面
    			$("#login").attr('href','jsp/user/user_detail.jsp');
    		}
    	});
    </script>
  </head>
  
  <body>
   		  <div class="navbar navbar-default navbar-fixed-top">

        <div class="navbar-header">
           　        	<a href="##" class="navbar-brand">爱心公益网 </a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="##">公益首页</a></li>
            <li><a href="project/page?page=0">公益活动</a></li>     
            <li><a href="jsp/user/user_detail.jsp" id="personal" style="display: none;">个人中心</a></li>
        </ul>

        <form action="##" class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="请输入关键词" />
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <div class="navbar-right text-danger">
        	欢迎，<span id="user_Name"></span><a href="jsp/user/user_login.jsp" id="login">请登录</a>&nbsp;&nbsp;<span><a href="user/logout" class="text-danger" id="logout" style="display: none;">注销</a></span>
        </div>
    </div>
  </body>
</html>
