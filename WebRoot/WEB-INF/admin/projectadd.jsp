<%@ page language="java" import="java.util.*" pageEncoding="UTf-8"%>
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
    <script src="<%=basePath%>js/project.js"></script>
    <script type="text/javascript">
     function doUpload() {  //Ajax异步上传图片  
	     var formData = new FormData($("#uploadForm")[0]);    
	     $.ajax({    
	          url:'<%=basePath%>UploadImage', 
	          type: 'POST',    
	          data:  new FormData($('#uploadForm')[0]),   
	          async: false,    
	          cache: false,    
	          contentType: false,    
	          processData: false,    
	          success: function (returndata) {    
	              document.getElementById("showpic").src="${PIC}";/*这是预览图片用的，自己在文件上传表单外添加*/  
	          },    
	          error: function (returndata) {    
	              alert(returndata);    
	          }    
     });    
}
   
 function end(){
     var endtime = $('#endTime').val();
     var starttime = $('#startTime').val();
     var start = new Date(starttime.replace("-", "/").replace("-", "/"));
     var end = new Date(endtime.replace("-", "/").replace("-", "/"));
     if (end <=start) {
         alert('结束日期不能小于开始日期！');
         $('#endTime').val(null);
         return false;
     }
     else {
         return true;
     }};
    
	$(function(){
		//点击input标签提示信息消失
		$("input").click(function(){
			$("#alert").hide();
		});
		if(${projectAdd==false}){
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
  <body>
	<jsp:include page="admin_top.jsp"></jsp:include>
    <div class="container">
	    <div class="row col-xs-10 col-xs-offset-1">
	    	<div class="row">
	    		<ol class="breadcrumb" style="background: none;">
				    <li><a href="#" class="text-a">首页</a></li>
				    <li><a href="#" class="text-a">添加活动</a></li>
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
				        	<form action="admin/projectadd" method="post">				
					        	<span>项目标题</span>
					        	<input class="form-control" name="pro_Title" type="text" value="${addproject.pro_Title}" placeholder="请输入项目标题" required="required">
					        	<span>募捐种类</span>
								<select class="form-control" name="pro_Type">
								<option value="1" selected="selected">善款</option>
								<option value="2">物品</option>
								</select>
					     	    <span>项目简介</span>
					        	<textarea rows="3" cols="3" name="pro_Des" required="required">${addproject.pro_Des}</textarea><br>
					        	<span>募捐目标</span>
								<input class="form-control" name="pro_TargetNumber" type="number" value="${addproject.pro_TargetNumber}" placeholder="请输入活动募捐目标" required="required">
								<span>项目起止时间 </span>
								<p>
									<input class="form-control left" name="pro_StartTime" id="startTime" type="date" value="${addproject.pro_StartTime}" required="required">
									<span>至</span>
									<input class="form-control right" name="pro_EndTime" id="endTime" type="date" value="${addproject.pro_EndTime}" required="required" onblur="end()">
								</p>
								<span>主办单位(执 行 方) </span>
								<input class="form-control" name="pro_Sponsor" type="text" value="${addproject.pro_Sponsor}" placeholder="请输入项目执行方" required="required">
								<span>项目状态</span>
								<select class="form-control" name="pro_Status">
								<option value="donate" selected="selected">募捐中</option>
								<option value="execute">执行中</option>
								<option value="end">已结束</option>
								</select><br>
								<button class="btn btn-success pull-right">添加活动</button>	
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
