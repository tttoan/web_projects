package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class SavingAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String saving_03() throws Exception {
		// creates and configures scheduler instance
		DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("03_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("03_events");

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Saving");
		messageStore.setSample_dsc("JavaPlanner provides simple integration with the server side. Add, edit, delete events and then, reload the page. All the changes will be saved.");
		return SUCCESS;
	}

	public String events_03() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}