package com.donate.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.donate.entity.User;
/**
 * 
 * @author Fog
 *功能：登陆拦截器
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
	    HttpServletResponse response = (HttpServletResponse) arg1;    
        HttpServletRequest request=(HttpServletRequest)arg0;  
        HttpSession session = request.getSession(true);    
        
        User user = (User) session.getAttribute("user");
        String url=request.getHeader("REFERER");
        //记住被拦截的页面，（以便登陆之后可以直接跳转）
        session.setAttribute("url",url);
        if(user==null)  
        {  
              //判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转  
             if(url!=null && !url.equals("") && ( url.indexOf("Login")<0 && url.indexOf("login")<0 ))  
             {  
                 response.sendRedirect("../jsp/user/user_login.jsp");  
                 return ;  
            }             
       }  
         
          //已通过验证，用户访问继续  
         arg2.doFilter(arg0, arg1);  

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
