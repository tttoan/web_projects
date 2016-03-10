package com.home.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.UserHome;
import com.home.model.Customer;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements Action, ModelDriven<User>, ServletContextAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	public User user = new User();
	public List<Customer> listCustomer = new ArrayList<>();
	public List<Customer> listBirthCus = new ArrayList<>();
	private HttpServletRequest request;
	private ServletContext ctx;
	private Map<String, Object> session;
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
		UserHome userHome = new UserHome(getSessionFactory());
		User userDB = userHome.getUserByCredentials(user.getUserName(), user.getPassword());
		if (userDB == null) {
			addActionError("Username or password is not valid");
		} else {
			user = userDB;
			setSession(ActionContext.getContext().getSession());
			getSession().put("logined", "true");
			getSession().put("context", new Date());
		}
	}

	public String getListCustomerByBirthday() {
		try {
			listBirthCus.clear();
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			listCustomer = cusHome.findAll();
			for (Customer customer : listCustomer) {
				Calendar birthday = Calendar.getInstance();
				birthday.set(Calendar.HOUR_OF_DAY, 0);
				birthday.set(Calendar.MINUTE, 0);
				birthday.set(Calendar.SECOND, 0);
				birthday.set(Calendar.MILLISECOND, 0);

				Calendar today = (Calendar) birthday.clone();
				Calendar furture = (Calendar) birthday.clone();
				furture.add(Calendar.DATE, 7);
				birthday.setTime(customer.getDirectorBirthday());
				birthday.set(Calendar.YEAR, furture.get(Calendar.YEAR));
				if (birthday.getTimeInMillis() >= today.getTimeInMillis() && birthday.getTimeInMillis() <= furture.getTimeInMillis()) {
					listBirthCus.add(customer);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
