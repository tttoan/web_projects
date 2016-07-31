package com.home.model;

public class MessageStore {

	private String scheduler;
	private String sample_name;
	private String sample_dsc;

	public String getScheduler() {
		return scheduler;
	}
	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	private String data;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public void setSample_name(String sample_name) {
		this.sample_name = sample_name;
	}
	public String getSample_name() {
		return sample_name;
	}
	
	public void setSample_dsc(String sample_dsc) {
		this.sample_dsc = sample_dsc;
	}
	public String getSample_dsc() {
		return sample_dsc;
	}
}