package com.home.model;

// Generated Mar 9, 2016 8:44:07 PM by Hibernate Tools 4.3.1

/**
 * RegisterProduct generated by hbm2java
 */
public class RegisterProduct implements java.io.Serializable {

	private Integer id;
	private PromotionProduct promotionProduct;
	private PromotionRegister promotionRegister;
	private Integer point;
	private Integer box;
	private Integer product_id;

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public RegisterProduct() {
	}

	public RegisterProduct(PromotionProduct promotionProduct, PromotionRegister promotionRegister) {
		this.promotionProduct = promotionProduct;
		this.promotionRegister = promotionRegister;
	}

	public RegisterProduct(PromotionProduct promotionProduct, PromotionRegister promotionRegister, Integer point, Integer box) {
		this.promotionProduct = promotionProduct;
		this.promotionRegister = promotionRegister;
		this.point = point;
		this.box = box;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PromotionProduct getPromotionProduct() {
		return this.promotionProduct;
	}

	public void setPromotionProduct(PromotionProduct promotionProduct) {
		this.promotionProduct = promotionProduct;
	}

	public PromotionRegister getPromotionRegister() {
		return this.promotionRegister;
	}

	public void setPromotionRegister(PromotionRegister promotionRegister) {
		this.promotionRegister = promotionRegister;
	}

	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getBox() {
		return this.box;
	}

	public void setBox(Integer box) {
		this.box = box;
	}

}
