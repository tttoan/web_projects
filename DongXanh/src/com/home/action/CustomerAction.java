package com.home.action;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
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
import com.home.dao.ProductHome;
import com.home.dao.UserHome;
import com.home.model.Customer;
import com.home.model.GroupCustomer;
import com.home.model.Product;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements Action, ModelDriven<Customer>, ServletContextAware, ServletRequestAware {
	private boolean edit = false;
	public int customerId;
	private Customer cus = new Customer();
	private List<Customer> customers = new ArrayList<Customer>();
	private String lookupEmployeeForCus;
	public List<String> listLookupEmployeeForCus = new ArrayList<>();
	private int grpCusId;
	private ServletContext ctx;
	private HttpServletRequest request;
	private Workbook workbook;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private User emp = new User();
	public 	int yearNow = (Calendar.getInstance()).get(Calendar.YEAR);
	private List<Customer> listCustomer = new ArrayList<>();
	private List<User> listEmployee = new ArrayList<>();
	private List<GroupCustomer> listGrpCus = new ArrayList<>();
	private GroupCustomer grpCustomer = new GroupCustomer();
	private Customer cus1Level1 = new Customer();
	private Customer cus2Level1 = new Customer();
	private Customer cus3Level1 = new Customer();
	private Customer cus4Level1 = new Customer();
	private Customer cus5Level1 = new Customer();
	private int cus1Level1Id;
	private int cus2Level1Id;
	private int cus3Level1Id;
	private int cus4Level1Id;
	private int cus5Level1Id;
	private String commonCusPhone;
	
	public String retrievePhoneById()  throws Exception {
		int commonCusId = 0;
		if(cus1Level1Id != 0)
			commonCusId = cus1Level1Id;
		else if(cus2Level1Id != 0)
			commonCusId = cus2Level1Id;
		else if(cus3Level1Id != 0)
			commonCusId = cus3Level1Id;
		else if(cus4Level1Id != 0)
			commonCusId = cus4Level1Id;
		else if(cus5Level1Id != 0)
			commonCusId = cus5Level1Id;
		CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
		Customer record = cusHome.findById(commonCusId);
		if(record != null)
			commonCusPhone = record.getTelefone();
		else 
			commonCusPhone = "";
		return SUCCESS;
	}
	
	public Customer getCus1Level1() {
		return cus1Level1;
	}
	public void setCus1Level1(Customer cus1Level1) {
		this.cus1Level1 = cus1Level1;
	}
	public Customer getCus2Level1() {
		return cus2Level1;
	}
	public void setCus2Level1(Customer cus2Level1) {
		this.cus2Level1 = cus2Level1;
	}
	public Customer getCus3Level1() {
		return cus3Level1;
	}
	public void setCus3Level1(Customer cus3Level1) {
		this.cus3Level1 = cus3Level1;
	}
	public Customer getCus4Level1() {
		return cus4Level1;
	}
	public void setCus4Level1(Customer cus4Level1) {
		this.cus4Level1 = cus4Level1;
	}
	public Customer getCus5Level1() {
		return cus5Level1;
	}
	public void setCus5Level1(Customer cus5Level1) {
		this.cus5Level1 = cus5Level1;
	}
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
		if (customerId != 0) {
			try {
				CustomerHome cusHome = new CustomerHome(getSessionFactory());
				cus = cusHome.findById(customerId);
				setEdit(true);
			} catch (Exception e) {
				throw e;
			}
		}
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
		loadLookupEmployee();
		loadLookupCustomer();
		loadLookupGrpCustomer();
	}
	
	public void loadLookupCustomer() {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			listCustomer = cusHome.getListCustomer();
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup customers. Exception: "+e.getMessage());
		}
	}

	public void loadLookupEmployee() {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			listEmployee = userHome.getListUser();
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup customers. Exception: "+e.getMessage());
		}
		
	}
	public void loadLookupGrpCustomer() {
		try {
			GroupCustomerHome grpCusHome = new GroupCustomerHome(getSessionFactory());
			listGrpCus = grpCusHome.getListGrpCustomer();
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup group of customers. Exception: "+e.getMessage());
		}
		
	}
	
	public String findCustomer(){
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			setCustomer(cusHome.findById(customerId));
			System.out.println(getCustomer().getCustomerCode());
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String deleteCustomerById() throws Exception {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			Customer customer = cusHome.findById(customerId);
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
			if(cus.getId() == 0)
				cusHome.attachDirty(getCustomer());
			else
				cusHome.updateDirty(cus);
			
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
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
		return cus;
	}

	public void setCustomer(Customer customer) {
		this.cus = customer;
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

	public User getEmp() {
		return emp;
	}

	public void setEmp(User emp) {
		this.emp = emp;
	}

	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public List<User> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<User> listEmployee) {
		this.listEmployee = listEmployee;
	}



	public boolean isEdit() {
		return edit;
	}



	public void setEdit(boolean edit) {
		this.edit = edit;
	}



	public List<GroupCustomer> getListGrpCus() {
		return listGrpCus;
	}



	public void setListGrpCus(List<GroupCustomer> listGrpCus) {
		this.listGrpCus = listGrpCus;
	}
	public GroupCustomer getGrpCustomer() {
		return grpCustomer;
	}
	public void setGrpCustomer(GroupCustomer grpCustomer) {
		this.grpCustomer = grpCustomer;
	}
	public String getCommonCusPhone() {
		return commonCusPhone;
	}
	public void setCommonCusPhone(String commonCusPhone) {
		this.commonCusPhone = commonCusPhone;
	}

	public int getCus1Level1Id() {
		return cus1Level1Id;
	}

	public void setCus1Level1Id(int cus1Level1Id) {
		this.cus1Level1Id = cus1Level1Id;
	}

	public int getCus2Level1Id() {
		return cus2Level1Id;
	}

	public void setCus2Level1Id(int cus2Level1Id) {
		this.cus2Level1Id = cus2Level1Id;
	}

	public int getCus3Level1Id() {
		return cus3Level1Id;
	}

	public void setCus3Level1Id(int cus3Level1Id) {
		this.cus3Level1Id = cus3Level1Id;
	}

	public int getCus4Level1Id() {
		return cus4Level1Id;
	}

	public void setCus4Level1Id(int cus4Level1Id) {
		this.cus4Level1Id = cus4Level1Id;
	}

	public int getCus5Level1Id() {
		return cus5Level1Id;
	}

	public void setCus5Level1Id(int cus5Level1Id) {
		this.cus5Level1Id = cus5Level1Id;
	}
}
