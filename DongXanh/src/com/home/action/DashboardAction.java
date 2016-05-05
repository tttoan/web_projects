package com.home.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.GroupCustomerHome;
import com.home.dao.PromotionHome;
import com.home.entities.UserAware;
import com.home.model.Customer;
import com.home.model.Dashboard;
import com.home.model.GroupCustomer;
import com.home.model.Role;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class DashboardAction  implements Action, ServletContextAware, UserAware {
	private ServletContext ctx;
	private Dashboard dashboard ;
	public List<Customer> birthdayCustomers;
	public User user;
	private User userSes;

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

	public User getUserSes() {
		return userSes;
	}
	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}
	
	public static void main(String[] args) {
		new DashboardAction().getListCustomerByBirthday();
	}
	
	private void checkPromotionResult() throws Exception {
		try {
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			promotionHome.setFinish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentUser() throws Exception {
		try {
			User user = (User) ActionContext.getContext().getSession().get(MyConts.LOGIN_SESSION);
			if(user == null){
				user = new User();
				user.setUserName("user");
				user.setFullName("user");
				user.setRole(new Role());
				user.getRole().setRoleId(-1);
				user.getRole().setRoleName("guest");
				//return Action.LOGIN; 
				
				this.user = new User(user.getId(), user.getUserName(), user.getFullName());
				this.user.setRole(user.getRole());
			}else{
				this.user = new User(user.getId(), user.getUserName(), user.getFullName());
				this.user.setRole(new Role());
				this.user.getRole().setRoleId(user.getRole().getRoleId());
				this.user.getRole().setRoleName(user.getRole().getRoleName());
				
				//ActionContext.getContext().getSession().put(MyConts.LOGIN_SESSION, user);
			}
			
			return Action.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
	}
	

	public String getStatisticPromotions() throws Exception {
		try {
			//Check promotion status
			checkPromotionResult();
			//Get list
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			int[] arr = promotionHome.totalStatisticPromotions();
			dashboard = new Dashboard();
			dashboard.setCurrentPromotion(arr[0]);
			dashboard.setFeaturePromotion(arr[1]);
			dashboard.setNextFinishPomotion(arr[2]);
			dashboard.setPreFinishPromotion(arr[3]);
			dashboard.setTotalPromotion(arr[4]);
			
			return Action.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
	}
	
	public String getListCustomerByBirthday() {
		try {
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			GroupCustomerHome groupCusHome = new GroupCustomerHome(HibernateUtil.getSessionFactory());
			HashMap<Integer, String> groups = groupCusHome.getListGroupCustomer();
			
			birthdayCustomers = new ArrayList<Customer>();
			List<Customer> cuss = cusHome.findAll();
			for (Customer customer : cuss) {
				if(customer.getDirectorBirthday() == null)
					continue;
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
					Customer cus = new Customer();
					cus.setCustomerCode(customer.getCustomerCode());
					cus.setBusinessName(customer.getBusinessName());
					cus.setDirectorBirthday(customer.getDirectorBirthday());
					cus.setDirectorDomicile(SystemUtil.convertDateToString(new Date(birthday.getTimeInMillis()), "dd/MM/yyyy"));
					GroupCustomer g = new GroupCustomer();
					g.setGroupName("Nhóm khách hàng");
					if(customer.getGroupCustomer() != null){
						g.setGroupName(groups.get(customer.getGroupCustomer().getId()));
					}
					cus.setGroupCustomer(g);
					//System.out.println(today.get(Calendar.DATE) + "/" + furture.getTime());
					//System.out.println(birthday.get(Calendar.DATE) + "/" + birthday.getTime());
					cus.setTotalVipCustomer(birthday.get(Calendar.DATE)-today.get(Calendar.DATE));
					birthdayCustomers.add(cus);
				}
			}
			return Action.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public List<Customer> getBirthdayCustomers() {
		return birthdayCustomers;
	}


	public void setBirthdayCustomers(List<Customer> birthdayCustomers) {
		this.birthdayCustomers = birthdayCustomers;
	}

	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}
}
