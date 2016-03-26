package com.home.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import com.home.dao.GroupCustomerHome;
import com.home.entities.UserAware;
import com.home.model.GroupCustomer;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class GroupCustomerAction extends ActionSupport implements Action, ModelDriven<GroupCustomer>, UserAware {
	private static final long serialVersionUID = 1L;
	public GroupCustomer grpCustomer = new GroupCustomer();
	public List<String> listGrpName = new ArrayList<>();
	public List<GroupCustomer> listGrpCustomer = new ArrayList<>();
	private HttpServletRequest request;
	private User userSes;

	@Override
	public GroupCustomer getModel() {
		return grpCustomer;
	}

	public User getUserSes() {
		return userSes;
	}
	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;

	}
	
	public String getListGrpCustomer() {
		try {
			listGrpCustomer.clear();
			GroupCustomerHome grpCusHome = new GroupCustomerHome(getSessionFactory());
			listGrpCustomer = grpCusHome.getListGrpCustomer();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String getListGrpName(){
		try {
			listGrpName.clear();
			GroupCustomerHome grpCusHome = new GroupCustomerHome(getSessionFactory());
			listGrpCustomer = grpCusHome.getListGrpCustomer();
			for (GroupCustomer groupCustomer : listGrpCustomer)
				listGrpName.add(groupCustomer.getGroupName().trim());
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
}
