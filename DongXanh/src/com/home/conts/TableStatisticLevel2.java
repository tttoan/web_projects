package com.home.conts;

public enum TableStatisticLevel2 {
	month(0), 
	dateReceived(1), 
	customerCodeLevel2(2), 
	customerNameLevel2(3), 
	customerCodeLevel1(4),
	customerNameLevel1(5),
	productCode(6), 
	categoryName(7), 
	productName(8), 
	totalBox(9), 
	quantiy(10), 
	unitPrice(11), 
	total(12), 
	userFullName(13);
	
	private int value;
	private TableStatisticLevel2(int value){
		this.value = value;
	}
	public int value(){
		return this.value;
	}
}
