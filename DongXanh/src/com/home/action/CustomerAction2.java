package com.home.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.CustomerHome;
import com.home.entities.DefineColumnImport;
import com.home.model.Customer;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction2 extends ActionSupport implements Action, ServletContextAware{
	private ServletContext ctx;
	private List<Customer> data;
	private int recordsFiltered ;
	private int recordsTotal  ;
	private int draw;
	private String order;
	private String search;
	private List<Object[]> listTableColumn = new ArrayList<Object[]>();
	private List<DefineColumnImport> listDefineColumnsLevel1;
	private List<DefineColumnImport> listDefineColumnsLevel2;
	private List<String> listColumnExcel;
	
	public static void main(String[] args) {
		try {
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			System.out.println(cusHome.getListCustomer(0, 100, "").size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String lisCustomerJson() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			String start  = (request.getParameter("start"));
			String length  = (request.getParameter("length"));
			String strDraw  = (request.getParameter("draw"));
			draw = StringUtil.notNull(strDraw).isEmpty()?0:Integer.parseInt(strDraw);
			order = StringUtil.notNull((request.getParameter("order[i][dir]")));
			System.out.println("order = " + order);
			search = StringUtil.replaceInvalidChar(StringUtil.notNull(request.getParameter("search[value]")));
			System.out.println("search = " + search);

			int pageSize = length != null? Integer.parseInt(length) : 0;
			int skip = start != null ? Integer.parseInt(start) : 0;

			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			data = cusHome.getListCustomer(skip, pageSize, search);
			// Get Total Record Count for Pagination
			recordsTotal = cusHome.getTotalRecords();
			recordsFiltered = recordsTotal;

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
			defineTableViewCustomer();
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

}
