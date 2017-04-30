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
/**
 *     
 * @author Fog
 *功能：ajax异步上传图片后台逻辑
 */
@MultipartConfig
public class UploadImage extends HttpServlet {

	private static final long serialVersionUID = 1L;  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
	        String picPath = null;  
	    
	            Part part = request.getPart("pic");//前台的文件标签的name,若ajax直接提交表单，这里无法获取  
	            String file = part.getHeader("Content-Disposition");  
	            //获取文件名  
	            String fileName = file.substring(file.lastIndexOf("=")+2, file.length()-1);  
	            request.getSession().setAttribute("fileName",fileName);
	            //获取项目的部署路劲  
	            String basePath = getServletContext().getRealPath("/");  
	            String id=request.getParameter("id");
	            if(id!=null){//更新图片
	            	picPath = basePath+"img\\"+id+".jpg";
	            	request.getSession().setAttribute("PIC",null);
	            }
	            	
	            else{//上传图片
	            	picPath = basePath+"img\\"+fileName;  
	            	//将刚刚上传的文件路径存在session中方便页面显示  
		            request.getSession().setAttribute("PIC","img\\"+fileName);
	            }      	
	            //上传文件
	            part.write(picPath);  
	            
	          
	            try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	}
	       
}
