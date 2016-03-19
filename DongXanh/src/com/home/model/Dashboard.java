package com.home.model;

public class Dashboard implements java.io.Serializable {

	private int currentPromotion;
	private int featurePromotion;
	private int nextFinishPomotion;
	private int preFinishPromotion;
	private int totalPromotion;
	
	public int getCurrentPromotion() {
		return currentPromotion;
	}
	public void setCurrentPromotion(int currentPromotion) {
		this.currentPromotion = currentPromotion;
	}
	public int getFeaturePromotion() {
		return featurePromotion;
	}
	public void setFeaturePromotion(int featurePromotion) {
		this.featurePromotion = featurePromotion;
	}
	public int getNextFinishPomotion() {
		return nextFinishPomotion;
	}
	public void setNextFinishPomotion(int nextFinishPomotion) {
		this.nextFinishPomotion = nextFinishPomotion;
	}
	public int getPreFinishPromotion() {
		return preFinishPromotion;
	}
	public void setPreFinishPromotion(int preFinishPromotion) {
		this.preFinishPromotion = preFinishPromotion;
	}
	public int getTotalPromotion() {
		return totalPromotion;
	}
	public void setTotalPromotion(int totalPromotion) {
		this.totalPromotion = totalPromotion;
	}
}
