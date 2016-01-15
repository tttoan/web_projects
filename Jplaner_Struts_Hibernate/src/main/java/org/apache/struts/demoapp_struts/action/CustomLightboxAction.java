package org.apache.struts.demoapp_struts.action;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.MessageStore;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXExternalLightboxForm;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.opensymphony.xwork2.ActionSupport;

public class CustomLightboxAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MessageStore messageStore = new MessageStore();
	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String custom_lightbox_11() throws Exception {
		// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setWidth(900);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.config.setDetailsOnCreate(true);
    	s.config.setDblClickCreate(true);
    	s.config.setDetailsOnDblClick(true);

    	s.load("11_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("11_events");

    	DHXExternalLightboxForm box = s.lightbox.setExternalLightboxForm("./11_custom_editor.action", 296, 140);    
    	box.setClassName("custom_lightbox");

		messageStore.setScheduler(s.render());
		messageStore.setSample_name("Custom lightbox");
		messageStore.setSample_dsc("There is the possibility to create a fully custom lightbox. Double click on any empty cell or event in the JavaPlanner and open lightbox that is fully custom and contains regular HTML inputs.");
		return SUCCESS;
	}

	public String editor_11() throws Exception {
		return SUCCESS;
	}

	public String events_11() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
		messageStore.setData(evs.run());
		return SUCCESS;
	}
}