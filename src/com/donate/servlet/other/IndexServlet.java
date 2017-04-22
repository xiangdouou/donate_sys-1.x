package com.donate.servlet.other;

import java.io.IOException;
import java.util.ArrayList;
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
		//查询活动状态是‘募捐’或‘执行’的活动，存到List集合中，并放到Session.
				List<String> statusList=new ArrayList<String>();	
				statusList.add("donate");
				statusList.add("execute");
				List<Project> projects=projectDao.getWithOr(Project.class,"pro_Status",statusList);
				request.getSession().setAttribute("projects", projects);
				
				//查询所有活动的总钱数，放到session中
				List<Money> moneys=moneytDao.getAll(Money.class);
				int total_money=0;  //总钱数
				for(Money money : moneys){
					total_money+=money.getMon_Number();
				}
				request.getSession().setAttribute("total_money",total_money);
				
				//查询所有活动募捐的物品总数，放到session中
				List<Goods> goodss=goodsDao.getAll(Goods.class);
				int total_goods=0;  //总钱数
				for(Goods goods : goodss){
					total_goods+=goods.getGo_Number();
				}
				request.getSession().setAttribute("total_goods",total_goods);
				
				response.sendRedirect("./index.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

}
