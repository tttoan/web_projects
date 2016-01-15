package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomUnitsEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxSelect;
import com.dhtmlx.planner.controls.DHXUnitsView;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class UnitsViewAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String units_08() throws Exception {
		// creates and configures scheduler instance
		DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("08_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("08_events");
    	s.setInitialView("topic");

    	// creates units view
    	DHXUnitsView view = new DHXUnitsView("topic", "event_topic", "Topic");
    	view.setServerListLink("topic");
    	s.views.add(view);

    	// adds section in lightbox
    	DHXLightboxSelect sel = new DHXLightboxSelect("event_topic", "Topic");
    	sel.setServerList("topic");
    	s.lightbox.add(sel);

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Units view");
		messageStore.setSample_dsc("Units View lets you display several calendars for multiple resources simultaneously (e.g. human resources, rooms, etc.). The calendars are arranged in columns, so you can see all of them on one page.");
		return SUCCESS;
	}

	public String events_08() throws Exception {
		CustomUnitsEventsManager evs = new CustomUnitsEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}