package com.donate.servlet.admin;

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

public class RecordListServlet extends HttpServlet {

	private EntityDao<Goods> goodsDao=new EntityDaoImpl<Goods>();
	private int page_nums=0;   //总页数
	private int page_sum=5;   //每页的活动数
	private int page_cur=0;   //当前页码
	List<Goods> recordlist=null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				//获取页数
				page_cur=Integer.parseInt(request.getParameter("page"));
				//查询没有确认的捐赠记录
				List<Goods> goods=goodsDao.getByParam(Goods.class,"do_actual","false");
				
				recordlist=new ArrayList<Goods>();
				//获取总页数（活动总数/每页的活动数）
				if(goods.size()%page_sum==0)
					page_nums=goods.size()/page_sum;
				else
					page_nums=goods.size()/page_sum+1;
				if(page_cur<=1){
					int i;
					//将活动列表倒序
					Collections.reverse(goods);	
					for(i=0;i<page_sum && i<goods.size();i++)
						recordlist.add(goods.get(i));
					page_cur=1;
		 		}
				else if(page_cur>=page_nums){  
					//如果页码>=总页数，
					for(int i=0;i<page_sum && i<goods.size();i++)
						recordlist.add(goods.get(i));
					Collections.reverse(recordlist);	
					page_cur=page_nums;
				}
				else{
					//页码没有超出界限
					//将活动列表倒序
					Collections.reverse(goods);	
					for(int i=(page_cur-1)*page_sum;i<page_cur*page_sum && i<goods.size();i++)
						recordlist.add(goods.get(i));
				}
				request.setAttribute("recordlist",recordlist);
				request.getRequestDispatcher("/WEB-INF/admin/recordlist.jsp?page="+page_cur).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
