package com.home.model;

// Generated Mar 9, 2016 8:44:07 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * PromotionRegister generated by hbm2java
 */
public class PromotionRegister implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private Integer totalPoint;
	private Integer totalBox;
	private Set<RegisterGift> registerGifts = new HashSet<RegisterGift>(0);
	private Set<RegisterProduct> registerProducts = new HashSet<RegisterProduct>(0);

	public PromotionRegister() {
	}

	public PromotionRegister(Customer customer) {
		this.customer = customer;
	}

	public PromotionRegister(Customer customer, Integer totalPoint, Integer totalBox, Set<RegisterGift> registerGifts, Set<RegisterProduct> registerProducts) {
		this.customer = customer;
		this.totalPoint = totalPoint;
		this.totalBox = totalBox;
		this.registerGifts = registerGifts;
		this.registerProducts = registerProducts;
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

	public Integer getTotalPoint() {
		return this.totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getTotalBox() {
		return this.totalBox;
	}

	public void setTotalBox(Integer totalBox) {
		this.totalBox = totalBox;
	}

	public Set<RegisterGift> getRegisterGifts() {
		return this.registerGifts;
	}

	public void setRegisterGifts(Set<RegisterGift> registerGifts) {
		this.registerGifts = registerGifts;
	}

	public Set<RegisterProduct> getRegisterProducts() {
		return this.registerProducts;
	}

	public void setRegisterProducts(Set<RegisterProduct> registerProducts) {
		this.registerProducts = registerProducts;
	}

}
