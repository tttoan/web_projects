package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomUnitsEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxSelect;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class LightboxAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String lightbox_10() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("10_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("10_events");
    	s.config.setDetailsOnCreate(true);
    	s.config.setDetailsOnDblClick(true);

    	// lightbox configuring
    	s.lightbox.get("description").setHeight(30);
    	DHXLightboxSelect sel = new DHXLightboxSelect("event_topic", "Topic");
    	sel.setServerList("topic");
    	s.lightbox.add(sel,1);

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Lightbox");
		messageStore.setSample_dsc("Lightbox is an editor of JavaPlanner's events. To open lightbox, double click on any cell in the JavaPlanner. Lighbox can contain any number of controls (by default, a text field for the event description and selects for setting the event duration).");
		return SUCCESS;
	}

	public String events_10() throws Exception {
		CustomUnitsEventsManager evs = new CustomUnitsEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}