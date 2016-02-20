package com.home.action;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.conts.CustomerTable;
import com.home.dao.CustomerHome;
import com.home.dao.UserHome;
import com.home.model.Customer;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction implements Action, ModelDriven<Customer>, ServletContextAware, ServletRequestAware {
	private String customerId;
	private Customer customer = new Customer();
	public List<Customer> customers = new ArrayList<Customer>();
	private String lookupEmployeeForCus;
	public List<String> listLookupEmployeeForCus = new ArrayList<>();
	private ServletContext ctx;
	private HttpServletRequest request;
	private Workbook workbook;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
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
	public Customer getModel() {
		return customer;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);

	}
	public SessionFactory getSessionFactory(){
		return (SessionFactory) ctx.getAttribute("SessionFactory");
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String listCustomer() throws Exception {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			customers = cusHome.getListCustomer();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String deleteCustomerById() throws Exception {
		try {
			Integer id = Integer.parseInt(getCustomerId());
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			Customer customer = cusHome.findById(id);
			cusHome.delete(customer);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	public String listLookupEmployeeForCus() throws Exception{
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			List<User> users = userHome.getListUser();
			for (User user : users) {
				listLookupEmployeeForCus.add(user.getFullName());
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	public String addCustomer() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			User user = userHome.getUserByFullName(getLookupEmployeeForCus());
			customer.setUser(user);
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			cusHome.attachDirty(customer);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
				customer = new Customer();
				customer.setCustomerCode((String)xls.getValue(row.getCell(CustomerTable.customerCode.value())));
				customer.setCustomerName((String)xls.getValue(row.getCell(CustomerTable.customerName.value())));
				customer.setCreateTime((Date)xls.getValue(row.getCell(CustomerTable.createTime.value())));
				customer.setCertificateNumber((String)xls.getValue(row.getCell(CustomerTable.certificateNumber.value())));
				customer.setCertificateDate((Date)xls.getValue(row.getCell(CustomerTable.certificateDate.value())));
				customer.setCertificateAddress((String)xls.getValue(row.getCell(CustomerTable.certificateAddress.value())));
				customer.setTaxNumber((String)xls.getValue(row.getCell(CustomerTable.taxNumber.value())));
				customer.setBudgetRegister((Integer)xls.getValue(row.getCell(CustomerTable.budgetRegister.value())));
				customer.setTelefone((String)xls.getValue(row.getCell(CustomerTable.telefone.value())));
				customer.setFax((String)xls.getValue(row.getCell(CustomerTable.fax.value())));
				customer.setEmail((String)xls.getValue(row.getCell(CustomerTable.email.value())));
				customer.setSocialAddress((String)xls.getValue(row.getCell(CustomerTable.socialAddress.value())));
				customer.setBusinessAddress((String)xls.getValue(row.getCell(CustomerTable.businessAddress.value())));
				customer.setAdviser((String)xls.getValue(row.getCell(CustomerTable.adviser.value())));
				customer.setDirector((String)xls.getValue(row.getCell(CustomerTable.director.value())));
				customer.setDirectorMobile((String)xls.getValue(row.getCell(CustomerTable.directorMobile.value())));
				customer.setDirectorBirthday((Date)xls.getValue(row.getCell(CustomerTable.directorBirthday.value())));
				customer.setDirectorDomicile((String)xls.getValue(row.getCell(CustomerTable.directorDomicile.value())));
				customer.setSellMan((String)xls.getValue(row.getCell(CustomerTable.sellMan.value())));
				customer.setSellManMobile((String)xls.getValue(row.getCell(CustomerTable.sellManMobile.value())));
				customer.setBudgetOriginal((Integer)xls.getValue(row.getCell(CustomerTable.budgetOriginal.value())));
				customer.setOtherBusiness((String)xls.getValue(row.getCell(CustomerTable.otherBusiness.value())));
				
				customer.setCustomer1Level1((String)xls.getValue(row.getCell(CustomerTable.customer1Level1.value())));
				customer.setCustomer1Phone((String)xls.getValue(row.getCell(CustomerTable.customer1Phone.value())));
				customer.setCustomer1Percent((String)xls.getValue(row.getCell(CustomerTable.customer1Percent.value())));
				
				customer.setCustomer2Level1((String)xls.getValue(row.getCell(CustomerTable.customer2Level1.value())));
				customer.setCustomer2Phone((String)xls.getValue(row.getCell(CustomerTable.customer2Phone.value())));
				customer.setCustomer2Percent((String)xls.getValue(row.getCell(CustomerTable.customer2Percent.value())));
				
				customer.setCustomer3Level1((String)xls.getValue(row.getCell(CustomerTable.customer3Level1.value())));
				customer.setCustomer3Phone((String)xls.getValue(row.getCell(CustomerTable.customer3Phone.value())));
				customer.setCustomer3Percent((String)xls.getValue(row.getCell(CustomerTable.customer3Percent.value())));
				
				customer.setCustomer4Level1((String)xls.getValue(row.getCell(CustomerTable.customer4Level1.value())));
				customer.setCustomer4Phone((String)xls.getValue(row.getCell(CustomerTable.customer4Phone.value())));
				customer.setCustomer4Percent((String)xls.getValue(row.getCell(CustomerTable.customer4Percent.value())));
				
				customer.setCustomer5Level1((String)xls.getValue(row.getCell(CustomerTable.customer5Level1.value())));
				customer.setCustomer5Phone((String)xls.getValue(row.getCell(CustomerTable.customer5Phone.value())));
				customer.setCustomer5Percent((String)xls.getValue(row.getCell(CustomerTable.customer5Percent.value())));
				
				customer.setRevenue1((String)xls.getValue(row.getCell(CustomerTable.revenue1.value())));
				customer.setRevenue2((String)xls.getValue(row.getCell(CustomerTable.revenue2.value())));
				customer.setPercentProvide1((String)xls.getValue(row.getCell(CustomerTable.percentProvide1.value())));
				customer.setPercentProvide2((String)xls.getValue(row.getCell(CustomerTable.percentProvide2.value())));
				customer.setPercentProvide3((String)xls.getValue(row.getCell(CustomerTable.percentProvide3.value())));
				customer.setPercentProvide4((String)xls.getValue(row.getCell(CustomerTable.percentProvide4.value())));
				customer.setProductSell((String)xls.getValue(row.getCell(CustomerTable.productSell.value())));
				customer.setProduct1Hot((String)xls.getValue(row.getCell(CustomerTable.product1Hot.value())));
				customer.setProduct2Hot((String)xls.getValue(row.getCell(CustomerTable.product2Hot.value())));
				customer.setProduct3Hot((String)xls.getValue(row.getCell(CustomerTable.product3Hot.value())));
				customer.setProduct4Hot((String)xls.getValue(row.getCell(CustomerTable.product4Hot.value())));
				customer.setProduct5Hot((String)xls.getValue(row.getCell(CustomerTable.product5Hot.value())));
				customer.setProduct6Hot((String)xls.getValue(row.getCell(CustomerTable.product6Hot.value())));
				
				customer.setFarmProduct1((String)xls.getValue(row.getCell(CustomerTable.farmProduct1.value())));
				customer.setFarmProduct1Session((String)xls.getValue(row.getCell(CustomerTable.farmProduct1Session.value())));
				
				customer.setFarmProduct2((String)xls.getValue(row.getCell(CustomerTable.farmProduct2.value())));
				customer.setFarmProduct2Session((String)xls.getValue(row.getCell(CustomerTable.farmProduct2Session.value())));
				
				customer.setFarmProduct3((String)xls.getValue(row.getCell(CustomerTable.farmProduct3.value())));
				customer.setFarmProduct3Session((String)xls.getValue(row.getCell(CustomerTable.farmProduct3Session.value())));
				
				customer.setFarmProduct4((String)xls.getValue(row.getCell(CustomerTable.farmProduct4.value())));
				customer.setFarmProduct4Session((String)xls.getValue(row.getCell(CustomerTable.farmProduct4Session.value())));
				
				customer.setUser(userHome.getUserByFullName((String)xls.getValue(row.getCell(CustomerTable.userFullName.value()))));
				
				addCustomer();
				customer = null;
			}
		} catch (Exception e) {
			throw e;
		}
		FileUtils.deleteQuietly(theFile);
		return SUCCESS;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getLookupEmployeeForCus() {
		return lookupEmployeeForCus;
	}

	public void setLookupEmployeeForCus(String lookupEmployeeForCus) {
		this.lookupEmployeeForCus = lookupEmployeeForCus;
	}
}
