package com.donate.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
    
@MultipartConfig
public class UploadImage extends HttpServlet {

	private static final long serialVersionUID = 1L;  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
	        String picflag = request.getParameter("picture");  
	        String picPath = null;  
	    
	            Part part = request.getPart("pic");//前台的文件标签的name,若ajax直接提交表单，这里无法获取  
	            String file = part.getHeader("Content-Disposition");  
	            //获取文件名  
	            String fileName = file.substring(file.lastIndexOf("=")+2, file.length()-1);  
	            request.getSession().setAttribute("fileName",fileName);
	            //获取项目的部署路劲  
	            String basePath = getServletContext().getRealPath("/");  
	            picPath = basePath+"img\\"+fileName;  
	            //上传文件到部署路劲  
	            part.write(picPath);  
	            //将路径存在session中方便下面显示是用  
	            request.getSession().setAttribute("PIC","img\\"+fileName);  
	            //以下代码是使用了  AJax异步请求时使用的  
//	            Gson gson = new Gson();//创建gson对象  
//	            response.setContentType("text/json;charset=utf-8");//设置响应的方式为json  
//	            response.getWriter().print(gson.toJson("<font color=red>用户名或密码错误</font>"));  
	        	}
	       
}
