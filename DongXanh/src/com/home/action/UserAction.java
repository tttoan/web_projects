package com.home.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.UserHome;
import com.home.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction implements Action, ModelDriven<User>, ServletContextAware, ServletRequestAware {
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

	@Override
	public String execute() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		UserHome userHome = new UserHome(sf);
		User userDB = userHome.getUserByCredentials(user.getUserName(), user.getPassword());
		if (userDB == null)
			return ERROR;
		else {
			user.setEmail(userDB.getEmail());
			user.setUserName(userDB.getUserName());
			return SUCCESS;
		}
	}

	public String addUser() throws Exception {
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			UserHome userHome = new UserHome(sf);
			userHome.attachDirty(user);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	

}
