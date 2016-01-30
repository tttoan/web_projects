package com.home.model;

// Generated Jan 27, 2016 10:24:28 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * PromotionCustomer generated by hbm2java
 */
public class PromotionCustomer implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private Gift gift;
	private Promotion promotion;
	private Integer minQuantity;
	private Integer maxQuantity;
	private String note;
	private Set<PromotionCustomerProduct> promotionCustomerProducts = new HashSet<PromotionCustomerProduct>(0);

	public PromotionCustomer() {
	}

	public PromotionCustomer(Customer customer, Gift gift, Promotion promotion) {
		this.customer = customer;
		this.gift = gift;
		this.promotion = promotion;
	}

	public PromotionCustomer(Customer customer, Gift gift, Promotion promotion, Integer minQuantity, Integer maxQuantity, String note, Set<PromotionCustomerProduct> promotionCustomerProducts) {
		this.customer = customer;
		this.gift = gift;
		this.promotion = promotion;
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

	public Gift getGift() {
		return this.gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
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

	public void setPromotionCustomerProducts(Set<PromotionCustomerProduct> promotionCustomerProducts) {
		this.promotionCustomerProducts = promotionCustomerProducts;
	}

}
