package com.donate.dao;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author 此处写自己的姓名
 * @param T 为泛型类名
 * 功能：操作数据库接口，包括增、删、查、改方法的声明。
 */
public interface EntityDao<T> {
	//保存
	public void sava(T t);
	
	//根据具体值查询
	public List<T> getByParam(Class t,String param,Object value)throws Exception;
	
	//多参数查询
	public  List<T> getByParams(int type,Class t,Map<String,Object> params);
	
	//or查询
	public List<T> getWithOr(Class t,String param,List<?> list);
	
	//查询所有记录
	public List<T> getAll(Class t);
	//删除
	public void delete(T t);
	
	//修改
	public void update (T t);
	
}
