package com.donate.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donate.dao.EntityDao;
import com.donate.dao.EntityDaoImpl;
import com.donate.entity.User;
/**
 * 
 * @author Fog
 *功能：用户信息修改
 */
public class UserUpdate extends HttpServlet {

	private EntityDao<User> userDao=new EntityDaoImpl<User>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		try {
			User user=new User();
			user.setId(Integer.parseInt(request.getParameter("id")));
			user.setUser_Name(new String(request.getParameter("user_Name").getBytes("ISO-8859-1"),"utf-8"));
			user.setUser_Pass(request.getParameter("user_Pass"));
			user.setUser_Age(Integer.parseInt(request.getParameter("user_Age")));
			user.setUser_Sex(request.getParameter("user_Sex"));
			user.setUser_Phone(request.getParameter("user_Phone"));
			user.setUser_Address(new String(request.getParameter("user_Address").getBytes("ISO-8859-1"),"utf-8"));
			user.setUser_Email(request.getParameter("user_Email"));
			
			System.out.println("username "+user.getUser_Name());
			
			//查询数据是否存在要修改的用户名
			List<User> users=userDao.getByParam(User.class,"user_Name",user.getUser_Name());
			System.out.println(users.get(0).getId()+" "+user.getId());
			if(users.get(0).getId()!=user.getId()){  
				//如果存在，返回修改界面
				request.setAttribute("userUpdate", false);
				request.getRequestDispatcher("../jsp/user/user_detail_myinfo.jsp").forward(request, response);
				return;
			}
			
			//修改信息
			userDao.update(user);
			request.getSession().setAttribute("user",user);
			request.getRequestDispatcher("../jsp/user/user_detail_myinfo.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("../jsp/user/user_detail_myinfo.jsp").forward(request, response);
		}
	}

}
