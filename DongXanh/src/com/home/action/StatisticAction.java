package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.home.dao.StatisticHome;
import com.home.dao.UserHome;
import com.home.model.Statistic;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class StatisticAction implements Action, ModelDriven<Statistic>, ServletContextAware, ServletRequestAware {
	private HttpServletRequest request;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String employeeName;
	public List<String> listEmployeeName = new ArrayList<>();
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

	private Statistic statistic = new Statistic();
	public List<Statistic> statistics = new ArrayList<Statistic>();
	private ServletContext ctx;
	private Workbook workbook;

	@Override
	public Statistic getModel() {
		return statistic;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String execute() throws Exception {
		return ERROR;
	}

	public SessionFactory getSessionFactory(){
		return HibernateUtil.getSessionFactory();
	}
	public String listInvoice() throws Exception {
		try {
			StatisticHome sttHome = new StatisticHome(getSessionFactory());
			statistics = sttHome.getListInvoice();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String listEmployeeName() throws Exception{
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			List<User> users = userHome.getListUser();
			for (User user : users) {
				listEmployeeName.add(user.getFullName());
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String addInvoice() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			User user = userHome.getUserByFullName(getEmployeeName());
			//statistic.setUser(user);
			StatisticHome sttHome = new StatisticHome(getSessionFactory());
			sttHome.attachDirty(statistic);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String importCustomer() throws Exception {
		return SUCCESS;
	}
	
	public String importInvoice() throws Exception {
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
				statistic = new Statistic();
				statistic.setDateReceived((Date)xls.getValue(row.getCell(InvoiceTable.dateReceived.value())));
				//statistic.setCustomerCodeLevel2((String) xls.getValue(row.getCell(InvoiceTable.customerCodeLevel2.value())));
				statistic.setCustomerNameLevel2((String) xls.getValue(row.getCell(InvoiceTable.customerNameLevel2.value())));
				//statistic.setCustomerCodeLevel1((String) xls.getValue(row.getCell(InvoiceTable.customerCodeLevel1.value())));
				statistic.setCustomerNameLevel1((String) xls.getValue(row.getCell(InvoiceTable.customerNameLevel1.value())));
				statistic.setProductCode((String) xls.getValue(row.getCell(InvoiceTable.productCode.value())));
				statistic.setCategoryName((String) xls.getValue(row.getCell(InvoiceTable.categoryName.value())));
				statistic.setProductName((String) xls.getValue(row.getCell(InvoiceTable.productName.value())));
				statistic.setTotalBox(((Double) xls.getValue(row.getCell(InvoiceTable.totalBox.value()))).intValue());
				statistic.setQuantiy(((Double) xls.getValue(row.getCell(InvoiceTable.quantiy.value()))).intValue());
				statistic.setUnitPrice(BigDecimal.valueOf((Double)xls.getValue(row.getCell(InvoiceTable.unitPrice.value()))));
				statistic.setTotal(BigDecimal.valueOf((Double) xls.getValue(row.getCell(InvoiceTable.total.value()))));
				//statistic.setUser(userHome.getUserByFullName((String)xls.getValue(row.getCell(InvoiceTable.userFullName.value()))));
				addInvoice();
				statistic = null;
			}
		} catch (Exception e) {
			throw e;
		}
		FileUtils.deleteQuietly(theFile);
		return SUCCESS;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
