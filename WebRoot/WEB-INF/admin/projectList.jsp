<%@ page language="java" import="java.util.*" pageEncoding="UTf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/listinfo.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/allproject.css">		
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
    <script type="text/javascript">
    function selectchange(index){   
    		window.location.href="<%=basePath%>admin/projectlist?pro_status="+index +"&page=0";
    }
    $(function(){
    	/* 设置下拉菜单选中项为地址中的活动状态参数 */
        $("#selector").val('${param.pro_status}');
    });
	</script>
  </head>  
  <body>
	<jsp:include page="admin_top.jsp"></jsp:include>
    <div class="container">
	    <div class="row col-xs-10 col-xs-offset-1">
	    	<div class="row">
	    		<ol class="breadcrumb" style="background: none;">
				    <li>首页</li>
				    <li>项目列表</li>
				</ol>
	    	</div>
	    	<div class="row rowtop" style="margin-top: -18px;">		    	
		    	项目状态:
		    		<select onchange="selectchange(this.options[this.options.selectedIndex].value)" class="selector" id="selector">
		    		    <option value="all" selected="selected">全部</option>
		    			<option value="donate">募捐中</option>
		    			<option value="execute">执行中</option>
		    			<option value="end">结束</option>
		    		</select>
	    	</div>
	    	<div class="row">	
	    	<c:forEach items="${adminPro_List }" var="project">
	    		<div class="media">	
					<br>				 
				        <a class="pull-left" href="project/detail?pro_id=${project.id }">
				            <img class="media-object" src="img/${project.id }.jpg">
				        </a>
				        <div class="media-body">
							<div class="leftmedia">							
								<!-- 活动标题 -->
					        	<span class="media-heading h4"><a class="text-a" href="project/detail?pro_id=${project.id }">${project.pro_Title }</a></span>
					     		<div class="descript small">
					            	<span class="text-muted">项目简介&nbsp;|&nbsp;</span>
					            	<!-- 项目简介 -->
					        		<span>${project.pro_Des }</span>
								</div>
								<br>
								<div class="small">									
										<span class="text-muted">筹款目标&nbsp;|&nbsp;</span>
										<span>${project.pro_TargetNumber}</span>
										<br>
										<span class="text-muted">筹款时间&nbsp;|&nbsp;</span>
										<span>${project.pro_StartTime }</span>
										至
										<span>${project.pro_EndTime}</span>
										<br>
										<span class="text-muted">执 行 方&nbsp;|&nbsp;</span>
										<span>${project.pro_Sponsor}</span> 
								</div>
							</div>
							<div class="rightmedia">
								<div class="small">
								
									<p>		
										<!-- 项目状态 -->							
										<span class="text-muted">项目状态:&nbsp;</span>
										<span>${pro_status[project.pro_Status]}</span>
										<br>
									</p>
									<p class="left">
										<!-- 已筹元数或件数 -->
										<span class="text-muted">已筹:&nbsp;</span>
										<span class="text-red">${project.pro_CurNumber}</span>
									</p>
									<p class="right text-right">
										<!-- 捐款 人次-->
										<span class="text-red">${project.pro_CurPeoples }</span>
										<span class="text-muted">人次捐款</span> 
									</p>
										<!-- 进度条 -->
									<div class="progress progress-striped active" style="float:left;height: 10px;">
									       <div class="progress-bar progress-bar-success" style="width:${project.pro_CurNumber>=project.pro_TargetNumber?100:project.pro_CurNumber*100/project.pro_TargetNumber}%" ></div>
									</div>
									<!-- 进度条数字 -->
									<span class="text-muted number text-right"><fmt:formatNumber  type="number" value="${project.pro_CurNumber>=project.pro_TargetNumber?100:project.pro_CurNumber*100/project.pro_TargetNumber}" maxFractionDigits="0"/> %</span> 
								   	<p>
										<a class="btn btn-success btn-sm" href="<%=basePath%>admin/alertproject.jsp">修改</a> 			
										<button class="btn btn-success btn-sm">删除</button>
									</p>
								</div>
							</div>
				        </div>				        
    				</div>
	    	</c:forEach>		
					
    				<ul class="pager">
					    <li><a href="admin/projectlist?pro_status=${param.pro_status}&page=${param.page-1}">«上一页</a></li>
					    <li><a href="admin/projectlist?pro_status=${param.pro_status}&page=${param.page+1}">下一页»</a></li>
					</ul>
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
