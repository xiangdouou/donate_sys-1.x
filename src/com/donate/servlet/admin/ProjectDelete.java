package com.donate.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donate.dao.EntityDao;
import com.donate.dao.EntityDaoImpl;
import com.donate.entity.Project;

public class ProjectDelete extends HttpServlet {

	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			try {
				//获取页面传来的活动id
				int id=Integer.parseInt(request.getParameter("pro_id"));
				 String de_url=request.getHeader("REFERER");
				//根据id获取活动
				List<Project> projects=projectDao.getByParam(Project.class,"id",id);
				//删除活动
				projectDao.delete(projects.get(0));
				
				//request.getRequestDispatcher(de_url).forward(request, response);
				response.sendRedirect(de_url);
				
			} catch (Exception e) {
				//发生异常跳转到活动列表页面
				request.getRequestDispatcher("projectlist?pro_status=all&page=1").forward(request, response);
			}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}
