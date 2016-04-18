package com.home.action;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;

public class ReportRevenuesAction  implements Action, ServletContextAware{
	private ServletContext ctx;
	
	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}

	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}

}
