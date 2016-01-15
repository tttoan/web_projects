package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxMiniCalendar;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class MiniCalendarsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String minicalendars_12() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("12_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("12_events");

    	s.calendars.attachMiniCalendar();
    	s.lightbox.add(new DHXLightboxMiniCalendar("cal", "Time period"));

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Mini calendars");
		messageStore.setSample_dsc("To simplify navigation, you can display a small date picker on the left side of the JavaPlanner header. Also, you can put a date picker in the lightbox or any HTML container outside the JavaPlanner.");
		return SUCCESS;
	}

	public String events_12() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}