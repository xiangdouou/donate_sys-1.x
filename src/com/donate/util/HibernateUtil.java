package com.donate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/***
 * 
 * @author 此处填自己的性名
 * 功能：hibernate工具类
 *
 */
public class HibernateUtil {
	//创建会话工厂
	private static SessionFactory sessionFactory;
	//创建会话
	private static Session session;

	static {
		//加载hibernate.cfg.xml配置文件
		Configuration config = new Configuration().configure();
		
		//创建ServiceRegistry 
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(config.getProperties()) .buildServiceRegistry();
		
		//创建会话工厂		                           
		sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory(sr);
	}
	
	//获取会话工厂
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//获取会话
	public static Session getSession(){
		session=sessionFactory.openSession();
		return session;
	}
	
	//关闭会话
	public static void closeSession(Session session){
		if(session!=null){
			session.close();
		}
	}
}
