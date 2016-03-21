package com.home.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.RoleHome;
import com.home.dao.UserHome;
import com.home.model.Role;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements Action, ModelDriven<User>, ServletContextAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	public int userId;
	public int roleId;
	public User user = new User();
	public Role role = new Role();
	public List<User> listEmployee = new ArrayList<>();
	private List<Role> listRole = new ArrayList<>();
	private HttpServletRequest request;
	private ServletContext ctx;

	@Override
	public User getModel() {
		user = new User();
		user.setBirthDate(new Date());
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
		this.setCtx(sc);
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public String execute() throws Exception {
		if (userId != 0) {
			try {
				UserHome userHome = new UserHome(getSessionFactory());
				user = userHome.findById(userId);
				setEdit(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		try {
			findAllRole();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String findAllRole() throws Exception {
		try {
			RoleHome roleHome = new RoleHome(getSessionFactory());
			listRole = roleHome.findAllRole();
			return SUCCESS;
		} catch (Exception e) {
			return INPUT;
		}

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
			if(!edit){
				boolean exist = userHome.checkUsernameExist(user.getUserName());
				if (exist) {
					addActionError("Username already exists");
					return INPUT;
				}
			}
			Role rl = new Role();
			rl.setRoleId(getRoleId());
			user.setRole(rl);
			if(user.getId() == 0)
				userHome.attachDirty(user);
			else
				userHome.updateDirty(user);
			return SUCCESS;
		} catch (Exception e) {
			addActionError(e.getMessage());
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

	public ServletContext getCtx() {
		return ctx;
	}

	public void setCtx(ServletContext ctx) {
		this.ctx = ctx;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}
}
