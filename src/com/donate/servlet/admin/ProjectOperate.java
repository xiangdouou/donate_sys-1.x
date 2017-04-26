package com.donate.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectOperate extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取要执行的操作类型
		String type=request.getParameter("type");
		//如果是添加活动，跳转到添加活动页面
		if(type.equals("add")){
			request.getRequestDispatcher("/WEB-INF/admin/projectadd.jsp").forward(request, response);
		}
		if(type.equals("update")){
			request.getRequestDispatcher("/WEB-INF/admin/projectupdate.jsp").forward(request, response);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
