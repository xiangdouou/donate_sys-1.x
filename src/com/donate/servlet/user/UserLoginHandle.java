package com.donate.servlet.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donate.dao.EntityDao;
import com.donate.dao.EntityDaoImpl;
import com.donate.entity.User;
/**
 * 
 * @author 此处写自己的姓名
 * 功能：用户登陆servlet
 *
 */
public class UserLoginHandle extends HttpServlet {

	private EntityDao<User> userDao=new EntityDaoImpl<User>();
	private User user;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	//处理post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			try {

				//如果用户名或密码为空，重定向到登陆页面
				if(request.getParameter("user_name")==null || request.getParameter("user_pass")==null){
					response.sendRedirect("../jsp/user/user_login.jsp");
					return;
				}
				//获取从页面传过来的用户名和密码
				String user_name=new String(request.getParameter("user_name").getBytes("ISO-8859-1"),"utf-8");
				String user_pass=request.getParameter("user_pass");	
				//声明一个用户并赋值
				this.user=new User();
				this.user.setUser_Name(user_name);
				this.user.setUser_Pass(user_pass);
				//获取之前的页面(从哪个页面跳转过来)的url,
				String url=(String) request.getSession().getAttribute("url");
			
				if(userLogin(user)==true){
				//登陆成功
				//将user放到session
				List<User> users=userDao.getByParam(User.class,"user_Name",user_name);
				request.getSession().setAttribute("user",users.get(0));
				
				//如果是管理员登陆
				if(user.getUser_Name().equals("admin"))
					response.sendRedirect("../admin/projectlist?pro_status=all&page=1");
				else{
					if(url!=null){
						if(url.indexOf("admin")>0)
							response.sendRedirect("../IndexServlet");
						else
							response.sendRedirect(url);
					}
						
					else
						response.sendRedirect("../IndexServlet");
				}		
			}
			else{
				//登陆失败
				request.setAttribute("login",false);
				request.getRequestDispatcher("../jsp/user/user_login.jsp").forward(request, response);
			}
			} catch (Exception e) {
				response.sendRedirect("../jsp/user/user_login.jsp");
			}
			
			
	}
	
	
	//用户登陆
	public boolean userLogin(User user) {
		//创建Map集合
		Map<String,Object> params=new HashMap<String, Object>();
		//将用户名和密码加入到集合
		params.put("user_Name",user.getUser_Name());
		params.put("user_Pass",user.getUser_Pass());
		//调用多参数查询方法 getByParams，返回查询到的用户列表
		List<User> users=userDao.getByParams(1,User.class, params);
		//如果用户列表为空，说明数据库中没有该用户
		if(!users.isEmpty()){
			this.user=users.get(0);
			return true;	
		}
			return false;
				
	}

}
