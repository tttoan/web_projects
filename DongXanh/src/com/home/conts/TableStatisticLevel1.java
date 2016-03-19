package com.home.conts;

public enum TableStatisticLevel1 {
	dateReceived(1), 
	customerCodeLevel1(1),
	productCode(6),
	productName(7),
	unitPrice(11), 
	quantiy(12), 
	total(14);
	private int value;
	private TableStatisticLevel1(int value){
		this.value = value;
	}
	public int value(){
		return this.value;
	}
}