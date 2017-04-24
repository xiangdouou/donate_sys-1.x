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
    <title>项目详细</title>
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">	
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/detail.css">
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
  </head>
  <body>
     <c:import url="../top.jsp"/>
     <div class="container">
	    <div class="row col-xs-10 col-xs-offset-1" > 
	    	<!-- 项目详情 -->
	    	<div class="row">
		     	   <ol class="breadcrumb">
				        <li><a href="#">首页</a></li>
				        <li><a href="#">项目列表</a></li>
				        <li class="active">项目名字</li>
				   </ol>
		    </div>
		    		 <div class="row detailrow">
		    	<div class="col-xs-6">
		    		<img src="<%=basePath%>img/d1.jpg">
		    		<span class="real-icon"></span>
		    	</div>
				<div class="col-xs-6 detailinfo">
					 <!-- 捐款状态 -->
					 <div class="text-muted">状态：<span>${pro_status['cur_project.pro_Status'] }</span></div>
					 <hr class="divider"/>
					 <!-- 目标数 -->
					 <p>
					 	<img src="<%=basePath%>img/icon1.png">
					 	<span class="text-danger">${cur_project.pro_TargetNumber}</span>元/件
					 </p>
					 <!-- 已筹数 -->
					 <p>
					 	<img src="<%=basePath%>img/icon2.png">
					 	<span class="text-danger">${cur_project.pro_CurNumber}</span>元/件
					 </p>
					 <!-- 捐款起止时间 -->
					 <div class="text-muted">时间：<span>${cur_project.pro_StartTime}</span>至<span>${project.pro_EndTime}</span></div>
					 <!-- 已捐人次 -->
					 <div class="text-muted">捐款人次：<span>${cur_project.pro_CurPeoples}</span>人次</div>
					 <hr class="divider"/>
					 <!-- Type=1为捐钱 -->
					 <c:if test="${cur_project.pro_Type==1 }">
					 <!-- 选择要捐的金额 -->
					 <div class="moneydiv">
					 	<form action="project/donate?type=${cur_project.pro_Type }" method="post">  
					 		金额：
						 	<input type="number" name="money_Number" step="20" min="20">
						 	<button type="submit" class="btn btn-success btn-sm">我要捐钱</button>
					 	</form>
					 </div>
					 </c:if>
					 
					<!-- Type=2为捐物品 -->
					<c:if test="${cur_project.pro_Type==2 }">
					<!-- 选择要捐的物资的类别和数量 -->
					 <div class="mupindiv">
					 	<form action="project/donate?type=${cur_project.pro_Type }" method="post">
						 	类别：
						 	<select name="goods_Name">
						 		<option>衣服</option>
						 		<option>书包</option>
						 		<option>书本文具</option>
						 		<option>玩具</option>
						 	</select>
						 	数量：
						 	<input type="number" name="goods_Number" min="1">
						 	<button type="submit" class="btn btn-success btn-sm">我要捐物</button>
					 	</form>
					 </div>
					</c:if>

				</div>
		     	
		    </div>
		    <!-- 项目进展 -->
		    <div class="row brow">
				<div class="col-xs-7">
				    <ul id="myTab" class="nav nav-tabs ">
				        <li class="active"><a href="#tab1" data-toggle="tab">捐助说明</a></li>
				        <li><a href="#tab2" data-toggle="tab">项目进展</a></li>
				        <li><a href="#tab3" data-toggle="tab">结项报告</a></li>
	    		    </ul>	 
				    <div id="myTabContent" class="tab-content">
				        <div class="tab-pane fade in  active" id="tab1">
				        	<!-- 项目详情 -->
				        	<div class="detailtab">
				        		${cur_project.pro_Des}
				        	</div>
				        </div>
				        <div class="tab-pane fade" id="tab2">规则内容面板</div>
				        <div class="tab-pane fade" id="tab3">论坛内容面板</div>  
				     </div>
				
				</div>
				<div class="col-xs-5">
					<div class="rightinfo">
						<p class="lead">发起方</p>
						<p>${cur_project.pro_Sponsor}</p>
					</div>
					<!-- 滚动信息列表 -->
					<div class="gundong" id="layer1">
						<div class="title">
					   		<span class="gundongtitle h2">信息跟踪</span>	
					   	</div>
					   	  <div id="box">
						
						   <c:forEach items="${cur_project.pro_Type==1?curpro_moneys:curpro_goodss}" var="donate_rec">
						   		<ul id="cont1" class="list-group">
							   		<li class="list-group-item">
										<!-- 募捐人姓名 -->
										<span>${donate_rec.user_Name}</span>
										<!-- 募捐的时间 -->
										在
										<span class="text-info">${donate_rec.do_Time}</span>
										<!-- 募捐的金额 -->
										捐助了
										<c:if test="${cur_project.pro_Type==1}">
											<span class="money">${donate_rec.mon_Number}</span>元
										</c:if>
										
										<c:if test="${cur_project.pro_Type==2}">
											<span class="money">${donate_rec.go_Number}</span>元
										</c:if>
										
									</li>   
						   		</ul>
						   </c:forEach>
								
						    <ul id="cont2"></ul>
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

<script>
	var area = document.getElementById('box');
	var cont1 = document.getElementById('cont1');
	var cont2 = document.getElementById('cont2');
	
	area.scrollTop = 0;
	// 克隆cont1给cont2
		cont2.innerHTML = cont1.innerHTML;
		function myScroll() {
		    if(area.scrollTop >= cont1.scrollHeight) {
		        area.scrollTop = 0;
		    }else {
		        area.scrollTop++;
	    }
	}
	
	var time = 50;
	var interval = setInterval('myScroll()', time);
	
	area.onmouseover = function () {
	    clearInterval(interval);
	};
	
	area.onmouseout = function () {
	    // 继续执行之前的定时器
	    interval = setInterval('myScroll()', time);
	};
</script>






  

  </body>
</html>
