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

public class ProjectOperate extends HttpServlet {

	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			try {
				//获取要执行的操作类型
				String type=request.getParameter("type");
				//如果是添加活动，跳转到添加活动页面
				if(type.equals("add")){
					request.getRequestDispatcher("/WEB-INF/admin/projectadd.jsp").forward(request, response);
				}
				//如果是更新活动
				if(type.equals("update")){
					Integer pro_id=Integer.parseInt(request.getParameter("pro_id"));
					List<Project> projects=projectDao.getByParam(Project.class,"id",pro_id);
					request.setAttribute("updateproject",projects.get(0));
					
					String up_url=request.getHeader("REFERER");
					request.getSession().setAttribute("up_url", up_url);
					request.getRequestDispatcher("/WEB-INF/admin/projectupdate.jsp").forward(request, response);
				}
			} catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/admin/projectlist.jsp?pro_status=all&page=1").forward(request, response);
				
			}
			
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
