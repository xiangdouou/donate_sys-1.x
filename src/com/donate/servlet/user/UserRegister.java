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

/***
 * 
 * @author 此处写自己的名字
 * 功能：用户注册操作
 *
 */
public class UserRegister extends HttpServlet {
	
	//初始化接口
	private EntityDao<User> userDao=new EntityDaoImpl<User>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			try {
				//创建user对象并给用户属性赋值
				User user=new User();
				user.setUser_Name(new String(request.getParameter("user_Name").getBytes("ISO-8859-1"),"utf-8"));
				user.setUser_Pass(request.getParameter("user_Pass"));
				user.setUser_Age(Integer.parseInt(request.getParameter("user_Age")));
				user.setUser_Sex(request.getParameter("user_Sex"));
				user.setUser_Phone(request.getParameter("user_Phone"));
				user.setUser_Address(new String(request.getParameter("user_Address").getBytes("ISO-8859-1"),"utf-8"));
				user.setUser_Email(request.getParameter("user_Email"));
				if(userRegister(user)){
					//注册成功，跳转到登陆页面
					request.setAttribute("user", user);
					request.getRequestDispatcher("../jsp/user/user_login.jsp").forward(request, response);
				}
				else{
					
					request.setAttribute("register", false);
					
					request.getRequestDispatcher("../jsp/user/user_register.jsp").forward(request, response);
				}
			} catch (Exception e) {			
				response.sendRedirect("../jsp/user/user_register.jsp");
			}
	}
	
	
	
	//用户注册
	public boolean userRegister(User user) throws Exception {
		//首先查看数据库是否存在此用户名
		List<User> users=userDao.getByParam(User.class,"user_Name",user.getUser_Name());
		//如果存在此用户名，注册失败
		if(!users.isEmpty())
			return false;
		else{
			//注册成功
			userDao.sava(user);
			return true;
		}
		
	}

}
