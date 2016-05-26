package com.home.action;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssignCustomerAction extends ActionSupport implements Action, ServletContextAware {

	private ServletContext ctx;

	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}


}
