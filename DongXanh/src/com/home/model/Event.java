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
	public int typeOfDay;
	public int warningType;
	public String contactAddress;

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public int getWarningType() {
		return warningType;
	}

	public void setWarningType(int warningType) {
		this.warningType = warningType;
	}
	public void setWarningType(String warningType) {
		if(warningType != null && !warningType.isEmpty()){
			this.warningType = Integer.parseInt(warningType);
		}
	}

	public int getTypeOfDay() {
		return typeOfDay;
	}

	public void setTypeOfDay(int typeOfDay) {
		this.typeOfDay = typeOfDay;
	}
	public void setTypeOfDay(String typeOfDay) {
		this.typeOfDay = Integer.parseInt(typeOfDay == "" ? "1"
				: typeOfDay);
	}
	
	public int getCustomerIdOld() {
		return customerIdOld;
	}

	public void setCustomerIdOld(int customerIdOld) {
		this.customerIdOld = customerIdOld;
	}

	public void setCustomerIdOld(String customerIdOld) {
		this.customerIdOld = Integer.parseInt(customerIdOld == "" ? "0"
				: customerIdOld);
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
			//e.printStackTrace();
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
		return "Event [contactType=" + contactType + ", typeOfDay=" + typeOfDay + " employeeId="
				+ employeeId + ", customerId=" + customerId
				+ ", customerIdOld=" + customerIdOld +", start_date=" + start_date + ", planDateOld="
				+ planDateOld + "]";
	}

}
