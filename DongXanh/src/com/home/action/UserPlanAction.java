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
import com.dhtmlx.planner.controls.DHXExternalLightboxForm;
import com.dhtmlx.planner.controls.DHXGridView;
import com.dhtmlx.planner.controls.DHXGridViewColumn;
import com.dhtmlx.planner.controls.DHXGridViewColumn.Aligns;
import com.dhtmlx.planner.controls.DHXLightboxMiniCalendar;
import com.dhtmlx.planner.controls.DHXMonthView;
import com.dhtmlx.planner.controls.DHXTimelineView;
import com.dhtmlx.planner.controls.DHXTimelineView.RenderModes;
import com.dhtmlx.planner.controls.DHXTimelineView.XScaleUnits;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;
import com.home.conts.UserPlanDefine;
import com.home.dao.ContactTypeHome;
import com.home.dao.CustomEventsManager;
import com.home.dao.CustomerHome;
import com.home.dao.TimelineTypeHome;
import com.home.dao.UserHome;
import com.home.dao.UserPlanHome;
import com.home.entities.UserAware;
import com.home.model.ContactType;
import com.home.model.Customer;
import com.home.model.MessageStore;
import com.home.model.TimelineType;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserPlanAction extends ActionSupport implements UserAware, UserPlanDefine {
	private static final long serialVersionUID = 1L;
	private MessageStore messageStore = new MessageStore();
	private User userSes;
	private User selectedUserPlan;
	private List<Customer> listCustomer = new ArrayList<Customer>();
	private List<User> listEmployee = new ArrayList<>();
	private List<ContactType> listContactType = new ArrayList<>();
	private List<TimelineType> listTimelineType = new ArrayList<>();
	private InputStream ical;
	private InputStream fileInputStream;
	public boolean isPermissionAccept = false;
//	private String contactAddress;
//
//	public String getContactAddress() {
//		return contactAddress;
//	}
//
//	public void setContactAddress(String contactAddress) {
//		this.contactAddress = contactAddress;
//	}

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
		setListCustomer(custHome.getListCustomerByUserId(getSelectedUserPlan()
				.getId()));
	}
	private void getAllContactType() {
		ContactTypeHome ctHome = new ContactTypeHome(
				HibernateUtil.getSessionFactory());
		setListContactType(ctHome.findAll());
	}
	private void getAllTimelineType() {
		TimelineTypeHome tlHome = new TimelineTypeHome(
				HibernateUtil.getSessionFactory());
		setListTimelineType(tlHome.findAll());
	}
	private void getEmployees() {
		boolean isAdmin = (userSes.getRole().getRoleId() == ROLE_ADMIN || userSes
				.getRole().getRoleId() == ROLE_LEADER);
		UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());
		setListEmployee(userHome.getLookupEmployee(userSes.getId(), isAdmin));
		if (selectedUserPlan != null && selectedUserPlan.getId() != null) {
			for (User user : listEmployee) {
				if (user.getId() == selectedUserPlan.getId()) {
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

	/*private DHXGridView customGridView() {
		DHXGridView grid = new DHXGridView(GRIF_VIEW_NAME);
		grid.setLabel("Chi tiết");
		//NVTT
		DHXGridViewColumn employeeName = new DHXGridViewColumn("employeeName", "NVTT");
		employeeName.setWidth(180);
		grid.addOption(employeeName);
		//KH Cũ
		DHXGridViewColumn customerNameOld = new DHXGridViewColumn("customerNameOld", "KH Cũ");
		customerNameOld.setWidth(200);
		grid.addOption(customerNameOld);
		//KH Mới
		DHXGridViewColumn customerNameNew= new DHXGridViewColumn("customerNameNew", "KH Mới");
		customerNameNew.setWidth(200);
		grid.addOption(customerNameNew);
		//Hình Thức LH
		DHXGridViewColumn contactTypeName= new DHXGridViewColumn("contactTypeName", "Hình Thức LH");
		contactTypeName.setWidth(120);
		grid.addOption(contactTypeName);
		//Thời Điểm
		DHXGridViewColumn timelineTypeName = new DHXGridViewColumn("timelineTypeName", "Thời Điểm");
		timelineTypeName.setWidth(100);
		grid.addOption(timelineTypeName);
		//Chỉnh Sửa
		DHXGridViewColumn lastModified=new DHXGridViewColumn("lastModified", "Chỉnh Sửa");
		//lastModified.setWidth(200);
		grid.addOption(lastModified);
//		Calendar cal = Calendar.getInstance();
//		grid.setFrom(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
//				cal.get(Calendar.DAY_OF_MONTH));
		grid.setPaging(true);
		return grid;
	}*/
	
	private DHXGridView customGridView() {
		DHXGridView grid = new DHXGridView(GRIF_VIEW_NAME);
		grid.setLabel("Chi tiết");
		//NVTT
		DHXGridViewColumn user_name = new DHXGridViewColumn("nvtt", "NVTT");
		user_name.setWidth(120);
		grid.addOption(user_name);
		//KH Cũ
		DHXGridViewColumn dayPlan = new DHXGridViewColumn("dayPlan", "Ngày công tác");
		dayPlan.setWidth(200);
		dayPlan.setAlign(Aligns.LEFT);
		grid.addOption(dayPlan);
		//Hình Thức LH
		DHXGridViewColumn contactTypeName= new DHXGridViewColumn("contactTypeName", "Hình Thức LH");
		contactTypeName.setWidth(100);
		contactTypeName.setAlign(Aligns.LEFT);
		grid.addOption(contactTypeName);
		//Thời Điểm
		DHXGridViewColumn timelineTypeName = new DHXGridViewColumn("timelineTypeName", "Thời Điểm");
		timelineTypeName.setWidth(100);
		grid.addOption(timelineTypeName);
		//KH Mới
		DHXGridViewColumn customerCode= new DHXGridViewColumn("customerCode", "Mã KH");
		customerCode.setWidth(150);
		customerCode.setAlign(Aligns.LEFT);
		grid.addOption(customerCode);
		//KH Mới
		DHXGridViewColumn customerName= new DHXGridViewColumn("customerName", "Tên người QĐCV");
		customerName.setWidth(250);
		customerName.setAlign(Aligns.LEFT);
		grid.addOption(customerName);
		//Chỉnh Sửa
		DHXGridViewColumn customerAddress=new DHXGridViewColumn("customerAddress", "Điện thoại/Địa chỉ");
		//customerAddress.setWidth(250);
		customerAddress.setAlign(Aligns.LEFT);
		grid.addOption(customerAddress);
		
		grid.setPaging(true);
		return grid;
	}

	public String getUserPlan() throws Exception {
		try {
			messageStore = new MessageStore();
			int emp_id = -1;
			String currentTab = "Timeline";
			selectedUserPlan = null;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext
						.getContext().get(ServletActionContext.HTTP_REQUEST);
				emp_id = Integer.parseInt(request.getParameter("emp_id"));
				if(!StringUtil.notNull(request.getParameter("curTabName")).isEmpty())
					currentTab = StringUtil.notNull(request.getParameter("curTabName"));
				if (emp_id > 0) {
					// System.out.println("emp_id="+emp_id);
					selectedUserPlan = new User();
					selectedUserPlan.setId(emp_id);
				}
			} catch (Exception e) {
			}
			// creates and configures scheduler instance
			DHXPlanner planner = new DHXPlanner("./codebase/", DHXSkin.GLOSSY);
			// Planner
			//planner.setInitialDate(new Date());
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.set(Calendar.DAY_OF_WEEK, 2);
			planner.setInitialDate(c.getTime());
			
			//planner.extensions.add(DHXExtension.CONTAINER_AUTORESIZE);
			planner.setWidth(1100);
			//planner.setWidth(97, "%");
			planner.setHeight(650, "px");
			
			planner.setInitialView(currentTab);
			// Template
			planner.templates.setMonthScaleDate("{date:date(%l, %d/%m/%Y)}");
			planner.templates.setDayScaleDate("{date:date(%l, %d/%m/%Y)}");
			planner.templates.setWeekScaleDate("{date:date(%l, %d/%m/%Y)}");
			// Config
			planner.config.setDetailsOnCreate(true);
			planner.config.setDblClickCreate(true);
			planner.config.setDetailsOnDblClick(true);
			planner.config.setDefaultDate("%d/%m/%Y");
			planner.config.setMonthDate("%m/%Y");
			planner.config.setScrollHour(24);
			planner.config.setFirstHour(8);
			planner.config.setLastHour(19);
			planner.config.setHourSizePx(55);
			// View
//			planner.views.getView(0).setLabel("Tháng");
//			planner.views.getView(1).setLabel("Tuần");
//			planner.views.getView(2).setLabel("Ngày");
			planner.views.clear();
			planner.views.add(new DHXMonthView());
			planner.views.getView(0).setLabel("Tháng");
			
			planner.load("events?emp_id=" + emp_id+"&curTabName=" + currentTab, DHXDataFormat.JSON);
			planner.data.dataprocessor.setURL("events?emp_id=" + emp_id+"&curTabName=" + currentTab);

			// Xem chi tiết
			planner.views.add(customGridView());

			// // Xem tóm tắt
			// DHXAgendaView agen = new DHXAgendaView();
			// agen.setLabel("Danh sách");
			// planner.views.add(agen);

			// Xem Timline
			DHXTimelineView view = new DHXTimelineView("Timeline", "typeOfDay",
					"Lịch tuần");
			view.setRenderMode(RenderModes.BAR);
	    	view.setXScaleUnit(XScaleUnits.DAY);
	    	view.addSecondScale(DHXTimelineView.XScaleUnits.DAY, "%l, %d/%m");
	    	view.setXStep(1);
			view.setXSize(7);// (8PM - 8AM)/30min
			view.setXStart(0);// 8AM/30min
			view.setXLength(7);// 24/30min
			view.setServerList("typeOfDayCollect");
		
			planner.views.add(view);

			planner.calendars.attachMiniCalendar();
			planner.lightbox.add(new DHXLightboxMiniCalendar("cal",
					"Time period"));
			DHXExternalLightboxForm box = planner.lightbox
					.setExternalLightboxForm("./custom_editor.action", 400, 350);
			box.setClassName("custom_lightbox");

			// planner.toPDF();
			// planner.toICal("16_ical");

			//System.out.println(planner.render());
			messageStore.setScheduler(planner.render());
			editor_custom();
			// System.out.println("vao 2");
			// System.out.println("selectedUserPlan = " +
			// selectedUserPlan.getId());
			
//			ArrayList<DHXView> vv = planner.views.getViews();
//			for (DHXView dhxView : vv) {
//				System.out.println("UserPlanAction.getUserPlan() "+ dhxView.getLabel());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public User getSelectedUserPlan() {
		if (selectedUserPlan != null) {
			//System.out.println("get selectedUserPlan");
			return selectedUserPlan;
		} else {
			//System.out.println("get userSes");
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
		getAllContactType();
		getAllTimelineType();
		//setContactAddress("my address");
		return SUCCESS;
	}

	private void checkPermissionAccept() {
		if (userSes.getRole() != null && userSes.getRole().getRoleId() != null
				&& userSes.getRole().getRoleId() > 0) {
			isPermissionAccept = !(userSes.getRole().getRoleId() == ROLE_ADMIN || userSes
					.getRole().getRoleId() == ROLE_LEADER);
		}
	}

	public String planEvents() throws Exception {
		System.out.println("get planEvents...");
		int emp_id = -1;
		String currentTab = "";
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			emp_id = Integer.parseInt(request.getParameter("emp_id"));
			if(!StringUtil.notNull(request.getParameter("curTabName")).isEmpty())
				currentTab = StringUtil.notNull(request.getParameter("curTabName"));
			if (emp_id > 0) {
				System.out.println("emp_id=" + emp_id);
				selectedUserPlan = new User();
				selectedUserPlan.setId(emp_id);
				editor_custom();
			}
			//System.out.println(request.getParameter("contactType") + "/" + request.getParameter("contactAddress") + " : " +  contactAddress);
			String contactType = StringUtil.notNull(request.getParameter("contactType"));
			if(contactType.matches("^(1|2)$")){
				String customerId = StringUtil.notNull(request.getParameter("customerId"));
				String contactAddress = StringUtil.notNull(request.getParameter("contactAddress"));
				/**
				 * Update customer address
				 */
				if(!customerId.isEmpty()){
					new CustomerHome(HibernateUtil.getSessionFactory()).updateCustomerAddress(Integer.parseInt(customerId), contactAddress);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		// request.setCharacterEncoding("UTF-8");
		// request.setAttribute("Content-type", "text/html; charset=UTF-8");
		CustomEventsManager evs = null;
		if(currentTab.equals(GRIF_VIEW_NAME)){
			evs = new CustomEventsManager(request,
					getSelectedUserPlan(),true);
			System.out.println("UserPlanAction.planEvents() "+currentTab);
		}
		else{
			evs = new CustomEventsManager(request,
					getSelectedUserPlan());
		}
		
		//evs.addResponseAttribute("contactAddress", contactAddress);
		//request.setAttribute("contactAddress", contactAddress);
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

	public String getPlanStatistic() {
		return SUCCESS;
	}

	public String getPlanGeneral() {
		return SUCCESS;
	}

	public String getPlanDetail() {
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
	
	public List<TimelineType> getListTimelineType() {
		return listTimelineType;
	}

	public void setListTimelineType(List<TimelineType> listTimelineType) {
		this.listTimelineType = listTimelineType;
	}

	public List<ContactType> getListContactType() {
		return listContactType;
	}

	public void setListContactType(List<ContactType> listContactType) {
		this.listContactType = listContactType;
	}
}
