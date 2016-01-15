package org.apache.struts.demoapp_struts.action;

import java.util.Calendar;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXFilter;
import com.dhtmlx.planner.controls.DHXRule;
import com.dhtmlx.planner.controls.DHXRule.DHXOperator;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class FilteringAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String filtering_14() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("13_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("14_events");

    	DHXFilter filter = new DHXFilter("month");
    	filter.addRule(new DHXRule("start_date", DHXOperator.Greater, Calendar.getInstance()));
    	s.views.getView(0).setFilter(filter);

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Events filtering");
		messageStore.setSample_dsc("You may filter events by one or several criteria, using different filtering rules for different views. Filtering can be applied initially or invoked on some action.");
		return SUCCESS;
	}

	public String events_14() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}