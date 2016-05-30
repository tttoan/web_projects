package com.home.entities;

public class DefineColumnImport {
	private String title;
	private String fieldEntName;
	private String index;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFieldEntName() {
		return fieldEntName;
	}
	public void setFieldEntName(String fieldEntName) {
		this.fieldEntName = fieldEntName;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public DefineColumnImport(String title, String fieldEntName, String index) {
		super();
		this.title = title;
		this.fieldEntName = fieldEntName;
		this.index = index;
	}
	
}
