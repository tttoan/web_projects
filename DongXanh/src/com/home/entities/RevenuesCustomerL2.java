package com.home.entities;

import java.math.BigDecimal;
import java.util.List;

import com.home.model.Product;

public class RevenuesCustomerL2 {
	private int no;
	private String customerCodeL2;
	private String customerNameL2;
	private String customerNameL1;
	private String sellman;
	private BigDecimal totalRevenues;
	private long totalProduct;
	private List<Product> listProduct;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCustomerCodeL2() {
		return customerCodeL2;
	}
	public void setCustomerCodeL2(String customerCodeL2) {
		this.customerCodeL2 = customerCodeL2;
	}
	public String getCustomerNameL2() {
		return customerNameL2;
	}
	public void setCustomerNameL2(String customerNameL2) {
		this.customerNameL2 = customerNameL2;
	}
	public String getCustomerNameL1() {
		return customerNameL1;
	}
	public void setCustomerNameL1(String customerNameL1) {
		this.customerNameL1 = customerNameL1;
	}
	public String getSellman() {
		return sellman;
	}
	public void setSellman(String sellman) {
		this.sellman = sellman;
	}
	public BigDecimal getTotalRevenues() {
		return totalRevenues;
	}
	public void setTotalRevenues(BigDecimal totalRevenues) {
		this.totalRevenues = totalRevenues;
	}
	public long getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(long totalProduct) {
		this.totalProduct = totalProduct;
	}
	public List<Product> getListProduct() {
		return listProduct;
	}
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
}
