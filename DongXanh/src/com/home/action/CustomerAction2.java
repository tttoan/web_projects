package com.home.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.CustomerHome;
import com.home.dao.EventsNoteHome;
import com.home.dao.UserHome;
import com.home.dao.WorkingPlanHome;
import com.home.entities.DefineColumnImport;
import com.home.entities.UserAware;
import com.home.entities.UserPlanGeneral;
import com.home.model.Customer;
import com.home.model.EventsNote;
import com.home.model.GroupCustomerDetail;
import com.home.model.User;
import com.home.util.DateUtils;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction2 extends ActionSupport implements Action,
		ServletContextAware, ServletResponseAware, ServletRequestAware,
		UserAware {

	private ServletContext ctx;
	private User userSes;
	private List<Customer> data;
	private int recordsFiltered;
	private int recordsTotal;
	private int draw;
	private String order;
	private String search = "";
	private String varCusByUser;
	private boolean varCusAssign;
	private boolean varCusNotAssign;
	private int varCusByLevel1;
	private List<Object[]> listTableColumn = new ArrayList<Object[]>();
	private List<DefineColumnImport> listDefineColumnsLevel1;
	private List<DefineColumnImport> listDefineColumnsLevel2;
	private List<String> listColumnExcel;
	protected HttpServletResponse servletResponse;
	protected List<String> listCustomerType;
	protected List<Customer> listCustomerToRank;
	protected List<User> listUser;// NVTT
	private Workbook workbook;
	private InputStream inputStream;

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	protected HttpServletRequest servletRequest;

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public User getUserSes() {
		return userSes;
	}

	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}

	public String storeParameterSession() {
		try {
			Map sessionMap = (Map) ActionContext.getContext().get("session");
			if (sessionMap.containsKey("varCusByUser")) {
				sessionMap.remove("varCusByUser");
			}
			if (sessionMap.containsKey("varCusAssign")) {
				sessionMap.remove("varCusAssign");
			}
			if (sessionMap.containsKey("varCusNotAssign")) {
				sessionMap.remove("varCusNotAssign");
			}
			if (sessionMap.containsKey("varCusByLevel1")) {
				sessionMap.remove("varCusByLevel1");
			}
			try {
				sessionMap.put("varCusByUser", getVarCusByUser());
				sessionMap.put("varCusAssign", isVarCusAssign());
				sessionMap.put("varCusNotAssign", isVarCusNotAssign());
				sessionMap.put("varCusByLevel1", getVarCusByLevel1());
			} catch (Exception e) {
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String exportCustomerExecl() {

		try {
			ServletContext servletContext = ServletActionContext
					.getServletContext();
			String pathname = servletContext
					.getRealPath("/WEB-INF/template/excel/customer.xlsx");
			File theFile = new File(pathname);
			ExcelUtil xls = new ExcelUtil();

			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			String hearder = StringUtil
					.notNull(request.getParameter("hearder"));
			String[] listheader = hearder.split("-");

			System.out.println("listheader:" + listheader.length);
			List<Object> listHeaderExecl = new ArrayList<Object>();
			defineTableViewCustomer();

			for (int i = 0; i < listheader.length; i++) {
				System.out.println(listheader[i]);
				if (listheader[i].equals("1")) {
					if (null != listTableColumn.get(i)) {
						listHeaderExecl.add(String.valueOf(listTableColumn.get(i)[0]));
					}
				}
			}

			try (FileInputStream fis = new FileInputStream(theFile)) {
				workbook = xls.getWorkbook(fis,
						FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				int startIndexRow = 0;
				int startIndexCell = 0;

				// add header
				xls.addRowData(sheet, startIndexRow, startIndexCell, listHeaderExecl.toArray());
				startIndexRow++;
				// add row
				CustomerHome cusHome = new CustomerHome(
						HibernateUtil.getSessionFactory());
				data = cusHome.getListCustomer(0, 2000, search, varCusByUser,
						varCusAssign & !varCusNotAssign ? 1 : (!varCusAssign
								& varCusNotAssign ? 2 : 0), varCusByLevel1);
				if (listheader.length >= 50) {
					int stt = 0;
					for (Customer item : data) {
						stt++;
						listHeaderExecl = new ArrayList<Object>();
					//	System.out.println("stt:"+stt);
						// { "data": "no", "autoWidth": true,"visible":
						// isColumnVisible(1) },
						/* 1 */if (listheader[0].equals("1")) {
							listHeaderExecl.add(String.valueOf(stt));
						}
						// { "data": "createTime", "autoWidth": true ,"visible":
						// isColumnVisible(2)
						/* 2 */if (listheader[1].equals("1")) {
							listHeaderExecl
									.add(item.getCreateTime().toString());
						}
						// *3*/{ "data": "customerCode", "autoWidth":
						// true,"visible": isColumnVisible(3) },
						/* 3 */if (listheader[2].equals("1")) {
							listHeaderExecl.add(item.getCustomerCode());
						}
						// *4*/{ "data": "groupCustomer.groupName", "autoWidth":
						// true,"visible": isColumnVisible(4) },
						/* 4 */if (listheader[3].equals("1")) {
							listHeaderExecl.add(item.getGroupCustomer()
									.getGroupName());
						}
						// 5/{ "data": "user.fullName", "autoWidth":
						// true,"visible": isColumnVisible(5) },
						/* 5 */if (listheader[4].equals("1")) {
							listHeaderExecl.add(item.getUser().getFullName());
						}
						// *6*/{ "data": "statisticName", "autoWidth":
						// true,"visible": isColumnVisible(6)},
						/* 6 */if (listheader[5].equals("1")) {
							listHeaderExecl.add(item.getStatisticName());
						}
						// *7*/{ "data": "businessName", "autoWidth":
						// true,"visible": isColumnVisible(7)},
						/* 7 */if (listheader[6].equals("1")) {
							listHeaderExecl.add(item.getBusinessName());
						}
						// *8*/{ "data": "certificateNumber", "autoWidth":
						// true,"visible": isColumnVisible(8)},
						/* 8 */if (listheader[7].equals("1")) {
							listHeaderExecl.add(item.getCertificateNumber());
						}
						// *9*/{ "data": "certificateDate", "autoWidth":
						// true,"visible": isColumnVisible(9)
						/* 9 */if (listheader[8].equals("1")) {
							listHeaderExecl.add(item.getCertificateDate());
						}

						// *10*/{ "data": "certificateAddress", "autoWidth":
						// true,"visible": isColumnVisible(10) },
						/* 10 */if (listheader[9].equals("1")) {
							listHeaderExecl.add(item.getCertificateAddress());
						}

						// *11*/{ "data": "taxNumber", "autoWidth":
						// true,"visible": isColumnVisible(11) },
						/* 11 */if (listheader[10].equals("1")) {
							listHeaderExecl.add(item.getTaxNumber());
						}
						// *12*/{ "data": "budgetRegister", "autoWidth":
						// true,"visible": isColumnVisible(12) },
						/* 12 */if (listheader[11].equals("1")) {
							listHeaderExecl.add(item.getBudgetRegister());
						}
						// *13*/{ "data": "telefone", "autoWidth":
						// true,"visible": isColumnVisible(13) },
						/* 13 */if (listheader[12].equals("1")) {
							listHeaderExecl.add(item.getTelefone());
						}
						// *14*/{ "data": "fax", "autoWidth": true,"visible":
						// isColumnVisible(14) },
						/* 14 */if (listheader[13].equals("1")) {
							listHeaderExecl.add(item.getFax());
						}
						// *15*/{ "data": "email", "autoWidth": true,"visible":
						// isColumnVisible(15) },
						/* 15 */if (listheader[14].equals("1")) {
							listHeaderExecl.add(item.getEmail());
						}
						// *16*/{ "data": "socialAddress", "autoWidth":
						// true,"visible": isColumnVisible(16) },
						/* 16 */if (listheader[15].equals("1")) {
							listHeaderExecl.add(item.getSocialAddress());
						}
						// *17*/{ "data": "businessAddress", "autoWidth":
						// true,"visible": isColumnVisible(17) },
						/* 17 */if (listheader[16].equals("1")) {
							listHeaderExecl.add(item.getBusinessAddress());
						}
						// *18*/{ "data": "adviser", "autoWidth":
						// true,"visible": isColumnVisible(18) },
						/* 18 */if (listheader[17].equals("1")) {
							listHeaderExecl.add(item.getAdviser());
						}
						// *19*/{ "data": "director", "autoWidth":
						// true,"visible": isColumnVisible(19) },
						/* 19 */if (listheader[18].equals("1")) {
							listHeaderExecl.add(item.getDirector());
						}
						// *20*/{ "data": "directorMobile", "autoWidth":
						// true,"visible": isColumnVisible(20) },
						/* 20 */if (listheader[19].equals("1")) {
							listHeaderExecl.add(item.getDirectorMobile());
						}

						// 21{ "data": "directorBirthday", "autoWidth":
						// true,"visible": isColumnVisible(21)
						/* 21 */if (listheader[20].equals("1")) {
							listHeaderExecl.add(item.getDirectorBirthday());
						}
						// 22*/{ "data": "directorDomicile", "autoWidth":
						// true,"visible": isColumnVisible(22) },
						/* 22 */if (listheader[21].equals("1")) {
							listHeaderExecl.add(item.getDirectorDomicile());
						}
						// *23*/{ "data": "sellMan", "autoWidth":
						// true,"visible": isColumnVisible(23) },
						/* 23 */if (listheader[22].equals("1")) {
							listHeaderExecl.add(item.getSellMan());
						}
						// *24*/{ "data": "sellManMobile", "autoWidth":
						// true,"visible": isColumnVisible(24) },
						/* 24 */if (listheader[23].equals("1")) {
							listHeaderExecl.add(item.getSellManMobile());
						}
						// *25*/{ "data": "budgetOriginal", "autoWidth":
						// true,"visible": isColumnVisible(25)},
						/* 25 */if (listheader[24].equals("1")) {
							listHeaderExecl.add(item.getBudgetOriginal());
						}
						// *26*/{ "data": "otherBusiness", "autoWidth":
						// true,"visible": isColumnVisible(26)},
						/* 26 */if (listheader[25].equals("1")) {
							listHeaderExecl.add(item.getOtherBusiness());
						}
						// *27*/{ "data":
						// "customerByCustomer5Level1Id.businessName",
						// "autoWidth": true,"visible": isColumnVisible(27) },
						/* 27 */if (listheader[26].equals("1")) {
							listHeaderExecl.add(item
									.getCustomerByCustomer5Level1Id()
									.getBusinessName());
						}
						// *28*/{ "data": "customer5Percent", "autoWidth":
						// true,"visible": isColumnVisible(28) },
						/* 28 */if (listheader[27].equals("1")) {
							listHeaderExecl.add(String.valueOf(item
									.getCustomer5Percent()));
						}
						// *29*/{ "data":
						// "customerByCustomer4Level1Id.businessName",
						// "autoWidth": true,"visible": isColumnVisible(29)},
						/* 29 */if (listheader[28].equals("1")) {
							listHeaderExecl.add(item
									.getCustomerByCustomer4Level1Id()
									.getBusinessName());
						}
						// *30*/{ "data": "customer4Percent", "autoWidth":
						// true,"visible": isColumnVisible(30) },
						/* 30 */if (listheader[29].equals("1")) {
							listHeaderExecl.add(String.valueOf(item
									.getCustomer4Percent()));
						}

						// *31*/{ "data":
						// "customerByCustomer3Level1Id.businessName",
						// "autoWidth": true,"visible": isColumnVisible(31) },
						/* 31 */if (listheader[30].equals("1")) {
							listHeaderExecl.add(item
									.getCustomerByCustomer3Level1Id()
									.getBusinessName());
						}
						// *32*/{ "data": "customer3Percent", "autoWidth":
						// true,"visible": isColumnVisible(32) },
						/* 32 */if (listheader[31].equals("1")) {
							listHeaderExecl.add(String.valueOf(item
									.getCustomer3Percent()));
						}
						// *33*/{ "data":
						// "customerByCustomer2Level1Id.businessName",
						// "autoWidth": true,"visible": isColumnVisible(33) },
						/* 33 */if (listheader[32].equals("1")) {
							listHeaderExecl.add(item
									.getCustomerByCustomer2Level1Id()
									.getBusinessName());
						}
						// *34*/{ "data": "customer2Percent", "autoWidth":
						// true,"visible": isColumnVisible(34) },
						/* 34 */if (listheader[33].equals("1")) {
							listHeaderExecl.add(String.valueOf(item
									.getCustomer2Percent()));
						}
						// *35*/{ "data":
						// "customerByCustomer1Level1Id.businessName",
						// "autoWidth": true,"visible": isColumnVisible(35) },
						/* 35 */if (listheader[34].equals("1")) {
							listHeaderExecl.add(item
									.getCustomerByCustomer1Level1Id()
									.getBusinessName());
						}
						// *36*/{ "data": "customer1Percent", "autoWidth":
						// true,"visible": isColumnVisible(36) },
						/* 36 */if (listheader[35].equals("1")) {
							listHeaderExecl.add(String.valueOf(item
									.getCustomer1Percent()));
						}
						// *37*/{ "data": "product1Hot", "autoWidth":
						// true,"visible": isColumnVisible(37) },
						/* 37 */if (listheader[36].equals("1")) {
							listHeaderExecl.add(item.getProduct1Hot());
						}
						// *38*/{ "data": "product2Hot", "autoWidth":
						// true,"visible": isColumnVisible(38) },
						/* 38 */if (listheader[37].equals("1")) {
							listHeaderExecl.add(item.getProduct2Hot());
						}
						// *39*/{ "data": "product3Hot", "autoWidth":
						// true,"visible": isColumnVisible(39) },
						/* 39 */if (listheader[38].equals("1")) {
							listHeaderExecl.add(item.getProduct3Hot());
						}
						// *40*/{ "data": "product4Hot", "autoWidth":
						// true,"visible": isColumnVisible(40) },
						/* 40 */if (listheader[39].equals("1")) {
							listHeaderExecl.add(item.getProduct4Hot());
						}

						// *41*/{ "data": "product5Hot", "autoWidth":
						// true,"visible": isColumnVisible(41) },
						/* 41 */if (listheader[40].equals("1")) {
							listHeaderExecl.add(item.getProduct5Hot());
						}
						// *42*/{ "data": "product6Hot", "autoWidth":
						// true,"visible": isColumnVisible(42)},
						/* 42 */if (listheader[41].equals("1")) {
							listHeaderExecl.add(item.getProduct6Hot());
						}
						// *43*/{ "data": "farmProduct1", "autoWidth":
						// true,"visible": isColumnVisible(43) },
						/* 43 */if (listheader[42].equals("1")) {
							listHeaderExecl.add(item.getFarmProduct1());
						}
						// *44*/{ "data": "farmProduct1Session", "autoWidth":
						// true,"visible": isColumnVisible(44) },
						/* 44 */if (listheader[43].equals("1")) {
							listHeaderExecl.add(item.getFarmProduct1Session());
						}
						// *45*/{ "data": "farmProduct2", "autoWidth":
						// true,"visible": isColumnVisible(45) },
						/* 45 */if (listheader[44].equals("1")) {
							listHeaderExecl.add(item.getFarmProduct2());
						}
						// *46*/{ "data": "farmProduct2Session", "autoWidth":
						// true,"visible": isColumnVisible(46) },
						/* 46 */if (listheader[45].equals("1")) {
							listHeaderExecl.add(item.getFarmProduct2Session());
						}
						// *47*/{ "data": "farmProduct3", "autoWidth":
						// true,"visible": isColumnVisible(47)},
						/* 47 */if (listheader[46].equals("1")) {
							listHeaderExecl.add(item.getFarmProduct3());
						}
						// *48*/{ "data": "farmProduct3Session", "autoWidth":
						// true,"visible": isColumnVisible(48) },
						/* 48 */if (listheader[47].equals("1")) {
							listHeaderExecl.add(item.getFarmProduct3Session());
						}
						// *49*/{ "data": "farmProduct4", "autoWidth":
						// true,"visible": isColumnVisible(49)},
						/* 49 */if (listheader[48].equals("1")) {
							listHeaderExecl.add(item.getFarmProduct4());
						}
						// *50*/{ "data": "farmProduct4Session", "autoWidth":
						// true,"visible": isColumnVisible(50) },
						/* 50 */if (listheader[49].equals("1")) {
							listHeaderExecl.add(item.getFarmProduct4Session());
						}
						xls.addRowData(sheet, startIndexRow, startIndexCell, listHeaderExecl.toArray());
						startIndexRow++;

					}
				}

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				inputStream = new ByteArrayInputStream(baos.toByteArray());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;

	}

	public String lisCustomerJson() throws Exception {
		try {

			try {
				Map sessionMap = (Map) ActionContext.getContext()
						.get("session");
				varCusByUser = StringUtil.replaceInvalidChar(StringUtil
						.notNull(sessionMap.get("varCusByUser")));
				varCusAssign = (boolean) sessionMap.get("varCusAssign");
				varCusNotAssign = (boolean) sessionMap.get("varCusNotAssign");
				varCusByLevel1 = Integer.parseInt(StringUtil.notNull(sessionMap
						.get("varCusByLevel1")));
				System.out.println("Parameter: " + varCusByUser + " - "
						+ varCusAssign + " - " + varCusNotAssign + " - "
						+ varCusByLevel1);
			} catch (Exception e) {
				varCusByUser = "";
				varCusAssign = true;
				varCusNotAssign = true;
				varCusByLevel1 = -1;
			}

			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table

			String start = (request.getParameter("start"));
			String length = (request.getParameter("length"));
			String strDraw = (request.getParameter("draw"));
			draw = StringUtil.notNull(strDraw).isEmpty() ? 0 : Integer
					.parseInt(strDraw);
			order = StringUtil.notNull((request.getParameter("order[i][dir]")));
			System.out.println("order = " + order);
			search = StringUtil.replaceInvalidChar(StringUtil.notNull(request
					.getParameter("search[value]")));
			System.out.println("search = " + search);

			int pageSize = length != null ? Integer.parseInt(length) : 0;
			int skip = start != null ? Integer.parseInt(start) : 0;
			System.out.println("pageSize = " + pageSize);
			System.out.println("skip = " + skip);

			CustomerHome cusHome = new CustomerHome(
					HibernateUtil.getSessionFactory());
			// Get Total Record Count for Pagination
			recordsTotal = cusHome.getTotalRecords(search, varCusByUser,
					varCusAssign & !varCusNotAssign ? 1 : (!varCusAssign
							& varCusNotAssign ? 2 : 0), varCusByLevel1);
			recordsFiltered = recordsTotal;
			if (pageSize == -1) {
				pageSize = recordsFiltered;
			}
			data = cusHome.getListCustomer(skip, pageSize, search,
					varCusByUser, varCusAssign & !varCusNotAssign ? 1
							: (!varCusAssign & varCusNotAssign ? 2 : 0),
					varCusByLevel1);

			System.out.println("Records total " + data.size() + "/"
					+ recordsTotal);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
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
		DefineColumnImport dci = new DefineColumnImport("Mã khách hàng",
				"customerCode", "1");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Tên Bảng Kê", "statisticName", "2");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Nhóm", "groupCustomer", "3");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Cấp 1 đang nhận hành chính",
				"customerByCustomer1Level1Id", "4");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Tên doanh nghiệp", "businessName", "5");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Tên thường gọi", "otherBusiness", "6");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Giấy phép ĐKKD", "certificateNumber", "7");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Ngày cấp giấy phép ĐKKD",
				"certificateDate", "8");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Người đại diện pháp luật", "lawyer", "9");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Địa chỉ kinh doanh", "businessAddress",
				"10");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Mã số thuế", "taxNumber", "11");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Vốn đăng kí", "budgetRegister", "12");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Điện thoại bàn", "telefone", "13");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Fax", "fax", "14");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Người quyết định chính công việc",
				"director", "15");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("ĐT di động người QĐCV", "directorMobile",
				"16");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Ngày sinh", "directorBirthday", "17");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("Người bán hàng trực tiếp", "sellMan",
				"18");
		getListDefineColumnsLevel2().add(dci);
		dci = new DefineColumnImport("ĐT di động người bán hàng",
				"sellManMobile", "19");
		getListDefineColumnsLevel2().add(dci);
	}

	private void defineColumnImportLevel1() {
		setListDefineColumnsLevel1(new ArrayList<DefineColumnImport>());
		DefineColumnImport dci = new DefineColumnImport("Mã khách hàng",
				"customerCode", "2");
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
		listTableColumn.add(new Object[] { "Người quyết định chính công việc",
				false });
		listTableColumn.add(new Object[] { "ĐTDĐ Người quyết định", false });
		listTableColumn.add(new Object[] { "Ngày sinh", false });
		listTableColumn.add(new Object[] { "Nguyên quán", false });
		listTableColumn.add(new Object[] { "Người bán hàng trực tiếp", false });
		listTableColumn.add(new Object[] { "ĐTDĐ Người bán hàng", false });
		listTableColumn
				.add(new Object[] { "Ước vốn tự có để kinh doanh", false });
		listTableColumn
				.add(new Object[] { "Ngành nghề kinh doanh khác", false });
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
		listTableColumn
				.add(new Object[] { "5 Sản phẩm thuốc trừ bệnh", false });
		listTableColumn.add(new Object[] { "3 Sản phẩm kích thích sinh trưởng",
				false });
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

	public static void main(String[] args) {
		try {

			int index = 0;
			String abc = "1-0-0-1-1-1-1-1-1-0zz";
			System.out.println(abc.substring(0, (index) * 2) + "5"
					+ abc.substring(((index) * 2) + 1, abc.length()));

			// CustomerAction2 c = new CustomerAction2();
			// c.defineTableViewCustomer();
			// c.generateCookieColumnVisible();

			// CustomerHome cusHome = new
			// CustomerHome(HibernateUtil.getSessionFactory());
			// System.out.println(cusHome.getListCustomer(0, 100, "", "", 0,
			// false).size());
			// System.out.println(cusHome.getTotalRecords("", "", 0, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void generateCookieColumnVisible() {
		boolean isExist = false;
		String columnActive = "";
		for (Cookie c : servletRequest.getCookies()) {
			if (c.getName().equals("columnActive")) {
				columnActive = c.getValue();
				System.out.println(columnActive);
				isExist = true;
			}
		}
		String splitSep = "";
		for (int i = 0; i < listTableColumn.size(); i++) {
			if (isExist) {
				if (columnActive.split("-")[i].equals("1"))
					listTableColumn.get(i)[1] = true;
				else
					listTableColumn.get(i)[1] = false;
			} else {
				if ((boolean) listTableColumn.get(i)[1] == true)
					columnActive += splitSep + "1";
				else
					columnActive += splitSep + "0";
			}
			splitSep = "-";
		}

		Cookie div = new Cookie("columnActive", columnActive);

		div.setMaxAge(60 * 60 * 24 * 1); // Make the cookie last a day!
		servletResponse.addCookie(div);
	}

	public String SearchCustomer() {
		listCustomerType = GetListCustomerType();
		listCustomerToRank = GetListCustomerToRank();
		listUser = GetListUser();
		System.out.println("listUser:" + listUser.size());
		return SUCCESS;

	}

	public List<String> GetListCustomerType() {
		List<String> result = new ArrayList<String>();
		try {
			result.add("DSKH đã phân công");
			result.add("DSKH chưa phân công");
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup group of customers. Exception: "
					+ e.getMessage());
		}
		return result;
	}

	public List<Customer> GetListCustomerToRank() {
		List<Customer> listdata = new ArrayList<Customer>();
		try {
			CustomerHome cusHome = new CustomerHome(
					HibernateUtil.getSessionFactory());
			listdata = cusHome.getLookupCustomer(0, 1);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup group of customers. Exception: "
					+ e.getMessage());
		}
		return listdata;
	}

	public List<User> GetListUser() {
		List<User> listdata = new ArrayList<User>();
		try {
			UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());
			listdata = userHome.getListUser();
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error: load lookup group of customers. Exception: "
					+ e.getMessage());
		}
		return listdata;
	}

	@Override
	public void validate() {

		try {
			defineTableViewCustomer();
			// Save and Load to cookie
			generateCookieColumnVisible();
			defineColumnImportLevel1();
			defineColumnImportLevel2();
			generateColumnExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public List<Customer> getData() {
		return data;
	}

	public void setData(List<Customer> data) {
		this.data = data;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<Object[]> getListTableColumn() {
		return listTableColumn;
	}

	public void setListTableColumn(List<Object[]> listTableColumn) {
		this.listTableColumn = listTableColumn;
	}

	public List<DefineColumnImport> getListDefineColumnsLevel1() {
		return listDefineColumnsLevel1;
	}

	public void setListDefineColumnsLevel1(
			List<DefineColumnImport> listDefineColumnsLevel1) {
		this.listDefineColumnsLevel1 = listDefineColumnsLevel1;
	}

	public List<DefineColumnImport> getListDefineColumnsLevel2() {
		return listDefineColumnsLevel2;
	}

	public void setListDefineColumnsLevel2(
			List<DefineColumnImport> listDefineColumnsLevel2) {
		this.listDefineColumnsLevel2 = listDefineColumnsLevel2;
	}

	public List<String> getListColumnExcel() {
		return listColumnExcel;
	}

	public void setListColumnExcel(List<String> listColumnExcel) {
		this.listColumnExcel = listColumnExcel;
	}

	public String getVarCusByUser() {

		return varCusByUser;
	}

	public void setVarCusByUser(String varCusByUser) {
		this.varCusByUser = varCusByUser;
	}

	public boolean isVarCusAssign() {
		return varCusAssign;
	}

	public void setVarCusAssign(boolean varCusAssign) {
		this.varCusAssign = varCusAssign;
	}

	public boolean isVarCusNotAssign() {
		return varCusNotAssign;
	}

	public void setVarCusNotAssign(boolean varCusNotAssign) {
		this.varCusNotAssign = varCusNotAssign;
	}

	public int getVarCusByLevel1() {
		return varCusByLevel1;
	}

	public void setVarCusByLevel1(int varCusByLevel1) {
		this.varCusByLevel1 = varCusByLevel1;
	}

	public List<String> getListCustomerType() {
		return listCustomerType;
	}

	public void setListCustomerType(List<String> listCustomerType) {
		this.listCustomerType = listCustomerType;
	}

	public List<Customer> getListCustomerToRank() {
		return listCustomerToRank;
	}

	public void setListCustomerToRank(List<Customer> listCustomerToRank) {
		this.listCustomerToRank = listCustomerToRank;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
