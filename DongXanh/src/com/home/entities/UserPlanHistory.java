package com.home.entities;

import java.sql.Date;

import com.home.util.StringUtil;

public class UserPlanHistory {
	private int no;
	private int event_id;
	private String event_name;
	private Date event_date;
	private String nvtt;
	private String full_name;
	private Date plan_date_old;
	private Date plan_date_new;
	private String customer_old;
	private String customer_new;
	private String contact_type_old;
	private String contact_type_new;
	private String action;
	private Date last_modified;
	
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getNvtt() {
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
		return nvtt;
	}
	public void setNvtt(String nvtt) {
		this.nvtt = nvtt;
	}
	public String getUserName() {
		return this.nvtt;
	}
	public Date getPlan_date_old() {
		return plan_date_old;
	}
	public void setPlan_date_old(Date plan_date_old) {
		this.plan_date_old = plan_date_old;
	}
	public Date getPlan_date_new() {
		return plan_date_new;
	}
	public void setPlan_date_new(Date plan_date_new) {
		this.plan_date_new = plan_date_new;
	}
	public String getCustomer_old() {
		return customer_old;
	}
	public void setCustomer_old(String customer_old) {
		this.customer_old = customer_old;
	}
	public String getCustomer_new() {
		return customer_new;
	}
	public void setCustomer_new(String customer_new) {
		this.customer_new = customer_new;
	}
	public String getContact_type_old() {
		return contact_type_old;
	}
	public void setContact_type_old(String contact_type_old) {
		this.contact_type_old = contact_type_old;
	}
	public String getContact_type_new() {
		return contact_type_new;
	}
	public void setContact_type_new(String contact_type_new) {
		this.contact_type_new = contact_type_new;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	
}
