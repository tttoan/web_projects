package com.home.action;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.GiftHome;
import com.home.dao.PromotionGiftHome;
import com.home.dao.PromotionRegistHome;
import com.home.dao.RegisterGiftHome;
import com.home.entities.UserAware;
import com.home.model.PromotionGift;
import com.home.model.PromotionRegister;
import com.home.model.RegisterGift;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author USER
 *
 */
public class RegisterGiftAction implements ServletContextAware, UserAware {

	private ServletContext ctx;

	private RegisterGift record ;
	private List<RegisterGift> records ;
	private String result;
	private String message;
	private int totalRecordCount;
	private LinkedHashMap<Integer, String> gifts ;

	private Integer id;
	private Integer gift_id;
	private Integer register_id;
	private Integer p_gift_id;
	private Integer promotion_id;
	private boolean applyAll;
	private Integer total;
	private User userSes;


	public static void main(String[] args) {
		try {
			RegisterGiftAction action = new RegisterGiftAction();
			//action.create();
			//action.list();
			//action.delete();
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
		PromotionGiftHome productHome = new PromotionGiftHome(HibernateUtil.getSessionFactory());
		try {
			return productHome.getTotalRecords(1);
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
			register_id = Integer.parseInt(request.getParameter("register_id"));
			//System.out.println("startPageIndex: " + startPageIndex);
			//System.out.println("recordsPerPage: " + recordsPerPage);

			if(register_id != null){
				RegisterGiftHome registerGiftHome = new RegisterGiftHome(HibernateUtil.getSessionFactory());
				records = registerGiftHome.getListRegisterGifts(register_id, startPageIndex, recordsPerPage);
				// Get Total Record Count for Pagination
				totalRecordCount = registerGiftHome.getTotalRecords(register_id);
			}else{
				totalRecordCount = 0;
			}
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
			register_id = Integer.parseInt(request.getParameter("register_id"));
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			RegisterGiftHome registerGiftHome = new RegisterGiftHome(HibernateUtil.getSessionFactory());
			
			record = new RegisterGift();
			record.setId(id);
			record.setTotal(total);
			
			PromotionGift promotionGift = new PromotionGift();
			promotionGift.setId(p_gift_id);
			record.setPromotionGift(promotionGift);
			
			if(applyAll){
				PromotionRegistHome promotionRegistHome = new PromotionRegistHome(HibernateUtil.getSessionFactory());
				List<PromotionRegister> list = promotionRegistHome.getListPromotionRegister(promotion_id);
				for (PromotionRegister pr : list) {
					record.setPromotionRegister(pr);
					registerGiftHome.attachDirty(record);
				}
			}else{
				PromotionRegister promotionRegister = new PromotionRegister();
				promotionRegister.setId(register_id);
				record.setPromotionRegister(promotionRegister);
				registerGiftHome.attachDirty(record);
			}
			
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
			register_id = Integer.parseInt(request.getParameter("register_id"));
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			RegisterGiftHome registerGiftHome = new RegisterGiftHome(HibernateUtil.getSessionFactory());
			
			RegisterGift record = new RegisterGift();
			record.setId(id);
			record.setTotal(total);
			
			PromotionGift promotionGift = new PromotionGift();
			promotionGift.setId(p_gift_id);
			record.setPromotionGift(promotionGift);
			
			if(applyAll){
				PromotionRegistHome promotionRegistHome = new PromotionRegistHome(HibernateUtil.getSessionFactory());
				List<PromotionRegister> list = promotionRegistHome.getListPromotionRegister(promotion_id);
				for (PromotionRegister pr : list) {
					record.setPromotionRegister(pr);
					registerGiftHome.update(record);
				}
			}else{
				PromotionRegister promotionRegister = new PromotionRegister();
				promotionRegister.setId(register_id);
				record.setPromotionRegister(promotionRegister);
				registerGiftHome.update(record);
			}
			
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
			RegisterGiftHome registerGiftHome = new RegisterGiftHome(HibernateUtil.getSessionFactory());
			RegisterGift record = registerGiftHome.findById(id);
			registerGiftHome.delete(record);
			result = "OK";
		} catch (Exception e){
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getRegisterGifts() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			GiftHome giftHome = new GiftHome(HibernateUtil.getSessionFactory());
			gifts =  giftHome.getListGifts(promotion_id);
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	public RegisterGift getRecord() {
		return record;
	}

	public void setRecord(RegisterGift record) {
		this.record = record;
	}

	public List<RegisterGift> getRecords() {
		return records;
	}

	public void setRecords(List<RegisterGift> records) {
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

	public LinkedHashMap<Integer, String> getGifts() {
		return gifts;
	}

	public void setGifts(LinkedHashMap<Integer, String> gifts) {
		this.gifts = gifts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGift_id() {
		return gift_id;
	}

	public void setGift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}

	public Integer getRegister_id() {
		return register_id;
	}

	public void setRegister_id(Integer register_id) {
		this.register_id = register_id;
	}

	public Integer getP_gift_id() {
		return p_gift_id;
	}

	public void setP_gift_id(Integer p_gift_id) {
		this.p_gift_id = p_gift_id;
	}
	
	public Integer getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}

	public boolean getApplyAll() {
		return applyAll;
	}

	public void setApplyAll(boolean applyAll) {
		this.applyAll = applyAll;
	}
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
