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
/**
 * 
 * @author Fog
 *功能：管理员活动列表分页（所有活动）
 */
public class AdminProjectList extends HttpServlet {

	private int page_nums=0;   //总页数
	private int page_sum=3;   //每页的活动数
	private int page_cur=0;   //当前页码
	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	private List<Project> projects=null;
	private List<Project> pagePro_List=null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			try {
				page_cur=Integer.parseInt(request.getParameter("page"));
				pagePro_List=new ArrayList<Project>();
				//获取请求的活动参数
				String pro_status=request.getParameter("pro_status");
				//查出所有活动
				if(pro_status.equals("all"))
					projects=projectDao.getAll(Project.class);
				else{
					//根据请求的活动状态参数查询活动列表
					projects=projectDao.getByParam(Project.class,"pro_Status",pro_status);
				}
				
				//获取总页数（活动总数/每页的活动数）
				if(projects.size()%page_sum==0)
					page_nums=projects.size()/page_sum;
				else
					page_nums=projects.size()/page_sum+1;
				
				//System.out.println(page_cur);
				//如果页码<=1，取前6个活动
				if(page_cur<=1){
					int i;
					//将活动列表倒序
					Collections.reverse(projects);	
					for(i=0;i<page_sum && i<projects.size();i++){
						pagePro_List.add(projects.get(i));
						System.out.println("i"+i);
					}
					page_cur=1;
					//System.out.println("pagePro_List size"+pagePro_List.size());
		 		}
				else if(page_cur>=page_nums){  
					//如果页码>=总页数，
					for(int i=0;i<page_sum && i<projects.size();i++){
						pagePro_List.add(projects.get(i));
					}
					Collections.reverse(pagePro_List);	
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
				//response.sendRedirect("../jsp/project/pro_list.jsp?page="+page_cur);
				request.getRequestDispatcher("/WEB-INF/admin/projectList.jsp?pro_status="+pro_status+"&page="+page_cur).forward(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}
