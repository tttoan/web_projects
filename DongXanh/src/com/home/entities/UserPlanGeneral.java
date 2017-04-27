package com.home.entities;

import java.sql.Date;

import com.dhtmlx.planner.DHXEvent;
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
	private String telefone;
	private String address;
	private int contact_type;
	private String contact_address;
	
	public String getContact_address() {
		return contact_address;
	}

	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}

	public int getContact_type() {
		return contact_type;
	}
	
	public String getContactType() {
		switch (contact_type) {
		case 1:
			return "Gặp trực tiếp";
		case 2:
			return "Gọi điện thoại";
		case 3:
			return "Họp online";
		case 4:
			return "Họp trực tiếp";
		case 5:
			return "Công tác";
		default:
			return "";
		}
	}
	
	public void setContact_type(int contact_type) {
		this.contact_type = contact_type;
	}
	public static void main(String[] args) {
		UserPlanGeneral u = new UserPlanGeneral();
		u.setFull_name("Tran Thien TOan");
		System.out.println(u.getNVTT());
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getFull_name() {
		return full_name;
	}
	public String getNVTT(){
		if(StringUtil.notNull(full_name).length() > 0){
			String arr[] = full_name.split(" ");
			if(arr.length > 0){
				String nvtt = "";
				for (int i = 0; i < arr.length; i++) {
					if(i == arr.length-1){
						nvtt += arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1).toLowerCase();
					}else{
						nvtt += arr[i].substring(0, 1).toUpperCase();
					}
				}
				return nvtt;
			}
		}
		return getUser_name();
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
		if(StringUtil.notNull(business_name).replace("0.0", "").isEmpty()){
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
