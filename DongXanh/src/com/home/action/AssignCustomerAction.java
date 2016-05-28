package com.home.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.CustomerHome;
import com.home.dao.UserHome;
import com.home.entities.UserAware;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AssignCustomerAction extends ActionSupport implements Action, ServletContextAware,UserAware {

	private ServletContext ctx;
	private List<User> listEmployee = new ArrayList<>();
	private List<Object[]> listCustomer1 = new ArrayList<>();
	private List<Object[]> listCustomer2 = new ArrayList<>();
	private User userSes;
	public User getUserSes() {
		return userSes;
	}

	public void setUserSes(User userSes) {
		this.userSes = userSes;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void loadLookupEmployee() {
		try {
			UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());
			listEmployee = userHome.getLookupEmployee();
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup customers. Exception: " + e.getMessage());
		}
	}
	
	public String getCustomerByNVTT(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			int user_id = -1;
			try {
				user_id = Integer.parseInt(request.getParameter("user_id"));
			} catch (Exception e) {
			}
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			listCustomer1 = cusHome.getCustomersByNVTT(user_id);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getCustomerFree(){
		try {
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			listCustomer2 = cusHome.getCustomersFree();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String setAssignCustomer2NVTT(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			int cus_id = Integer.parseInt(request.getParameter("cus_id"));
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			cusHome.assignCustomer2NVTT(cus_id, user_id);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String setUnAssignCustomer2NVTT(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			int cus_id = Integer.parseInt(request.getParameter("cus_id"));
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			cusHome.unAssignCustomer2NVTT(cus_id);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	
	@Override
	public void validate() {
		try {
			loadLookupEmployee();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<User> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<User> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public List<Object[]> getListCustomer1() {
		return listCustomer1;
	}

	public void setListCustomer1(List<Object[]> listCustomer1) {
		this.listCustomer1 = listCustomer1;
	}

	public List<Object[]> getListCustomer2() {
		return listCustomer2;
	}

	public void setListCustomer2(List<Object[]> listCustomer2) {
		this.listCustomer2 = listCustomer2;
	}
	
}
