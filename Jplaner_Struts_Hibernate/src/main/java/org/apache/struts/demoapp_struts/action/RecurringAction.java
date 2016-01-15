package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomRecEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;
import com.opensymphony.xwork2.ActionSupport;

public class RecurringAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String recurring_04() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.config.setFullDay(true);
    	s.config.setMultiDay(true);
    	s.setWidth(900);
    	s.load("04_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("04_events");
    	s.extensions.add(DHXExtension.RECURRING);

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Recurring events");
		messageStore.setSample_dsc("Recurring events are events that repeat in series, for example, the birthday of your friend, a monthly salary or a weekly staff meeting. Create a recurring event and try to edit it: you may change all occurrences in the series at once or edit just a single instance.");
		return SUCCESS;
	}

	public String events_04() throws Exception {
		CustomRecEventsManager evs = new CustomRecEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}