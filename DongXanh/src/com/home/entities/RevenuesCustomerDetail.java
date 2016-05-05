package com.home.entities;

import java.math.BigDecimal;
import java.util.List;

import com.home.model.Product;
import com.home.util.SystemUtil;

public class RevenuesCustomerDetail {
	private int no;
	private String customerCode;
	private String customerName;
	private String customerLocation;
	private String sellMan;
	private String create_time;
	private String provider;
	private long totalProduct;
	private BigDecimal revenues;
	private List<Product> listProduct;
	
	public BigDecimal getRevenues() {
		return revenues;
	}
	public void setRevenues(BigDecimal revenues) {
		this.revenues = revenues;
	}
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
	public String getSellMan() {
		return sellMan;
	}
	public void setSellMan(String sellMan) {
		this.sellMan = sellMan;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
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
