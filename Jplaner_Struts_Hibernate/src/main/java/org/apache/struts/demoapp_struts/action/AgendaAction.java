package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXAgendaView;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;
import com.opensymphony.xwork2.ActionSupport;

public class AgendaAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String agenda_05() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setWidth(900);
    	s.setInitialDate(2013, 1, 7);
    	s.load("05_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("05_events");
    	s.extensions.add(DHXExtension.RECURRING);
    	s.views.add(new DHXAgendaView());
    	s.setInitialView("agenda");
    	DHXAgendaView agenda = (DHXAgendaView) s.views.getView(3);
    	agenda.setStartDate(2012, 11, 20);
    	agenda.setEndDate(2013, 02, 01);

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Agenda view");
		messageStore.setSample_dsc("Agenda view presents a list of ongoing and upcoming events in the order that they are to be taken place. The view contains 2 columns: one for the event date and the other for description.");
		return SUCCESS;
	}

	public String events_05() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}