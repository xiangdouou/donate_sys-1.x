package com.donate.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author 此处写自己的姓名
 * 功能：用户实体类，对应数据库中的user表
 *
 */
@Entity
//user:数据库中的user表；donate:数据库名
@Table(name="user",schema="donate_sys")
public class User {
	private Integer id;
	private String user_Name;
	private String user_Pass;
	private int  user_Age;
	private String user_Sex;
	private String user_Phone;
	private String user_Email;
	private String user_Address;
	
	//Id:表的主键
	@Id
	//主键自动递增
	@GeneratedValue(strategy=GenerationType.AUTO)
	//对应表中id字段，不能为空值
	@Column(name="id",nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	//Basic:表普通字段
	@Basic
	@Column(name="user_Name",nullable=false)
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	
	@Basic
	@Column(name="user_Pass",nullable=false)
	public String getUser_Pass() {
		return user_Pass;
	}
	public void setUser_Pass(String user_Pass) {
		this.user_Pass = user_Pass;
	}
	
	@Basic
	@Column(name="user_Age",nullable=false)
	public int getUser_Age() {
		return user_Age;
	}
	public void setUser_Age(int user_Age) {
		this.user_Age = user_Age;
	}
	
	@Basic
	@Column(name="user_Sex",nullable=false)
	public String getUser_Sex() {
		return user_Sex;
	}
	public void setUser_Sex(String user_Sex) {
		this.user_Sex = user_Sex;
	}
	
	@Basic
	@Column(name="user_Phone",nullable=false)
	public String getUser_Phone() {
		return user_Phone;
	}
	public void setUser_Phone(String user_Phone) {
		this.user_Phone = user_Phone;
	}
	
	@Basic
	@Column(name="user_Email",nullable=false)
	public String getUser_Email() {
		return user_Email;
	}
	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}
	
	@Basic
	@Column(name="user_Address",nullable=false)
	public String getUser_Address() {
		return user_Address;
	}
	public void setUser_Address(String user_Address) {
		this.user_Address = user_Address;
	}
	
	
	

}
