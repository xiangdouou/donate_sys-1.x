package com.donate.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogout extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//将用户登陆状态设置为false
			request.getSession().setAttribute("login_status",false);
			//跳转到之前的页面（从哪个页面点的“注销”就跳转到哪个页面）
			response.sendRedirect(request.getHeader("REFERER"));
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}
