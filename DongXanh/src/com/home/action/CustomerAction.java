package com.home.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;
import com.home.dao.CustomerHome;
import com.home.model.Customer;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction implements Action, ModelDriven<Customer>,
		ServletContextAware, ServletRequestAware {
	private String customerId;
	private Customer customer = new Customer();
	public List<Customer> customers = new ArrayList<Customer>();
	private ServletContext ctx;
	 private HttpServletRequest request;
	@Override
	public Customer getModel() {
		return customer;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String listCustomer() throws Exception {
		try {
		
			SessionFactory sf = (SessionFactory) ctx
					.getAttribute("SessionFactory");
			CustomerHome cusHome = new CustomerHome(sf);
			customers = cusHome.getListCustomer();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	public String deleteCustomerById() throws Exception {
		try {
			Integer id = Integer.parseInt(getCustomerId());
			SessionFactory sf = (SessionFactory) ctx
					.getAttribute("SessionFactory");
			CustomerHome cusHome = new CustomerHome(sf);
			Customer customer = cusHome.findById(id);
			cusHome.delete(customer);
			return SUCCESS;
		} catch (Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	public String addCustomer() throws Exception {
		try {
			
			SessionFactory sf = (SessionFactory) ctx
					.getAttribute("SessionFactory");
			CustomerHome cusHome = new CustomerHome(sf);
			cusHome.attachDirty(customer);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	

}
