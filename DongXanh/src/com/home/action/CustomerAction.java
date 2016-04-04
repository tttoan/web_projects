package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.hibernate.SessionFactory;
import com.home.conts.CustomerTable;
import com.home.dao.CustomerHome;
import com.home.dao.GroupCustomerHome;
import com.home.dao.UserHome;
import com.home.entities.City;
import com.home.entities.UserAware;
import com.home.model.Customer;
import com.home.model.GroupCustomer;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements Action, ModelDriven<Customer>, UserAware {
	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	private int custId = 0;
	private String varCityCode = "";
	private Customer cust = new Customer();
	public List<String> listLookupEmployeeForCus = new ArrayList<>();
	private List<Customer> customers = new ArrayList<>();
	private int grpCusId;
	private ServletContext ctx;
	private HttpServletRequest request;
	private Workbook workbook;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private User emp = new User();
	public int yearNow = (Calendar.getInstance()).get(Calendar.YEAR);
	private List<Customer> listCustomer = new ArrayList<>();
	private List<User> listEmployee = new ArrayList<>();
	private List<City> listCity = new ArrayList<>();
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
	private String commonCusPhone = "";
	private User userSes;

	public String retrievePhoneById() throws Exception {
		int commonCusId = 0;
		if (cus1Level1Id != 0)
			commonCusId = cus1Level1Id;
		else if (cus2Level1Id != 0)
			commonCusId = cus2Level1Id;
		else if (cus3Level1Id != 0)
			commonCusId = cus3Level1Id;
		else if (cus4Level1Id != 0)
			commonCusId = cus4Level1Id;
		else if (cus5Level1Id != 0)
			commonCusId = cus5Level1Id;
		CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
		Customer record = cusHome.findById(commonCusId);
		if (record != null)
			commonCusPhone = record.getTelefone();
		else
			commonCusPhone = "";
		return SUCCESS;
	}

	@Override
	public Customer getModel() {
		cust = new Customer();
		cust.setCustomer1Percent((float) 0);
		cust.setCustomer2Percent((float) 0);
		cust.setCustomer3Percent((float) 0);
		cust.setCustomer4Percent((float) 0);
		cust.setCustomer5Percent((float) 0);
		cust.setFarmProduct1((float) 0);
		cust.setFarmProduct2((float) 0);
		cust.setFarmProduct3((float) 0);
		cust.setFarmProduct4((float) 0);
		cust.setFarmProduct1Session("");
		cust.setFarmProduct2Session("");
		cust.setFarmProduct3Session("");
		cust.setFarmProduct4Session("");
		cust.setCustomerCode("");
		return cust;
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public String execute() throws Exception {
		if (custId != 0) {
			try {
				CustomerHome cusHome = new CustomerHome(getSessionFactory());
				setCust(cusHome.findById(custId));
				varCityCode = getCust().getCustomerCode().substring(0, 2);
				setEdit(true);
			} catch (Exception e) {
				throw e;
			}
		} else {
			getModel();
			cust.setCustomerCode(generateCustomerCode());
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		try {
			loadLookupEmployee();
			loadLookupCustomer();
			loadLookupGrpCustomer();
			loadLookupCity();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadLookupCity() {
		try {
			ServletContext servletContext = ServletActionContext.getServletContext();
			String pathname = servletContext.getRealPath("/WEB-INF/template/excel/country.xlsx");
			File theFile = new File(pathname);
			ExcelUtil xls = new ExcelUtil();
			listCity = new ArrayList<City>();
			try (FileInputStream fis = new FileInputStream(theFile)) {
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheet("city");
				for(int i = 0; i< sheet.getPhysicalNumberOfRows(); i++){
					Row row = sheet.getRow(i);
					Cell code = row.getCell(0);
					Cell name = row.getCell(1);
					listCity.add(new City(xls.getValue(code).toString(), xls.getValue(name).toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup citys. Exception: " + e.getMessage());
		}
	}

	public void loadLookupCustomer() {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			listCustomer = cusHome.getListCustomer();
			GroupCustomerHome groupCusHome = new GroupCustomerHome(HibernateUtil.getSessionFactory());
			HashMap<Integer, String> groups = groupCusHome.getListGroupCustomer();
			for (Customer customer : listCustomer) {
				if (customer.getGroupCustomer() != null) {
					GroupCustomer g = new GroupCustomer();
					g.setId(customer.getGroupCustomer().getId());
					g.setGroupName(groups.get(customer.getGroupCustomer().getId()));
					customer.setGroupCustomer(g);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup customers. Exception: " + e.getMessage());
		}
	}

	public void loadLookupEmployee() {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			listEmployee = userHome.getListUser();
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup customers. Exception: " + e.getMessage());
		}

	}

	public void loadLookupGrpCustomer() {
		try {
			GroupCustomerHome grpCusHome = new GroupCustomerHome(getSessionFactory());
			listGrpCus = grpCusHome.getListGrpCustomer();
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup group of customers. Exception: " + e.getMessage());
		}

	}

	public String findAllCustomer() {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			customers = cusHome.findAll();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String findCustomer() {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			setCust(cusHome.findById(custId));
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String deleteCustomerById() throws Exception {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			Customer customer = cusHome.findById(custId);
			cusHome.delete(customer);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	private String generateCustomerCode() throws Exception {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			String sId = cusHome.getMaxId() + "";
			for (int i = sId.length(); i < 3; i++) {
				sId = "0" + sId;
			}
			return sId;
		} catch (Exception e) {
			throw e;
		}
	}

	public String notifyListCutomer() throws Exception {
		try {
			// CustomerHome cusHome = new CustomerHome(getSessionFactory());
			// Customer customer = cusHome.findById(id);
			// cusHome.delete(customer);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String addCustomer() throws Exception {
		try {
			if (emp.getId() > 0)
				getCust().setUser(emp);
			if (grpCustomer.getId() > 0)
				getCust().setGroupCustomer(grpCustomer);
			if (cus1Level1.getId() > 0)
				getCust().setCustomerByCustomer1Level1Id(cus1Level1);
			if (cus2Level1.getId() > 0)
				getCust().setCustomerByCustomer2Level1Id(cus2Level1);
			if (cus3Level1.getId() > 0)
				getCust().setCustomerByCustomer3Level1Id(cus3Level1);
			if (cus4Level1.getId() > 0)
				getCust().setCustomerByCustomer4Level1Id(cus4Level1);
			if (cus5Level1.getId() > 0)
				getCust().setCustomerByCustomer5Level1Id(cus5Level1);
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			if (getCust().getId() == 0)
				cusHome.attachDirty(getCust());
			else
				cusHome.updateDirty(getCust());
			return SUCCESS;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
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
				cust = new Customer();
				cust.setCustomerCode((String) xls.getValue(row.getCell(CustomerTable.customerCode.value())));
				cust.setBusinessName((String) xls.getValue(row.getCell(CustomerTable.businessName.value())));
				cust.setCreateTime((Date) xls.getValue(row.getCell(CustomerTable.createTime.value())));
				cust.setCertificateNumber((String) xls.getValue(row.getCell(CustomerTable.certificateNumber.value())));
				cust.setCertificateDate((Date) xls.getValue(row.getCell(CustomerTable.certificateDate.value())));
				cust.setCertificateAddress((String) xls.getValue(row.getCell(CustomerTable.certificateAddress.value())));
				cust.setTaxNumber((String) xls.getValue(row.getCell(CustomerTable.taxNumber.value())));
				// cust.setBudgetRegister((Integer)xls.getValue(row.getCell(CustomerTable.budgetRegister.value())));
				cust.setTelefone((String) xls.getValue(row.getCell(CustomerTable.telefone.value())));
				cust.setFax((String) xls.getValue(row.getCell(CustomerTable.fax.value())));
				cust.setEmail((String) xls.getValue(row.getCell(CustomerTable.email.value())));
				cust.setSocialAddress((String) xls.getValue(row.getCell(CustomerTable.socialAddress.value())));
				cust.setBusinessAddress((String) xls.getValue(row.getCell(CustomerTable.businessAddress.value())));
				cust.setAdviser((String) xls.getValue(row.getCell(CustomerTable.adviser.value())));
				cust.setDirector((String) xls.getValue(row.getCell(CustomerTable.director.value())));
				cust.setDirectorMobile((String) xls.getValue(row.getCell(CustomerTable.directorMobile.value())));
				cust.setDirectorBirthday((Date) xls.getValue(row.getCell(CustomerTable.directorBirthday.value())));
				cust.setDirectorDomicile((String) xls.getValue(row.getCell(CustomerTable.directorDomicile.value())));
				cust.setSellMan((String) xls.getValue(row.getCell(CustomerTable.sellMan.value())));
				cust.setSellManMobile((String) xls.getValue(row.getCell(CustomerTable.sellManMobile.value())));
				cust.setBudgetOriginal((Integer) xls.getValue(row.getCell(CustomerTable.budgetOriginal.value())));
				cust.setOtherBusiness((String) xls.getValue(row.getCell(CustomerTable.otherBusiness.value())));

				// cust.setCustomer1Level1((String)
				// xls.getValue(row.getCell(CustomerTable.customer1Level1.value())));
				// cust.setCustomer1Phone((String)
				// xls.getValue(row.getCell(CustomerTable.customer1Phone.value())));
				// //
				// cust.setCustomer1Percent((String)xls.getValue(row.getCell(CustomerTable.customer1Percent.value())));
				//
				// cust.setCustomer2Level1((String)
				// xls.getValue(row.getCell(CustomerTable.customer2Level1.value())));
				// cust.setCustomer2Phone((String)
				// xls.getValue(row.getCell(CustomerTable.customer2Phone.value())));
				// //
				// cust.setCustomer2Percent((String)xls.getValue(row.getCell(CustomerTable.customer2Percent.value())));
				//
				// cust.setCustomer3Level1((String)
				// xls.getValue(row.getCell(CustomerTable.customer3Level1.value())));
				// cust.setCustomer3Phone((String)
				// xls.getValue(row.getCell(CustomerTable.customer3Phone.value())));
				// //
				// cust.setCustomer3Percent((String)xls.getValue(row.getCell(CustomerTable.customer3Percent.value())));
				//
				// cust.setCustomer4Level1((String)
				// xls.getValue(row.getCell(CustomerTable.customer4Level1.value())));
				// cust.setCustomer4Phone((String)
				// xls.getValue(row.getCell(CustomerTable.customer4Phone.value())));
				// //
				// cust.setCustomer4Percent((String)xls.getValue(row.getCell(CustomerTable.customer4Percent.value())));
				//
				// cust.setCustomer5Level1((String)
				// xls.getValue(row.getCell(CustomerTable.customer5Level1.value())));
				// cust.setCustomer5Phone((String)
				// xls.getValue(row.getCell(CustomerTable.customer5Phone.value())));
				// cust.setCustomer5Percent((String)xls.getValue(row.getCell(CustomerTable.customer5Percent.value())));

				// cust.setRevenue1((String)xls.getValue(row.getCell(CustomerTable.revenue1.value())));
				// cust.setRevenue2((String)xls.getValue(row.getCell(CustomerTable.revenue2.value())));
				cust.setPercentProvide1((String) xls.getValue(row.getCell(CustomerTable.percentProvide1.value())));
				cust.setPercentProvide2((String) xls.getValue(row.getCell(CustomerTable.percentProvide2.value())));
				cust.setPercentProvide3((String) xls.getValue(row.getCell(CustomerTable.percentProvide3.value())));
				cust.setPercentProvide4((String) xls.getValue(row.getCell(CustomerTable.percentProvide4.value())));
				cust.setProductSell((String) xls.getValue(row.getCell(CustomerTable.productSell.value())));
				cust.setProduct1Hot((String) xls.getValue(row.getCell(CustomerTable.product1Hot.value())));
				cust.setProduct2Hot((String) xls.getValue(row.getCell(CustomerTable.product2Hot.value())));
				cust.setProduct3Hot((String) xls.getValue(row.getCell(CustomerTable.product3Hot.value())));
				cust.setProduct4Hot((String) xls.getValue(row.getCell(CustomerTable.product4Hot.value())));
				cust.setProduct5Hot((String) xls.getValue(row.getCell(CustomerTable.product5Hot.value())));
				cust.setProduct6Hot((String) xls.getValue(row.getCell(CustomerTable.product6Hot.value())));

				// cust.setFarmProduct1((String)xls.getValue(row.getCell(CustomerTable.farmProduct1.value())));
				cust.setFarmProduct1Session((String) xls.getValue(row.getCell(CustomerTable.farmProduct1Session.value())));

				// cust.setFarmProduct2((String)xls.getValue(row.getCell(CustomerTable.farmProduct2.value())));
				cust.setFarmProduct2Session((String) xls.getValue(row.getCell(CustomerTable.farmProduct2Session.value())));

				// cust.setFarmProduct3((String)xls.getValue(row.getCell(CustomerTable.farmProduct3.value())));
				cust.setFarmProduct3Session((String) xls.getValue(row.getCell(CustomerTable.farmProduct3Session.value())));

				// cust.setFarmProduct4((String)xls.getValue(row.getCell(CustomerTable.farmProduct4.value())));
				cust.setFarmProduct4Session((String) xls.getValue(row.getCell(CustomerTable.farmProduct4Session.value())));

				cust.setUser(userHome.getUserByFullName((String) xls.getValue(row.getCell(CustomerTable.userFullName.value()))));

				addCustomer();
				setCust(null);
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

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public User getUserSes() {
		return userSes;
	}

	@Override
	public void setUserSes(User user) {
		this.userSes = user;
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

	public String getVarCityCode() {
		return varCityCode;
	}

	public void setVarCityCode(String varCityCode) {
		this.varCityCode = varCityCode;
	}

	public List<City> getListCity() {
		return listCity;
	}

	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}
}
