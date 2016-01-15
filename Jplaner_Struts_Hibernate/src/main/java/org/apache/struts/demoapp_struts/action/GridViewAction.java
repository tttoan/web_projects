package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXGridView;
import com.dhtmlx.planner.controls.DHXGridViewColumn;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;
import com.opensymphony.xwork2.ActionSupport;

public class GridViewAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String grid_07() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.setWidth(900);
    	s.load("07_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("07_events");
    	s.extensions.add(DHXExtension.RECURRING);
    	DHXGridView view = new DHXGridView();
    	view.addOption(new DHXGridViewColumn("text", "Text"));
    	view.addOption(new DHXGridViewColumn("start_date", "Start date", 150));
    	view.addOption(new DHXGridViewColumn("end_date", "End date", 150));
    	view.setFrom(2013, 1, 1);
    	s.views.add(view);
    	s.setInitialView("grid");

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Grid view");
		messageStore.setSample_dsc("Grid view presents a list of upcoming events and unlike the Agenda view, it allows you to configure the number of columns and choose what data to display. It also supports sorting (including custom) and data templates.");
		return SUCCESS;
	}

	public String events_07() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}