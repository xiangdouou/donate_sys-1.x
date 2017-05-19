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
/**
 * 
 * @author Fog
 * 删除捐物品记录
 */
public class RecordDelete extends HttpServlet {

	private EntityDao<Goods> goodsDao=new EntityDaoImpl<Goods>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				try {
					
					Integer id=Integer.parseInt(request.getParameter("id"));
					List<Goods> goods=goodsDao.getByParam(Goods.class,"id",id);
					
					goodsDao.delete(goods.get(0));
					response.sendRedirect("recordlist?page=1");
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
