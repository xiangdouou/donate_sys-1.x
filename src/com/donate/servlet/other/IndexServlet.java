package com.donate.servlet.other;

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
import com.donate.entity.Goods;
import com.donate.entity.Money;
import com.donate.entity.Project;

public class IndexServlet extends HttpServlet {

	//初始化接口
	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	private EntityDao<Money> moneytDao=new EntityDaoImpl<Money>();
	private EntityDao<Goods> goodsDao=new EntityDaoImpl<Goods>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doPost(request, response);
		//查询所有的活动，存到List集合中，并放到Session.
				List<Project> projects=projectDao.getAll(Project.class);
				//将活动集合倒序，将最近发布的活动放在前边
				Collections.reverse(projects);
				//将活动列表放到session中
				request.getSession().setAttribute("projects", projects);
				List<Project> hot_projects=new ArrayList<Project>();
				//将前8条状态为“募捐”的活动放到hot_projects中,如果活动总数小于8，跳出循环
				for(int i=0;i<8 && i<projects.size();){
					if(projects.get(i).getPro_Status().equals("donate")){
						hot_projects.add(projects.get(i));
						i++;
					}
					
				}
				request.getSession().setAttribute("hot_projects", hot_projects);
				
				//查询所有活动募捐的物品和钱的总数，放到session中
				int total_goods=0;  //总物品数
				int total_money=0;  //总钱数
				int total_people=0; //总人次
				for(Project project : projects){
					if(project.getPro_Type()==1)
						total_money+=project.getPro_CurNumber();
					if(project.getPro_Type()==2)
						total_goods+=project.getPro_CurNumber();
					total_people+=project.getPro_CurPeoples();
				}
				request.getSession().setAttribute("total_money",total_money);
				request.getSession().setAttribute("total_goods",total_goods);				
				request.getSession().setAttribute("total_people",total_people);	
				System.out.println("===========");
				response.sendRedirect("./index.jsp");
				
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

}
