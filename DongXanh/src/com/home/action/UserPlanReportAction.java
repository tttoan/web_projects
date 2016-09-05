package com.home.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.home.dao.WorkingPlanHome;
import com.home.entities.UserAware;
import com.home.entities.UserPlanGeneral;
import com.home.model.User;
import com.home.util.DateUtils;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserPlanReportAction extends ActionSupport implements UserAware, Action {
	private User userSes;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	
	public User getUserSes() {
		return userSes;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}

	public static void main(String[] args) {
		try {
//			UserPlanReportAction userPlanReportAction = new UserPlanReportAction();
//			WorkingPlanHome sttHome = new WorkingPlanHome(HibernateUtil.getSessionFactory());
//			List<UserPlanGeneral> rs = (sttHome.getAllUserPlan4Report(-1, new Date(new java.util.Date().getTime()-12l*30*24*60*60*1000),  new Date(new java.util.Date().getTime())));
//			for (UserPlanGeneral userPlanGeneral : rs) {
//				userPlanReportAction.getDaySection(userPlanGeneral.getStart_date());
//			}
			
			java.util.Date week = new java.util.Date();
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(week);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+2);
			Date startday = new Date(cal.getTimeInMillis());

			cal.setTime(week);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+8);
			Date endday = new Date(cal.getTimeInMillis());
			
			System.out.println(startday);
			System.out.println(endday);
			
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			System.out.println(DateUtils.getStringFromDate(cal.getTime(), "dd/MM/yyyy"));
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			System.out.println(DateUtils.getStringFromDate(cal.getTime(), "dd/MM/yyyy"));
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			System.out.println(DateUtils.getStringFromDate(cal.getTime(), "dd/MM/yyyy"));
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			System.out.println(DateUtils.getStringFromDate(cal.getTime(), "dd/MM/yyyy"));
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			System.out.println(DateUtils.getStringFromDate(cal.getTime(), "dd/MM/yyyy"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isManager(){
		if(userSes.getRole() != null && userSes.getRole().getRoleId() != null && userSes.getRole().getRoleId() > 0){
			return (userSes.getRole().getRoleId() == ROLE_ADMIN
					|| userSes.getRole().getRoleId() == ROLE_LEADER);
		}
		return false;
	}
	
	public String getPlanStatistic(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String strWeek = StringUtil.notNull(request.getParameter("week"));

			Date week = new Date(DateUtils.getDateFromString(strWeek, "dd/MM/yyyy").getTime());
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(week);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+2);
			Date startday = new Date(cal.getTimeInMillis());

			cal.setTime(week);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+8);
			Date endday = new Date(cal.getTimeInMillis());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(startday);
			WorkingPlanHome wpHome = new WorkingPlanHome(HibernateUtil.getSessionFactory());
			List<UserPlanGeneral> results = wpHome.getAllUserPlan4Report(isManager()?-1:userSes.getId(), startday, endday);

			StringBuilder html = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			html.append("<thead>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th colspan=\"9\">BÁO CÁO THỐNG KÊ SỐ LẦN TIẾP XÚC KHÁCH HÀNG</th>");
			html.append("</tr>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th rowspan=\"2\">No</th>");
			html.append("<th rowspan=\"2\">MKH</th>");
			html.append("<th rowspan=\"2\">Tên khách hàng</th>");
			html.append("<th rowspan=\"2\">NVTT</th>");
			html.append("<th colspan=\"2\">Số lần tiếp xúc KH</th>");
			html.append("<th rowspan=\"2\">Ngày tiếp xúc</th>");
			html.append("<th rowspan=\"2\" colspan=\"2\">Ghi chú</th>");
			html.append("</tr>");
				
			html.append("<tr class=\"headings\">");
			html.append("<th>ĐT</th>");
			html.append("<th>Trực tiếp</th>");
			html.append("</tr>");
			
			int sum_totalPhone = 0;
			int sum_totalMeet = 0;
			int no = 1;
			StringBuilder tblContent = new StringBuilder();
			LinkedHashMap<String, List<UserPlanGeneral>> hm = getUserPlanStatistic(results);
			Set<String> set = hm.keySet();
			for (String username_cuscode : set) {
				 List<UserPlanGeneral> listPlan = hm.get(username_cuscode);
				// renderer html content
				tblContent.append("<tr class=\"even pointer\">");
				tblContent.append("<th>" + (no) + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + (listPlan.get(0).getCustomer_code()) + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + (listPlan.get(0).getBusiness_name()) + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + (listPlan.get(0).getUser_name()) + "</th>");
				
				int totalPhone = 0;
				int totalMeet = 0;
				StringBuilder planDate  = new StringBuilder();
				for (UserPlanGeneral userPlanGeneral : listPlan) {
					if(userPlanGeneral.getPhone() > 0){
						totalPhone++;
						sum_totalPhone++;
					}else{
						totalMeet++;
						sum_totalMeet++;
					}
					planDate.append(DateUtils.getStringFromDate(userPlanGeneral.getStart_date(), "dd/MM/yyyy")).append("; ");
				}
				
				tblContent.append("<th>" + (totalPhone) + "</th>");
				tblContent.append("<th>" + (totalMeet) + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + planDate.toString() + "</th>");
				tblContent.append("<th style=\"text-align:left\" id=\"id_note\"></th>");
				tblContent.append("<th><button class=\"btn btn-info btn-xs\">Ghi chú</button></th>");
				tblContent.append("</tr>");
				no++;
			}
			
			html.append("<tr>");
			html.append("<th></th><th></th>");
			html.append("<th>TỔNG:</th>");
			html.append("<th></th>");
			html.append("<th>"+sum_totalPhone+"</th>");
			html.append("<th>"+sum_totalMeet+"</th>");
			html.append("<th></th><th></th><th></th>");
			html.append("</tr>");
			
			html.append("</thead>");
			
			html.append("<tbody>");
			html.append(tblContent);
			html.append("</tbody>");
			html.append("</table>");
			System.out.println(html.toString());
			inputStream = new ByteArrayInputStream(html.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	private LinkedHashMap<String, List<UserPlanGeneral>> getUserPlanStatistic(List<UserPlanGeneral> result) throws Exception{
		LinkedHashMap<String, List<UserPlanGeneral>> hm = new LinkedHashMap<String, List<UserPlanGeneral>>();
		for (UserPlanGeneral userPlanGeneral : result) {
			String key = userPlanGeneral.getUser_name() + userPlanGeneral.getCustomer_code();
			if(!hm.containsKey(key)){
				hm.put(key, new ArrayList<UserPlanGeneral>());
			}
			hm.get(key).add(userPlanGeneral);
		}
		return hm;
	}

	public String getPlanGeneral(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String strWeek = StringUtil.notNull(request.getParameter("week"));

			Date week = new Date(DateUtils.getDateFromString(strWeek, "dd/MM/yyyy").getTime());
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(week);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+2);
			Date startday = new Date(cal.getTimeInMillis());

			cal.setTime(week);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+8);
			Date endday = new Date(cal.getTimeInMillis());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(startday);
			WorkingPlanHome wpHome = new WorkingPlanHome(HibernateUtil.getSessionFactory());
			List<UserPlanGeneral> results = wpHome.getAllUserPlan4Report(isManager()?-1:userSes.getId(), startday, endday);

			StringBuilder html = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			html.append("<thead>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th colspan=\"21\">TỔNG HỢP LỊCH CÔNG TÁC</th>");
			html.append("</tr>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th rowspan=\"3\">TUẦN</th>");
			html.append("<th rowspan=\"3\">STT</th>");
			html.append("<th rowspan=\"3\">NHÂN VIÊN</th>");
			html.append("<th colspan=\"2\">THỨ 2</th>");
			html.append("<th colspan=\"2\">THỨ 3</th>");
			html.append("<th colspan=\"2\">THỨ 4</th>");
			html.append("<th colspan=\"2\">THỨ 5</th>");
			html.append("<th colspan=\"2\">THỨ 6</th>");
			html.append("<th colspan=\"2\">THỨ 7</th>");
			html.append("<th colspan=\"2\">CHỦ NHẬT</th>");
			html.append("<th rowspan=\"3\">SL KH làm việc trực tiếp</th>");
			html.append("<th rowspan=\"3\">Số ngày đi Ctác</th>");
			html.append("<th rowspan=\"3\">GHI CHÚ - Ý KIẾN - KẾT QUẢ CÔNG TÁC TUẦN</th>");
			html.append("<th rowspan=\"3\"></th>");
			html.append("</tr>");
				
			html.append("<tr class=\"headings\">");
			Calendar calt2 = Calendar.getInstance();
			calt2.setTime(startday);
			html.append("<th colspan=\"2\">"+ DateUtils.getStringFromDate(calt2.getTime(), "dd/MM/yyyy")+"</th>");
			Calendar calt3 = Calendar.getInstance();
			calt3.setTime(startday);
			calt3.set(Calendar.DAY_OF_MONTH, calt3.get(Calendar.DAY_OF_MONTH)+1);
			html.append("<th colspan=\"2\">"+ DateUtils.getStringFromDate(calt3.getTime(), "dd/MM/yyyy")+"</th>");
			Calendar calt4 = Calendar.getInstance();
			calt4.setTime(startday);
			calt4.set(Calendar.DAY_OF_MONTH, calt4.get(Calendar.DAY_OF_MONTH)+2);
			html.append("<th colspan=\"2\">"+ DateUtils.getStringFromDate(calt4.getTime(), "dd/MM/yyyy")+"</th>");
			Calendar calt5 = Calendar.getInstance();
			calt5.setTime(startday);
			calt5.set(Calendar.DAY_OF_MONTH, calt5.get(Calendar.DAY_OF_MONTH)+3);
			html.append("<th colspan=\"2\">"+ DateUtils.getStringFromDate(calt5.getTime(), "dd/MM/yyyy")+"</th>");
			Calendar calt6 = Calendar.getInstance();
			calt6.setTime(startday);
			calt6.set(Calendar.DAY_OF_MONTH, calt6.get(Calendar.DAY_OF_MONTH)+4);
			html.append("<th colspan=\"2\">"+ DateUtils.getStringFromDate(calt6.getTime(), "dd/MM/yyyy")+"</th>");
			Calendar calt7 = Calendar.getInstance();
			calt7.setTime(startday);
			calt7.set(Calendar.DAY_OF_MONTH, calt7.get(Calendar.DAY_OF_MONTH)+5);
			html.append("<th colspan=\"2\">"+ DateUtils.getStringFromDate(calt7.getTime(), "dd/MM/yyyy")+"</th>");
			Calendar calcn = Calendar.getInstance();
			calcn.setTime(startday);
			calcn.set(Calendar.DAY_OF_MONTH, calcn.get(Calendar.DAY_OF_MONTH)+6);
			html.append("<th colspan=\"2\">"+ DateUtils.getStringFromDate(calcn.getTime(), "dd/MM/yyyy")+"</th>");
			html.append("</tr>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th>LH</th>	<th>MKH</th>");
			html.append("<th>LH</th>	<th>MKH</th>");
			html.append("<th>LH</th>	<th>MKH</th>");
			html.append("<th>LH</th>	<th>MKH</th>");
			html.append("<th>LH</th>	<th>MKH</th>");
			html.append("<th>LH</th>	<th>MKH</th>");
			html.append("<th>LH</th>	<th>MKH</th>");
			html.append("</tr>");
			
			html.append("</thead>");

			StringBuilder tblContent = new StringBuilder();
			int no = 1;
			int WEEK_OF_YEAR = cal2.get(Calendar.WEEK_OF_YEAR);
			LinkedHashMap<String, List<UserPlanGeneral>> hm = getUserPlanGeneral(results);
			Set<String> set = hm.keySet();
			for (String username : set) {
				if(no==1){
					tblContent.append("<tr class=\"even pointer\">");
					//tblContent.append("<th rowspan=\""+set.size()+"\">" + (cal2.get(Calendar.WEEK_OF_YEAR)) + "</th>");
				}else{
					tblContent.append("<tr class=\"even pointer\">");
				}
				tblContent.append("<th>" + (WEEK_OF_YEAR) + "</th>");
				tblContent.append("<th>" + (no) + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + (username) + "</th>");
				
				int totalPhone = 0;
				int totalMeet  = 0;
				StringBuilder lht2 = new StringBuilder("<ul >");
				StringBuilder lht3 = new StringBuilder("<ul >");
				StringBuilder lht4 = new StringBuilder("<ul >");
				StringBuilder lht5 = new StringBuilder("<ul >");
				StringBuilder lht6 = new StringBuilder("<ul >");
				StringBuilder lht7 = new StringBuilder("<ul >");
				StringBuilder lhcn = new StringBuilder("<ul >");
				StringBuilder mkh2 = new StringBuilder("<ul >");
				StringBuilder mkh3 = new StringBuilder("<ul >");
				StringBuilder mkh4 = new StringBuilder("<ul >");
				StringBuilder mkh5 = new StringBuilder("<ul >");
				StringBuilder mkh6 = new StringBuilder("<ul >");
				StringBuilder mkh7 = new StringBuilder("<ul >");
				StringBuilder mkhcn = new StringBuilder("<ul >");
				List<UserPlanGeneral> listPlan = hm.get(username);
				for (int i = 0; i < listPlan.size(); i++) {
					if(listPlan.get(i).getPhone()>0){
						totalPhone++;
					}else{
						totalMeet++;
					}
					cal2.setTime(listPlan.get(i).getStart_date());
					if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
						lht2.append("<li>"+(listPlan.get(i).getPhone()>0?"ĐT":"CT")+"</li>");
						mkh2.append("<li>"+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code() + "-" + listPlan.get(i).getBusiness_name())+"</li>");
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
						lht3.append("<li>"+(listPlan.get(i).getPhone()>0?"ĐT":"CT")+"</li>");
						mkh3.append("<li>"+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code() + "-" + listPlan.get(i).getBusiness_name())+"</li>");
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
						lht4.append("<li>"+(listPlan.get(i).getPhone()>0?"ĐT":"CT")+"</li>");
						mkh4.append("<li>"+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code() + "-" + listPlan.get(i).getBusiness_name())+"</li>");		
										}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
						lht5.append("<li>"+(listPlan.get(i).getPhone()>0?"ĐT":"CT")+"</li>");
						mkh5.append("<li>"+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code() + "-" + listPlan.get(i).getBusiness_name())+"</li>");
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
						lht6.append("<li>"+(listPlan.get(i).getPhone()>0?"ĐT":"CT")+"</li>");
						mkh6.append("<li>"+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code() + "-" + listPlan.get(i).getBusiness_name())+"</li>");
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
						lhcn.append("<li>"+(listPlan.get(i).getPhone()>0?"ĐT":"CT")+"</li>");
						mkhcn.append("<li>"+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code() + "-" + listPlan.get(i).getBusiness_name())+"</li>");
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
						lht2.append("<li>"+(listPlan.get(i).getPhone()>0?"ĐT":"CT")+"</li>");
						mkh2.append("<li>"+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code() + "-" + listPlan.get(i).getBusiness_name())+"</li>");
					}
				}
				
				//thu2
				tblContent.append("<th style=\"text-align:left\">" + lht2.append("</ul>").toString() + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + mkh2.append("</ul>").toString() + "</th>");
				//thu3
				tblContent.append("<th style=\"text-align:left\">" + lht3.append("</ul>").toString() + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + mkh3.append("</ul>").toString() + "</th>");
				//thu4
				tblContent.append("<th style=\"text-align:left\">" + lht4.append("</ul>").toString() + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + mkh4.append("</ul>").toString() + "</th>");
				//thu5
				tblContent.append("<th style=\"text-align:left\">" + lht5.append("</ul>").toString() + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + mkh5.append("</ul>").toString() + "</th>");
				//thu6
				tblContent.append("<th style=\"text-align:left\">" + lht6.append("</ul>").toString() + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + mkh6.append("</ul>").toString() + "</th>");
				//thu7
				tblContent.append("<th style=\"text-align:left\">" + lht7.append("</ul>").toString() + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + mkh7.append("</ul>").toString() + "</th>");
				//cn
				tblContent.append("<th style=\"text-align:left\">" + lhcn.append("</ul>").toString() + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + mkhcn.append("</ul>").toString() + "</th>");
				
				tblContent.append("<th>" + totalPhone + "</th>");
				tblContent.append("<th>" + totalMeet + "</th>");
				tblContent.append("<th style=\"text-align:left\" id=\"id_note\"></th>");
				tblContent.append("<th><button class=\"btn btn-info btn-xs\">Ghi chú</button></th>");
				tblContent.append("</tr>");
				no++;
			}
			html.append("<tbody>");
			html.append(tblContent);
			html.append("</tbody>");
			html.append("</table>");
			System.out.println(html.toString());
			inputStream = new ByteArrayInputStream(html.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	private LinkedHashMap<String, List<UserPlanGeneral>> getUserPlanGeneral(List<UserPlanGeneral> result) throws Exception{
		LinkedHashMap<String, List<UserPlanGeneral>> hm = new LinkedHashMap<String, List<UserPlanGeneral>>();
		for (UserPlanGeneral userPlanGeneral : result) {
			if(!hm.containsKey(userPlanGeneral.getUser_name())){
				hm.put(userPlanGeneral.getUser_name(), new ArrayList<UserPlanGeneral>());
			}
			hm.get(userPlanGeneral.getUser_name()).add(userPlanGeneral);
		}
		return hm;
	}

	public String getPlanDetail(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String strWeek = StringUtil.notNull(request.getParameter("week"));

			Date week = new Date(DateUtils.getDateFromString(strWeek, "dd/MM/yyyy").getTime());
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(week);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+2);
			Date startday = new Date(cal.getTimeInMillis());

			cal.setTime(week);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+8);
			Date endday = new Date(cal.getTimeInMillis());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(startday);
			WorkingPlanHome wpHome = new WorkingPlanHome(HibernateUtil.getSessionFactory());
			List<UserPlanGeneral> results = wpHome.getAllUserPlan4Report(isManager()?-1:userSes.getId(), startday, endday);

			StringBuilder html = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			html.append("<thead>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th colspan=\"8\">CHI TIẾT LỊCH CÔNG TÁC NVTT</th>");
			html.append("</tr>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th>No</th>");
			html.append("<th>NVTT</th>");
			html.append("<th>Ngày</th>");
			html.append("<th>LH</th>");
			html.append("<th>Buổi</th>");
			html.append("<th>MKH</th>");
			html.append("<th>Tên người QĐCV</th>");
			html.append("<th>Ghi chú</th>");
			html.append("</tr>");
				
			html.append("</thead>");
			
			StringBuilder tblContent = new StringBuilder();
			int no = 1;
			for (int i = 0; i < results.size(); i++) {
				
				// renderer html content
				tblContent.append("<tr class=\"even pointer\">");
				tblContent.append("<th>" + (no) + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + (results.get(i).getUser_name()) + "</th>");
				
				Date datePlan = results.get(i).getStart_date();
				tblContent.append("<th style=\"text-align:right\">" + (getDayName(datePlan) + ", " + DateUtils.getStringFromDate(datePlan, "dd/MM/yy")) + "</th>");
				tblContent.append("<th>" + (results.get(i).getPhone()>0?"ĐT":"CT") + "</th>");
				tblContent.append("<th>" + (getDaySection(datePlan)) + "</th>");
				
				tblContent.append("<th style=\"text-align:left\">" + (results.get(i).getCustomer_code()) + "</th>");
				tblContent.append("<th style=\"text-align:left\">" + (results.get(i).getBusiness_name()) + "</th>");
				tblContent.append("<th style=\"text-align:left\"></th>");
				tblContent.append("</tr>");
				no++;
			}
			
			html.append("<tbody>");
			html.append(tblContent);
			html.append("</tbody>");
			html.append("</table>");
			System.out.println(html.toString());
			inputStream = new ByteArrayInputStream(html.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	
	}
	
	private String getDayName(Date datePlan) throws Exception{
		String[] arr = new String[]{"Chủ Nhật", "Thứ Hai","Thứ Ba","Thứ Tư","Thứ Năm","Thứ Sáu","Thứ Bảy"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(datePlan);
		return arr[cal.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	private String getDaySection(Date datePlan) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.setTime(datePlan);
		//System.out.println(datePlan + " ------> " + cal.get(Calendar.HOUR_OF_DAY));
		if(cal.get(Calendar.HOUR_OF_DAY) >= 12){
			return "C";
		}else{
			return "S";
		}
	}
}
