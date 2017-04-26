<%@ page language="java" import="java.util.*" pageEncoding="UTf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>项目列表</title>
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/addproject.css">		
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
    <script src="<%=basePath%>js/project.js" ></script>
    <script type="text/javascript">
$(function(){
		//点击input标签提示信息消失
		$("input").click(function(){
			$("#alert").hide();
		});
		if(${projectUpdate==false}){
			$(".alert").show();
		}
	var t; 
	$(".news").hover(function(){ 
		 clearInterval(t);
	},function(){ 
		 t = setInterval(function(){ 
				var ul = $(".list"); 
				var liHeight = ul.find("li:last").height();
				ul.animate({marginTop : liHeight +"px"},1000,function(){ 
					ul.find("li:last").prependTo(ul);
					ul.find("li:first").hide(); 
					ul.css({marginTop:0}); 
					ul.find("li:first").fadeIn(800); 
				});         
		},3000); 
	 }).trigger("mouseleave"); 
});
</script>
    
  </head>
  <style type="text/css">
</style>

    
  <body>
	<jsp:include page="admin_top.jsp"></jsp:include>
    <div class="container">
	    <div class="row col-xs-10 col-xs-offset-1">
	    	<div class="row">
	    		<ol class="breadcrumb" style="background: none;">
				    <li><a href="<%=basePath%>admin/allproject.jsp" class="text-a">首页</a></li>
				    <li>修改活动</li>
				</ol>
	    	</div>
	    	<div class="row rowtop" style="margin-top: -18px;">	
		    	<div class="news">
					<ul class="list">
						<li>腾讯公益大数据揭秘：看看哪里爱心最爆棚？</li>
						<li>垃圾分类从鼓励到强制，你准备好了吗？，你准备好了吗？你准备好了吗？重要的事情说三遍</li>
						<li>爱无国度，救助来自第三世界的人们，他们需要你的帮助！</li>
						<li>世界地球日 讲好我们的地球故事</li>
						<li>99公益日 一起爱！</li>
					</ul>
				</div>
	    	 		   
	    	</div>
	    	<div class="row">			
					<div class="col-xs-10 col-xs-offset-1">	
					<br>				 
				        <div class="col-xs-5">
					        <!-- 上传图片 -->
					        <form method="post" id="uploadForm" enctype="multipart/form-data">				        	
					       		<input type="file"  id="pic" name="pic" />   
					       		<p class="pull-right">
					        		<button class="btn btn-success"  onclick="doUpload();">添加图片</button>
					            </p>
					        </form>
					       	<div class="imgdiv">
					       		<img id="showpic" src="${PIC}" >
					       	</div>
				       		<br>	
				        </div>
				        <div class="col-xs-7">
				        <div class="alert alert-danger" style="display: none;">
				    		活动标题已存在！！！
				    	</div>
				        	<form action="admin/projectupdate" method="post">	
				        		<input type="hidden" name="id" value="${updateproject.id}"/>				
					        	<span>活动标题</span>
					        	<input class="form-control" name="pro_Title" value="${updateproject.pro_Title }" type="text">
					     	    <span>项目简介</span>
					        	<textarea rows="3" cols="30" name="pro_Des" > ${updateproject.pro_Des }</textarea><br>
					        	<span>筹款目标</span>
								<input class="form-control" type="text" name="pro_TatgetNumber" value="${updateproject.pro_TargetNumber }">
								<span>筹款起止时间 </span>
								<p>
									<input class="form-control left" type="date" name="pro_StartTime" value="${updateproject.pro_StartTime }">
									<span>至</span>
									<input class="form-control right" type="date" name="pro_EndTime" value="${updateproject.pro_EndTime }">
								</p>
								<span>执 行 方 </span>
								<input class="form-control" type="text" name="pro_Sponsor" value="${updateproject.pro_Sponsor }">
								<span>项目状态</span>
								<select class="form-control" name="pro_Status">
								<option selected="selected">募捐中</option>
								<option>执行中</option>
								<option>已结束</option>
								</select><br>
								<button class="btn btn-success pull-right">修改活动</button>															
				      		</form>	  
				        </div>				        
    				</div>
    		
			</div>	
		   
	    </div>
	</div>
 	<footer>
  		<div class="layout partner">
			<div class="hd"><h2>联系我们</h2></div>
			<div class="bd"></div>
			<div class="text-center">@版权归XXX所有</div>
	    </div>
    </footer>
  

  </body>
</html>
