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
import com.home.model.Role;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements Action, ModelDriven<User>, ServletContextAware, ServletRequestAware {
	
	public int userId;
	public int roleId;
	public User user = new User();
	public List<User> listEmployee = new ArrayList<>();
	private List<Role> listRole = new ArrayList<>();
	public Role role = new Role();
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
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public void validate() {
		
	}	
	
	public String findAllRole() throws Exception {
		try {
			RoleHome roleHome = new RoleHome(getSessionFactory());
			listRole = roleHome.findAllRole();
		} catch (Exception e) {
			throw e;
		}
		return SUCCESS;
	}
	
	public String listEmployee() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			listEmployee = userHome.getListUser();
		} catch (Exception e) {
			throw e;
		}
		return SUCCESS;
	}

	public String addEmployee() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			Role rl = new Role();
			rl.setRoleId(getRoleId());
			user.setRole(rl);
			userHome.attachDirty(user);
			return SUCCESS;
		} catch (Exception e) {
			return INPUT;
		}
	}
	public String deleteEmployeeById() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			User user = userHome.findById(getUserId());
			userHome.delete(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public int getUserId() {
		return userId;
	}

	public List<Role> getListRole() {
		return listRole;
	}

	public Role getRole() {
		return role;
	}

	public int getRoleId() {
		return roleId;
	}
}
