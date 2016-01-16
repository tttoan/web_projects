package com.home.model;

// Generated Jan 12, 2016 11:20:58 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * PromotionCustomer generated by hbm2java
 */
public class PromotionCustomer implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private Promotion promotion;
	private Gift gift;
	private Integer minQuantity;
	private Integer maxQuantity;
	private String note;
	private Set<PromotionCustomerProduct> promotionCustomerProducts = new HashSet<PromotionCustomerProduct>(
			0);

	public PromotionCustomer() {
	}

	public PromotionCustomer(Customer customer, Promotion promotion, Gift gift) {
		this.customer = customer;
		this.promotion = promotion;
		this.gift = gift;
	}

	public PromotionCustomer(Customer customer, Promotion promotion, Gift gift,
			Integer minQuantity, Integer maxQuantity, String note,
			Set<PromotionCustomerProduct> promotionCustomerProducts) {
		this.customer = customer;
		this.promotion = promotion;
		this.gift = gift;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
		this.note = note;
		this.promotionCustomerProducts = promotionCustomerProducts;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Gift getGift() {
		return this.gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public Integer getMinQuantity() {
		return this.minQuantity;
	}

	public void setMinQuantity(Integer minQuantity) {
		this.minQuantity = minQuantity;
	}

	public Integer getMaxQuantity() {
		return this.maxQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<PromotionCustomerProduct> getPromotionCustomerProducts() {
		return this.promotionCustomerProducts;
	}

	public void setPromotionCustomerProducts(
			Set<PromotionCustomerProduct> promotionCustomerProducts) {
		this.promotionCustomerProducts = promotionCustomerProducts;
	}

}
