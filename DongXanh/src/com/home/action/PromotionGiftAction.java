package com.home.action;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.GiftHome;
import com.home.dao.PromotionGiftHome;
import com.home.entities.UserAware;
import com.home.model.Gift;
import com.home.model.Promotion;
import com.home.model.PromotionGift;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author USER
 *
 */
public class PromotionGiftAction implements ServletContextAware, UserAware {

	private ServletContext ctx;

	private PromotionGift record ;
	private List<PromotionGift> records ;
	private String result;
	private String message;
	private int totalRecordCount;
	private LinkedHashMap<Integer, String> gifts ;

	private Integer id;
	private Integer maxQuantity;
	private Integer maxPoint;
	private Integer gift_id;
	private Integer promotion_id;
	private String unit;
	private String formula;
	private Double price;
	private User userSes;

	public static void main(String[] args) {
		try {
			PromotionGiftAction action = new PromotionGiftAction();
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
			String sorting = request.getParameter("jtSorting");
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			//System.out.println("startPageIndex: " + startPageIndex);
			//System.out.println("recordsPerPage: " + recordsPerPage);

			if(promotion_id != null){
				PromotionGiftHome productHome = new PromotionGiftHome(HibernateUtil.getSessionFactory());
				records = productHome.getListPromotionGifts(promotion_id, startPageIndex, recordsPerPage);
				// Get Total Record Count for Pagination
				totalRecordCount = productHome.getTotalRecords(promotion_id);
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
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			
			record = new PromotionGift();
			record.setId(id);
			record.setMaxPoint(maxPoint);
			record.setMaxQuantity(maxQuantity);
			record.setFormula(formula);
			record.setUnit(unit);
			record.setPrice(price);
			
			Gift gift = new Gift();
			gift.setId(gift_id);		
			record.setGift(gift);

			Promotion promotion = new Promotion();
			promotion.setId(promotion_id);
			record.setPromotion(promotion);
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionGiftHome productHome = new PromotionGiftHome(HibernateUtil.getSessionFactory());
			productHome.attachDirty(record);
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
			
			PromotionGift record = new PromotionGift();
			record.setId(id);
			record.setMaxPoint(maxPoint);
			record.setMaxQuantity(maxQuantity);
			record.setFormula(formula);
			record.setUnit(unit);
			record.setPrice(price);
			
			Gift gift = new Gift();
			gift.setId(gift_id);		
			record.setGift(gift);

			Promotion promotion = new Promotion();
			promotion.setId(promotion_id);
			record.setPromotion(promotion);
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionGiftHome productHome = new PromotionGiftHome(HibernateUtil.getSessionFactory());
			productHome.update(record);
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
			PromotionGiftHome productHome = new PromotionGiftHome(HibernateUtil.getSessionFactory());
			PromotionGift record = productHome.findById(id);
			productHome.delete(record);
			result = "OK";
		} catch (Exception e){
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getAllGifts() throws Exception {
		try {
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			GiftHome giftHome = new GiftHome(HibernateUtil.getSessionFactory());
			gifts =  giftHome.getListGifts();
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	

	public PromotionGift getRecord() {
		return record;
	}

	public void setRecord(PromotionGift record) {
		this.record = record;
	}

	public List<PromotionGift> getRecords() {
		return records;
	}

	public void setRecords(List<PromotionGift> records) {
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

	public Integer getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public Integer getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(Integer maxPoint) {
		this.maxPoint = maxPoint;
	}

	public Integer getGift_id() {
		return gift_id;
	}

	public void setGift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}

	public Integer getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
