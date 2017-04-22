package com.donate.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.donate.util.HibernateUtil;

/**
 *
 * @author 此处写自己的姓名
 * @param T 为泛型类名
 * 功能：实现EntityDao接口，对数据库的增、删、查、改的具体逻辑操作
 */
public class EntityDaoImpl <T> implements EntityDao<T>{
		
	//保存操作
	@Override
	public void sava(T t){
		Session session=HibernateUtil.getSession();
		Transaction  tx=session.beginTransaction();
		session.save(t);
		tx.commit();
		session.close();
	}
	
	//根据参数查询
	@Override
	public  List<T> getByParam( Class t,String param,Object value)throws Exception{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		List<T> tList=session.createCriteria(t).add(Restrictions.eq(param, value)).list();
		tx.commit();
		session.close();
		return tList;
	}
	
	//type=1:多参数查询,type=2:模糊查询
	@Override
	public List<T> getByParams(int type,Class t,Map<String,Object> params){
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		List<T> tList=null;
		//创建 Criteria
		Criteria criteria=session.createCriteria(t);
		//查询
		for(String param:params.keySet()){
			if(type==1)
			 tList=criteria.add(Restrictions.eq(param, params.get(param))).list();
			if(type==2)
				tList=criteria.add(Restrictions.ilike(param, (String) params.get(param),MatchMode.ANYWHERE)).list();
		}
		tx.commit();
		session.close();
		return tList;
	}
	
	//删除操作
	@Override
	public void delete(T t) {
		Session session=HibernateUtil.getSession();
		Transaction  tx=session.beginTransaction();
		session.delete(t);
		tx.commit();
		session.close();
	}
	
	//更新操作
	@Override
	public void update(T t) {
		Session session=HibernateUtil.getSession();
		Transaction  tx=session.beginTransaction();
		session.update(t);
		tx.commit();
		session.close();	
	}

	//or查询：查询param字段等于a或等于b(比如查询职位为‘经理’或‘员工’的记录)；a、b等值放在List中。
	@Override
	public List<T> getWithOr(Class t, String param,List<?> list) {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		List<T> tList=null;
		//创建 Criteria
		Criteria criteria=session.createCriteria(t);
		//查询
		tList=criteria.add(Restrictions.in(param,list)).list();
		tx.commit();
		session.close();
		return tList;
	}

	//查询所有记录
	@Override
	public List<T> getAll(Class t) {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		List<T> tList=null;
		//创建 Criteria
		Criteria criteria=session.createCriteria(t);
		//查询
		tList=criteria.list();
		return tList;
	}
}
