package com.home.entities;

public class DefineColumnImport {
	private String title;
	private String fieldEntName;
	private String indexColumn;
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
	public String getIndexColumn() {
		return indexColumn;
	}
	public void setIndexColumn(String indexColumn) {
		this.indexColumn = indexColumn;
	}
	public DefineColumnImport(String title, String fieldEntName, String indexColumn) {
		super();
		this.title = title;
		this.fieldEntName = fieldEntName;
		this.indexColumn = indexColumn;
	}
	
}
