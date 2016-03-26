package com.home.action;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.ProductHome;
import com.home.dao.PromotionProductHome;
import com.home.dao.RegisterProductHome;
import com.home.entities.UserAware;
import com.home.model.PromotionProduct;
import com.home.model.PromotionRegister;
import com.home.model.RegisterProduct;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author USER
 *
 */
public class RegisterProductAction implements ServletContextAware, UserAware{

	private ServletContext ctx;

	private RegisterProduct record ;
	private List<RegisterProduct> records ;
	private String result;
	private String message;
	private int totalRecordCount;
	private LinkedHashMap<Integer, String> products ;

	private Integer id;
	private Integer register_id;
	private Integer p_product_id;
	private Integer point;
	private Integer box;
	private Integer product_id;
	private Integer promotion_id;

	private User userSes;


	public static void main(String[] args) {
		try {
			RegisterProductAction action = new RegisterProductAction();
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
			//String sorting = request.getParameter("jtSorting");
			register_id = Integer.parseInt(request.getParameter("register_id"));
			//System.out.println("startPageIndex: " + startPageIndex);
			//System.out.println("recordsPerPage: " + recordsPerPage);

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
//			if(promotion_id == null){
//				promotion_id = (int) ActionContext.getContext().getSession().get("promotion_id");
//			}
			if(register_id != null){
				RegisterProductHome registerProductHome = new RegisterProductHome(HibernateUtil.getSessionFactory());
				records = registerProductHome.getListRegisterProducts(register_id, startPageIndex, recordsPerPage);
				// Get Total Record Count for Pagination
				totalRecordCount = registerProductHome.getTotalRecords(register_id);
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
			
			record = new RegisterProduct();
			record.setId(id);
			record.setBox(box);
			record.setPoint(point);
			
			PromotionProduct promotionProduct = new PromotionProduct();
			promotionProduct.setId(p_product_id);
			record.setPromotionProduct(promotionProduct);

			PromotionRegister promotionRegister = new PromotionRegister();
			promotionRegister.setId(register_id);
			record.setPromotionRegister(promotionRegister);
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			RegisterProductHome registerProductHome = new RegisterProductHome(HibernateUtil.getSessionFactory());
			registerProductHome.attachDirty(record);
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
			
			RegisterProduct record = new RegisterProduct();
			record.setId(id);
			record.setBox(box);
			record.setPoint(point);
			
			PromotionProduct promotionProduct = new PromotionProduct();
			promotionProduct.setId(p_product_id);
			record.setPromotionProduct(promotionProduct);

			PromotionRegister promotionRegister = new PromotionRegister();
			promotionRegister.setId(register_id);
			record.setPromotionRegister(promotionRegister);
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			RegisterProductHome registerProductHome = new RegisterProductHome(HibernateUtil.getSessionFactory());
			registerProductHome.update(record);
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
			RegisterProductHome registerProductHome = new RegisterProductHome(HibernateUtil.getSessionFactory());
			RegisterProduct record = registerProductHome.findById(id);
			registerProductHome.delete(record);
			result = "OK";
		} catch (Exception e){
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getRegisterProducts() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
			products =  productHome.getListProducts(promotion_id);
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public RegisterProduct getRecord() {
		return record;
	}

	public void setRecord(RegisterProduct record) {
		this.record = record;
	}

	public List<RegisterProduct> getRecords() {
		return records;
	}

	public void setRecords(List<RegisterProduct> records) {
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

	public Integer getRegister_id() {
		return register_id;
	}

	public void setRegister_id(Integer register_id) {
		this.register_id = register_id;
	}

	public Integer getP_product_id() {
		return p_product_id;
	}

	public void setP_product_id(Integer p_product_id) {
		this.p_product_id = p_product_id;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getBox() {
		return box;
	}

	public void setBox(Integer box) {
		this.box = box;
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
