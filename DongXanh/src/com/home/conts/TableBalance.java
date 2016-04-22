package com.home.conts;

public enum TableBalance {
	dateReceived(0), 
	customerCodeLevel1(1),
	productCode(3),
	totalBox(5);
	private int value;
	private TableBalance(int value){
		this.value = value;
	}
	public int value(){
		return this.value;
	}
}
