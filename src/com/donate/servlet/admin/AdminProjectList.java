package com.donate.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
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

public class AdminProjectList extends HttpServlet {

	private int page_nums=0;   //总页数
	private int page_sum=3;   //每页的活动数
	private int page_cur=0;   //当前页码
	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	private List<Project> projects=null;
	private List<Project> pagePro_List=null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		page_cur=Integer.parseInt(request.getParameter("page"));
		pagePro_List=new ArrayList<Project>();
		
		//获取所有的活动
		projects=projectDao.getAll(Project.class);
		//获取总页数（活动总数/每页的活动数）
		if(projects.size()%page_sum==0)
			page_nums=projects.size()/page_sum;
		else
			page_nums=projects.size()/page_sum+1;
		
		//System.out.println(page_cur);
		//如果页码<=0，取前6个活动
		if(page_cur<=1){
			int i,maxi;
			//将活动列表倒序
			Collections.reverse(projects);	
			for(i=0;i<page_sum && i<projects.size();i++){
				pagePro_List.add(projects.get(i));
			}
			page_cur=1;
 		}
		else if(page_cur>=page_nums){  
			//如果页码>=总页数，
			for(int i=0;i<page_sum && i<projects.size();i++){
				pagePro_List.add(projects.get(i));
			}
			page_cur=page_nums;
		}
		else{
			//页码没有超出界限
			//将活动列表倒序
			Collections.reverse(projects);	
			for(int i=(page_cur-1)*page_sum;i<page_cur*page_sum && i<projects.size();i++){
				pagePro_List.add(projects.get(i));
			}	
			
		}
		//将获取到的6个活动放到session
		request.getSession().setAttribute("adminPro_List", pagePro_List);
		response.sendRedirect("../jsp/project/pro_list.jsp?page="+page_cur);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}
