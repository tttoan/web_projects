package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.opensymphony.xwork2.ActionSupport;

public class SimpleInitAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String simple_init_01() throws Exception {
		// creates and configures scheduler instance
		DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);

    	// sets events set
    	CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
    	s.parse(evs.getEvents());

    	messageStore = new MessageStore();
		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Simple init");
		messageStore.setSample_dsc("Basic JavaPlanner has 3 default views: Day, Week, Month. You can add, edit, delete events but when you reload the page all changes will be lost.");
		return SUCCESS;
	}
}