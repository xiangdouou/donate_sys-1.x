package com.donate.servlet.project;

import java.io.IOException;
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

/**
 * 
 * @author Fog
 *功能：查看活动详情之前的操作
 */
public class ProjectDetail extends HttpServlet {
	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	private EntityDao<Money> moneyDao=new EntityDaoImpl<Money>();
	private EntityDao<Goods> goodsDao=new EntityDaoImpl<Goods>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		try {
			//获取页面传过来的活动id
			int pro_id=Integer.parseInt(request.getParameter("pro_id"));
			//根据id获取活动
			Project cur_project=projectDao.getByParam(Project.class,"id",pro_id).get(0);
			//将活动放到session
			request.getSession().setAttribute("cur_project", cur_project);
			System.out.println("pro_Type  "+cur_project.getPro_Type());
			//如果活动募捐的是钱
			if(cur_project.getPro_Type()==1){
				//根据活动名获取捐钱的记录列表
				List<Money> curpro_moneys=moneyDao.getByParam(Money.class,"pro_Title",cur_project.getPro_Title());
				System.out.println("size "+curpro_moneys.size());
				request.getSession().setAttribute("curpro_moneys", curpro_moneys);
			}
			
			//如果活动募捐的是物品
			if(cur_project.getPro_Type()==2){
				//根据活动名获取捐物品的记录列表
				List<Goods> curpro_goodss=goodsDao.getByParam(Goods.class,"pro_Title",cur_project.getPro_Title());
				request.getSession().setAttribute("curpro_goodss", curpro_goodss);
			}
			response.sendRedirect("../jsp/project/pro_detail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
