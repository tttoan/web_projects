package com.home.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.CategoryHome;
import com.home.dao.ProductHome;
import com.home.model.Category;
import com.home.model.Product;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author USER
 *
 */
public class ProductAction implements Action, ServletContextAware{

	private ServletContext ctx;

	private Product record ;
	private List<Product> records ;
	private String result;
	private String message;
	private int totalRecordCount;
	private HashMap<Integer, String> categories;

	private Integer id;
	private Integer proId;
	private Integer category_id;
	private String productCode;
	private String productType;
	private String productName;
	private String description;
	private BigDecimal unitPrice;
	private Integer minQuantity;
	private Integer maxQuantity;
	private Date exportDate;
	private Date launchDate;

	public static void main(String[] args) {
		try {
			ProductAction action = new ProductAction();
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
	
	private int getTotalProducts(){
		ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
		try {
			return productHome.getTotalRecords();
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
			//System.out.println("startPageIndex: " + startPageIndex);
			//System.out.println("recordsPerPage: " + recordsPerPage);
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
			records = productHome.getListProducts(startPageIndex, recordsPerPage);
			// Get Total Record Count for Pagination
			totalRecordCount = productHome.getTotalRecords();
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
			Category category = new Category();
			category.setId(category_id);
			record = new Product();
			record.setId(id);
			record.setProductCode(productCode);
			record.setProductType(getCategoryName());
			record.setProductName(productName);
			record.setDescription(description);
			record.setCategory(category);
			record.setUnitPrice(unitPrice);
			record.setMinQuantity(minQuantity);
			record.setMaxQuantity(maxQuantity);
			record.setExportDate(exportDate);
			record.setLaunchDate(new Date());

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
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
			Category category = new Category();
			category.setId(category_id);
			Product record = new Product();
			record.setId(id);
			record.setProductCode(productCode);
			record.setProductType(getCategoryName());
			record.setProductName(productName);
			record.setDescription(description);
			record.setCategory(category);
			record.setUnitPrice(unitPrice);
			record.setMinQuantity(minQuantity);
			record.setMaxQuantity(maxQuantity);
			record.setExportDate(exportDate);
			record.setLaunchDate(launchDate);

			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
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
			ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
			Product record = productHome.findById(id);
			productHome.delete(record);
			result = "OK";
		} catch (Exception e){
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getAllCategories() throws Exception {
		try {
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			CategoryHome categoryHome = new CategoryHome(HibernateUtil.getSessionFactory());
			categories = categoryHome.getListCategory();
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	private String getCategoryName(){
		try {
			if(categories != null && categories.containsKey(category_id)){
				return categories.get(category_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productType;
	}
	public String retrieveUnitPriceById()  throws Exception {
		ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
		Product record = productHome.findById(proId);
		if(record != null)
			unitPrice = record.getUnitPrice();
		else 
			unitPrice = new BigDecimal(0);
		return SUCCESS;
	}

	public Product getRecord() {
		return record;
	}

	public void setRecord(Product record) {
		this.record = record;
	}

	public List<Product> getRecords() {
		return records;
	}

	public void setRecords(List<Product> records) {
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(Integer minQuantity) {
		this.minQuantity = minQuantity;
	}

	public Integer getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public Date getExportDate() {
		return exportDate;
	}

	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	
	public HashMap<Integer, String> getCategories() {
		return categories;
	}

	public void setCategories(HashMap<Integer, String> categories) {
		this.categories = categories;
	}
	
	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}


}
