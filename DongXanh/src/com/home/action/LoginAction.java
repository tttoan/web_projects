package com.home.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.SessionFactory;

import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.UserHome;
import com.home.model.Customer;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements Action, ModelDriven<User>, SessionAware {
	private static final long serialVersionUID = 1L;
	public User userSes = new User();
	public List<Customer> listCustomer = new ArrayList<>();
	public List<Customer> listBirthCus = new ArrayList<>();
	private Map<String, Object> session;

	@Override
	public User getModel() {
		//userSes = new User();
		return userSes;
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory(this);
	}

	@Override
	public String execute() {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			userSes = userHome.getUserByCredentials(userSes.getUserName(), userSes.getPassword());
			if (userSes == null) {
				getModel();
				addActionError("Tên tài khoản hoặc mật khẩu không chính xác");
			} else {
				getSession().put(MyConts.LOGIN_SESSION, userSes);
//				getSession().put(MyConts.LOGIN_SESSION, new User(userSes.getId(),
//						 userSes.getUserName(), userSes.getFullName()));
				return SUCCESS;
			}
		} catch (Exception e) {
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	public String logOutWanted() throws Exception {
		try {
			session.remove(MyConts.LOGIN_SESSION);
			session.remove(MyConts.SESSION_NEW_STATISTICS_HISTORY);
			session.remove(MyConts.SESSION_NEW_CUSTOMERS_HISTORY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		// UserHome userHome = new UserHome(getSessionFactory());
		// User userDB = userHome.getUserByCredentials(user.getUserName(),
		// user.getPassword());
		// if (userDB == null) {
		// addActionError(getText("error.login"));
		// } else {
		// user = userDB;
		// setSession(ActionContext.getContext().getSession());
		// getSession().put("logined", "true");
		// getSession().put("context", new Date());
		// getSession().put(MyConts.LOGIN_SESSION, new User(user.getId(),
		// user.getUserName(), user.getFullName()));
		// }
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
