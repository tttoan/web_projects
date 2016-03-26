package com.home.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.ProductHome;
import com.home.dao.PromotionProductHome;
import com.home.entities.UserAware;
import com.home.model.Product;
import com.home.model.Promotion;
import com.home.model.PromotionProduct;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author USER
 *
 */
public class PromotionProductAction implements ServletContextAware, UserAware{

	private ServletContext ctx;

	private PromotionProduct record ;
	private List<PromotionProduct> records ;
	private String result;
	private String message;
	private int totalRecordCount;
	private LinkedHashMap<Integer, String> products ;

	private Integer id;
	private Integer maxQuantity;
	private Integer maxPoint;
	private Integer product_id;
	private Integer promotion_id;

	private User userSes;

	public static void main(String[] args) {
		try {
			PromotionProductAction action = new PromotionProductAction();
			//action.create();
			//action.list();
			//action.delete();
			System.out.println(action.getTotalProducts());
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

	private int getTotalProducts(){
		PromotionProductHome productHome = new PromotionProductHome(HibernateUtil.getSessionFactory());
		try {
			return productHome.getTotalRecords(1);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
//	public String setPromotionId() throws Exception {
//		System.out.println("promotion_id=" + promotion_id);
//		Map session = ActionContext.getContext().getSession();
//		session.put("promotion_id", promotion_id);
//		return Action.SUCCESS; 
//	}

	public String list() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
//			Map<String, String[]> map = request.getParameterMap();
//			Set<String> set = map.keySet();
//			for (String key : set) {
//				System.out.println(key + ": " + map.get(key));
//			}
			
			int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
			int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
			String sorting = request.getParameter("jtSorting");
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			//System.out.println("startPageIndex: " + startPageIndex);
			//System.out.println("recordsPerPage: " + recordsPerPage);

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
//			if(promotion_id == null){
//				promotion_id = (int) ActionContext.getContext().getSession().get("promotion_id");
//			}
			if(promotion_id != null){
				PromotionProductHome productHome = new PromotionProductHome(HibernateUtil.getSessionFactory());
				records = productHome.getListPromotionProducts(promotion_id, startPageIndex, recordsPerPage);
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
			
			record = new PromotionProduct();
			record.setId(id);
			record.setMaxPoint(maxPoint);
			record.setMaxQuantity(maxQuantity);
			
			Product product = new Product();
			product.setId(product_id);		
			record.setProduct(product);

			Promotion promotion = new Promotion();
			promotion.setId(promotion_id);
			record.setPromotion(promotion);
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionProductHome productHome = new PromotionProductHome(HibernateUtil.getSessionFactory());
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
			
			PromotionProduct record = new PromotionProduct();
			record.setId(id);
			record.setMaxPoint(maxPoint);
			record.setMaxQuantity(maxQuantity);
			
			Product product = new Product();
			product.setId(product_id);		
			record.setProduct(product);

			Promotion promotion = new Promotion();
			promotion.setId(promotion_id);
			record.setPromotion(promotion);
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionProductHome productHome = new PromotionProductHome(HibernateUtil.getSessionFactory());
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
			PromotionProductHome productHome = new PromotionProductHome(HibernateUtil.getSessionFactory());
			PromotionProduct record = productHome.findById(id);
			productHome.delete(record);
			result = "OK";
		} catch (Exception e){
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getAllProducts() throws Exception {
		try {
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
			products =  productHome.getListProducts();
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public PromotionProduct getRecord() {
		return record;
	}

	public void setRecord(PromotionProduct record) {
		this.record = record;
	}

	public List<PromotionProduct> getRecords() {
		return records;
	}

	public void setRecords(List<PromotionProduct> records) {
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

	public LinkedHashMap<Integer, String> getProducts() {
		return products;
	}

	public void setProducts(LinkedHashMap<Integer, String> products) {
		this.products = products;
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

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}



}
