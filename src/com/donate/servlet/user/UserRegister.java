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
				//创建user对象并赋值
				User user=new User();
				user.setUser_Name(new String(request.getParameter("user_name").getBytes("ISO-8859-1"),"utf-8"));
				user.setUser_Pass(request.getParameter("user_pass"));
				user.setUser_Age(Integer.parseInt(request.getParameter("user_age")));
				user.setUser_Phone(request.getParameter("user_phone"));
				user.setUser_Address(new String(request.getParameter("user_address").getBytes("ISO-8859-1"),"utf-8"));
				user.setUser_Email(request.getParameter("user_email"));
				if(userRegister(user)){
					//注册成功，跳转到登陆页面
					request.getSession().setAttribute("user", user);
					response.sendRedirect("../jsp/user/user_login.jsp");
				}
				else{
					//注册失败，跳转到注册页面
					request.getSession().setAttribute("reg_status",false);
					response.sendRedirect("../jsp/user/user_register.jsp");
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}
	
	
	//注册

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
