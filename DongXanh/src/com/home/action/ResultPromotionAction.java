package com.home.action;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.GroupCustomerHome;
import com.home.dao.PromotionHome;
import com.home.model.GroupCustomer;
import com.home.model.Promotion;
import com.home.model.PromotionCus;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ResultPromotionAction extends ActionSupport implements Action, ServletContextAware, ServletRequestAware {

	private ServletContext ctx;
	private HttpServletRequest request;
	private List<Promotion> promotions = new ArrayList<Promotion>();
	private List<PromotionCus> PromotionCuss = new ArrayList<PromotionCus>();
	private HashMap<Integer, String> groupCustomers ;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public static void main(String[] args) {
		try {
			new ResultPromotionAction().listPromotionActive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String listPromotionActive() throws Exception {
		try {
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			promotions = promotionHome.getListPromotionActive();
			
			GroupCustomerHome groupCustomerHome = new GroupCustomerHome(HibernateUtil.getSessionFactory());
			groupCustomers = groupCustomerHome.getListGroupCustomer();
			int idx = 1;
			for (Promotion p : promotions) {
				//System.out.println(p.getGroupCustomer().getId());
				GroupCustomer group = new GroupCustomer();
				group.setId(p.getGroupCustomer().getId());
				group.setGroupName(groupCustomers.get(group.getId()));
				p.setGroupCustomer(group);
				p.setRow_index(idx++);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String listPromotionCusResult() throws Exception {
		try {
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			Date start = new Date(new java.util.Date().getTime()-4*24*60*60*1000);
			Date end = new Date(new java.util.Date().getTime());
			PromotionCuss = promotionHome.listPromotionCusResult(start, end);
			for (PromotionCus promotionCus : PromotionCuss) {
				System.out.println("--------->" + promotionCus.getCustomerCode());
				System.out.println("--------->" + promotionCus.getCustomerName());
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<PromotionCus> getPromotionCuss() {
		return PromotionCuss;
	}

	public void setPromotionCuss(List<PromotionCus> promotionCuss) {
		PromotionCuss = promotionCuss;
	}

	public HashMap<Integer, String> getGroupCustomers() {
		return groupCustomers;
	}

	public void setGroupCustomers(HashMap<Integer, String> groupCustomers) {
		this.groupCustomers = groupCustomers;
	}
}
