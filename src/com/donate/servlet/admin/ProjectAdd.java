package com.donate.servlet.admin;

import java.io.File;
import java.io.IOException;
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
 *功能：添加活动后台逻辑
 */
public class ProjectAdd extends HttpServlet {

	//定义接口
	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
//			if(request.getParameter("pro_Title")==null){
//				request.getRequestDispatcher("/WEB-INF/admin/projectadd.jsp").forward(request, response);
//				return;
//			}
			
			Project project=new Project();
			project.setPro_Title(new String (request.getParameter("pro_Title").getBytes("ISO-8859-1"),"utf-8"));
			project.setPro_Type(Integer.parseInt(request.getParameter("pro_Type")));
			project.setPro_CurNumber(0);
			project.setPro_CurPeoples(0);
			project.setPro_Des(new String (request.getParameter("pro_Des").getBytes("ISO-8859-1"),"utf-8"));
			project.setPro_Sponsor(new String (request.getParameter("pro_Sponsor").getBytes("ISO-8859-1"),"utf-8"));
			project.setPro_StartTime(request.getParameter("pro_StartTime"));
			project.setPro_EndTime(request.getParameter("pro_EndTime"));
			project.setPro_Status(request.getParameter("pro_Status"));
			project.setPro_TargetNumber(Integer.parseInt(request.getParameter("pro_TargetNumber")));
			
			request.getSession().setAttribute("addproject",project);
			List<Project> projects=projectDao.getByParam(Project.class,"pro_Title",project.getPro_Title());
			if(!projects.isEmpty()){
				//如果要添加的活动的标题已存在，返回添加页面
				request.setAttribute("projectAdd",false);
				request.getRequestDispatcher("/WEB-INF/admin/projectadd.jsp").forward(request, response);
				return;
			}
			//保存活动
			projectDao.sava(project);
			
			//将session中的上传图片地址设为null
			request.getSession().setAttribute("PIC",null);
			//获取刚刚上传的图片的名
			String fileName=(String) request.getSession().getAttribute("fileName");
			//获取项目绝对路径
			String basePath = getServletContext().getRealPath("/");  
	        String picPath = basePath+"img\\";
	        //获取刚刚上传的图片
			File fileimg=new File(picPath+fileName);
			projects=projectDao.getByParam(Project.class,"pro_Title",project.getPro_Title());
			//修改图片名
			fileimg.renameTo(new File(picPath+projects.get(0).getId()+".jpg"));
			
			//跳转到活动列表分页servlet
			response.sendRedirect("projectlist?pro_status=all&page=0");
		
		} catch (Exception e) {
			//发生异常跳转到添加活动页面
			request.getRequestDispatcher("/WEB-INF/admin/projectadd.jsp").forward(request, response);
		}
	
		
	}
	

}
