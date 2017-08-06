package com.home.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticHistory implements Serializable{
	private int import_date;
	private BigDecimal total;
	private int quantity;
	private float total_box;
	private int product_id;
	private String product_name;
	private BigDecimal unit_price;
	
	public float getTotal_box() {
		return total_box;
	}
	public void setTotal_box(float total_box) {
		this.total_box = total_box;
	}
	public int getImport_date() {
		return import_date;
	}
	public void setImport_date(int import_date) {
		this.import_date = import_date;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public BigDecimal getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}
}
