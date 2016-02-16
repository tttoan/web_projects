package com.home.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;
import com.home.conts.RoleTable;
import com.home.dao.RoleHome;
import com.home.dao.UserHome;
import com.home.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction implements Action, ModelDriven<User>, ServletContextAware, ServletRequestAware {
	public User user = new User();
	public List<User> users = new ArrayList<>();
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

	public String listUser() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			users = userHome.getListUser();
		} catch (Exception e) {
			throw e;
		}
		return SUCCESS;
	}

	public String addUser() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			RoleHome roleHome = new RoleHome(getSessionFactory());
			user.setRole(roleHome.findRoleByName(RoleTable.Member.toString()));
			userHome.attachDirty(user);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

}
