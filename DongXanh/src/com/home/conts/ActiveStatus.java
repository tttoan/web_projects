package com.home.conts;

public enum ActiveStatus {
	Inactive(0), Active(1);
	public int val;
	ActiveStatus(int val){
		this.val = val;
	}
	int Key(){
		return val;
	}
}
