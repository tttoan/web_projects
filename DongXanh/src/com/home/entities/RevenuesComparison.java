package com.home.entities;

public class RevenuesComparison {
	private int no;
	private String customerCode;
	private String customerName;
	private String customerLocation;
	private String customerGroup;
	private float revenues1;
	private float revenues2;
	private boolean decrease30;
	private boolean increase30;
	private boolean notBuy;
	private boolean multiProvide;
	private String provider;
	private String sellMan;
	private String comment;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getCustomerLocation() {
		return customerLocation;
	}
	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}
	public String getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	public float getRevenues1() {
		return revenues1;
	}
	public void setRevenues1(float revenues1) {
		this.revenues1 = revenues1;
	}
	public float getRevenues2() {
		return revenues2;
	}
	public void setRevenues2(float revenues2) {
		this.revenues2 = revenues2;
	}
	public boolean isDecrease30() {
		return decrease30;
	}
	public void setDecrease30(boolean decrease30) {
		this.decrease30 = decrease30;
	}
	public boolean isIncrease30() {
		return increase30;
	}
	public void setIncrease30(boolean increase30) {
		this.increase30 = increase30;
	}
	public boolean isNotBuy() {
		return notBuy;
	}
	public void setNotBuy(boolean notBuy) {
		this.notBuy = notBuy;
	}
	public boolean isMultiProvide() {
		return multiProvide;
	}
	public void setMultiProvide(boolean multiProvide) {
		this.multiProvide = multiProvide;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getSellMan() {
		return sellMan;
	}
	public void setSellMan(String sellMan) {
		this.sellMan = sellMan;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
