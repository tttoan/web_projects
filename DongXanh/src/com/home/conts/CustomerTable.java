package com.home.conts;

public enum CustomerTable {
	customerCode(0), 
	businessName(1), 
	customerGroup(2), 
	certificateNumber(3), 
	certificateDate(4),
	certificateAddress(5),
	taxNumber(6), 
	employee(7);
	
	private int value;
	private CustomerTable(int value){
		this.value = value;
	}
	public int value(){
		return this.value;
	}
}
