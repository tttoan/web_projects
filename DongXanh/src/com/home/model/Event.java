package com.home.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;

public class Event extends DHXEvent {
	public String contactType;
	public int employeeId;
	public int customerId;
	public int customerIdOld;
	public Date planDateOld;

	public int getCustomerIdOld() {
		return customerIdOld;
	}

	public void setCustomerIdOld(int customerIdOld) {
		this.customerIdOld = customerIdOld;
	}

	public void setCustomerIdOld(String customerIdOld) {
		this.customerIdOld = Integer.valueOf(Integer.parseInt(customerIdOld));
	}

	public Date getPlanDateOld() {
		return planDateOld;
	}

	public void setPlanDateOld(Date planDateOld) {
		this.planDateOld = planDateOld;
	}

	public void setPlanDateOld(String planDateOld) {
		if (planDateOld.length() == 16)
			planDateOld = planDateOld + ":00";
		try {
			this.planDateOld = new SimpleDateFormat(
					DHXEventsManager.date_format).parse(planDateOld);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

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
		this.employeeId = Integer.parseInt(employeeId == null ? "0"
				: employeeId);
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = Integer.parseInt(customerId == null ? "0"
				: customerId);
	}

	@Override
	public String toString() {
		return "Event [contactType=" + contactType + ", employeeId="
				+ employeeId + ", customerId=" + customerId
				+ ", customerIdOld=" + customerIdOld +", start_date=" + start_date + ", planDateOld="
				+ planDateOld + "]";
	}

}
