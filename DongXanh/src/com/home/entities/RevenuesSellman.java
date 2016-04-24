package com.home.entities;

import java.math.BigDecimal;
import java.util.List;

import com.home.model.Product;

public class RevenuesSellman {
	private int no;
	private String sellman;
	private int totalCustomer;
	private BigDecimal totalRevenues;
	private long totalProduct;
	private List<Product> listProduct;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSellman() {
		return sellman;
	}
	public void setSellman(String sellman) {
		this.sellman = sellman;
	}
	public int getTotalCustomer() {
		return totalCustomer;
	}
	public void setTotalCustomer(int totalCustomer) {
		this.totalCustomer = totalCustomer;
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
