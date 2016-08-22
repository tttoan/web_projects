package com.home.action;

import java.sql.Date;

import com.home.dao.WorkingPlanHome;
import com.home.entities.UserAware;
import com.home.model.User;
import com.home.util.HibernateUtil;
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

	public static void main(String[] args) {
		try {
			WorkingPlanHome sttHome = new WorkingPlanHome(HibernateUtil.getSessionFactory());
			System.out.println(sttHome.getAllUserPlan4Report(-1, new Date(new java.util.Date().getTime()-12l*30*24*60*60*1000),  new Date(new java.util.Date().getTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
