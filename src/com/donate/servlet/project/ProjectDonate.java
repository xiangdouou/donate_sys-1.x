package com.donate.servlet.project;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
/**
 * 
 * @author Fog
 *功能：捐献功能后台逻辑
 */
public class ProjectDonate extends HttpServlet {

	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	private EntityDao<Money> moneyDao=new EntityDaoImpl<Money>();
	private EntityDao<Goods> goodsDao=new EntityDaoImpl<Goods>();
	
	private Integer money_nums=0;
	private Integer goods_nums=0;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				
					//获取页面传来的捐赠类型（钱or物品）
					int type=Integer.parseInt(request.getParameter("type"));
					//更新活动当前募捐到的物品（钱）总数
					Project project= (Project)request.getSession().getAttribute("cur_project");
					
					//参加活动人次加一
					project.setPro_CurPeoples(project.getPro_CurPeoples()+1);
					if(type==1){//如果是捐钱
						donateMoney(request,response);
						//加上钱数
						project.setPro_CurNumber(project.getPro_CurNumber()+this.money_nums);
						
						//更新捐钱记录列表
						List<Money> curpro_moneys = moneyDao.getByParam(Money.class,"pro_Title",project.getPro_Title());
						request.setAttribute("curpro_moneys", curpro_moneys);
						
					}
					if(type==2){//如果是捐物品
						donateGoods(request, response);
						//加上物品数
						project.setPro_CurNumber(project.getPro_CurNumber()+this.goods_nums);
						
						//更新捐钱列表
						List<Goods> curpro_goodss=goodsDao.getByParam(Goods.class,"pro_Title",project.getPro_Title());
						request.setAttribute("curpro_goodss", curpro_goodss);
	
					}
						
					projectDao.update(project);
					request.setAttribute("cur_project",project);
					request.getRequestDispatcher("../project/detail?pro_id="+project.getId()).forward(request, response);
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	//添加捐钱记录
	public void donateMoney(HttpServletRequest request, HttpServletResponse response){
		//获取钱数、捐献者姓名(当前用户名)、活动名、捐献日期
		this.money_nums=Integer.parseInt(request.getParameter("money_Number"));
		String user_Name=((User)request.getSession().getAttribute("user")).getUser_Name();
		String pro_Title=((Project)request.getSession().getAttribute("cur_project")).getPro_Title();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String do_Time=format.format(new Date());
		
		Money money=new Money();
		money.setMon_Number(money_nums);
		money.setUser_Name(user_Name);
		money.setPro_Title(pro_Title);
		money.setDo_Time(do_Time);
		
		moneyDao.sava(money);
	}
	public void donateGoods(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取物品名、物品数、捐献者姓名(当前用户名)、活动名、捐献日期
		String goods_name=new String(request.getParameter("goods_Name").getBytes("ISO-8859-1"),"utf-8");
		this.goods_nums=Integer.parseInt(request.getParameter("goods_Number"));
		String user_Name=((User)request.getSession().getAttribute("user")).getUser_Name();
		String pro_Title=((Project)request.getSession().getAttribute("cur_project")).getPro_Title();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String do_Time=format.format(new Date());
		
		Goods goods=new Goods();
		goods.setGo_Name(goods_name);
		goods.setGo_Number(goods_nums);
		goods.setUser_Name(user_Name);
		goods.setPro_Title(pro_Title);
		goods.setDo_Time(do_Time);
		
		goodsDao.sava(goods);
		
	}

}
