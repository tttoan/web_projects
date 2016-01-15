package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomUnitsEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxSelect;
import com.dhtmlx.planner.controls.DHXTimelineView;
import com.dhtmlx.planner.controls.DHXTimelineView.RenderModes;
import com.dhtmlx.planner.controls.DHXTimelineView.XScaleUnits;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class TimelineAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String timeline_09() throws Exception {
		// creates and configures scheduler instance
		DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.setWidth(900);
    	s.setInitialView("topic");
    	s.load("09_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("09_events");

    	DHXTimelineView view = new DHXTimelineView("topic", "event_topic", "Topic");
    	view.setRenderMode(RenderModes.BAR);
    	view.setXScaleUnit(XScaleUnits.MINUTE);
    	view.setXStep(30);
    	view.setXSize(10);
    	view.setXStart(16);
    	view.setXLength(48);
    	view.setServerList("topic");
    	s.views.add(view);

    	DHXLightboxSelect sel = new DHXLightboxSelect("event_topic", "Topic");
    	sel.setServerList("topic");
    	s.lightbox.add(sel);

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Timeline view");
		messageStore.setSample_dsc("Timeline View can be used to visualize and monitor the progress of ongoing tasks or projects. The vertical scale in the view presents event holders, while the horizontal scale is a configurable time-scale. There is the possibility to enable the hierarchical structure and show the resources in expandable groups.");
		return SUCCESS;
	}

	public String events_09() throws Exception {
		CustomUnitsEventsManager evs = new CustomUnitsEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}