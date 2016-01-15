package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomMapEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxText;
import com.dhtmlx.planner.controls.DHXMapView;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;
import com.opensymphony.xwork2.ActionSupport;

public class MapAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String map_06() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.xy.setMapDateWidth(160);
    	s.xy.setMapDescriptionWidth(300);
    	s.load("06_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("06_events");
    	s.extensions.add(DHXExtension.RECURRING);
    	s.views.add(new DHXMapView());
    	DHXMapView map = (DHXMapView) s.views.getView(3);
    	map.setStartDate(2013, 1, 1);
    	s.setInitialView("map");

    	// adds section in lightbox
    	DHXLightboxText loc = new DHXLightboxText("event_location", "Location");
    	loc.setHeight(40);
    	s.lightbox.add(loc);

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Map view");
		messageStore.setSample_dsc("Map view integrates Agenda view with Google Maps that allows you to display locations associated with the calendar events on the map. You can specify the event location in 2 ways: to specify the address in the event description or to set the point right on Google Maps.");
		return SUCCESS;
	}

	public String events_06() throws Exception {
		CustomMapEventsManager evs = new CustomMapEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}