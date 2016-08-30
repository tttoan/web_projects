package com.home.entities;

import java.sql.Date;

import com.home.util.StringUtil;

public class UserPlanGeneral {
	private String user_name;
	private String full_name;
	private String customer_code;
	private String business_name;
	private String statistic_name;
	private Date start_date;
	private Date end_date;
	private int phone;
	private int meet;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getBusiness_name() {
		if(StringUtil.notNull(business_name).isEmpty()){
			return statistic_name;
		}
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public String getStatistic_name() {
		if(StringUtil.notNull(statistic_name).isEmpty()){
			return business_name;
		}
		return statistic_name;
	}
	public void setStatistic_name(String statistic_name) {
		this.statistic_name = statistic_name;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getMeet() {
		return meet;
	}
	public void setMeet(int meet) {
		this.meet = meet;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
}