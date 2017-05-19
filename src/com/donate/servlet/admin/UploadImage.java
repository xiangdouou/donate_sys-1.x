    package com.donate.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;
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
	            //获取项目的部署路径
	            String basePath = getServletContext().getRealPath("/");  
	            picPath = basePath+"img\\"+fileName;  
	            
	            
		        File oldfile=new File(picPath);
		        //如果存在相同文件名，则用20位随机字符串代替文件名
		        if(oldfile.exists()){
		        	picPath=basePath+"img\\"+RandomStringUtils.randomAlphanumeric(20)+".jpg";
		        }
		        //上传文件
	            part.write(picPath);  
	            
	          //将刚刚上传的文件路径存在session中以便更新和添加活动时修改图片
		        request.getSession().setAttribute("PIC",picPath);
		        //返回给ajax数据
	            PrintWriter out=response.getWriter();
	            out.write("img\\"+fileName);
	        	}
	       
}
