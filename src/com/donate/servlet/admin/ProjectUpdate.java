package com.donate.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donate.dao.EntityDao;
import com.donate.dao.EntityDaoImpl;
import com.donate.entity.Project;
/**
 * 
 * @author Fog
 *功能：管理员修改活动信息
 */
public class ProjectUpdate extends HttpServlet {

	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Project project=new Project();
			project.setId(Integer.parseInt(request.getParameter("id")));
			project.setPro_Title(new String (request.getParameter("pro_Title").getBytes("ISO-8859-1"),"utf-8"));
			project.setPro_Type(Integer.parseInt(request.getParameter("pro_Type")));
			project.setPro_Des(new String (request.getParameter("pro_Des").getBytes("ISO-8859-1"),"utf-8"));
			project.setPro_Sponsor(new String (request.getParameter("pro_Sponsor").getBytes("ISO-8859-1"),"utf-8"));
			project.setPro_StartTime(request.getParameter("pro_StartTime"));
			project.setPro_EndTime(request.getParameter("pro_EndTime"));
			project.setPro_Status(request.getParameter("pro_Status"));
			project.setPro_TargetNumber(Integer.parseInt(request.getParameter("pro_TargetNumber")));
			
			request.getSession().setAttribute("updateproject",project);
			
			List<Project> projects;
			projects = projectDao.getByParam(Project.class,"pro_Title",project.getPro_Title());
			if(!projects.isEmpty()){
				//如果要添加的活动的标题已存在，返回添加页面
				request.setAttribute("projectUpdate",false);
				request.getRequestDispatcher("/WEB-INF/admin/projectupdate.jsp").forward(request, response);
				return;
			}
			
			//修改活动
			projectDao.update(project);
			
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/admin/projectupdate.jsp").forward(request, response);
		}
	
	}

}
