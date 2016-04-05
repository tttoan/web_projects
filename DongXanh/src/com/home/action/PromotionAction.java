package com.home.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.GroupCustomerHome;
import com.home.dao.PromotionHome;
import com.home.entities.UserAware;
import com.home.model.GroupCustomer;
import com.home.model.Promotion;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author USER
 *
 */
public class PromotionAction implements ServletContextAware, UserAware {

	private ServletContext ctx;
	private Promotion record ;
	private List<Promotion> records ;
	private String result;
	private String message;
	private int totalRecordCount;
	private HashMap<Integer, String> groupCustomers ;

	private Integer id;
	private String promotionName;
	private Date startDate;
	private Date endDate;
	private String remarks;
	private Boolean status;
	private Integer group_customer_id;
	private Short customerRegist;
	private User userSes; 
	private String rule;

	public static void main(String[] args) {
		try {
			new PromotionAction().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getUserSes() {
		return userSes;
	}
	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}
	@Override
	public void setServletContext(ServletContext ctx) {
		this.ctx = ctx;
	}
	
//	public String checkPromotionResult() throws Exception {
//		try {
//			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
//			promotionHome.setFinish();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Action.ERROR;
//		}
//		return Action.SUCCESS;
//	}

	public String list() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
			int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
			//String sorting = request.getParameter("jtSorting");
			//System.out.println("startPageIndex: " + startPageIndex);
			//System.out.println("recordsPerPage: " + recordsPerPage);

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			records = promotionHome.getListPromotions(startPageIndex, recordsPerPage);
			// Get Total Record Count for Pagination
			totalRecordCount = promotionHome.getTotalRecords();
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
			record = new Promotion();
			record.setId(id);
			record.setPromotionName(promotionName);
			record.setStartDate(startDate);
			record.setEndDate(endDate);
			record.setRemarks(remarks);
			record.setStatus(true);
			record.setCustomerRegist(customerRegist);
			GroupCustomer group = new GroupCustomer();
			group.setId(group_customer_id);
			record.setRule(rule);
			record.setGroupCustomer(group);

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			promotionHome.attachDirty(record);
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
			Promotion record = new Promotion();
			record.setId(id);
			record.setPromotionName(promotionName);
			record.setStartDate(startDate);
			record.setEndDate(endDate);
			record.setRemarks(remarks);
			record.setStatus(status);
			record.setCustomerRegist(customerRegist);
			record.setRule(rule);
			GroupCustomer group = new GroupCustomer();
			group.setId(group_customer_id);
			record.setGroupCustomer(group);

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			promotionHome.update(record);
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
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			Promotion record = promotionHome.findById(id);
			promotionHome.delete(record);
			result = "OK";
		} catch (Exception e){
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getAllGroupCustomer() throws Exception {
		try {
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			GroupCustomerHome groupCustomerHome = new GroupCustomerHome(HibernateUtil.getSessionFactory());
			groupCustomers = groupCustomerHome.getListGroupCustomer();
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public Promotion getRecord() {
		return record;
	}


	public void setRecord(Promotion record) {
		this.record = record;
	}


	public List<Promotion> getRecords() {
		return records;
	}


	public void setRecords(List<Promotion> records) {
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


	public HashMap<Integer, String> getGroupCustomers() {
		return groupCustomers;
	}


	public void setGroupCustomers(HashMap<Integer, String> groupCustomers) {
		this.groupCustomers = groupCustomers;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPromotionName() {
		return promotionName;
	}


	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public Integer getGroup_customer_id() {
		return group_customer_id;
	}


	public void setGroup_customer_id(Integer group_customer_id) {
		this.group_customer_id = group_customer_id;
	}
	
	public Short getCustomerRegist() {
		return customerRegist;
	}


	public void setCustomerRegist(Short customerRegist) {
		this.customerRegist = customerRegist;
	}
	
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
