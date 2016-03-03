package com.home.action;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.home.dao.GroupCustomerHome;
import com.home.dao.UserHome;
import com.home.model.Customer;
import com.home.model.GroupCustomer;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements Action, ModelDriven<Customer>, ServletContextAware, ServletRequestAware {
	public String customerId;
	private Customer customer = new Customer();
	private List<Customer> customers = new ArrayList<Customer>();
	private String lookupEmployeeForCus;
	public List<String> listLookupEmployeeForCus = new ArrayList<>();
	private List<GroupCustomer> listGroupCustomer = new ArrayList<>();
	private int grpCusId;
	private ServletContext ctx;
	private HttpServletRequest request;
	private Workbook workbook;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	public 	int yearNow = (Calendar.getInstance()).get(Calendar.YEAR);
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
		return getCustomer();
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.setCtx(sc);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);

	}
	public SessionFactory getSessionFactory(){
		return HibernateUtil.getSessionFactory();
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String listCustomer() throws Exception {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			setCustomers(cusHome.getListCustomer());
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	@Override
	public void validate() {
		
	}
	public String findCustomer(){
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			Integer id = Integer.parseInt(getCustomerId());
			setCustomer(cusHome.findById(id));
			System.out.println(getCustomer().getCustomerCode());
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
	
	public String notifyListCutomer() throws Exception{
		try {
//			CustomerHome cusHome = new CustomerHome(getSessionFactory());
//			Customer customer = cusHome.findById(id);
//			cusHome.delete(customer);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	public String listLookupEmployeeForCus(){
		try {
			GroupCustomerHome grpCusHome = new GroupCustomerHome(getSessionFactory());
			listGroupCustomer = grpCusHome.getListGrpCustomer();
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
			getCustomer().setUser(user);
			GroupCustomer grpCus = new GroupCustomer();
			grpCus.setId(grpCusId);
			getCustomer().setGroupCustomer(grpCus);
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			cusHome.attachDirty(getCustomer());
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
	public String importCustomer() throws Exception {
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
				setCustomer(new Customer());
				getCustomer().setCustomerCode((String)xls.getValue(row.getCell(CustomerTable.customerCode.value())));
				getCustomer().setBusinessName((String)xls.getValue(row.getCell(CustomerTable.businessName.value())));
				getCustomer().setCreateTime((Date)xls.getValue(row.getCell(CustomerTable.createTime.value())));
				getCustomer().setCertificateNumber((String)xls.getValue(row.getCell(CustomerTable.certificateNumber.value())));
				getCustomer().setCertificateDate((Date)xls.getValue(row.getCell(CustomerTable.certificateDate.value())));
				getCustomer().setCertificateAddress((String)xls.getValue(row.getCell(CustomerTable.certificateAddress.value())));
				getCustomer().setTaxNumber((String)xls.getValue(row.getCell(CustomerTable.taxNumber.value())));
				getCustomer().setBudgetRegister((Integer)xls.getValue(row.getCell(CustomerTable.budgetRegister.value())));
				getCustomer().setTelefone((String)xls.getValue(row.getCell(CustomerTable.telefone.value())));
				getCustomer().setFax((String)xls.getValue(row.getCell(CustomerTable.fax.value())));
				getCustomer().setEmail((String)xls.getValue(row.getCell(CustomerTable.email.value())));
				getCustomer().setSocialAddress((String)xls.getValue(row.getCell(CustomerTable.socialAddress.value())));
				getCustomer().setBusinessAddress((String)xls.getValue(row.getCell(CustomerTable.businessAddress.value())));
				getCustomer().setAdviser((String)xls.getValue(row.getCell(CustomerTable.adviser.value())));
				getCustomer().setDirector((String)xls.getValue(row.getCell(CustomerTable.director.value())));
				getCustomer().setDirectorMobile((String)xls.getValue(row.getCell(CustomerTable.directorMobile.value())));
				getCustomer().setDirectorBirthday((Date)xls.getValue(row.getCell(CustomerTable.directorBirthday.value())));
				getCustomer().setDirectorDomicile((String)xls.getValue(row.getCell(CustomerTable.directorDomicile.value())));
				getCustomer().setSellMan((String)xls.getValue(row.getCell(CustomerTable.sellMan.value())));
				getCustomer().setSellManMobile((String)xls.getValue(row.getCell(CustomerTable.sellManMobile.value())));
				getCustomer().setBudgetOriginal((Integer)xls.getValue(row.getCell(CustomerTable.budgetOriginal.value())));
				getCustomer().setOtherBusiness((String)xls.getValue(row.getCell(CustomerTable.otherBusiness.value())));
				
				getCustomer().setCustomer1Level1((String)xls.getValue(row.getCell(CustomerTable.customer1Level1.value())));
				getCustomer().setCustomer1Phone((String)xls.getValue(row.getCell(CustomerTable.customer1Phone.value())));
				getCustomer().setCustomer1Percent((String)xls.getValue(row.getCell(CustomerTable.customer1Percent.value())));
				
				getCustomer().setCustomer2Level1((String)xls.getValue(row.getCell(CustomerTable.customer2Level1.value())));
				getCustomer().setCustomer2Phone((String)xls.getValue(row.getCell(CustomerTable.customer2Phone.value())));
				getCustomer().setCustomer2Percent((String)xls.getValue(row.getCell(CustomerTable.customer2Percent.value())));
				
				getCustomer().setCustomer3Level1((String)xls.getValue(row.getCell(CustomerTable.customer3Level1.value())));
				getCustomer().setCustomer3Phone((String)xls.getValue(row.getCell(CustomerTable.customer3Phone.value())));
				getCustomer().setCustomer3Percent((String)xls.getValue(row.getCell(CustomerTable.customer3Percent.value())));
				
				getCustomer().setCustomer4Level1((String)xls.getValue(row.getCell(CustomerTable.customer4Level1.value())));
				getCustomer().setCustomer4Phone((String)xls.getValue(row.getCell(CustomerTable.customer4Phone.value())));
				getCustomer().setCustomer4Percent((String)xls.getValue(row.getCell(CustomerTable.customer4Percent.value())));
				
				getCustomer().setCustomer5Level1((String)xls.getValue(row.getCell(CustomerTable.customer5Level1.value())));
				getCustomer().setCustomer5Phone((String)xls.getValue(row.getCell(CustomerTable.customer5Phone.value())));
				getCustomer().setCustomer5Percent((String)xls.getValue(row.getCell(CustomerTable.customer5Percent.value())));
				
				getCustomer().setRevenue1((String)xls.getValue(row.getCell(CustomerTable.revenue1.value())));
				getCustomer().setRevenue2((String)xls.getValue(row.getCell(CustomerTable.revenue2.value())));
				getCustomer().setPercentProvide1((String)xls.getValue(row.getCell(CustomerTable.percentProvide1.value())));
				getCustomer().setPercentProvide2((String)xls.getValue(row.getCell(CustomerTable.percentProvide2.value())));
				getCustomer().setPercentProvide3((String)xls.getValue(row.getCell(CustomerTable.percentProvide3.value())));
				getCustomer().setPercentProvide4((String)xls.getValue(row.getCell(CustomerTable.percentProvide4.value())));
				getCustomer().setProductSell((String)xls.getValue(row.getCell(CustomerTable.productSell.value())));
				getCustomer().setProduct1Hot((String)xls.getValue(row.getCell(CustomerTable.product1Hot.value())));
				getCustomer().setProduct2Hot((String)xls.getValue(row.getCell(CustomerTable.product2Hot.value())));
				getCustomer().setProduct3Hot((String)xls.getValue(row.getCell(CustomerTable.product3Hot.value())));
				getCustomer().setProduct4Hot((String)xls.getValue(row.getCell(CustomerTable.product4Hot.value())));
				getCustomer().setProduct5Hot((String)xls.getValue(row.getCell(CustomerTable.product5Hot.value())));
				getCustomer().setProduct6Hot((String)xls.getValue(row.getCell(CustomerTable.product6Hot.value())));
				
				getCustomer().setFarmProduct1((String)xls.getValue(row.getCell(CustomerTable.farmProduct1.value())));
				getCustomer().setFarmProduct1Session((String)xls.getValue(row.getCell(CustomerTable.farmProduct1Session.value())));
				
				getCustomer().setFarmProduct2((String)xls.getValue(row.getCell(CustomerTable.farmProduct2.value())));
				getCustomer().setFarmProduct2Session((String)xls.getValue(row.getCell(CustomerTable.farmProduct2Session.value())));
				
				getCustomer().setFarmProduct3((String)xls.getValue(row.getCell(CustomerTable.farmProduct3.value())));
				getCustomer().setFarmProduct3Session((String)xls.getValue(row.getCell(CustomerTable.farmProduct3Session.value())));
				
				getCustomer().setFarmProduct4((String)xls.getValue(row.getCell(CustomerTable.farmProduct4.value())));
				getCustomer().setFarmProduct4Session((String)xls.getValue(row.getCell(CustomerTable.farmProduct4Session.value())));
				
				getCustomer().setUser(userHome.getUserByFullName((String)xls.getValue(row.getCell(CustomerTable.userFullName.value()))));
				
				addCustomer();
				setCustomer(null);
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

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<GroupCustomer> getListGroupCustomer() {
		return listGroupCustomer;
	}

	public void setListGroupCustomer(List<GroupCustomer> listGroupCustomer) {
		this.listGroupCustomer = listGroupCustomer;
	}

	public int getGrpCusId() {
		return grpCusId;
	}

	public void setGrpCusId(int grpCusId) {
		this.grpCusId = grpCusId;
	}

	public ServletContext getCtx() {
		return ctx;
	}

	public void setCtx(ServletContext ctx) {
		this.ctx = ctx;
	}
}
