package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;
import com.home.conts.InvoiceTable;
import com.home.dao.CustomerHome;
import com.home.dao.ProductHome;
import com.home.dao.StatisticHome;
import com.home.dao.UserHome;
import com.home.model.Customer;
import com.home.model.Product;
import com.home.model.Statistic;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StatisticAction extends ActionSupport implements Action, ModelDriven<Statistic>, ServletContextAware, ServletRequestAware {
	private boolean edit = false;
	private HttpServletRequest request;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	public List<Statistic> statistics = new ArrayList<Statistic>();
	private Statistic stat = new Statistic();
	private ServletContext ctx;
	private Workbook workbook;
	private List<User> listEmployee = new ArrayList<>();
	private List<Customer> listCustomer = new ArrayList<>();
	private List<Product> listProduct = new ArrayList<>();
	private int statId;
	private ArrayList<String> districts = new ArrayList<>();
	private Customer cusLevel1 = new Customer();
	private Customer cusLevel2 = new Customer();
	private User emp = new User();

	private Product pro = new Product();

	public String getUploadContentType() {
		return uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Override
	public Statistic getModel() {
		stat = new Statistic();
		return stat;
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
		if (statId != 0) {
			try {
				StatisticHome sttHome = new StatisticHome(getSessionFactory());
				stat = sttHome.findById(statId);
				setEdit(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return SUCCESS;
	}

	public String dbDistricts() {
		// Do the database code or business logic here.
		districts = new ArrayList<String>();
		districts.add("District 1");
		districts.add("District 2");
		districts.add("District 3");
		districts.add("District 4");
		districts.add("District 5");
		System.out.println(districts);
		return SUCCESS;
	}

	@Override
	public void validate() {
		// loadLookupEmployee();
		// loadLookupCustomer();
		// loadLookupProduct();
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	public String listStatistic() throws Exception {
		try {
			StatisticHome sttHome = new StatisticHome(getSessionFactory());
			statistics = sttHome.getListStatistic();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public void loadLookupProduct() {
		ProductHome proHome = new ProductHome(getSessionFactory());
		listProduct = proHome.getListProduct();
	}

	public void loadLookupCustomer() {
		CustomerHome cusHome = new CustomerHome(getSessionFactory());
		listCustomer = cusHome.getListCustomer();
	}

	public void loadLookupEmployee() {
		UserHome userHome = new UserHome(getSessionFactory());
		listEmployee = userHome.getListUser();
	}

	public String deleteStatistic() throws Exception {
		try {
			StatisticHome sttHome = new StatisticHome(getSessionFactory());
			Statistic stt = sttHome.findById(statId);
			sttHome.delete(stt);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String importStatistic() throws Exception {
		try {

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String addStatistic() throws Exception {
		try {
			stat.setUser(emp);
			stat.setCustomerByCustomerCodeLevel1(cusLevel1);
			stat.setCustomerByCustomerCodeLevel2(cusLevel2);
			stat.setProduct(pro);
			StatisticHome sttHome = new StatisticHome(HibernateUtil.getSessionFactory());
			if (stat.getId() == 0)
				sttHome.attachDirty(stat);
			else
				sttHome.updateDirty(stat);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String importStatisticLevel1() {
		try {
			StringBuilder logDuplicate = new StringBuilder();
			File theFile = new File(getUploadFileName());
			FileUtils.copyFile(getUpload(), theFile);
			Cell cell = null;
			Object value = null;
			try (FileInputStream fis = new FileInputStream(theFile)) {
				ExcelUtil xls = new ExcelUtil();
				StatisticHome sttHome = new StatisticHome(getSessionFactory());
				CustomerHome custHome = new CustomerHome(getSessionFactory());
				ProductHome proHome = new ProductHome(getSessionFactory());
				UserHome userHome = new UserHome(getSessionFactory());
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					cell = row.getCell(1);
					value = xls.getValue(cell);
					System.out.println(value);
					setStat(new Statistic());
//					// -------------dateReceived--------------
//					cell = row.getCell(InvoiceTable.dateReceived.value());
//					value = xls.getValue(cell);
//					getStat().setDateReceived((Date) value);
//					// ---------------------------
//					// -------------customerCodeLevel2--------------
//					cell = row.getCell(InvoiceTable.customerCodeLevel2.value());
//					value = xls.getValue(cell);
//					Customer cust = custHome.findCustomerByCode((String) value);
//					getStat().setCustomerByCustomerCodeLevel2(cust);
//					// ---------------------------
//					// -------------customerCodeLevel1--------------
//					cell = row.getCell(InvoiceTable.customerCodeLevel1.value());
//					value = xls.getValue(cell);
//					cust = custHome.findCustomerByCode((String) value);
//					getStat().setCustomerByCustomerCodeLevel1(cust);
//					// ---------------------------
//					// -------------productCode--------------
//					cell = row.getCell(InvoiceTable.productCode.value());
//					value = xls.getValue(cell);
//					Product pro = proHome.findProductByCode((String) value);
//					getStat().setProduct(pro);
//					// ---------------------------
//					// -------------totalBox--------------
//					cell = row.getCell(InvoiceTable.totalBox.value());
//					value = xls.getValue(cell);
//					getStat().setTotalBox(((Double) value).intValue());
//					// ---------------------------
//					// -------------quantiy--------------
//					cell = row.getCell(InvoiceTable.quantiy.value());
//					value = xls.getValue(cell);
//					getStat().setQuantity(((Double) value).intValue());
//					// ---------------------------
//					// -------------total--------------
//					cell = row.getCell(InvoiceTable.total.value());
//					value = xls.getValue(cell);
//					getStat().setTotal(BigDecimal.valueOf((Double) value));
//					// ---------------------------
//					// -------------total--------------
//					cell = row.getCell(InvoiceTable.userFullName.value());
//					value = xls.getValue(cell);
//					User user = userHome.getUserByFullName((String) value);
//					getStat().setUser(user);
//					// ---------------------------
//					
//					boolean isDuplicated = sttHome.isStatictisDuplicate(getStat().getDateReceived(), getStat().getCustomerByCustomerCodeLevel1().getId(), getStat().getCustomerByCustomerCodeLevel2().getId(), 
//							getStat().getProduct().getId(), getStat().getUser().getId());
//					if(!isDuplicated)
//						sttHome.attachDirty(getStat());
//					else
//						logDuplicate.append("<li>Warning: Row "+cell.getRowIndex()+ " duplicated</li>");
					setStat(new Statistic());
				}
				addActionMessage("<h3>Import complete!</h3><ul>"+logDuplicate+"<ul>");
			} catch (Exception e) {
				addActionError("Error: " + e.getMessage() + " with Row: " + cell.getRowIndex() + "; Column: " + cell.getColumnIndex() + "; Value: " + value);
			} finally {
				if (theFile.exists())
					FileUtils.deleteQuietly(theFile);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			addActionError("Error: "+e1.getMessage());
		}
		return SUCCESS;
	}

	public String importStatisticLevel2() {
		try {
			StringBuilder logDuplicate = new StringBuilder();
			File theFile = new File(getUploadFileName());
			FileUtils.copyFile(getUpload(), theFile);
			Cell cell = null;
			Object value = null;
			try (FileInputStream fis = new FileInputStream(theFile)) {
				ExcelUtil xls = new ExcelUtil();
				StatisticHome sttHome = new StatisticHome(getSessionFactory());
				CustomerHome custHome = new CustomerHome(getSessionFactory());
				ProductHome proHome = new ProductHome(getSessionFactory());
				UserHome userHome = new UserHome(getSessionFactory());
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					setStat(new Statistic());
					// -------------dateReceived--------------
					cell = row.getCell(InvoiceTable.dateReceived.value());
					value = xls.getValue(cell);
					getStat().setDateReceived((Date) value);
					// ---------------------------
					// -------------customerCodeLevel2--------------
					cell = row.getCell(InvoiceTable.customerCodeLevel2.value());
					value = xls.getValue(cell);
					Customer cust = custHome.findCustomerByCode((String) value);
					getStat().setCustomerByCustomerCodeLevel2(cust);
					// ---------------------------
					// -------------customerCodeLevel1--------------
					cell = row.getCell(InvoiceTable.customerCodeLevel1.value());
					value = xls.getValue(cell);
					cust = custHome.findCustomerByCode((String) value);
					getStat().setCustomerByCustomerCodeLevel1(cust);
					// ---------------------------
					// -------------productCode--------------
					cell = row.getCell(InvoiceTable.productCode.value());
					value = xls.getValue(cell);
					Product pro = proHome.findProductByCode((String) value);
					getStat().setProduct(pro);
					// ---------------------------
					// -------------totalBox--------------
					cell = row.getCell(InvoiceTable.totalBox.value());
					value = xls.getValue(cell);
					getStat().setTotalBox(((Double) value).intValue());
					// ---------------------------
					// -------------quantiy--------------
					cell = row.getCell(InvoiceTable.quantiy.value());
					value = xls.getValue(cell);
					getStat().setQuantity(((Double) value).intValue());
					// ---------------------------
					// -------------total--------------
					cell = row.getCell(InvoiceTable.total.value());
					value = xls.getValue(cell);
					getStat().setTotal(BigDecimal.valueOf((Double) value));
					// ---------------------------
					// -------------total--------------
					cell = row.getCell(InvoiceTable.userFullName.value());
					value = xls.getValue(cell);
					User user = userHome.getUserByFullName((String) value);
					getStat().setUser(user);
					// ---------------------------
					
					boolean isDuplicated = sttHome.isStatictisDuplicate(getStat().getDateReceived(), getStat().getCustomerByCustomerCodeLevel1().getId(), getStat().getCustomerByCustomerCodeLevel2().getId(), 
							getStat().getProduct().getId(), getStat().getUser().getId());
					if(!isDuplicated)
						sttHome.attachDirty(getStat());
					else
						logDuplicate.append("<li>Warning: Row "+cell.getRowIndex()+ " duplicated</li>");
					setStat(new Statistic());
				}
				addActionMessage("<h3>Import complete!</h3><ul>"+logDuplicate+"<ul>");
			} catch (Exception e) {
				addActionError("Error: " + e.getMessage() + " with Row: " + cell.getRowIndex() + "; Column: " + cell.getColumnIndex() + "; Value: " + value);
			} finally {
				if (theFile.exists())
					FileUtils.deleteQuietly(theFile);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			addActionError("Error: "+e1.getMessage());
		}
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

	public List<Statistic> getStats() {
		return statistics;
	}

	public void getStats(List<Statistic> statistics) {
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

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public int getStatId() {
		return statId;
	}

	public void getStatId(int statId) {
		this.statId = statId;
	}

	public Customer getCusLevel1() {
		return cusLevel1;
	}

	public void setCusLevel1(Customer cusLevel1) {
		this.cusLevel1 = cusLevel1;
	}

	public Customer getCusLevel2() {
		return cusLevel2;
	}

	public void setCusLevel2(Customer cusLevel2) {
		this.cusLevel2 = cusLevel2;
	}

	public User getEmp() {
		return emp;
	}

	public void setEmp(User emp) {
		this.emp = emp;
	}

	public Product getPro() {
		return pro;
	}

	public void setPro(Product pro) {
		this.pro = pro;
	}

	public Statistic getStat() {
		return stat;
	}

	public void setStat(Statistic stat) {
		this.stat = stat;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public void setStatId(int statId) {
		this.statId = statId;
	}

	public ArrayList<String> getDistricts() {
		return districts;
	}

	public void setDistricts(ArrayList<String> districts) {
		this.districts = districts;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

}
