package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
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
import org.apache.struts2.util.ServletContextAware;
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
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements Action, ModelDriven<Customer>, UserAware, ServletContextAware {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private static final long serialVersionUID = 1L;
	private List<String> listTableColumn = new ArrayList<String>();
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
	private File cusImageScan;
	private String cusImageScanContentType;
	private String cusImageScanFileName;
	private String varCreateTime = SDF.format(new Date());
	private String varCertificateDate = SDF.format(new Date());
	private String varDirectorBirthday = SDF.format(new Date());

	public String getVarCertificateDate() {
		return varCertificateDate;
	}

	public void setVarCertificateDate(String varCertificateDate) {
		this.varCertificateDate = varCertificateDate;
	}

	public String getVarDirectorBirthday() {
		return varDirectorBirthday;
	}

	public void setVarDirectorBirthday(String varDirectorBirthday) {
		this.varDirectorBirthday = varDirectorBirthday;
	}

	public String getVarCreateTime() {
		return varCreateTime;
	}

	public void setVarCreateTime(String varCreateTime) {
		this.varCreateTime = varCreateTime;
	}

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
				varCityCode = getCust().getCustomerCode().substring(0, getCust().getCustomerCode().length() - 3);
				varCreateTime = SDF.format(getCust().getCreateTime());
				varCertificateDate = SDF.format(getCust().getCertificateDate());
				varDirectorBirthday =  SDF.format(getCust().getDirectorBirthday());
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

	private void defineTableColumn(){
		listTableColumn = new ArrayList<String>();
		listTableColumn.add("STT");
		listTableColumn.add("Ngày lập");
		listTableColumn.add("Mã khách hàng");
		listTableColumn.add("Nhóm");
		listTableColumn.add("Nhân viên TT");
		listTableColumn.add("Tên bảng kê");
		listTableColumn.add("Tên doanh nghiệp");
		listTableColumn.add("Giấy phép ĐKKD số");
		listTableColumn.add("Ngày cấp");
		listTableColumn.add("Địa chỉ đăng kí KD");
		listTableColumn.add("Mã số thuế");
		listTableColumn.add("Vốn đăng kí");
		listTableColumn.add("Điện thoại bàn");
		listTableColumn.add("Fax");
		listTableColumn.add("Email");
		listTableColumn.add("Địa chỉ mạng xã hội");
		listTableColumn.add("Địa điểm kinh doanh");
		listTableColumn.add("Người đại diện pháp luật");
		listTableColumn.add("Người quyết định chính công việc");
		listTableColumn.add("ĐTDĐ Người quyết định");
		listTableColumn.add("Ngày sinh");
		listTableColumn.add("Nguyên quán");
		listTableColumn.add("Người bán hàng trực tiếp");
		listTableColumn.add("ĐTDĐ Người bán hàng");
		listTableColumn.add("Ước vốn tự có để kinh doanh");
		listTableColumn.add("Ngành nghề kinh doanh khác");
		listTableColumn.add("Cấp 1 (5)");
		listTableColumn.add("Tỉ lệ nhận (5)");
		listTableColumn.add("Cấp 1 (4)");
		listTableColumn.add("Tỉ lệ nhận (4)");
		listTableColumn.add("Cấp 1 (3)");
		listTableColumn.add("Tỉ lệ nhận (3)");
		listTableColumn.add("Cấp 1 (2)");
		listTableColumn.add("Tỉ lệ nhận (2)");
		listTableColumn.add("Cấp 1 (1)");
		listTableColumn.add("Tỉ lệ nhận (1)");
		listTableColumn.add("3 Sản phẩm thuốc trừ cỏ");
		listTableColumn.add("5 Sản phẩm thuốc trừ sâu");
		listTableColumn.add("3 Sản phẩm thuốc trừ rầy");
		listTableColumn.add("5 Sản phẩm thuốc trừ bệnh");
		listTableColumn.add("3 Sản phẩm kích thích sinh trưởng");
		listTableColumn.add("3 Sản phẩm thuốc trừ ốc");
		listTableColumn.add("Lúa (%)");
		listTableColumn.add("3 Mùa vụ Lúa");
		listTableColumn.add("Rau màu (%)");
		listTableColumn.add("3 Mùa vụ Rau màu");
		listTableColumn.add("Cây ăn trái (%)");
		listTableColumn.add("3 Mùa vụ Cây ăn trái");
		listTableColumn.add("Khác (%)");
		listTableColumn.add("3 Mùa vụ Khác");
	}
	
	@Override
	public void validate() {
		try {
			loadLookupEmployee();
			loadLookupCustomer();
			loadLookupGrpCustomer();
			loadLookupCity();
			defineTableColumn();
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
				for (int i = 0; i < sheet.getPhysicalNumberOfRows() - 1; i++) {
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
			listCustomer = cusHome.getLookupCustomer(custId);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup customers. Exception: " + e.getMessage());
		}
	}

	public void loadLookupEmployee() {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			listEmployee = userHome.getLookupEmployee();
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
			UserHome  userHome = new UserHome(getSessionFactory());
			GroupCustomerHome groupCusHome = new GroupCustomerHome(getSessionFactory());
			HashMap<Integer, String> groups = groupCusHome.getListGroupCustomer();
			HashMap<Integer, String> users = userHome.getFilterEmployee();
			for (Customer customer : customers) {
				if (customer.getGroupCustomer() != null) {
					GroupCustomer g = new GroupCustomer();
					g.setId(customer.getGroupCustomer().getId());
					g.setGroupName(groups.get(customer.getGroupCustomer().getId()));
					customer.setGroupCustomer(g);
					
					if(customer.getUser() !=null){
						User user = new User();
						user.setId(customer.getUser().getId());
						user.setFullName(users.get(customer.getUser().getId()));
						customer.setUser(user);
					}
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
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

	public String addCustomer() throws Exception {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			cust.setId(custId);
			cust.setCreateTime(SDF.parse(varCreateTime));
			cust.setCertificateDate(SDF.parse(varCertificateDate));
			cust.setDirectorBirthday(SDF.parse(varDirectorBirthday));
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
			// -----Upload image scan----
			if (getCusImageScanFileName() != null) {
				//File destFile = new File(SystemUtil.getValuePropertiesByKey("path.image.scan") + "\\", getCusImageScanFileName());
				String path_doc_scan = "path_doc_scan";
				String filePath = this.ctx.getRealPath("/").concat(path_doc_scan);
				File destFile = new File(filePath, getCusImageScanFileName());
				
				FileUtils.copyFile(cusImageScan, destFile, true);
				//cust.setPathDocScan(destFile.getAbsolutePath().replace("\\", "/"));
				cust.setPathDocScan(path_doc_scan + "/" + destFile.getName());
			}
			if (cust.getId() > 0) {
				cusHome.updateDirty(getCust());
			} else {
				cusHome.attachDirty(getCust());
				getModel();
			}
			return SUCCESS;
		} catch (Exception e) {
			cust.setCustomerCode(generateCustomerCode());
			addActionError(e.getMessage() + ".");
		}
		return INPUT;
	}

	public String importCustomer() throws Exception {
		try {
			StringBuilder logDuplicate = new StringBuilder();
			File theFile = new File(getUploadFileName());
			FileUtils.copyFile(getUpload(), theFile);
			Cell cell = null;
			Object value = null;
			try (FileInputStream fis = new FileInputStream(theFile)) {
				ExcelUtil xls = new ExcelUtil();
				CustomerHome custHome = new CustomerHome(getSessionFactory());
				UserHome userHome = new UserHome(getSessionFactory());
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					cust = new Customer();
					// -------------CreateTime--------------
					getCust().setCreateTime(new Date());
					// ---------------------------
					// -------------customerCode--------------
					cell = row.getCell(CustomerTable.customerCode.value());
					value = xls.getValue(cell);
					getCust().setCustomerCode((value + "").trim());
					// ---------------------------
					// -------------businessName--------------
					cell = row.getCell(CustomerTable.businessName.value());
					value = xls.getValue(cell);
					getCust().setBusinessName(value + "");
					// ---------------------------
					// -------------businessName--------------
					cell = row.getCell(CustomerTable.customerGroup.value());
					value = xls.getValue(cell);
					GroupCustomer gCust = new GroupCustomer();
					gCust.setId(((Double) value).intValue());
					getCust().setGroupCustomer(gCust);
					// ---------------------------
					// -------------certificateNumber--------------
					cell = row.getCell(CustomerTable.certificateNumber.value());
					value = xls.getValue(cell);
					getCust().setCertificateNumber(value + "");
					// ---------------------------
					// -------------certificateDate--------------
					cell = row.getCell(CustomerTable.certificateDate.value());
					value = xls.getValue(cell);
					getCust().setCertificateDate((Date) value);
					// ---------------------------
					// -------------certificateAddress--------------
					cell = row.getCell(CustomerTable.certificateAddress.value());
					value = xls.getValue(cell);
					getCust().setCertificateAddress(value + "");
					// ---------------------------
					// -------------taxNumber--------------
					cell = row.getCell(CustomerTable.taxNumber.value());
					value = xls.getValue(cell);
					getCust().setTaxNumber(value + "");
					// ---------------------------
					// -------------certificateNumber--------------
					cell = row.getCell(CustomerTable.employee.value());
					value = xls.getValue(cell);
					getCust().setUser(userHome.getUserByFullName((value + "").trim()));
					// ---------------------------
					boolean isExisted = custHome.isCustomerExist(getCust().getCustomerCode());
					if (!isExisted)
						custHome.attachDirty(getCust());
					else
						logDuplicate.append("<li>Cảnh báo: Dữ liệu dòng " + (cell.getRowIndex() + 1) + " đã được cập nhật rồi!</li>");
					setCust(new Customer());
				}
				addActionMessage("<h3>Cập nhật hoàn thành</h3><ul>" + logDuplicate + "</ul>");
			} catch (Exception e) {
				addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + e.getMessage() + " ở dòng: " + (cell.getRowIndex() + 1) + ", cột: " + (cell.getColumnIndex() + 1) + ", giá trị: " + value
						+ "</li></ul>");
			} finally {
				if (theFile.exists())
					FileUtils.deleteQuietly(theFile);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + ex.getMessage() + "</ul></li>");
		}
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

	public File getCusImageScan() {
		return cusImageScan;
	}

	public void setCusImageScan(File cusImageScan) {
		this.cusImageScan = cusImageScan;
	}

	public String getCusImageScanContentType() {
		return cusImageScanContentType;
	}

	public void setCusImageScanContentType(String cusImageScanContentType) {
		this.cusImageScanContentType = cusImageScanContentType;
	}

	public String getCusImageScanFileName() {
		return cusImageScanFileName;
	}

	public void setCusImageScanFileName(String cusImageScanFileName) {
		this.cusImageScanFileName = cusImageScanFileName;
	}
	
	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}

	public List<String> getListTableColumn() {
		return listTableColumn;
	}

	public void setListTableColumn(List<String> listTableColumn) {
		this.listTableColumn = listTableColumn;
	}

	public String notifyListCutomer(){
		return SUCCESS;
	}
}
