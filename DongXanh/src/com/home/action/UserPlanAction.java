package com.home.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXAgendaView;
import com.dhtmlx.planner.controls.DHXExternalLightboxForm;
import com.dhtmlx.planner.controls.DHXGridView;
import com.dhtmlx.planner.controls.DHXGridViewColumn;
import com.dhtmlx.planner.controls.DHXLightboxMiniCalendar;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;
import com.home.dao.CategoryHome;
import com.home.dao.CustomEventsManager;
import com.home.dao.CustomerHome;
import com.home.dao.StatisticHome;
import com.home.dao.UserHome;
import com.home.dao.UserPlanHome;
import com.home.entities.UserAware;
import com.home.model.Category;
import com.home.model.Customer;
import com.home.model.MessageStore;
import com.home.model.Statistic;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserPlanAction extends ActionSupport implements UserAware {
	private static final long serialVersionUID = 1L;
	private MessageStore messageStore = new MessageStore();
	private User userSes;
	private List<Customer> listCustomer = new ArrayList<Customer>();
	private List<User> listEmployee = new ArrayList<User>();
	private InputStream ical;
	private InputStream fileInputStream;
	public boolean isPermissionAccept = false;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	private Workbook workbook;

	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public User getUserSes() {
		return userSes;
	}

	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	private void getListCustomerByUserId() {
		CustomerHome custHome = new CustomerHome(
				HibernateUtil.getSessionFactory());
		setListCustomer(custHome.getListCustomerByUserId(userSes.getId()));
	}

	private void getEmployees() {
		UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());
		setListEmployee(userHome.getLookupEmployee());
	}

	public InputStream getIcal() {
		return ical;
	}

	public String ical_16() throws Exception {
		String data = ServletActionContext.getRequest().getParameter("ical");
		byte[] bytes = data.getBytes("UTF-8");
		ical = new ByteArrayInputStream(bytes);
		return SUCCESS;
	}

	private DHXGridView customGridView() {
		DHXGridView grid = new DHXGridView();
		grid.setLabel("Chi tiết");
		grid.addOption(new DHXGridViewColumn("text", "Mô tả", 200));
		grid.addOption(new DHXGridViewColumn("start_date", "Bắt đầu", 200));
		grid.addOption(new DHXGridViewColumn("end_date", "Kết thúc", 200));
		grid.addOption(new DHXGridViewColumn("contactType",	"Hình thức liên hệ", 200));
		grid.addOption(new DHXGridViewColumn("customerId", "Khách hàng", 200));
		Calendar cal = Calendar.getInstance();
		grid.setFrom(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH));
		return grid;
	}

	public String getUserPlan() throws Exception {
		try {
			// creates and configures scheduler instance
			DHXPlanner planner = new DHXPlanner("./codebase/", DHXSkin.GLOSSY);
			planner.templates.setDayScaleDate("{date:date(%d/%m/%Y)}");
			planner.templates.setWeekScaleDate("{date:date(%d/%m/%Y)}");
			planner.setInitialDate(new Date());
			planner.config.setScrollHour(8);
			planner.setWidth(950);
			planner.config.setDetailsOnCreate(true);
			planner.config.setDblClickCreate(true);
			planner.config.setDetailsOnDblClick(true);
			planner.load("events", DHXDataFormat.JSON);
			planner.data.dataprocessor.setURL("events");
			// Xem chi tiết
			planner.views.add(customGridView());
			// Xem tóm tắt
			DHXAgendaView agen = new DHXAgendaView();
			agen.setLabel("Danh sách");
			planner.views.add(agen);

			planner.calendars.attachMiniCalendar();
			planner.lightbox.add(new DHXLightboxMiniCalendar("cal",
					"Time period"));

			planner.setInitialView("week");

			DHXExternalLightboxForm box = planner.lightbox
					.setExternalLightboxForm("./custom_editor.action", 400, 300);
			box.setClassName("custom_lightbox");

			planner.toPDF();
			planner.toICal("16_ical");

			//System.out.println(planner.render());
			messageStore.setScheduler(planner.render());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String editor_custom() throws Exception {
		getListCustomerByUserId();
		getEmployees();
		isPermissionAccept = !(userSes.getRole().getRoleId() == ROLE_ADMIN
				| userSes.getRole().getRoleId() == ROLE_LEADER);
		System.out.println("UserPlanAction.editor_custom() "+isPermissionAccept);
		return SUCCESS;
	}

	public String planEvents() throws Exception {
		CustomEventsManager evs = new CustomEventsManager(
				ServletActionContext.getRequest(), userSes);
		messageStore.setData(evs.run());
		return Action.SUCCESS;
	}

	public String exportEvent() {
		try {
			ServletContext servletContext = ServletActionContext
					.getServletContext();
			String pathname = servletContext
					.getRealPath("/WEB-INF/template/excel/event_template.xlsx");
			File theFile = new File(pathname);
			ExcelUtil xls = new ExcelUtil();
			UserPlanHome upHome = new UserPlanHome(
					HibernateUtil.getSessionFactory());
			List<Object[]> listEvent = upHome.getEventExport();
			try (FileInputStream fis = new FileInputStream(theFile)) {
				workbook = xls.getWorkbook(fis,
						FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				int startIndexRow = 5;
				int startIndexCell = 0;
				startIndexRow++;
				for (int i = 0; i < listEvent.size(); i++) {
					xls.addRowData(sheet, startIndexRow, startIndexCell,
							(i + 1), listEvent.get(i)[0], listEvent.get(i)[1],
							listEvent.get(i)[2], listEvent.get(i)[3],
							listEvent.get(i)[4], listEvent.get(i)[5],
							listEvent.get(i)[6]);
					startIndexRow++;

				}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public void setUserSes(User user) {
		this.userSes = user;

	}

	public List<User> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<User> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public boolean isPermissionAccept() {
		return isPermissionAccept;
	}

	public void setPermissionAccept(boolean isPermissionAccept) {
		this.isPermissionAccept = isPermissionAccept;
	}
}
