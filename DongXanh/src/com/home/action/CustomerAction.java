package com.home.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
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

import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.GroupCustomerHome;
import com.home.dao.UserHome;
import com.home.entities.City;
import com.home.entities.DefineColumnImport;
import com.home.entities.UserAware;
import com.home.model.Customer;
import com.home.model.GroupCustomer;
import com.home.model.GroupCustomerDetail;
import com.home.model.User;
import com.home.util.DateUtils;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements Action, ModelDriven<Customer>, UserAware, ServletContextAware {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private static final long serialVersionUID = 1L;
	private List<Object[]> listTableColumn = new ArrayList<Object[]>();
	private boolean edit = false;
	private int custId = 0;
	//private String varCityCode = "";
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
	private List<Customer> listCustomerL1 = new ArrayList<>();
	private List<User> listEmployee = new ArrayList<>();
	private List<City> listCity = new ArrayList<>();
	private List<GroupCustomer> listGrpCus = new ArrayList<>();
	private  List<GroupCustomerDetail> listGrpCusdetail = new ArrayList<GroupCustomerDetail>();
	private List<String> listtest = new ArrayList<String>();
	private GroupCustomer grpCustomer1 = new GroupCustomer();
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
	private File[] cusImageScan;
	private String cusImageScanContentType;
	private String cusImageScanFileName;
	
	private File[] cusImageSignature;
	private String varCreateTime = SDF.format(new Date());
	private String varCertificateDate ;//= SDF.format(new Date());
	private String varDirectorBirthday ;//= SDF.format(new Date());
	private String varBirthday;//= SDF.format(new Date());
	
	private List<DefineColumnImport> listDefineColumnsLevel1;
	private List<DefineColumnImport> listDefineColumnsLevel2;
	private List<String> listColumnExcel;
	private int totalRecordExcel = 0;
	private int processIndexExcel = 0;
	private String varFieldEntName;
	private String varIndexColumn;
	private String varIndexRow = "3";
	private String cusCodeGen;
	
	private BufferedImage bufferedImage ;
	private Properties properties ;
	private int images_x; 
	private int imgase_y;
	private int images_width;
	private int images_height;
	private String images_signature;

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
		cust.setGroupCustomer(new GroupCustomer());
		cust.getGroupCustomer().setId(MyConts.CUS_L2);
		return cust;
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public String execute() throws Exception {
		try {
			System.out.println("151===================123454");
			if(null==properties){
				properties = getProperties();
			}
			if (custId != 0) {
				try {
					CustomerHome cusHome = new CustomerHome(getSessionFactory());
					Customer cusTemp = cusHome.findById(custId);
					setCust(cusTemp);
					//varCityCode = getCust().getCustomerCode().substring(0, getCust().getCustomerCode().length() - 3);
					if(getCust().getCreateTime() != null){
						varCreateTime = SDF.format(getCust().getCreateTime());
					}
					if(getCust().getCertificateDate() != null){
						varCertificateDate = SDF.format(getCust().getCertificateDate());
					}
					if(getCust().getDirectorBirthday() != null){
						varDirectorBirthday = SDF.format(getCust().getDirectorBirthday());
					}
					//Start:
					if(getCust().getBirthday() != null){
						varBirthday = SDF.format(getCust().getBirthday());
					}
					//End:
					setEdit(true);
				} catch (Exception e) {
					throw e;
				}
			} else {
				getModel();				
				cust.setCustomerCode(cusCodeGen);
			}
		//	String setImages_signature = "path_doc_scan/DT104_0_1498276593379.png";
		//	cust.setImages_signature(setImages_signature);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: int execute() method. Exception: " + e.getMessage());
		}
		return SUCCESS;
	}
	

	private void generateColumnExcel() {
		listColumnExcel = new ArrayList<String>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (char a : alphabet) {
			listColumnExcel.add("Cột " + (a + "").toUpperCase());
		}
	}

	private void defineColumnImportLevel2() {
		setListDefineColumnsLevel2(new ArrayList<DefineColumnImport>());
		DefineColumnImport dci = new DefineColumnImport("Mã khách hàng", "customerCode", "1");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Tên Bảng Kê", "statisticName", "2");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Nhóm", "groupCustomer", "3");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Cấp 1 đang nhận hành chính", "customerByCustomer1Level1Id", "4");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Tên doanh nghiệp", "businessName", "5");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Tên thường gọi", "otherBusiness", "6");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Giấy phép ĐKKD", "certificateNumber", "7");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Ngày cấp giấy phép ĐKKD", "certificateDate", "8");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Người đại diện pháp luật", "lawyer", "9");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Địa chỉ kinh doanh", "businessAddress", "10");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Mã số thuế", "taxNumber", "11");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Vốn đăng kí", "budgetRegister", "12");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Điện thoại bàn", "telefone", "13");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Fax", "fax", "14");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Người quyết định chính công việc", "director", "15");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("ĐT di động người QĐCV", "directorMobile", "16");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Ngày sinh", "directorBirthday", "17");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Người bán hàng trực tiếp", "sellMan", "18");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("ĐT di động người bán hàng", "sellManMobile", "19");
		getListDefineColumnsLevel2().add(dci);
	}

	private void defineColumnImportLevel1() {
		setListDefineColumnsLevel1(new ArrayList<DefineColumnImport>());
		DefineColumnImport dci = new DefineColumnImport("Mã khách hàng", "customerCode", "2");
		getListDefineColumnsLevel1().add(dci);
		dci = new DefineColumnImport("Tên khách hàng", "businessName", "3");
		getListDefineColumnsLevel1().add(dci);
		dci = new DefineColumnImport("Tên Bảng Kê", "statisticName", "4");
		getListDefineColumnsLevel1().add(dci);
		dci = new DefineColumnImport("Địa chỉ", "businessAddress", "5");
		getListDefineColumnsLevel1().add(dci);
		dci = new DefineColumnImport("Điện thoại bàn", "telefone", "6");
		getListDefineColumnsLevel1().add(dci);
	}

	private void defineTableViewCustomer() {
		listTableColumn = new ArrayList<Object[]>();
		listTableColumn.add(new Object[] { "STT", true });
		listTableColumn.add(new Object[] { "Ngày lập", true });
		listTableColumn.add(new Object[] { "Mã khách hàng", true });
		listTableColumn.add(new Object[] { "Nhóm", true });
		listTableColumn.add(new Object[] { "Nhân viên TT", true });
		listTableColumn.add(new Object[] { "Tên bảng kê", true });
		listTableColumn.add(new Object[] { "Tên doanh nghiệp", true });
		listTableColumn.add(new Object[] { "Giấy phép ĐKKD số", false });
		listTableColumn.add(new Object[] { "Ngày cấp", false });
		listTableColumn.add(new Object[] { "Địa chỉ đăng kí KD", false });
		listTableColumn.add(new Object[] { "Mã số thuế", false });
		listTableColumn.add(new Object[] { "Vốn đăng kí", false });
		listTableColumn.add(new Object[] { "Điện thoại bàn", true });
		listTableColumn.add(new Object[] { "Fax", false });
		listTableColumn.add(new Object[] { "Email", true });
		listTableColumn.add(new Object[] { "Địa chỉ mạng xã hội", false });
		listTableColumn.add(new Object[] { "Địa điểm kinh doanh", false });
		listTableColumn.add(new Object[] { "Người đại diện pháp luật", false });
		listTableColumn.add(new Object[] { "Người quyết định chính công việc", false });
		listTableColumn.add(new Object[] { "ĐTDĐ Người quyết định", false });
		listTableColumn.add(new Object[] { "Ngày sinh", false });
		listTableColumn.add(new Object[] { "Nguyên quán", false });
		listTableColumn.add(new Object[] { "Người bán hàng trực tiếp", false });
		listTableColumn.add(new Object[] { "ĐTDĐ Người bán hàng", false });
		listTableColumn.add(new Object[] { "Ước vốn tự có để kinh doanh", false });
		listTableColumn.add(new Object[] { "Ngành nghề kinh doanh khác", false });
		listTableColumn.add(new Object[] { "Cấp 1 (5)", false });
		listTableColumn.add(new Object[] { "Tỉ lệ nhận (5)", false });
		listTableColumn.add(new Object[] { "Cấp 1 (4)", false });
		listTableColumn.add(new Object[] { "Tỉ lệ nhận (4)", false });
		listTableColumn.add(new Object[] { "Cấp 1 (3)", false });
		listTableColumn.add(new Object[] { "Tỉ lệ nhận (3)", false });
		listTableColumn.add(new Object[] { "Cấp 1 (2)", false });
		listTableColumn.add(new Object[] { "Tỉ lệ nhận (2)", false });
		listTableColumn.add(new Object[] { "Cấp 1 (1)", false });
		listTableColumn.add(new Object[] { "Tỉ lệ nhận (1)", false });
		listTableColumn.add(new Object[] { "3 Sản phẩm thuốc trừ cỏ", false });
		listTableColumn.add(new Object[] { "5 Sản phẩm thuốc trừ sâu", false });
		listTableColumn.add(new Object[] { "3 Sản phẩm thuốc trừ rầy", false });
		listTableColumn.add(new Object[] { "5 Sản phẩm thuốc trừ bệnh", false });
		listTableColumn.add(new Object[] { "3 Sản phẩm kích thích sinh trưởng", false });
		listTableColumn.add(new Object[] { "3 Sản phẩm thuốc trừ ốc", false });
		listTableColumn.add(new Object[] { "Lúa (%)", false });
		listTableColumn.add(new Object[] { "3 Mùa vụ Lúa", false });
		listTableColumn.add(new Object[] { "Rau màu (%)", false });
		listTableColumn.add(new Object[] { "3 Mùa vụ Rau màu", false });
		listTableColumn.add(new Object[] { "Cây ăn trái (%)", false });
		listTableColumn.add(new Object[] { "3 Mùa vụ Cây ăn trái", false });
		listTableColumn.add(new Object[] { "Khác (%)", false });
		listTableColumn.add(new Object[] { "3 Mùa vụ Khác", false });
	}

	@Override
	public void validate() {
		try {
			//Start:
			/**
			 * Check duplicate data
			 */
			if(!this.isEdit()){
				checkDuplicateData();
			}
			//End
			/**
			 * Load data lookup
			 */
			loadLookupEmployee();
			loadLookupCustomer();
			loadLookupGrpCustomer();
			loadGroupCustomerDetail();
			loadLookupCity();
			defineTableViewCustomer();
			defineColumnImportLevel1();
			defineColumnImportLevel2();
			generateColumnExcel();
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: checkDuplicateData. Exception: " + e.getMessage());
		}
	}
	
	private void checkDuplicateData(){
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			/**
			 * check customer code
			 */
			if(StringUtil.notNull(getCust().getCustomerCode()).length() > 0 && cusHome.existCustomer(custId, getCust().getCustomerCode())){
				//addFieldError("customerCode", "Đã tồn tại mã khách hàng này trong hệ thống");
				addActionError("Đã tồn tại mã khách hàng='"+getCust().getCustomerCode()+"' này trong hệ thống!");
			}
			/**
			 * check ten bang ke
			 */
//			if(StringUtil.notNull(getCust().getStatisticName()).length() > 0 && cusHome.existCustomerBangKe(custId, getCust().getStatisticName())){
//				//addFieldError("cust.statisticName", "Đã tồn tại tên bảng kê này trong hệ thống");
//				addActionError("Đã tồn tại tên bảng kê='"+getCust().getStatisticName()+"' này trong hệ thống!");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: checkDuplicateData. Exception: " + e.getMessage());
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
			listCustomerL1 = cusHome.getLookupCustomer(custId, MyConts.CUS_L1);
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
	
	public void loadGroupCustomerDetail() {
		try {
			
			listGrpCusdetail = new ArrayList<GroupCustomerDetail>();
			listGrpCusdetail.add(new GroupCustomerDetail(MyConts.GROUP_CUSTOMER_DETAIL_A_ID, MyConts.GROUP_CUSTOMER_DETAIL_A_NAME));
			listGrpCusdetail.add(new GroupCustomerDetail(MyConts.GROUP_CUSTOMER_DETAIL_B_ID, MyConts.GROUP_CUSTOMER_DETAIL_B_NAME));
			listGrpCusdetail.add(new GroupCustomerDetail(MyConts.GROUP_CUSTOMER_DETAIL_C_ID, MyConts.GROUP_CUSTOMER_DETAIL_C_NAME));
			listGrpCusdetail.add(new GroupCustomerDetail(MyConts.GROUP_CUSTOMER_DETAIL_D_ID, MyConts.GROUP_CUSTOMER_DETAIL_D_NAME));
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup group of customers. Exception: " + e.getMessage());
		}		
	}
	
	

	public String findAllCustomer() {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			customers = cusHome.findAll();
			UserHome userHome = new UserHome(getSessionFactory());
			GroupCustomerHome groupCusHome = new GroupCustomerHome(getSessionFactory());
			HashMap<Integer, String> groups = groupCusHome.getListGroupCustomer();
			HashMap<Integer, String> users = userHome.getFilterEmployee();
			for (Customer customer : customers) {
				if (customer.getGroupCustomer() != null) {
					GroupCustomer g = new GroupCustomer();
					g.setId(customer.getGroupCustomer().getId());
					g.setGroupName(groups.get(customer.getGroupCustomer().getId()));
					customer.setGroupCustomer(g);

					if (customer.getUser() != null) {
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
			Customer cusTemp = cusHome.findById(custId);
			if(cusTemp.getCustomerByCustomer1Level1Id() != null){
				Customer cus1L1 = cusHome.findById(cusTemp.getCustomerByCustomer1Level1Id().getId());
				cusTemp.setCustomerByCustomer1Level1Id(cus1L1);
			}
			if(cusTemp.getCustomerByCustomer2Level1Id() != null){
				Customer cus2L1 = cusHome.findById(cusTemp.getCustomerByCustomer2Level1Id().getId());
				cusTemp.setCustomerByCustomer2Level1Id(cus2L1);
			}
			if(cusTemp.getCustomerByCustomer3Level1Id() != null){
				Customer cus3L1 = cusHome.findById(cusTemp.getCustomerByCustomer3Level1Id().getId());
				cusTemp.setCustomerByCustomer3Level1Id(cus3L1);
			}
			if(cusTemp.getCustomerByCustomer4Level1Id() != null){
				Customer cus4L1 = cusHome.findById(cusTemp.getCustomerByCustomer4Level1Id().getId());
				cusTemp.setCustomerByCustomer4Level1Id(cus4L1);
			}
			if(cusTemp.getCustomerByCustomer5Level1Id() != null){
				Customer cus5L1 = cusHome.findById(cusTemp.getCustomerByCustomer5Level1Id().getId());
				cusTemp.setCustomerByCustomer5Level1Id(cus5L1);
			}
			if(null!=cusTemp.getGrpCusdetailId() && cusTemp.getGrpCusdetailId()>=0){
				loadGroupCustomerDetail();
				for(GroupCustomerDetail item : listGrpCusdetail){
					System.out.println("==========item.getGroupCustomerId():"+item.getGroupCustomerId());
					if(item.getGroupCustomerId()==cusTemp.getGrpCusdetailId()){
						cusTemp.setGroup_detail_name(item.getGroupName());
					}
				}
			}
			setCust(cusTemp);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String deleteCustomerById() throws Exception {
		try {
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			cusHome.updateCustomerStatus(custId);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String generateCustomerCode() throws Exception {
		try {
			//HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String citiCode = StringUtil.notNull(cusCodeGen).toUpperCase();
			
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			if(citiCode.matches("^[a-zA-Z]+$")){
				String maxCode = StringUtil.notNull(cusHome.getMaxCustomerCode(citiCode));
				if(maxCode.matches("^[a-zA-Z]+[0-9]+$")){
					cusCodeGen = citiCode + StringUtil.roundZero((Integer.parseInt(maxCode.replaceAll("^[a-zA-Z]+", ""))+1), 5-citiCode.length());
				}else{
					cusCodeGen =  citiCode + StringUtil.roundZero(1, 5-citiCode.length());
				}
			}else{
				cusCodeGen =  StringUtil.roundZero(cusHome.getMaxId() + 1, 5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @To Do: save new & update customer inform 
	 * @return
	 * @throws Exception
	 */
	public String addCustomer() throws Exception {
		try {
		//	System.out.println("============addCustomer=================:"+cust.getImagesSignature().toString());
			if(null==properties){
				properties = getProperties();
			}
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			/**
			 * set value
			 */
			cust.setId(custId);
			if(StringUtil.notNull(varCreateTime).matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
				cust.setCreateTime(SDF.parse(varCreateTime));	
			}else{
				cust.setCreateTime(null);	
			}
			if(StringUtil.notNull(varCertificateDate).matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
				cust.setCertificateDate(SDF.parse(varCertificateDate));
			}else{
				cust.setCertificateDate(null);
			}
			if(StringUtil.notNull(varDirectorBirthday).matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
				cust.setDirectorBirthday(SDF.parse(varDirectorBirthday));
			}else{
				cust.setDirectorBirthday(null);
			}
			//Start: 
			if(StringUtil.notNull(varBirthday).matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
				cust.setBirthday(SDF.parse(varBirthday));
			}else{
				cust.setBirthday(null);
			}
			//End:
			cust.setCustomerIsActive(true);
			System.out.println("00000000000000000:"+grpCustomer1.getGroupName());
			if (emp.getId() > 0)
				getCust().setUser(emp);
			if (grpCustomer1.getId() > 0)
				getCust().setGroupCustomer(grpCustomer1);
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
			if (StringUtil.notNull(getCusImageScanFileName()).length() > 0) {
				String path_doc_scan = MyConts.UPLOAD_DIR;
				String filePath = this.ctx.getRealPath("/").concat(path_doc_scan);
				
				String[] arrOriginalName = getCusImageScanFileName().split(",");
				String imageName = "";
				for (int i = 0; i < cusImageScan.length; i++) {
					String newName = cust.getCustomerCode() + "_" + i + "_" + new Date().getTime() + "." + FilenameUtils.getExtension(arrOriginalName[i]);
					System.out.println("602 filePath+"+filePath+"============"+newName);
					FileUtils.copyFile(cusImageScan[i], new File(filePath, newName), true);
					imageName += newName + "|";
					if (arrOriginalName[i].contains(this.images_signature)) {
						String signature_name = cust.getCustomerCode() + "_" + i + "_" + new Date().getTime() + "." + FilenameUtils.getExtension(arrOriginalName[i]);
						System.out.println("602 filePath+"+filePath+"============"+signature_name);
						
						String images_output = filePath+"/"+signature_name;
						CutPicture(cusImageScan[i], images_output, images_x, imgase_y, images_width, images_height);
						//FileUtils.copyFile(cusImageScan[i], new File(filePath, signature_name), true);
						
						cust.setImagesSignature(path_doc_scan + "/" +signature_name);
					}
				}
				
				cust.setPathDocScan(path_doc_scan + "/" + imageName.replaceAll(Pattern.quote("|")+"$", ""));
			}
			if (cust.getId() > 0) {
				cusHome.updateDirty(getCust());
			} else {
				cusHome.attachDirty(getCust());
				getModel();
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			cust.setCustomerCode(cusCodeGen);
			addActionError(e.getMessage() + ".");
		}
		return INPUT;
	}

	public String importCustomer() throws Exception {
		try {
			cust = new Customer();
			StringBuilder logDuplicate = new StringBuilder();
			File theFile = new File(getUploadFileName());
			FileUtils.copyFile(getUpload(), theFile);
			Cell cell = null;
			Object value = null;
			try (FileInputStream fis = new FileInputStream(theFile)) {
				ExcelUtil xls = new ExcelUtil();
				CustomerHome custHome = new CustomerHome(getSessionFactory());
				// UserHome userHome = new UserHome(getSessionFactory());
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				totalRecordExcel = sheet.getPhysicalNumberOfRows();
				for (int i = 0; i < Integer.parseInt(varIndexRow) - 1; i++) {
					rowIterator.next();
				}
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					processIndexExcel = row.getRowNum() + 1;
					cust = new Customer();
					cust.setCustomerIsActive(true);
					cell = row.getCell(0);
					if (cell == null)
						break;
					value = xls.getValue(cell);
					if (StringUtil.notNull(value).isEmpty())
						continue;
					String[] arrIndexColumn = varIndexColumn.split(",");
					String[] arrFieldEntName = varFieldEntName.split(",");

					// -------------CreateTime--------------
					getCust().setCreateTime(new Date());
					// ---------------------------
					// -------------GroupCustomer--------------
					GroupCustomer gCust = new GroupCustomer();
					gCust.setId(1);
					getCust().setGroupCustomer(gCust);
					// ---------------------------
					for (int i = 0; i < arrIndexColumn.length; i++) {
						cell = row.getCell(Integer.parseInt(arrIndexColumn[i].trim()));
						value = xls.getValue(cell);
						if (arrFieldEntName[i].trim().equals("customerByCustomer1Level1Id")) {
							String[] arrCustomerName = StringUtil.notNull(value).split(",");
							for (int j = 1; j <= arrCustomerName.length; j++) {
								Customer cus = custHome.findByName(StringUtil.notNull(arrCustomerName[j - 1].trim()));
								switch (j) {
									case 1:
										cust.setCustomerByCustomer1Level1Id(cus);
										break;
									case 2:
										cust.setCustomerByCustomer2Level1Id(cus);
										break;
									case 3:
										cust.setCustomerByCustomer3Level1Id(cus);
										break;
									case 4:
										cust.setCustomerByCustomer4Level1Id(cus);
										break;
									case 5:
										cust.setCustomerByCustomer5Level1Id(cus);
										break;
									default:
										break;
								}
							}
						} else if (arrFieldEntName[i].trim().equals("groupCustomer")) {
							// -------------CustomerGroup--------------
							gCust = new GroupCustomer();
							gCust.setId(2);
							getCust().setGroupCustomer(gCust);
							// ---------------------------
						} else {
							Field f = cust.getClass().getField(arrFieldEntName[i].trim());
							if (f.getType() == Date.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(cust, null);
								} else {
									try {
										f.set(cust, (Date) value);
									} catch (Exception e) {
										f.set(cust, DateUtils.tryConvertStringToDate(StringUtil.notNull(value)));
									}
								}
							} else if (f.getType() == BigDecimal.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(cust, new BigDecimal(0));
								} else {
									f.set(cust, new BigDecimal(StringUtil.notNull(value)));
								}
							} else {
								f.set(cust, StringUtil.notNull(value));
							}
						}
					}
					// ---------------------------
					boolean isExisted = custHome.existCustomer(custId, getCust().getCustomerCode());
					if (!isExisted)
						custHome.attachDirty(getCust());
					else
						logDuplicate.append("<li>Cảnh báo: Dữ liệu dòng " + (cell.getRowIndex() + 1) + " đã được cập nhật rồi!</li>");
					setCust(new Customer());
				}
				addActionMessage("<h3>Cập nhật hoàn thành</h3><ul>" + logDuplicate + "</ul>");
			} catch (Exception e) {
				e.printStackTrace();
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
	
	private Properties getProperties() {
		//System.out.println("=======================================getProperties");
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			 prop.load(inputStream);
			this.images_x         = Integer.parseInt(prop.getProperty("x"));
			this.imgase_y         = Integer.parseInt(prop.getProperty("y"));
			this.images_width     = Integer.parseInt(prop.getProperty("width"));
			this.images_height    = Integer.parseInt(prop.getProperty("height"));
			this.images_signature = prop.getProperty("picture_signature");
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

	public void CutPicture(File filename ,String images2 ,int x , int y , int width , int height){
		
	       try {			
				this.bufferedImage = getBufferImageIO(filename);  
				int width_start    = (bufferedImage.getWidth()*x)/100;
				int height_start   = (bufferedImage.getHeight()*y)/100;
				int width_end      = (bufferedImage.getWidth()*width)/100;
				int height_end     = (bufferedImage.getHeight()*height)/100;
			    BufferedImage dest = this.bufferedImage.getSubimage(width_start, height_start, width_end, height_end);
	            File outputfile = new File(images2);
	            ImageIO.write(dest, "jpg", outputfile);


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public  BufferedImage getBufferImageIO(File filename) {
		BufferedImage originalImage=null;
		try {
		
			originalImage = ImageIO.read(filename);			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return originalImage;
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

	public List<Customer> getListCustomerL1() {
		return listCustomerL1;
	}

	public void setListCustomerL1(List<Customer> listCustomerL1) {
		this.listCustomerL1 = listCustomerL1;
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
		return grpCustomer1;
	}

	public void setGrpCustomer(GroupCustomer grpCustomer) {
		this.grpCustomer1 = grpCustomer;
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

//	public String getVarCityCode() {
//		return varCityCode;
//	}
//
//	public void setVarCityCode(String varCityCode) {
//		this.varCityCode = varCityCode;
//	}

	public List<City> getListCity() {
		return listCity;
	}

	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}

	public File[] getCusImageScan() {
		return cusImageScan;
	}

	public void setCusImageScan(File[] cusImageScan) {
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

	public List<Object[]> getListTableColumn() {
		return listTableColumn;
	}

	public void setListTableColumn(List<Object[]> listTableColumn) {
		this.listTableColumn = listTableColumn;
	}

	public String notifyListCutomer() {
		return SUCCESS;
	}

	public List<String> getListColumnExcel() {
		return listColumnExcel;
	}

	public void setListColumnExcel(List<String> listColumnExcel) {
		this.listColumnExcel = listColumnExcel;
	}



	public String getVarFieldEntName() {
		return varFieldEntName;
	}

	public void setVarFieldEntName(String varFieldEntName) {
		this.varFieldEntName = varFieldEntName;
	}

	public String getVarIndexColumn() {
		return varIndexColumn;
	}

	public void setVarIndexColumn(String varIndexColumn) {
		this.varIndexColumn = varIndexColumn;
	}

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
	
	//Start: 
	public String getVarBirthday() {
		return varBirthday;
	}

	public void setVarBirthday(String varBirthday) {
		this.varBirthday = varBirthday;
	}
	//End: 
	public String getVarCreateTime() {
		return varCreateTime;
	}

	public void setVarCreateTime(String varCreateTime) {
		this.varCreateTime = varCreateTime;
	}

	public String getVarIndexRow() {
		return varIndexRow;
	}

	public void setVarIndexRow(String varIndexRow) {
		this.varIndexRow = varIndexRow;
	}

	public int getTotalRecordExcel() {
		return totalRecordExcel;
	}

	public void setTotalRecordExcel(int totalRecordExcel) {
		this.totalRecordExcel = totalRecordExcel;
	}

	public int getProcessIndexExcel() {
		return processIndexExcel;
	}

	public void setProcessIndexExcel(int processIndexExcel) {
		this.processIndexExcel = processIndexExcel;
	}

	public List<DefineColumnImport> getListDefineColumnsLevel1() {
		return listDefineColumnsLevel1;
	}

	public void setListDefineColumnsLevel1(List<DefineColumnImport> listDefineColumnsLevel1) {
		this.listDefineColumnsLevel1 = listDefineColumnsLevel1;
	}

	public List<DefineColumnImport> getListDefineColumnsLevel2() {
		return listDefineColumnsLevel2;
	}

	public void setListDefineColumnsLevel2(List<DefineColumnImport> listDefineColumnsLevel2) {
		this.listDefineColumnsLevel2 = listDefineColumnsLevel2;
	}
	
	public String getCusCodeGen() {
		return cusCodeGen;
	}

	public void setCusCodeGen(String cusCodeGen) {
		this.cusCodeGen = cusCodeGen;
	}

	public List<GroupCustomerDetail> getListGrpCusdetail() {
		return listGrpCusdetail;
	}

	public void setListGrpCusdetail(List<GroupCustomerDetail> listGrpCusdetail) {
		this.listGrpCusdetail = listGrpCusdetail;
	}

	

	public int getImages_x() {
		return images_x;
	}

	public void setImages_x(int images_x) {
		this.images_x = images_x;
	}

	public int getImgase_y() {
		return imgase_y;
	}

	public void setImgase_y(int imgase_y) {
		this.imgase_y = imgase_y;
	}

	public int getImages_width() {
		return images_width;
	}

	public void setImages_width(int images_width) {
		this.images_width = images_width;
	}

	public int getImages_height() {
		return images_height;
	}

	public void setImages_height(int images_height) {
		this.images_height = images_height;
	}

	public String getImages_signature() {
		return images_signature;
	}

	public void setImages_signature(String images_signature) {
		this.images_signature = images_signature;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	
	
	
    
}
