package org.apache.struts.demoapp_struts.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class ExportAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String export_16() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("16_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("16_events");
    	s.toPDF();
    	s.toICal("16_ical");

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Export to PDF and iCal");
		messageStore.setSample_dsc("To provide printing capabilities, scheduler supports export to a PDF document. Also, there is support for data export to the iCal format that allows you to share the JavaPlanner data across different apps and devices.");
		return SUCCESS;
	}

	public String events_16() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
	
	private InputStream ical;
	public InputStream getIcal() {
		return ical;
	}

	public String ical_16() throws Exception {
		String data = ServletActionContext.getRequest().getParameter("ical");
		byte[] bytes = data.getBytes("UTF-8");
		ical = new ByteArrayInputStream(bytes);
		return SUCCESS;
	}
}