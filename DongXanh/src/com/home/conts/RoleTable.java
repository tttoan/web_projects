package com.home.conts;

public enum RoleTable {
	Admin(1), 
	Employee(2),
	Leader(3);
	private int value;
	private RoleTable(int value){
		this.value = value;
	}
	public int value(){
		return this.value;
	}
}
