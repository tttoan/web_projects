package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import com.home.conts.TableStatisticLevel1;
import com.home.conts.TableStatisticLevel2;
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
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
	private String importLevel = "";
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

	@Override
	public void validate() {
		loadLookupEmployee();
		loadLookupCustomer();
		loadLookupProduct();
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

	public String importStatisticLevelOne() {
		try {
			importLevel= "tab1";
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
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				Customer cust = null;
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					cell = row.getCell(TableStatisticLevel1.customerCodeLevel1.value());
					value = xls.getValue(cell);
					if(((String)value).isEmpty())
						continue;
					if (((String) value).toLowerCase().trim().startsWith("mã khách hàng")) {
						setStat(new Statistic());
						String customerCode = ((String) value).split(":")[1].trim();
						// Remove 2 first character
						cust = custHome.findCustomerByCode(customerCode.substring(2));
						
					} else if (xls.isDateReceived(((String) value), sdf) && cust != null) {
						// -------------Check data valid--------------
						cell = row.getCell(TableStatisticLevel1.total.value());
						value = xls.getValue(cell);
						if(((String)value).isEmpty())
							continue;
						// ---------------------------
						setStat(new Statistic());
						// -------------customerCodeLevel1--------------
						getStat().setCustomerByCustomerCodeLevel1(cust);
						// ---------------------------
						// -------------dateReceived--------------
						cell = row.getCell(TableStatisticLevel1.dateReceived.value());
						value = xls.getValue(cell);
						getStat().setDateReceived(sdf.parse((String) value));
						// ---------------------------
						// -------------productCode--------------
						cell = row.getCell(TableStatisticLevel1.productCode.value());
						value = xls.getValue(cell);
						Product pro = proHome.findProductByCode((String) value);
						getStat().setProduct(pro);
						// ---------------------------
						// -------------quantiy--------------
						cell = row.getCell(TableStatisticLevel1.quantiy.value());
						value = xls.getValue(cell);
						getStat().setQuantity(Integer.parseInt(((String)value).replace(".", "")));
						// ---------------------------
						// -------------total--------------
						cell = row.getCell(TableStatisticLevel1.total.value());
						value = xls.getValue(cell);
						getStat().setTotal(new BigDecimal(((String)value).replace(".", "")));
						// ---------------------------
						boolean isDuplicated = sttHome.isStatictisDuplicateLevel1(getStat().getDateReceived(), getStat().getCustomerByCustomerCodeLevel1().getId(), getStat().getProduct().getId());
						if (!isDuplicated)
							sttHome.attachDirty(getStat());
						else
							logDuplicate.append("<li>Cảnh báo: Dữ liệu dòng " + (cell.getRowIndex()+1) + " đã được cập nhật rồi!</li>");
						setStat(new Statistic());
					}
				}
				addActionMessage("<h3>Cập nhật hoàn thành</h3><ul>" + logDuplicate + "</ul>");
			} catch (Exception e) {
				addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + e.getMessage() + " ở dòng: " + (cell.getRowIndex()+1) + ", cột: " + (cell.getColumnIndex()+1) + ", giá trị: " + value + "</li></ul>");
			} finally {
				if (theFile.exists())
					FileUtils.deleteQuietly(theFile);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + ex.getMessage() + "</ul></li>");
		}
		return SUCCESS;
	}

	public String importStatisticLevelTwo() {
		try {
			importLevel= "tab2";
			StringBuilder logDuplicate = new StringBuilder();
			String filePath = request.getSession().getServletContext().getRealPath("/");
			File theFile = new File(filePath, getUploadFileName());
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
					cell = row.getCell(TableStatisticLevel2.dateReceived.value());
					value = xls.getValue(cell);
					getStat().setDateReceived((Date) value);
					// ---------------------------
					// -------------customerCodeLevel2--------------
					cell = row.getCell(TableStatisticLevel2.customerCodeLevel2.value());
					value = xls.getValue(cell);
					Customer cust = custHome.findCustomerByCode((String) value);
					getStat().setCustomerByCustomerCodeLevel2(cust);
					// ---------------------------
					// -------------customerCodeLevel1--------------
					cell = row.getCell(TableStatisticLevel2.customerCodeLevel1.value());
					value = xls.getValue(cell);
					cust = custHome.findCustomerByCode((String) value);
					getStat().setCustomerByCustomerCodeLevel1(cust);
					// ---------------------------
					// -------------productCode--------------
					cell = row.getCell(TableStatisticLevel2.productCode.value());
					value = xls.getValue(cell);
					Product pro = proHome.findProductByCode(String.valueOf(value));
					getStat().setProduct(pro);
					// ---------------------------
					// -------------totalBox--------------
					cell = row.getCell(TableStatisticLevel2.totalBox.value());
					value = xls.getValue(cell);
					getStat().setTotalBox(((Double)value).intValue());
					// ---------------------------
					// -------------quantiy--------------
					cell = row.getCell(TableStatisticLevel2.quantiy.value());
					value = xls.getValue(cell);
					getStat().setQuantity(((Double)value).intValue());
					// ---------------------------
					// -------------total--------------
					cell = row.getCell(TableStatisticLevel2.total.value());
					value = xls.getValue(cell);
					getStat().setTotal(new BigDecimal(String.valueOf(value)));
					// ---------------------------
					// -------------userFullName--------------
					cell = row.getCell(TableStatisticLevel2.userFullName.value());
					value = xls.getValue(cell);
					User user = userHome.getUserByFullName((String) value);
					getStat().setUser(user);
					// ---------------------------

					boolean isDuplicated = sttHome.isStatictisDuplicateLevel2(getStat().getDateReceived(), getStat().getCustomerByCustomerCodeLevel1().getId(), getStat().getCustomerByCustomerCodeLevel2()
							.getId(), getStat().getProduct().getId(), getStat().getUser().getId());
					if (!isDuplicated)
						sttHome.attachDirty(getStat());
					else
						logDuplicate.append("<li>Cảnh báo: Dữ liệu dòng " + (cell.getRowIndex()+1) + " đã được cập nhật rồi!</li>");
					setStat(new Statistic());
				}
				addActionMessage("<h3>Cập nhật hoàn thành</h3><ul>" + logDuplicate + "</ul>");
			} catch (Exception e) {
				addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + e.getMessage() + " ở dòng: " + (cell.getRowIndex()+1) + ", cột: " + (cell.getColumnIndex()+1) + ", giá trị: " + value + "</li></ul>");
			} finally {
				if (theFile.exists())
					FileUtils.deleteQuietly(theFile);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + ex.getMessage() + "</ul></li>");
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

	public String getImportLevel() {
		return importLevel;
	}

	public void setImportLevel(String importLevel) {
		this.importLevel = importLevel;
	}

}
