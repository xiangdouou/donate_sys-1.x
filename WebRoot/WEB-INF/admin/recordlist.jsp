<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'recordactual.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
function f(){
	var msg=confirm("是否确认本条捐物记录，请确保捐赠的真实性！！！");
	if(msg==true){
		document.getElementById("form1").submit();
	}
}
function f1(gid){
	var msg=confirm("是否删除本条捐物记录，请确保捐赠的真实性！！！");
	if(msg==true){
		window.location.href="admin/recorddelete?id="+gid;
	}
}
</script>
<style type="text/css">
.f1{
	background:transparent;
	border: none;
	width: 140px;
}
#u1{
	    margin-left: 950px;
}
</style>
  </head>
  
  <body>
  <c:import url="admin_top.jsp"></c:import>
  <div class="container">
  		<h2>捐物实情确认</h2>
  		<hr>
  	<!-- （检查无误后）确认捐款记录 -->
  	<form action="admin/recordconfirm" method="post" id="form1">
	   <table class="table table-striped table-bordered">
	    		<tr>
	    			<th>ID</th>
   					<th>姓名</th>
   					<th>物品名</th>
   					<th>捐款数量</th>
   					<th>捐款时间</th>	
   					<th>公益活动</th>
   					<th>操作</th>	
   				</tr>
   				<c:forEach items="${requestScope.recordlist }" var="goods">
	   				<tr class="t1">
	   					<td><input readonly="readonly"  class="f1" name="id" value="${goods.id }"/></td>
			   			<td><input readonly="readonly" class="f1" name="user_Name" value="${goods.user_Name }"/></td>
			    		<td><input class="f1" name="go_Name" value="${goods.go_Name }"/></td>
			    		<td><input  class="f1" name="go_Number" value="${goods.go_Number }"/></td>
			    		<td><input readonly="readonly" class="f1" name="do_Time" value="${goods.do_Time}"/></td>
			    		<td><input readonly="readonly" class="f1" name="pro_Title" value="${goods.pro_Title}"/></td>
			    		<td>		
				    		<button type="button" class="btn btn-success btn-sm" onclick="f()">确认</button>
				    		<button type="button" class="btn btn-danger btn-sm" onclick="f1(${goods.id })">删除</button>
			    		</td>
	   				</tr>
		    	</c:forEach>
	    			</table>
   				</form>
   				<ul class="pager" id="u1">
	   				<li>
	   	   				<a href="admin/recordlist?page=${param.page-1==0?1:param.page-1 }">上一页</a>
	   		   		</li>
	   		   		
	   		   		<li>
		   				<a href="admin/recordlist?page=${param.page+1 }">下一页</a>
	   		   		</li>
   				</ul>	
   				<div >
   					提示：点击对应属性（物品名、捐款数量）的值进行修改
   				</div>	
  </div>
   </body>
</html>
