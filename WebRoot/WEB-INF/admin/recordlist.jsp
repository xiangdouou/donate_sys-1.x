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
  	<!-- （检查无误后）确认捐款记录 -->
  	<form action="admin/recordconfirm" method="post">
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
				    		<button type="submit" class="btn btn-success btn-sm">确认</button>
				    		<a href="admin/recorddelete?id=${goods.id }"><button class="btn btn-danger btn-sm">删除</button></a>
			    		</td>
	   				</tr>
		    	</c:forEach>
	    			</table>
   				</form>
   				<ul class="pager" id="u1">
	   				<li>
	   	   				<a href="admin/recordlist?page=${param.page-1==0?1:param.page }">上一页</a>
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
