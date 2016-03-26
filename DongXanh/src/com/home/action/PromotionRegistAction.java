package com.home.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.GroupCustomerHome;
import com.home.dao.PromotionRegistHome;
import com.home.entities.UserAware;
import com.home.model.Customer;
import com.home.model.Promotion;
import com.home.model.PromotionRegister;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author USER
 *
 */
public class PromotionRegistAction implements ServletContextAware, UserAware{

	private ServletContext ctx;
	private PromotionRegister record ;
	private List<PromotionRegister> records ;
	private String result;
	private String message;
	private int totalRecordCount;
	private HashMap<Integer, String> mapCustomers ;

	private Integer id;
	private Integer customer_id;
	private Integer group_customer_id;
	private Integer totalPoint;
	private Integer totalBox;
	private Integer promotion_id;
	private User userSes;


	public static void main(String[] args) {
		try {
			PromotionRegistHome promotionRegistHome = new PromotionRegistHome(HibernateUtil.getSessionFactory());
			promotionRegistHome.getListPromotionRegister(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void setServletContext(ServletContext ctx) {
		this.ctx = ctx;
	}
	public User getUserSes() {
		return userSes;
	}
	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}
	public String list() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
			int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
			//String sorting = request.getParameter("jtSorting");
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			group_customer_id = Integer.parseInt(request.getParameter("group_customer_id"));

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionRegistHome promotionRegistHome = new PromotionRegistHome(HibernateUtil.getSessionFactory());
			records = promotionRegistHome.getListPromotionRegisters(promotion_id, startPageIndex, recordsPerPage);
			// Get Total Record Count for Pagination
			totalRecordCount = promotionRegistHome.getTotalRecords();
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}


	public String create() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			
			record = new PromotionRegister();
			record.setId(id);
			Customer cus = new Customer();
			cus.setId(customer_id);
			record.setCustomer(cus);
			record.setCustomer_id(cus.getId());
			Promotion promotion = new Promotion();
			promotion.setId(promotion_id);
			record.setPromotion(promotion);
			record.setPromotion_id(promotion.getId());
			record.setTotalBox(totalBox);
			record.setTotalPoint(totalPoint);

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionRegistHome promotionRegistHome = new PromotionRegistHome(HibernateUtil.getSessionFactory());
			promotionRegistHome.attachDirty(record);
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String update() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			
			PromotionRegister record = new PromotionRegister();
			record.setId(id);
			Customer cus = new Customer();
			cus.setId(customer_id);
			record.setCustomer(cus);
			record.setCustomer_id(cus.getId());
			Promotion promotion = new Promotion();
			promotion.setId(promotion_id);
			record.setPromotion(promotion);
			record.setPromotion_id(promotion.getId());
			record.setTotalBox(totalBox);
			record.setTotalPoint(totalPoint);

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionRegistHome promotionRegistHome = new PromotionRegistHome(HibernateUtil.getSessionFactory());
			promotionRegistHome.update(record);
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String delete() throws Exception {
		try {
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionRegistHome promotionRegistHome = new PromotionRegistHome(HibernateUtil.getSessionFactory());
			PromotionRegister  record = promotionRegistHome.findById(id);
			promotionRegistHome.delete(record);
			result = "OK";
		} catch (Exception e){
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getCustomerRegister() throws Exception {
		try {
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			GroupCustomerHome groupCustomerHome = new GroupCustomerHome(HibernateUtil.getSessionFactory());
			mapCustomers = groupCustomerHome.getListCustomers();
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}


	public PromotionRegister getRecord() {
		return record;
	}


	public void setRecord(PromotionRegister record) {
		this.record = record;
	}


	public List<PromotionRegister> getRecords() {
		return records;
	}


	public void setRecords(List<PromotionRegister> records) {
		this.records = records;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getTotalRecordCount() {
		return totalRecordCount;
	}


	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}


	public HashMap<Integer, String> getMapCustomers() {
		return mapCustomers;
	}


	public void setMapCustomers(HashMap<Integer, String> mapCustomers) {
		this.mapCustomers = mapCustomers;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}


	public Integer getGroup_customer_id() {
		return group_customer_id;
	}


	public void setGroup_customer_id(Integer group_customer_id) {
		this.group_customer_id = group_customer_id;
	}


	public Integer getTotalPoint() {
		return totalPoint;
	}


	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}


	public Integer getTotalBox() {
		return totalBox;
	}


	public void setTotalBox(Integer totalBox) {
		this.totalBox = totalBox;
	}


	public Integer getPromotion_id() {
		return promotion_id;
	}


	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}
}
