package com.home.action;

import com.home.entities.UserAware;
import com.home.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserPlanReportAction extends ActionSupport implements UserAware {
	private User userSes;
	
	public User getUserSes() {
		return userSes;
	}

	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}

	
	public String getPlanStatistic(){
		return SUCCESS;
	}

	public String getPlanGeneral(){
		return SUCCESS;
	}

	public String getPlanDetail(){
		return SUCCESS;
	}
}
