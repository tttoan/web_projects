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
import javax.servlet.http.HttpServletRequest;
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
import com.home.dao.CustomEventsManager;
import com.home.dao.CustomerHome;
import com.home.dao.UserHome;
import com.home.dao.UserPlanHome;
import com.home.entities.UserAware;
import com.home.model.Customer;
import com.home.model.MessageStore;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserPlanAction extends ActionSupport implements UserAware {
	private static final long serialVersionUID = 1L;
	private MessageStore messageStore = new MessageStore();
	private User userSes;
	private User selectedUserPlan;
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
		setListCustomer(custHome.getListCustomerByUserId(getSelectedUserPlan().getId()));
	}

	private void getEmployees() {
		boolean isAdmin = (userSes.getRole().getRoleId() == ROLE_ADMIN
				|| userSes.getRole().getRoleId() == ROLE_LEADER);
		UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());
		setListEmployee(userHome.getLookupEmployee(userSes.getId(), isAdmin));
		if(selectedUserPlan != null && selectedUserPlan.getId() != null){
			for (User user : listEmployee) {
				if(user.getId() == selectedUserPlan.getId()){
					selectedUserPlan = user;
					break;
				}
			}
		}
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
			int emp_id = -1;
			selectedUserPlan = null;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				emp_id = Integer.parseInt(request.getParameter("emp_id"));
				if(emp_id > 0){
					//System.out.println("emp_id="+emp_id);
					selectedUserPlan = new User();
					selectedUserPlan.setId(emp_id);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// creates and configures scheduler instance
			DHXPlanner planner = new DHXPlanner("./codebase/", DHXSkin.GLOSSY);
			planner.templates.setDayScaleDate("{date:date(%D, %d/%m/%Y)}");
			planner.templates.setWeekScaleDate("{date:date(%D, %d/%m/%Y)}");
			planner.setInitialDate(new Date());
			planner.config.setScrollHour(7);
			planner.setWidth(1000);
			planner.setHeight(650);
			planner.config.setDetailsOnCreate(true);
			planner.config.setDblClickCreate(true);
			planner.config.setDetailsOnDblClick(true);
			planner.load("events?emp_id="+emp_id, DHXDataFormat.JSON);
			planner.data.dataprocessor.setURL("events?emp_id="+emp_id);
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
			editor_custom();
			System.out.println("vao 2");
			//System.out.println("selectedUserPlan = " + selectedUserPlan.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public User getSelectedUserPlan() {
		if(selectedUserPlan != null){
			System.out.println("get selectedUserPlan");
			return selectedUserPlan;
		}else{
			System.out.println("get userSes");
			return userSes;
		}
	}

	public void setSelectedUserPlan(User selectedUserPlan) {
		this.selectedUserPlan = selectedUserPlan;
	}

	public String editor_custom() throws Exception {
		getListCustomerByUserId();
		checkPermissionAccept();
		getEmployees();
		System.out.println("UserPlanAction.editor_custom() "+isPermissionAccept);
		return SUCCESS;
	}
	
	private void checkPermissionAccept(){
		if(userSes.getRole() != null && userSes.getRole().getRoleId() != null && userSes.getRole().getRoleId() > 0){
			isPermissionAccept = !(userSes.getRole().getRoleId() == ROLE_ADMIN
					|| userSes.getRole().getRoleId() == ROLE_LEADER);
		}
	}

	public String planEvents() throws Exception {
		System.out.println("get planEvents...");
		int emp_id = -1;
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			emp_id = Integer.parseInt(request.getParameter("emp_id"));
			if(emp_id > 0){
				System.out.println("emp_id="+emp_id);
				selectedUserPlan = new User();
				selectedUserPlan.setId(emp_id);
				editor_custom();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		//request.setCharacterEncoding("UTF-8");
		//request.setAttribute("Content-type", "text/html; charset=UTF-8");
		CustomEventsManager evs = new CustomEventsManager(
				request, getSelectedUserPlan());
		messageStore.setData(evs.run());
		//System.out.println(messageStore.getData());
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
	
	public String getPlanStatistic(){
		return SUCCESS;
	}

	public String getPlanGeneral(){
		return SUCCESS;
	}

	public String getPlanDetail(){
		return SUCCESS;
	}

	
	@Override
	public void setUserSes(User user) {
		this.userSes = user;
		System.out.println("vao 1");
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
