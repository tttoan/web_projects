package com.home.model;



import com.dhtmlx.planner.DHXEvent;

public class Event extends DHXEvent {
	public String contactType;
	public int employeeId;
	public int customerId;
	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = Integer.parseInt(employeeId==null?"0":employeeId);
	}
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = Integer.parseInt(customerId==null?"0":customerId);
	}

	@Override
	public String toString() {
		return "Event [contactType=" + contactType + ", employeeId=" + employeeId + ", customerId=" + customerId + "]";
	}
	
}
