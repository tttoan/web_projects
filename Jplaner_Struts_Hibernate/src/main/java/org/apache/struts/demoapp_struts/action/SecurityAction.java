package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.DHXStatus;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class SecurityAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String security_15() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("15_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("15_events");

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Security");
		messageStore.setSample_dsc("By default, any user can edit data in the scheduler and the related changes will be saved in the database. Built-in security manager will let you limit access to 'read', 'insert', 'update' and 'delete' operations for specific groups of users.");
		return SUCCESS;
	}

	public String events_15() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		evs.security.deny(DHXStatus.UPDATE);
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}