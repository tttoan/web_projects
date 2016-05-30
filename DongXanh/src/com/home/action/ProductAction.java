package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.CategoryHome;
import com.home.dao.ProductHome;
import com.home.entities.UserAware;
import com.home.model.Category;
import com.home.model.Product;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
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
//	private User userSes;
//	public User getUserSes() {
//		return userSes;
//	}
//
//	public void setUserSes(User userSes) {
//		this.userSes = userSes;
//	}

	private Integer id;
	private Integer proId;
	private Integer category_id;
	private String productCode;
	private String productName;
	private String description;
	private BigDecimal unitPrice;
	private Integer quantity;
	private Integer point;
	//Import product
	private Integer colProductCode;
	private Integer colProductName;
	private Integer colProductQuantity;
	private Integer colProductPoint;
	private Integer colProductPrice;
	private Integer rowProductStart;
	//Upload file
	private File upload_excel;//[your file name parameter
	private String upload_excelContentType;//[your file name parameter]ContentType ==> default in struts
	private String upload_excelFileName;//[your file name parameter]FileName ==> default in struts
	private Workbook workbook;
	
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
			try {
				productName = StringUtil.notNull(request.getParameter("productName"));
			} catch (Exception e) {
				productName = "";
			}
			//System.out.println("startPageIndex: " + startPageIndex);
			//System.out.println("recordsPerPage: " + recordsPerPage);
			
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
			if(productName.isEmpty()){
				records = productHome.getListProducts(startPageIndex, recordsPerPage);
				// Get Total Record Count for Pagination
				totalRecordCount = productHome.getTotalRecords();
			}else{
				records = productHome.getListProducts(startPageIndex, recordsPerPage, productName);
				// Get Total Record Count for Pagination
				totalRecordCount = productHome.getTotalRecords(productName);
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
			Category category = new Category();
			category.setId(category_id);
			record = new Product();
			record.setId(id);
			record.setProductCode(productCode);
			record.setProductName(productName);
			record.setDescription(description);
			record.setCategory(category);
			record.setUnitPrice(unitPrice);
			record.setQuantity(quantity);
			record.setPoint(point);
			record.setExportDate(new Date());

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
			record.setProductName(productName);
			record.setDescription(description);
			record.setCategory(category);
			record.setUnitPrice(unitPrice);
			record.setQuantity(quantity);
			record.setPoint(point);
			record.setExportDate(new Date());

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
	
	public String retrieveUnitPriceById()  throws Exception {
		ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
		Product record = productHome.findById(proId);
		if(record != null)
			unitPrice = record.getUnitPrice();
		else 
			unitPrice = new BigDecimal(0);
		return SUCCESS;
	}
	
	public String importProducts(){
		try {
			//System.out.println(upload_excel);
			File theFile = new File(getUpload_excelFileName());
			FileUtils.copyFile(getUpload_excel(), theFile);
			Cell cell = null;
			Object value = null;
			try (FileInputStream fis = new FileInputStream(theFile)) {
				ProductHome productHome = new ProductHome(HibernateUtil.getSessionFactory());
				ExcelUtil xls = new ExcelUtil();
//				System.out.println(colProductCode);
//				System.out.println(colProductName);
//				System.out.println(colProductQuantity);
//				System.out.println(colProductPrice);
//				System.out.println(rowProductStart);
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				int row_index = 0;
				while (rowIterator.hasNext()) {
					if(row_index >= rowProductStart){
						Row row = rowIterator.next();
						
						//Product code
						cell = row.getCell(colProductCode);
						value = xls.getValue(cell);
						productCode = StringUtil.notNull(value);
						//Product name
						cell = row.getCell(colProductName);
						value = xls.getValue(cell);
						productName = StringUtil.notNull(value);
						//Product quantity
						cell = row.getCell(colProductQuantity);
						value = xls.getValue(cell);
						try {
							quantity = Integer.parseInt(value.toString().replaceAll("\\.[0-9]+", ""));
						} catch (Exception e) {
							quantity = 0;
						}
						//Product point
						cell = row.getCell(colProductPoint);
						value = xls.getValue(cell);
						try {
							point = Integer.parseInt(value.toString().replaceAll("\\.[0-9]+", ""));
						} catch (Exception e) {
							point = 0;
						}
						//Product price
						cell = row.getCell(colProductPrice);
						value = xls.getValue(cell);
						try {
							unitPrice = new BigDecimal(value.toString().replaceAll("\\.[0-9]+", "").replace(",", ""));
						} catch (Exception e) {
							unitPrice = new BigDecimal("0");
						}
						
						if(productCode.length() > 0 && productName.length() > 0 &&
								(quantity > 0 || point > 0 || unitPrice.intValue() > 0)){
							Category category = new Category();
							category.setId(1);
							Product record = new Product();
							record.setId(id);
							record.setProductCode(productCode);
							record.setProductName(productName);
							record.setDescription(description);
							record.setCategory(category);
							record.setUnitPrice(unitPrice);
							record.setQuantity(quantity);
							record.setPoint(point);
							record.setExportDate(new Date());
							
							if(productHome.existProduct(record)){
								//Update
								productHome.updateByProductCode(record);
							}else{
								//Create
								productHome.attachDirty(record);
							}
						}
						
					}
					row_index++;
				}
				
			} catch (Exception e) {
				result = "ERROR";
				message = e.getMessage();
				e.printStackTrace();
			} finally {
				if (theFile.exists())
					FileUtils.deleteQuietly(theFile);
			}
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			e.printStackTrace();
		}
		return Action.SUCCESS;
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
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getColProductCode() {
		return colProductCode;
	}

	public void setColProductCode(Integer colProductCode) {
		this.colProductCode = colProductCode;
	}

	public Integer getColProductName() {
		return colProductName;
	}

	public void setColProductName(Integer colProductName) {
		this.colProductName = colProductName;
	}

	public Integer getColProductQuantity() {
		return colProductQuantity;
	}

	public void setColProductQuantity(Integer colProductQuantity) {
		this.colProductQuantity = colProductQuantity;
	}

	public Integer getColProductPrice() {
		return colProductPrice;
	}

	public void setColProductPrice(Integer colProductPrice) {
		this.colProductPrice = colProductPrice;
	}

	public Integer getRowProductStart() {
		return rowProductStart;
	}

	public void setRowProductStart(Integer rowProductStart) {
		this.rowProductStart = rowProductStart;
	}

	public File getUpload_excel() {
		return upload_excel;
	}

	public void setUpload_excel(File upload_excel) {
		this.upload_excel = upload_excel;
	}
	public String getUpload_excelContentType() {
		return upload_excelContentType;
	}

	public void setUpload_excelContentType(String upload_excelContentType) {
		this.upload_excelContentType = upload_excelContentType;
	}

	public String getUpload_excelFileName() {
		return upload_excelFileName;
	}

	public void setUpload_excelFileName(String upload_excelFileName) {
		this.upload_excelFileName = upload_excelFileName;
	}
	public Integer getColProductPoint() {
		return colProductPoint;
	}

	public void setColProductPoint(Integer colProductPoint) {
		this.colProductPoint = colProductPoint;
	}
}
