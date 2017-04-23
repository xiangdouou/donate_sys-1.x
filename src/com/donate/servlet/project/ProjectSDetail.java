package com.donate.servlet.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donate.dao.EntityDao;
import com.donate.dao.EntityDaoImpl;
import com.donate.entity.Project;

public class ProjectSDetail extends HttpServlet {
	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	private List<Project> projects=null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取所有状态为“募捐”或“执行”的活动
		List<String> params=new ArrayList<String>();
		params.add("donate");
		params.add("execute");
		projects=projectDao.getWithOr(Project.class,"pro_Status",params);
		//jaing
		Collections.reverse(projects);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
