package com.home.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.home.conts.InvoiceTypeText;
import com.home.conts.MyConts;
import com.home.dao.CategoryHome;
import com.home.dao.CustomerHome;
import com.home.dao.ProductHome;
import com.home.dao.StatisticHome;
import com.home.dao.UserHome;
import com.home.entities.DefineColumnImport;
import com.home.entities.StatisticCustom;
import com.home.entities.UserAware;
import com.home.model.Category;
import com.home.model.Customer;
import com.home.model.InvoiceType;
import com.home.model.Product;
import com.home.model.Statistic;
import com.home.model.StatisticCompare;
import com.home.model.User;
import com.home.util.DateUtils;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StatisticAction extends ActionSupport implements Action, ModelDriven<Statistic>, UserAware, InvoiceTypeText {
	private static final long serialVersionUID = 1L;
	private Calendar calendar = Calendar.getInstance();
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	public List<Statistic> statistics = new ArrayList<Statistic>();
	private Statistic stat = new Statistic();
	private List<User> listEmployee = new ArrayList<>();
	private List<Customer> listCustomerLevel1 = new ArrayList<>();
	private List<Customer> listCustomerLevel2 = new ArrayList<>();
	private List<Product> listProduct = new ArrayList<>();
	//private ArrayList<String> districts = new ArrayList<>();
	private List<StatisticCompare> listStatComp = new ArrayList<>();
	private Customer cusLevel1 = new Customer();
	private Customer cusLevel2 = new Customer();
	//private Product pro = new Product();
	//private User emp = new User();
	private StatisticCustom sttCustom = new StatisticCustom();
	//private boolean edit = false;
	private HttpServletRequest request;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	//private int statId;
	private String chooseTab = "";
	private String chooseSubTab = "";
	private InputStream fileInputStream;
	private Workbook workbook;
	private User userSes;
	private String fromDate;
	private List<String> listColumnExcel;
	private int totalRecordExcel = 0;
	private int processIndexExcel = 0;
	private String varFieldEntName;
	private String varIndexColumn;
	private String varIndexRow = "4";
	private String varDateReceived = SDF.format(new Date());
	private String varFromDate = SDF.format(new Date());
	private String varToDate = SDF.format(new Date());
	private List<DefineColumnImport> listDefineColStatisticLevel1;
	private List<DefineColumnImport> listDefineColStatisticLevel2;
	private List<DefineColumnImport> listDefineColStatisticBalance;

	private void generateColumnExcel() {
		listColumnExcel = new ArrayList<String>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (char a : alphabet) {
			listColumnExcel.add("Cột " + (a + "").toUpperCase());
		}
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	private String toDate;

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
		stat.setDateReceived(new Date());
		//stat.setQuantity(0);
		//stat.setTotalBox(0);
		//stat.setTotal(new BigDecimal(0));
		return stat;
	}

	public User getUserSes() {
		return userSes;
	}

	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}

	@Override
	public String execute() throws Exception {
//		if (statId != 0) {
//			try {
//				StatisticHome sttHome = new StatisticHome(getSessionFactory());
//				stat = sttHome.findById(statId);
//				varDateReceived = SDF.format(stat.getDateReceived());
//				setEdit(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw e;
//			}
//		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		try {
			loadLookupEmployee();
			loadLookupCustomer();
			loadLookupProduct();
			generateColumnExcel();
			defineColImportStatisticLevel1();
			defineColImportStatisticLevel2();
			defineColImportStatisticBalance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void defineColImportStatisticLevel1() {
		setListDefineColStatisticLevel1(new ArrayList<DefineColumnImport>());
		DefineColumnImport dci = new DefineColumnImport("Ngày hóa đơn", "dateReceived", "0");
		getListDefineColStatisticLevel1().add(dci);
		dci = new DefineColumnImport("Mã khách hàng cấp 1", "customerByCustomerCodeLevel1", "2");
		getListDefineColStatisticLevel1().add(dci);
		dci = new DefineColumnImport("Mã hàng", "product", "4");
		getListDefineColStatisticLevel1().add(dci);
		dci = new DefineColumnImport("Số lượng", "quantity", "7");
		getListDefineColStatisticLevel1().add(dci);
		dci = new DefineColumnImport("Danh số bán", "total", "9");
		getListDefineColStatisticLevel1().add(dci);
	}

	private void defineColImportStatisticLevel2() {
		setListDefineColStatisticLevel2(new ArrayList<DefineColumnImport>());
		DefineColumnImport dci = new DefineColumnImport("Ngày nhận", "dateReceived", "1");
		getListDefineColStatisticLevel2().add(dci);
		dci = new DefineColumnImport("Mã khách hàng cấp 2", "customerByCustomerCodeLevel2", "2");
		getListDefineColStatisticLevel2().add(dci);
		dci = new DefineColumnImport("Mã khách hàng cấp 1", "customerByCustomerCodeLevel1", "4");
		getListDefineColStatisticLevel2().add(dci);
		dci = new DefineColumnImport("Mã hàng", "product", "6");
		getListDefineColStatisticLevel2().add(dci);
		dci = new DefineColumnImport("Số thùng", "totalBox", "9");
		getListDefineColStatisticLevel2().add(dci);
		dci = new DefineColumnImport("Số lượng", "quantity", "10");
		getListDefineColStatisticLevel2().add(dci);
		dci = new DefineColumnImport("Thành tiền", "total", "12");
		getListDefineColStatisticLevel2().add(dci);
	}

	private void defineColImportStatisticBalance() {
		setListDefineColStatisticBalance(new ArrayList<DefineColumnImport>());
		DefineColumnImport dci = new DefineColumnImport("Ngày nhận", "dateReceived", "0");
		getListDefineColStatisticBalance().add(dci);
		dci = new DefineColumnImport("Mã khách hàng cấp 1", "customerByCustomerCodeLevel1", "1");
		getListDefineColStatisticBalance().add(dci);
		dci = new DefineColumnImport("Mã hàng", "product", "3");
		getListDefineColStatisticBalance().add(dci);
		dci = new DefineColumnImport("Số dư đầu kỳ (Thùng)", "totalBox", "5");
		getListDefineColStatisticBalance().add(dci);
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
		listProduct = proHome.getLookupProduct();
	}

	public void loadLookupCustomer() {
		CustomerHome cusHome = new CustomerHome(getSessionFactory());
		setListCustomerLevel1(cusHome.getLookupCustomer(-1, 1));
		setListCustomerLevel2(cusHome.getLookupCustomer(-1, 2));
	}

	public void loadLookupEmployee() {
		UserHome userHome = new UserHome(getSessionFactory());
		listEmployee = userHome.getLookupEmployee();
	}

	public String testStatistic() throws Exception {
		try {
			System.out.println("asdasdasdasdasdasd");
			chooseTab = "asdasdasdasda11111sdasd";
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String importBalance() {
		try {
			chooseTab = "levelOne";
			chooseSubTab = "quickBalance";
			stat = new Statistic();
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
				Session session = getSessionFactory().openSession();
				Transaction tx  = session.beginTransaction();
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				totalRecordExcel = sheet.getPhysicalNumberOfRows();
				for (int i = 0; i <= Integer.parseInt(varIndexRow) - 1; i++) {
					rowIterator.next();
				}
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					processIndexExcel = row.getRowNum() + 1;
					setStat(new Statistic());
					cell = row.getCell(0);
					if (cell == null)
						break;
					value = xls.getValue(cell);
					if (StringUtil.notNull(value).isEmpty())
						continue;

					String[] arrIndexColumn = varIndexColumn.split(",");
					String[] arrFieldEntName = varFieldEntName.split(",");

					for (int i = 0; i < arrIndexColumn.length; i++) {
						cell = row.getCell(Integer.parseInt(arrIndexColumn[i].trim()));
						value = xls.getValue(cell);
						if (arrFieldEntName[i].trim().equals("customerByCustomerCodeLevel1")) {
							Customer cus = custHome.findCustomerByCode(session, StringUtil.notNull(arrIndexColumn[i].trim()));
							stat.setCustomerByCustomerCodeLevel1(cus);
						} else if (arrFieldEntName[i].trim().equals("customerByCustomerCodeLevel2")) {
							Customer cus = custHome.findCustomerByCode(session, StringUtil.notNull(arrIndexColumn[i].trim()));
							stat.setCustomerByCustomerCodeLevel2(cus);
						} else if (arrFieldEntName[i].trim().equals("product")) {
							Product pro = proHome.findProductByCode(session, StringUtil.notNull(value));
							stat.setProduct(pro);
						} else {
							Field f = stat.getClass().getField(arrFieldEntName[i].trim());
							if (f.getType() == Date.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(stat, null);
								} else {
									try {
										f.set(stat, (Date) value);
									} catch (Exception e) {
										f.set(stat, DateUtils.tryConvertStringToDate(StringUtil.notNull(value)));
									}
								}
							} else if (f.getType() == BigDecimal.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(stat, new BigDecimal(0));
								} else {
									f.set(stat, new BigDecimal(StringUtil.notNull(value)));
								}
							} else if (f.getType() == Integer.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(stat, 0);
								} else {
									f.set(stat, ((Double) value).intValue());
								}
							} else {
								f.set(stat, StringUtil.notNull(value));
							}
						}
					}
					// --------Balance Type-------
					InvoiceType invoiceType = new InvoiceType();
					invoiceType.setId(MyConts.INVOICE_INVENTORY_L1);
					stat.setInvoiceType(invoiceType);
					// ---------------------------
					boolean isDuplicated = sttHome.isStatictisDuplicateLevel1(session, getStat().getDateReceived(), getStat().getCustomerByCustomerCodeLevel1() == null ? null : getStat()
							.getCustomerByCustomerCodeLevel1().getId(), getStat().getProduct() == null ? null : getStat().getProduct().getId(), getStat().getInvoiceType().getId());
					if (!isDuplicated)
						sttHome.attachDirty(session, getStat());
					else
						logDuplicate.append("<li>Cảnh báo: Dữ liệu dòng " + (cell.getRowIndex() + 1) + " đã được cập nhật rồi!</li>");
					setStat(new Statistic());
				}
				if (session != null) {
					tx.commit();
					session.flush();
					session.close();
				}
				addActionMessage("<h3>Cập nhật hoàn thành</h3><ul>" + logDuplicate + "</ul>");
			} catch (Exception e) {
				String ms = "";
				if (cell != null)
					ms = " ở dòng: " + (cell.getRowIndex() + 1) + ", cột: " + (cell.getColumnIndex() + 1) + ", giá trị: " + value;
				addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + e.getMessage() + ms + "</li></ul>");
				e.printStackTrace();
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

	public String importStatistic() {
		try {
			chooseTab = "levelOne";
			chooseSubTab = "quickInvoiceLevel1";
			stat = new Statistic();
			StringBuilder logDuplicate = new StringBuilder();
			File theFile = new File(getUploadFileName());
			FileUtils.copyFile(getUpload(), theFile);
			Cell cell = null;
			Object value = null;
			try (FileInputStream fis = new FileInputStream(theFile)) {
				ExcelUtil xls = new ExcelUtil();
				StatisticHome sttHome = new StatisticHome(getSessionFactory());
				//CustomerHome custHome = new CustomerHome(getSessionFactory());
				//ProductHome proHome = new ProductHome(getSessionFactory());
				Session session = getSessionFactory().openSession();
				Transaction tx  = session.beginTransaction();
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				totalRecordExcel = sheet.getPhysicalNumberOfRows();
				for (int i = 0; i <= Integer.parseInt(varIndexRow) - 1; i++) {
					rowIterator.next();
				}
				while (rowIterator.hasNext()) {
					long t1 = System.currentTimeMillis();
					Row row = rowIterator.next();
					processIndexExcel = row.getRowNum() + 1;
					setStat(new Statistic());
					cell = row.getCell(0);
					if (cell == null)
						break;
					value = xls.getValue(cell);
					if (StringUtil.notNull(value).isEmpty())
						continue;

					String[] arrIndexColumn = varIndexColumn.split(",");
					String[] arrFieldEntName = varFieldEntName.split(",");
					boolean flagLevel1 = false;
					boolean flagLevel2 = false;

					for (int i = 0; i < arrIndexColumn.length; i++) {
						cell = row.getCell(Integer.parseInt(arrIndexColumn[i].trim()));
						value = xls.getValue(cell);
						//System.out.println(arrFieldEntName[i] + "=" + value);
						if (arrFieldEntName[i].trim().equals("customerByCustomerCodeLevel1")) {
							//Customer cus = custHome.findCustomerByCode(session, StringUtil.notNull(value));
							Customer cus = lookupCustomer(getListCustomerLevel1(), StringUtil.notNull(value));
							stat.setCustomerByCustomerCodeLevel1(cus);
							flagLevel1 = true;
						} else if (arrFieldEntName[i].trim().equals("customerByCustomerCodeLevel2")) {
							//Customer cus = custHome.findCustomerByCode(session, StringUtil.notNull(value));
							Customer cus = lookupCustomer(getListCustomerLevel2(), StringUtil.notNull(value));
							stat.setCustomerByCustomerCodeLevel2(cus);
							flagLevel2 = true;

						} else if (arrFieldEntName[i].trim().equals("product")) {
							//Product pro = proHome.findProductByCode(session, StringUtil.notNull(value));
							Product pro = lookupProduct(getListProduct(), StringUtil.notNull(value));
							stat.setProduct(pro);
						} else {
							Field f = stat.getClass().getField(arrFieldEntName[i].trim());
							if (f.getType() == Date.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(stat, null);
								} else {
									try {
										f.set(stat, (Date) value);
									} catch (Exception e) {
										f.set(stat, DateUtils.tryConvertStringToDate(StringUtil.notNull(value)));
									}
								}
							} else if (f.getType() == BigDecimal.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(stat, new BigDecimal(0));
								} else {
									f.set(stat, new BigDecimal(StringUtil.notNull(value)));
								}
							} else if (f.getType() == Integer.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(stat, 0);
								} else {
									f.set(stat, ((Double) value).intValue());
								}
							} else if (f.getType() == Float.class) {
								if (StringUtil.notNull(value).isEmpty()) {
									f.set(stat, 0);
								} else {
									f.set(stat, ((Double) value).floatValue());
								}
							} else {
								f.set(stat, StringUtil.notNull(value));
							}
						}
					}
					long t2 = System.currentTimeMillis();
					
					InvoiceType invoiceType = new InvoiceType();
					if (flagLevel1 && flagLevel2) {
						chooseTab = "levelTwo";
						chooseSubTab = "quickInvoiceLevel2";
						invoiceType.setId(MyConts.INVOICE_STATISTIC_CUS_L2);
						stat.setInvoiceType(invoiceType);
					} else {
						invoiceType.setId(MyConts.INVOICE_STATISTIC_CUS_L1);
						stat.setInvoiceType(invoiceType);
					}

					// ---------------------------
					boolean isDuplicated = sttHome.isStatictisDuplicateLevel1(session, getStat().getDateReceived(), getStat().getCustomerByCustomerCodeLevel1() == null ? null : getStat()
							.getCustomerByCustomerCodeLevel1().getId(), getStat().getProduct() == null ? null : getStat().getProduct().getId(), getStat().getInvoiceType().getId());
					if (!isDuplicated)
						sttHome.attachDirty(session, getStat());
					else
						logDuplicate.append("<li>Cảnh báo: Dữ liệu dòng " + (cell.getRowIndex() + 1) + " đã được cập nhật rồi!</li>");
					setStat(new Statistic());
					
					long t3 = System.currentTimeMillis();
					
					//System.out.println(processIndexExcel + " -> " + (float)(t2-t1)/1000 + "/" + (float)(t3-t2)/1000 + "/" + (float)(t3-t1)/1000);
				}
				if(session != null){
					tx.commit();
					session.flush();
					session.close();
				}
				addActionMessage("<h3>Cập nhật hoàn thành</h3><ul>" + logDuplicate + "</ul>");
			} catch (Exception e) {
				String ms = "";
				if (cell != null)
					ms = " ở dòng: " + (cell.getRowIndex() + 1) + ", cột: " + (cell.getColumnIndex() + 1) + ", giá trị: " + value;
				addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + e.getMessage() + ms + "</li></ul>");
				e.printStackTrace();
				return ERROR;
			} finally {
				if (theFile.exists())
					FileUtils.deleteQuietly(theFile);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionError("<h3>Cập nhật thất bại</h3><ul><li>Lỗi: " + ex.getMessage() + "</ul></li>");
			return ERROR;
		}
		return SUCCESS;
		
	}
	
	private Customer lookupCustomer(List<Customer> listCuss, String cusCode){
		for (Customer customer : listCuss) {
			if(cusCode.equalsIgnoreCase(customer.getCustomerCode())){
				return customer;
			}
		}
		return null;
	}
	
	private Product lookupProduct(List<Product> listProduct, String productCode){
		for (Product p : listProduct) {
			if(productCode.equalsIgnoreCase(p.getProductCode())){
				return p;
			}
		}
		return null;
	}
	
	public String exportStatistic() {
		try {
			chooseTab = "levelTwo";
			chooseSubTab = "exportInvoiceLevel2";
			statistics.clear();
			ServletContext servletContext = ServletActionContext.getServletContext();
			String pathname = servletContext.getRealPath("/WEB-INF/template/excel/blank.xlsx");
			File theFile = new File(pathname);
			ExcelUtil xls = new ExcelUtil();
			CategoryHome catHome = new CategoryHome(getSessionFactory());
			StatisticHome sttHome = new StatisticHome(getSessionFactory());
			sttCustom.setFromDate(SDF.parse(varFromDate));
			sttCustom.setToDate(SDF.parse(varToDate));
			statistics = sttHome.getListExportStatisticLevel2(sttCustom);

			try (FileInputStream fis = new FileInputStream(theFile)) {
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				int startIndexRow = 0;
				int startIndexCell = 0;
				xls.addRowData(sheet, startIndexRow, startIndexCell, "Tháng", "Ngày nhận", "Mã Cấp 2", "Tên cấp 2", "Mã Cấp 1", "Tên Cấp 1", "Mã Hàng", "Mặt Hàng", "Tên Hàng", "Số Thùng", "Số Lượng",
						"Giá có điểm+Ko điểm", "Thành Tiền", "NVTT");
				startIndexRow++;
				for (Statistic entry : statistics) {
					calendar.setTime(entry.getDateReceived());
					Category cat = catHome.findById(entry.getProduct().getCategory().getId());
					xls.addRowData(sheet, startIndexRow, startIndexCell, 
							calendar.get(Calendar.MONTH) + "", 
							SDF.format(entry.getDateReceived()), 
							entry.getCustomerByCustomerCodeLevel2().getCustomerCode(), 
							entry.getCustomerByCustomerCodeLevel2().getDirector(), 
							entry.getCustomerByCustomerCodeLevel1().getCustomerCode(), 
							entry.getCustomerByCustomerCodeLevel1().getDirector(), 
							entry.getProduct().getProductCode(), 
							cat.getCategoryCode(), 
							entry.getProduct().getProductName(),
							entry.getTotalBox() + "", 
							entry.getQuantity() + "", 
							SystemUtil.format2Money(entry.getProduct().getUnitPrice()), 
							SystemUtil.format2Money(entry.getTotal()), 
							entry.getUser().getFullName());
					startIndexRow++;
				}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
				if (statistics.size() <= 0)
					addActionMessage("Không tìm thấy dữ liệu!");
				else
					addActionMessage("Kết xuất hoàn thành!");
			}

		} catch (Exception e) {
			addActionError(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String compareStatistic() throws Exception {
		StatisticHome sttHome = new StatisticHome(getSessionFactory());
		ProductHome proHome = new ProductHome(getSessionFactory());
		listStatComp = sttHome.getDataStatisticCompare(SDF.parse(fromDate), SDF.parse(toDate), cusLevel1.getId(), INVOICE_SOLD_ID);
		List<StatisticCompare> listStatCompLevel2 = sttHome.getDataStatisticCompare(SDF.parse(fromDate), SDF.parse(toDate), cusLevel1.getId(), INVOICE_SOLD_LEVEL2_ID);
		calendar.setTime(SDF.parse(fromDate));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);
		List<StatisticCompare> listStatBalance = sttHome.getDataStatisticCompare(calendar.getTime(), cusLevel1.getId(), INVOICE_BALANCE_ID);
		for (int i = 0; i < listStatComp.size(); i++) {
			Product pro = proHome.findById(listStatComp.get(i).getProductId());
			listStatComp.get(i).setProduct(pro);
			for (StatisticCompare cLevel2 : listStatCompLevel2) {
				if (listStatComp.get(i).getProductId() == cLevel2.getProductId()) {
					listStatComp.get(i).setTotalBoxLevel2(cLevel2.getTotalBox());
					listStatComp.get(i).setTotalLevel2(cLevel2.getTotal());
					break;
				} else {
					listStatComp.get(i).setTotalBoxLevel2((long) 0);
					listStatComp.get(i).setTotalLevel2(new BigDecimal(0));
				}
			}
			for (StatisticCompare csBalance : listStatBalance) {
				if (listStatComp.get(i).getProductId() == csBalance.getProductId()) {
					listStatComp.get(i).setBalance(csBalance.getTotalBox());
					listStatComp.get(i).setDifferent(csBalance.getTotalBox() + listStatComp.get(i).getTotalBox() - listStatComp.get(i).getTotalBoxLevel2());
					break;
				}
			}
		}
		return SUCCESS;
	}

	public List<User> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<User> listEmployee) {
		this.listEmployee = listEmployee;
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

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

//	public int getStatId() {
//		return statId;
//	}
//
//	public void getStatId(int statId) {
//		this.statId = statId;
//	}

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

//	public User getEmp() {
//		return emp;
//	}
//
//	public void setEmp(User emp) {
//		this.emp = emp;
//	}

//	public Product getPro() {
//		return pro;
//	}
//
//	public void setPro(Product pro) {
//		this.pro = pro;
//	}

	public Statistic getStat() {
		return stat;
	}

	public void setStat(Statistic stat) {
		this.stat = stat;
	}

//	public boolean isEdit() {
//		return edit;
//	}
//
//	public void setEdit(boolean edit) {
//		this.edit = edit;
//	}

//	public void setStatId(int statId) {
//		this.statId = statId;
//	}

//	public ArrayList<String> getDistricts() {
//		return districts;
//	}
//
//	public void setDistricts(ArrayList<String> districts) {
//		this.districts = districts;
//	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public StatisticCustom getSttCustom() {
		return sttCustom;
	}

	public void setSttCustom(StatisticCustom sttCustom) {
		this.sttCustom = sttCustom;
	}

	public String getChooseTab() {
		return chooseTab;
	}

	public void setChooseTab(String chooseTab) {
		this.chooseTab = chooseTab;
	}

	public String getChooseSubTab() {
		return chooseSubTab;
	}

	public void setChooseSubTab(String chooseSubTab) {
		this.chooseSubTab = chooseSubTab;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<StatisticCompare> getListStatComp() {
		return listStatComp;
	}

	public void setListStatComp(List<StatisticCompare> listStatComp) {
		this.listStatComp = listStatComp;
	}

	public List<String> getListColumnExcel() {
		return listColumnExcel;
	}

	public void setListColumnExcel(List<String> listColumnExcel) {
		this.listColumnExcel = listColumnExcel;
	}

	public List<DefineColumnImport> getListDefineColStatisticLevel1() {
		return listDefineColStatisticLevel1;
	}

	public void setListDefineColStatisticLevel1(List<DefineColumnImport> listDefineColStatisticLevel1) {
		this.listDefineColStatisticLevel1 = listDefineColStatisticLevel1;
	}

	public List<DefineColumnImport> getListDefineColStatisticLevel2() {
		return listDefineColStatisticLevel2;
	}

	public void setListDefineColStatisticLevel2(List<DefineColumnImport> listDefineColStatisticLevel2) {
		this.listDefineColStatisticLevel2 = listDefineColStatisticLevel2;
	}

	public List<DefineColumnImport> getListDefineColStatisticBalance() {
		return listDefineColStatisticBalance;
	}

	public void setListDefineColStatisticBalance(List<DefineColumnImport> listDefineColStatisticBalance) {
		this.listDefineColStatisticBalance = listDefineColStatisticBalance;
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

	public String getVarIndexRow() {
		return varIndexRow;
	}

	public void setVarIndexRow(String varIndexRow) {
		this.varIndexRow = varIndexRow;
	}

	public String getVarDateReceived() {
		return varDateReceived;
	}

	public void setVarDateReceived(String varDateReceived) {
		this.varDateReceived = varDateReceived;
	}

	public List<Customer> getListCustomerLevel1() {
		return listCustomerLevel1;
	}

	public void setListCustomerLevel1(List<Customer> listCustomerLevel1) {
		this.listCustomerLevel1 = listCustomerLevel1;
	}

	public List<Customer> getListCustomerLevel2() {
		return listCustomerLevel2;
	}

	public void setListCustomerLevel2(List<Customer> listCustomerLevel2) {
		this.listCustomerLevel2 = listCustomerLevel2;
	}

	public String getVarFromDate() {
		return varFromDate;
	}

	public void setVarFromDate(String varFromDate) {
		this.varFromDate = varFromDate;
	}

	public String getVarToDate() {
		return varToDate;
	}

	public void setVarToDate(String varToDate) {
		this.varToDate = varToDate;
	}

}
