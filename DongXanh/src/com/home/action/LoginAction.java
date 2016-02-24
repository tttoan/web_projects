package com.home.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.UserHome;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements Action, ModelDriven<User>, ServletContextAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	public User user = new User();
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

	public SessionFactory getSessionFactory() {
		return (SessionFactory) ctx.getAttribute("SessionFactory");
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;

	}

	@Override
	public void validate() {
		UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());
		User userDB = userHome.getUserByCredentials(user.getUserName(), user.getPassword());
		if (userDB == null) {
			addActionError("Username or password is not valid");
		} else {
			user = userDB;
		}
	};

}
