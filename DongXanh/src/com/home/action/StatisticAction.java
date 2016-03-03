package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.conts.InvoiceTable;
import com.home.dao.CustomerHome;
import com.home.dao.ProductHome;
import com.home.dao.StatisticHome;
import com.home.dao.UomHome;
import com.home.dao.UserHome;
import com.home.model.Customer;
import com.home.model.Product;
import com.home.model.Statistic;
import com.home.model.Uom;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StatisticAction extends ActionSupport implements Action, ModelDriven<Statistic>, ServletContextAware, ServletRequestAware {
	private HttpServletRequest request;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private Statistic statistic = new Statistic();
	public List<Statistic> statistics = new ArrayList<Statistic>();
	private ServletContext ctx;
	private Workbook workbook;
	private List<User> listEmployee = new ArrayList<>();
	private List<Customer> listCustomer = new ArrayList<>();
	private List<Product> listProduct = new ArrayList<>();
	private List<Uom> listUom = new ArrayList<>();
	private Map<String, Customer> lookupCustomer = new HashMap<>();
	private Map<String, User> lookupEmployee = new HashMap<>();
	private Map<String, Product> lookupProduct = new HashMap<>();
	private String employeeName;
	private String productName;
	private String customerNameLevel1;
	private String customerNameLevel2;
	private int uomId;

	public File getUpload() {
		return upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	

	@Override
	public Statistic getModel() {
		return getStatistic();
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.setCtx(sc);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Override
	public void validate(){
		loadLookupEmployee();
		loadLookupCustomer();
		loadLookupProduct();
		loadLookupUom();
	}
	
	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	public String listStatistic() throws Exception {
		try {
			StatisticHome sttHome = new StatisticHome(getSessionFactory());
			statistics = sttHome.getListInvoice();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public void loadLookupProduct() {
		ProductHome proHome = new ProductHome(getSessionFactory());
		listProduct = proHome.getListProduct();
		for (Product pro : listProduct)
			lookupProduct.put(pro.getProductName() + " - " + pro.getProductCode() , pro);
	}
	public void loadLookupUom() {
		UomHome uomHome = new UomHome(getSessionFactory());
		listUom = uomHome.getListUOM();
	}
	public void loadLookupCustomer() {
		CustomerHome cusHome = new CustomerHome(getSessionFactory());
		listCustomer = cusHome.getListCustomer();
		for (Customer customer : listCustomer)
			lookupCustomer.put(customer.getDirector() + " - " + customer.getCustomerCode(), customer);
	}

	public void loadLookupEmployee() {
		UserHome userHome = new UserHome(getSessionFactory());
		listEmployee = userHome.getListUser();
		for (User user : listEmployee){
			lookupEmployee.put(user.getFullName() + " - " + user.getUserName(), user);
		}
	}
	public String addStatistic() throws Exception {
		try {
			User user = lookupEmployee.get(employeeName);
			Customer cusLevel1 = lookupCustomer.get(customerNameLevel1);
			Customer cusLevel2 = lookupCustomer.get(customerNameLevel2);
			Product product = lookupProduct.get(productName);
			Uom uom = new Uom();
			uom.setId(uomId);
			getStatistic().setUser(user);
			getStatistic().setCustomerByCustomerCodeLevel1(cusLevel1);
			getStatistic().setCustomerByCustomerCodeLevel2(cusLevel2);
			getStatistic().setProduct(product);
			getStatistic().setUom(uom);
			StatisticHome sttHome = new StatisticHome(HibernateUtil.getSessionFactory());
			sttHome.attachDirty(getStatistic());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String importStatisticLevel1() throws Exception {
		File theFile = new File(this.getUploadFileName());
		FileUtils.copyFile(upload, theFile);
		try (FileInputStream fis = new FileInputStream(theFile)) {
			ExcelUtil xls = new ExcelUtil();
			UserHome userHome = new UserHome(getSessionFactory());
			workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				setStatistic(new Statistic());
				getStatistic().setDateReceived((Date) xls.getValue(row.getCell(InvoiceTable.dateReceived.value())));
				// statistic.setCustomerCodeLevel2((String)
				// xls.getValue(row.getCell(InvoiceTable.customerCodeLevel2.value())));
				// statistic.setCustomerCodeLevel1((String)
				// xls.getValue(row.getCell(InvoiceTable.customerCodeLevel1.value())));
//				getStatistic().setProductCode((String) xls.getValue(row.getCell(InvoiceTable.productCode.value())));
//				getStatistic().setCategoryName((String) xls.getValue(row.getCell(InvoiceTable.categoryName.value())));
//				getStatistic().setProductName((String) xls.getValue(row.getCell(InvoiceTable.productName.value())));
//				getStatistic().setTotalBox(((Double) xls.getValue(row.getCell(InvoiceTable.totalBox.value()))).intValue());
				getStatistic().setQuantity(((Double) xls.getValue(row.getCell(InvoiceTable.quantiy.value()))).intValue());
				getStatistic().setUnitPrice(BigDecimal.valueOf((Double) xls.getValue(row.getCell(InvoiceTable.unitPrice.value()))));
				getStatistic().setTotal(BigDecimal.valueOf((Double) xls.getValue(row.getCell(InvoiceTable.total.value()))));
				// statistic.setUser(userHome.getUserByFullName((String)xls.getValue(row.getCell(InvoiceTable.userFullName.value()))));
				addStatistic();
				setStatistic(null);
			}
		} catch (Exception e) {
			throw e;
		}
		FileUtils.deleteQuietly(theFile);
		return SUCCESS;
	}

	public List<User> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<User> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	public List<Statistic> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistic> statistics) {
		this.statistics = statistics;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getCtx() {
		return ctx;
	}

	public void setCtx(ServletContext ctx) {
		this.ctx = ctx;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getCustomerNameLevel1() {
		return customerNameLevel1;
	}

	public void setCustomerNameLevel1(String customerNameLevel1) {
		this.customerNameLevel1 = customerNameLevel1;
	}

	public String getCustomerNameLevel2() {
		return customerNameLevel2;
	}

	public void setCustomerNameLevel2(String customerNameLevel2) {
		this.customerNameLevel2 = customerNameLevel2;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public Map<String, Product> getLookupProduct() {
		return lookupProduct;
	}

	public void setLookupProduct(Map<String, Product> lookupProduct) {
		this.lookupProduct = lookupProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Uom> getListUom() {
		return listUom;
	}

	public void setListUom(List<Uom> listUom) {
		this.listUom = listUom;
	}

	public int getUomId() {
		return uomId;
	}

	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
}
