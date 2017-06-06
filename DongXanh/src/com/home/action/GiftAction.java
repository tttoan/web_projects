package com.home.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.conts.Params;
import com.home.dao.GiftHome;
import com.home.entities.UserAware;
import com.home.model.Gift;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author USER
 *
 */
public class GiftAction implements ServletContextAware, UserAware{

	private ServletContext ctx;
	private Gift record ;
	private List<Gift> records ;
	private String result;
	private String message;
	private int totalRecordCount;

	private Integer id;
	private String giftName;
	private User userSes;

	public static void main(String[] args) {
		try {
			GiftAction action = new GiftAction();
			action.create();
			//action.list();
			//action.delete();
			//New test
			System.out.println(action.getTotalProducts());
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
	private int getTotalProducts(){
		GiftHome giftHome = new GiftHome(HibernateUtil.getSessionFactory());
		try {
			return giftHome.getTotalRecords();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

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
			GiftHome giftHome = new GiftHome(HibernateUtil.getSessionFactory());
			records = giftHome.getListGifts(startPageIndex, recordsPerPage);
			// Get Total Record Count for Pagination
			totalRecordCount = giftHome.getTotalRecords();
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
			record = new Gift();
			record.setId(id);
			record.setGiftName(giftName);
			System.out.println(giftName);
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			GiftHome giftHome = new GiftHome(HibernateUtil.getSessionFactory());
			giftHome.attachDirty(record);
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
			Gift record = new Gift();
//			if(id == 0){
//				giftName = Params.GIFT_DEBT;
//			}
			record.setId(id);
			record.setGiftName(giftName);
			System.out.println(giftName);
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			GiftHome giftHome = new GiftHome(HibernateUtil.getSessionFactory());
			giftHome.update(record);
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
			if(id != 0){
				//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
				GiftHome giftHome = new GiftHome(HibernateUtil.getSessionFactory());
				Gift record = giftHome.findById(id);
				giftHome.delete(record);
			}
			result = "OK";
		} catch (Exception e){
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public Gift getRecord() {
		return record;
	}

	public void setRecord(Gift record) {
		this.record = record;
	}

	public List<Gift> getRecords() {
		return records;
	}

	public void setRecords(List<Gift> records) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

}
