package com.donate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="project",schema="donate_sys")
public class Project implements Serializable{
	private Integer id;
	private String pro_Title;
	private String pro_Des;
	private Integer pro_Type;  //捐款类型（1：募捐钱/2：募捐物品）
	private String pro_StartTime;
	private String pro_EndTime;
	private String pro_Status;
	private String pro_Sponsor;
	private Integer pro_TargetNumber;
	private Integer pro_CurNumber;
	private Integer pro_CurPeoples;
	
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
	@Column(name="pro_Title",nullable=false)
	public String getPro_Title() {
		return pro_Title;
	}
	public void setPro_Title(String pro_Title) {
		this.pro_Title = pro_Title;
	}
	
	@Basic
	@Column(name="pro_Des",nullable=false)
	public String getPro_Des() {
		return pro_Des;
	}
	public void setPro_Des(String pro_Des) {
		this.pro_Des = pro_Des;
	}
	
	@Basic
	@Column(name="pro_StartTime",nullable=false)
	public String getPro_StartTime() {
		return pro_StartTime;
	}
	public void setPro_StartTime(String pro_StartTime) {
		this.pro_StartTime = pro_StartTime;
	}
	
	@Basic
	@Column(name="pro_EndTime",nullable=true)
	public String getPro_EndTime() {
		return pro_EndTime;
	}
	public void setPro_EndTime(String pro_EndTime) {
		this.pro_EndTime = pro_EndTime;
	}
	
	@Basic
	@Column(name="pro_Status",nullable=false)
	public String getPro_Status() {
		return pro_Status;
	}
	public void setPro_Status(String pro_Status) {
		this.pro_Status = pro_Status;
	}
	
	@Basic
	@Column(name="pro_Type",nullable=false)
	public Integer getPro_Type() {
		return pro_Type;
	}
	public void setPro_Type(Integer pro_Type) {
		this.pro_Type = pro_Type;
	}
	
	@Basic
	@Column(name="pro_Sponsor",nullable=false)
	public String getPro_Sponsor() {
		return pro_Sponsor;
	}
	public void setPro_Sponsor(String pro_Sponsor) {
		this.pro_Sponsor = pro_Sponsor;
	}
	
	@Basic
	@Column(name="pro_TargetNumber",nullable=false)
	public Integer getPro_TargetNumber() {
		return pro_TargetNumber;
	}
	public void setPro_TargetNumber(Integer pro_TargetNumber) {
		this.pro_TargetNumber = pro_TargetNumber;
	}
	@Basic
	@Column(name="pro_CurNumber")
	public Integer getPro_CurNumber() {
		return pro_CurNumber;
	}
	public void setPro_CurNumber(Integer pro_CurNumber) {
		this.pro_CurNumber = pro_CurNumber;
	}
	
	
	@Basic
	@Column(name="pro_CurPeoples")
	public Integer getPro_CurPeoples() {
		return pro_CurPeoples;
	}
	public void setPro_CurPeoples(Integer pro_CurPeoples) {
		this.pro_CurPeoples = pro_CurPeoples;
	}
	
	
	
	
	

}
