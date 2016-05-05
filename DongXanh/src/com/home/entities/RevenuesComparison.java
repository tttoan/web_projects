package com.home.entities;

import java.math.BigDecimal;

public class RevenuesComparison {
	private int no;
	private String customerCode="";
	private String customerName="";
	private String customerLocation="";
	private String customerGroup="";
	private BigDecimal revenues1;
	private BigDecimal revenues2;
	private String decrease30="";
	private String increase30="";
	private String notBuy="";
	private String multiProvide="";
	private String provider="";
	private String sellMan="";
	private String comment="";
	
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
	public BigDecimal getRevenues1() {
		return revenues1;
	}
	public void setRevenues1(BigDecimal revenues1) {
		this.revenues1 = revenues1;
	}
	public BigDecimal getRevenues2() {
		return revenues2;
	}
	public void setRevenues2(BigDecimal revenues2) {
		this.revenues2 = revenues2;
	}
	public String getDecrease30() {
		return decrease30;
	}
	public void setDecrease30(String decrease30) {
		this.decrease30 = decrease30;
	}
	public String getIncrease30() {
		return increase30;
	}
	public void setIncrease30(String increase30) {
		this.increase30 = increase30;
	}
	public String getNotBuy() {
		return notBuy;
	}
	public void setNotBuy(String notBuy) {
		this.notBuy = notBuy;
	}
	public String getMultiProvide() {
		return multiProvide;
	}
	public void setMultiProvide(String multiProvide) {
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
