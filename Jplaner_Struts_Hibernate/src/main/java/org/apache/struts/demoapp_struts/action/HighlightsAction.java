package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.api.DHXBlockTime;
import com.dhtmlx.planner.api.DHXMarkTime;
import com.dhtmlx.planner.api.DHXZone;
import com.dhtmlx.planner.api.DHXTimeSpan.DHXDayOfWeek;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class HighlightsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String highlights_13() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("13_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("13_events");

    	// sets block on Sunday
    	DHXBlockTime block = new DHXBlockTime();
    	block.setDay(DHXDayOfWeek.SUNDAY);
    	s.timespans.add(block);

    	// sets timespan on today from 8 to 20
    	DHXMarkTime m1 = new DHXMarkTime();
    	m1.setStartDate(8, 0);
    	m1.setEndDate(20, 0);
    	m1.setCssClass("blue_section");
    	s.timespans.add(m1);

    	// sets timespan on Monday and Driday from 10 to 15
    	DHXMarkTime m2 = new DHXMarkTime();
    	m2.setDays(DHXDayOfWeek.MONDAY, DHXDayOfWeek.FRIDAY);
    	m2.addZone(new DHXZone(900, 1140));
    	m2.setCssClass("red_section");
    	s.timespans.add(m2);

    	// enables highlight
    	s.highlight.enable("highlight_section", 30);

    	// adding custom styles
    	String css = "<style>.blue_section { background-color: #2babf5; opacity: 0.27; filter:alpha(opacity=27); }</style>";
    	css += "<style>.red_section { background-color: red; opacity: 0.27; filter:alpha(opacity=27); }</style>";
    	css += "<style>.highlight_section { opacity: 0.25; z-index:0; filter:alpha(opacity=25); }  .highlight_section:hover { background-color: #90ee90;}</style>";

		messageStore.setScheduler(css + s.render());
		messageStore.setSample_name("Highlight");
		messageStore.setSample_dsc("You may highlight specific blocks in the JavaPlanner (periods of particular importance) with any CSS style. Also, there is the possibility to highlight the area under the mouse pointer.");
		return SUCCESS;
	}

	public String events_13() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}