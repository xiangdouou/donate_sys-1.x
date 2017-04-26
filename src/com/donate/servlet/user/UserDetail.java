package com.donate.servlet.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
import com.donate.entity.User;

public class UserDetail extends HttpServlet {

	//定义操作数据库接口	
	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	private EntityDao<Money> moneyDao=new EntityDaoImpl<Money>();
	private EntityDao<Goods> goodsDao=new EntityDaoImpl<Goods>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				try {
					
						String type=request.getParameter("type");
						
						System.out.println(type);
						myInfo(request, response);
						
						//如果是查看个人主页
						if(type==null){
							response.sendRedirect("../jsp/user/user_detail.jsp");
						}
						//如果是查看捐物品记录
						else if(type.equals("goods")){
							response.sendRedirect("../jsp/user/user_detail_goods.jsp?list=li2");
						}
						
						//如果是查看捐钱记录
						else if(type.equals("money")){
							response.sendRedirect("../jsp/user/user_detail_money.jsp?list=li3");
						}
						
						//如果是修改个人信息
						else if(type.equals("money")){
							response.sendRedirect("../jsp/user/user_detail_myinfo.jsp?list=li4");
						}
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}
	
	//获取参加的活动
	public void myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取当前用户
		User user=(User) request.getSession().getAttribute("user");
		//定义一个集合，保存该用户参加的所有活动的活动名(从捐钱记录表和捐物品记录表中查询)；
		List<String> params=new ArrayList<String>();
		
		//总钱数和总物品数
		Integer my_moneysnum=0;
		Integer my_goodsnum=0;
		
		//从捐钱记录表中根据用户名查询该用户的所有捐钱记录,并将活动名加到集合中
		List<Money> moneys=moneyDao.getByParam(Money.class,"user_Name",user.getUser_Name());
		for(Money money:moneys){
			//累计捐出的钱数总和
			my_moneysnum+=money.getMon_Number();
			params.add(money.getPro_Title());
		}
		
		//从物品记录表中根据用户名查询该用户的所有捐物品记录，并将活动名加到集合中
		List<Goods> goodss=goodsDao.getByParam(Goods.class,"user_Name",user.getUser_Name());
		for(Goods goods:goodss){
			//累计捐出的物品数总和
			my_goodsnum+=goods.getGo_Number();
			params.add(goods.getPro_Title());
		}
		
		//去除集合中重复的活动名
		HashSet<String> hs=new HashSet<String>(params);
		params.clear();
		params.addAll(hs);
		
		//根据活动名列表查询活动
		List<Project> my_projects=projectDao.getWithOr(Project.class,"pro_Title",params);
		
		//倒序捐钱记录表，并取前10条
		Collections.reverse(moneys);
		List<Money> my_moneys=null;
		if(moneys.size()<9)
			my_moneys=moneys.subList(0,moneys.size());
		else
			my_moneys=moneys.subList(0,9);
		
		//倒序捐物品记录表，并取前10条
		Collections.reverse(goodss);
		List<Goods> my_goodss=null;
		if(goodss.size()<9)
			my_goodss=goodss.subList(0,goodss.size());
		else
			my_goodss=goodss.subList(0,9);
		//将参加的活动、最近捐钱记录、最近捐物品记录、捐钱总数、捐物品总数放到session
		request.getSession().setAttribute("my_projects",my_projects);
		request.getSession().setAttribute("my_moneys",my_moneys);
		request.getSession().setAttribute("my_goodss",my_goodss);
		request.getSession().setAttribute("my_moneysnum",my_moneysnum);
		request.getSession().setAttribute("my_goodsnum",my_goodsnum);		
	}
}
