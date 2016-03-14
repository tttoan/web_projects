package com.home.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import antlr.collections.List;

public class PromotionCus {
	private String promotionName;
	private int customerId;
	private String customerCode;
	private String customerName;
	private String sellMan;
	private long totalProduct;
	private  long totalPass;
	private  long totalBox;
	private  long quality;
	private  long totalGift;
	private float percentPass;
	private boolean result;
	private int row_index;
	private int userId;
	private String productCode;
	private String categoryName;
	private String productName;
	private  long totaPoint;
	private BigDecimal totaPrice;
	private Set<Product> products = new HashSet<Product>(0);
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
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
	public String getSellMan() {
		return sellMan;
	}
	public void setSellMan(String sellMan) {
		this.sellMan = sellMan;
	}
	public long getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(long totalProduct) {
		this.totalProduct = totalProduct;
	}
	public long getTotalPass() {
		return totalPass;
	}
	public void setTotalPass(long totalPass) {
		this.totalPass = totalPass;
	}
	public long getTotalBox() {
		return totalBox;
	}
	public void setTotalBox(long totalBox) {
		this.totalBox = totalBox;
	}
	public long getQuality() {
		return quality;
	}
	public void setQuality(long quality) {
		this.quality = quality;
	}
	public long getTotalGift() {
		return totalGift;
	}
	public void setTotalGift(long totalGift) {
		this.totalGift = totalGift;
	}
	public float getPercentPass() {
		return percentPass;
	}
	public void setPercentPass(float percentPass) {
		this.percentPass = percentPass;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public int getRow_index() {
		return row_index;
	}
	public void setRow_index(int row_index) {
		this.row_index = row_index;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getTotaPoint() {
		return totaPoint;
	}
	public void setTotaPoint(long totaPoint) {
		this.totaPoint = totaPoint;
	}
	public BigDecimal getTotaPrice() {
		return totaPrice;
	}
	public void setTotaPrice(BigDecimal totaPrice) {
		this.totaPrice = totaPrice;
	}
	
	
}
