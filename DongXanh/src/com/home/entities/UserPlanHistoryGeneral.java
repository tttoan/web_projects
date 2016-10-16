package com.home.entities;

import java.util.ArrayList;
import java.util.List;

import com.home.action.UserPlanReportAction.WARNING;

public class UserPlanHistoryGeneral {
	private int no;
	private String user_name;
	private String nvtt;
	private String time;
	private int totalChangeTime;
	private int totalChangeCus;
	private List<WARNING> warnings = new ArrayList<WARNING>();
	
	public void addWarning(WARNING warning) {
		if(!warnings.contains(warning)){
			warnings.add(warning);
		}
	}
	public List<WARNING> getWarnings() {
		return warnings;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getNvtt() {
		return nvtt;
	}
	public void setNvtt(String nvtt) {
		this.nvtt = nvtt;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getTotalChangeTime() {
		return totalChangeTime;
	}
	public void setTotalChangeTime(int totalChangeTime) {
		this.totalChangeTime = totalChangeTime;
	}
	public int getTotalChangeCus() {
		return totalChangeCus;
	}
	public void setTotalChangeCus(int totalChangeCus) {
		this.totalChangeCus = totalChangeCus;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
