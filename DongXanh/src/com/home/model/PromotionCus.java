package com.home.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
	private String resultString;
	private int row_index;
	private int userId;
	private String productCode;
	private String categoryName;
	private String productName;
	private  long totalPoint;
	private BigDecimal totaPrice;
	private Set<Product> products = new HashSet<Product>(0);
	private Set<PromotionGift> promotionGifts = new HashSet<PromotionGift>(0);
	
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	public Set<PromotionGift> getPromotionGifts() {
		return promotionGifts;
	}
	public void setPromotionGifts(Set<PromotionGift> promotionGifts) {
		this.promotionGifts = promotionGifts;
	}
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
	public long getTotaPoint(HashMap<Integer, Integer> mapProductPoint) {
		if(totalPoint > 0){
			return totalPoint;
		}
		else{
			int total_p = 0;
			for (Product product : products) {
				if(mapProductPoint.containsKey(product.getId())){
					total_p += (product.getMaxQuantity() * mapProductPoint.get(product.getId()));
				}
			}
			totalPoint = total_p;
			return totalPoint;
		}
	}
	public void setTotalPoint(long totaPoint) {
		this.totalPoint = totaPoint;
	}
	public BigDecimal getTotaPrice() {
		return totaPrice;
	}
	public void setTotaPrice(BigDecimal totaPrice) {
		this.totaPrice = totaPrice;
	}
	
	public Object[][] paramProducts(){
		Object[][]values = new Object[products.size()][3];
		int i = 0;
		for (Product product : products) {
			values[i++] = new Object[]{product.getProductCode(), product.getMaxQuantity()};
		}
		return values;
	}
	
}
