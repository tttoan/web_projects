package com.home.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.UserHome;
import com.home.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction implements Action, ModelDriven<User>, ServletContextAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private HttpServletRequest request;
	private ServletContext ctx;

	@Override
	public User getModel() {
		return user;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}
	public SessionFactory getSessionFactory(){
		return (SessionFactory) ctx.getAttribute("SessionFactory");
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String addUser() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			userHome.attachDirty(user);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	

}
