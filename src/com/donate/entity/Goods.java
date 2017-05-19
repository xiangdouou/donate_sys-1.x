package com.donate.entity;

import java.io.Serializable;

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
 *功能：物品实体类，与数据库中goods表对应
 */
@Entity
@Table(name="goods",schema="donate_sys")
public class Goods implements Serializable {
	private Integer id;
	private String go_Name;    //物品名
	private Integer go_Number;  //数量
	private String user_Name;  //对应用户名
	private String pro_Title;  //对应活动标题
	private String do_Time;    //捐赠时间
	private String do_actual;  //实际捐助的物品
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Basic
	@Column(name="go_Name",nullable=false)
	public String getGo_Name() {
		return go_Name;
	}
	public void setGo_Name(String go_Name) {
		this.go_Name = go_Name;
	}
	
	@Basic
	@Column(name="go_Number",nullable=false)
	public Integer getGo_Number() {
		return go_Number;
	}
	public void setGo_Number(Integer go_Number) {
		this.go_Number = go_Number;
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
	
	@Basic
	@Column(name="do_actual",nullable=false)
	public String getDo_actual() {
		return do_actual;
	}
	public void setDo_actual(String do_actual) {
		this.do_actual = do_actual;
	}
	
	
	
	

}
