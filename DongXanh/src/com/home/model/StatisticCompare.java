package com.home.model;

import java.math.BigDecimal;

public class StatisticCompare {
	private int productId;
	private Product product;
	private Long balance;
	private Long totalBox;
	private BigDecimal total;
	private Long totalBoxLevel2;
	private BigDecimal totalLevel2;
	private Long different;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Long getTotalBoxLevel2() {
		return totalBoxLevel2;
	}
	public void setTotalBoxLevel2(Long totalBoxLevel2) {
		this.totalBoxLevel2 = totalBoxLevel2;
	}
	public BigDecimal getTotalLevel2() {
		return totalLevel2;
	}
	public void setTotalLevel2(BigDecimal totalLevel2) {
		this.totalLevel2 = totalLevel2;
	}
	public Long getDifferent() {
		return different;
	}
	public void setDifferent(Long different) {
		this.different = different;
	}
	public Long getTotalBox() {
		return totalBox;
	}
	public void setTotalBox(Long totalBox) {
		this.totalBox = totalBox;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
