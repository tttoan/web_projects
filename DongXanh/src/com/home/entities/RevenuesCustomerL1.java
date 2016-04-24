package com.home.entities;

import java.math.BigDecimal;
import java.util.List;

import com.home.model.Product;

public class RevenuesCustomerL1 {
	private int no;
	private String customerCodeL1;
	private String customerNameL1;
	private int totalCustomerL2;
	private String customerCodeL2;
	private BigDecimal totalRevenues;
	private long totalProduct;
	private List<Product> listProduct;
	
	public String getCustomerCodeL2() {
		return customerCodeL2;
	}
	public void setCustomerCodeL2(String customerCodeL2) {
		this.customerCodeL2 = customerCodeL2;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCustomerCodeL1() {
		return customerCodeL1;
	}
	public void setCustomerCodeL1(String customerCodeL1) {
		this.customerCodeL1 = customerCodeL1;
	}
	public String getCustomerNameL1() {
		return customerNameL1;
	}
	public void setCustomerNameL1(String customerNameL1) {
		this.customerNameL1 = customerNameL1;
	}
	public int getTotalCustomerL2() {
		return totalCustomerL2;
	}
	public void setTotalCustomerL2(int totalCustomerL2) {
		this.totalCustomerL2 = totalCustomerL2;
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
