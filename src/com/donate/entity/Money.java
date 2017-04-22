package com.donate.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author 次此处写自己的姓名
 * 功能：金额实体类，与数据库中money表对应
 *
 */
@Entity
@Table(name="money",schema="donate_sys")
public class Money {
	private Integer id;
	private Integer mon_Number;   //捐献金额数
	private String user_Name;     //捐钱的用户名
	private String pro_Title;     //对应的活动标题
	private String do_Time;       //捐赠时间
	
	@Id
	@Column(name="id",nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Basic 
	@Column(name="mon_Number",nullable=false)
	public Integer getMon_Number() {
		return mon_Number;
	}
	public void setMon_Number(Integer mon_Number) {
		this.mon_Number = mon_Number;
	}
	
	@Basic 
	@Column(name="user_Name",nullable=false)
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	
	@Basic 
	@Column(name="pro_Title",nullable=false)
	public String getPro_Title() {
		return pro_Title;
	}
	public void setPro_Title(String pro_Title) {
		this.pro_Title = pro_Title;
	}
	
	@Basic 
	@Column(name="do_Time",nullable=false)
	public String getDo_Time() {
		return do_Time;
	}
	public void setDo_Time(String do_Time) {
		this.do_Time = do_Time;
	}
	
	
	

}
