package com.home.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.home.dao.CustomEventsManager;
import com.home.entities.UserAware;
import com.home.model.MessageStore;
import com.home.model.User;
import com.opensymphony.xwork2.Action;

public class UserPlanAction implements Action, UserAware {
	private MessageStore messageStore;
	private User userSes; 

	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public User getUserSes() {
		return userSes;
	}

	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}
	
	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}

	public String getUserPlan() throws Exception {
		try {
			// creates and configures scheduler instance
			DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.GLOSSY);
			s.setInitialDate(new Date());
			s.config.setScrollHour(8);
			s.setWidth(950);
			s.load("setUserPlanAction", DHXDataFormat.JSON);
			s.data.dataprocessor.setURL("setUserPlanAction");

			System.out.println(s.render());
			messageStore = new MessageStore();
			messageStore.setScheduler(s.render());
			messageStore.setSample_name("Saving");
			messageStore.setSample_dsc("tttoan ong noi day hahahaha");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String updateUserPlan() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return Action.SUCCESS;
	}
	

}
