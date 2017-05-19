package com.donate.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donate.dao.EntityDao;
import com.donate.dao.EntityDaoImpl;
import com.donate.entity.Goods;
import com.donate.entity.Project;
/**
 * 
 * @author Fog
 *确认捐物品记录
 */
public class RecordConfirm extends HttpServlet {

	private EntityDao<Goods> goodsDao=new EntityDaoImpl<Goods>();
	
	private EntityDao<Project> projectDao=new EntityDaoImpl<Project>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
					Integer id=Integer.parseInt(request.getParameter("id"));
					String user_Name=new String(request.getParameter("user_Name").getBytes("ISO-8859-1"),"utf-8");
					Integer go_Number=Integer.parseInt(request.getParameter("go_Number"));
					String go_Name=new String(request.getParameter("go_Name").getBytes("ISO-8859-1"),"utf-8");
					String do_Time=request.getParameter("do_Time");
					String pro_Title=new String(request.getParameter("pro_Title").getBytes("ISO-8859-1"),"utf-8");;
					
					Goods goods=new Goods();
					goods.setId(id);
					goods.setUser_Name(user_Name);
					goods.setGo_Name(go_Name);
					goods.setDo_Time(do_Time);
					goods.setGo_Number(go_Number);
					goods.setPro_Title(pro_Title);
					goods.setDo_actual("true");
					//更新捐款记录
					goodsDao.update(goods);
				
					//根据活动标题获取活动
					Project project=projectDao.getByParam(Project.class,"pro_Title",pro_Title).get(0);
					project.setPro_CurNumber(project.getPro_CurNumber()+go_Number);
					project.setPro_CurPeoples(project.getPro_CurPeoples()+1);
					//更新活动
					projectDao.update(project);
					
					response.sendRedirect("recordlist?page=1");
					
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
