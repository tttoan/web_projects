package com.home.model;

// Generated Feb 24, 2016 9:17:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Promotion generated by hbm2java
 */
public class Promotion implements java.io.Serializable {

	private Integer id;
	private GroupCustomer groupCustomer;
	private String promotionName;
	private Date startDate;
	private Date endDate;
	private String remarks;
	private Boolean status;
	private Integer group_customer_id;
	private Integer promotion_id;
	private Set<PromotionGift> promotionGifts = new HashSet<PromotionGift>(0);
	private Set<PromotionProduct> promotionProducts = new HashSet<PromotionProduct>(0);

	public Promotion() {
	}

	public Promotion(GroupCustomer groupCustomer, String promotionName, Date startDate, Date endDate, String remarks, Boolean status, Set<PromotionGift> promotionGifts,
			Set<PromotionProduct> promotionProducts) {
		this.groupCustomer = groupCustomer;
		this.promotionName = promotionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remarks = remarks;
		this.status = status;
		this.promotionGifts = promotionGifts;
		this.promotionProducts = promotionProducts;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GroupCustomer getGroupCustomer() {
		return this.groupCustomer;
	}

	public void setGroupCustomer(GroupCustomer groupCustomer) {
		this.groupCustomer = groupCustomer;
	}

	public String getPromotionName() {
		return this.promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<PromotionGift> getPromotionGifts() {
		return this.promotionGifts;
	}

	public void setPromotionGifts(Set<PromotionGift> promotionGifts) {
		this.promotionGifts = promotionGifts;
	}

	public Set<PromotionProduct> getPromotionProducts() {
		return this.promotionProducts;
	}

	public void setPromotionProducts(Set<PromotionProduct> promotionProducts) {
		this.promotionProducts = promotionProducts;
	}
	
	public Integer getGroup_customer_id() {
		return group_customer_id;
	}

	public void setGroup_customer_id(Integer group_customer_id) {
		this.group_customer_id = group_customer_id;
	}

	public Integer getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}
}
