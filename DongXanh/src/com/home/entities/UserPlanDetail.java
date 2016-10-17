package com.home.entities;

import com.dhtmlx.planner.DHXEvent;

public class UserPlanDetail extends DHXEvent{
	public String nvtt;
	public String dayPlan;
	public String contactTypeName;
	public String timelineTypeName;
	public String customerCode;
	public String customerName;
	public String customerAddress;
	
	public String getNvtt() {
		return nvtt;
	}
	public void setNvtt(String nvtt) {
		this.nvtt = nvtt;
	}
	public String getDayPlan() {
		return dayPlan;
	}
	public void setDayPlan(String dayPlan) {
		this.dayPlan = dayPlan;
	}
	public String getContactTypeName() {
		return contactTypeName;
	}
	public void setContactTypeName(String contactTypeName) {
		this.contactTypeName = contactTypeName;
	}
	public String getTimelineTypeName() {
		return timelineTypeName;
	}
	public void setTimelineTypeName(String timelineTypeName) {
		this.timelineTypeName = timelineTypeName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	
}
