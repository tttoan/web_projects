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

import com.home.dao.EventsHistoryHome;
import com.home.dao.EventsNoteHome;
import com.home.dao.WorkingPlanHome;
import com.home.entities.UserAware;
import com.home.entities.UserPlanGeneral;
import com.home.entities.UserPlanHistory;
import com.home.model.EventsNote;
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
			EventsNoteHome enHome = new EventsNoteHome(HibernateUtil.getSessionFactory());
			List<UserPlanGeneral> results = wpHome.getAllUserPlan4Report(isManager()?-1:userSes.getId(), startday, endday);

			StringBuilder html = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			html.append("<thead>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th colspan=\"10\">BÁO CÁO THỐNG KÊ SỐ LẦN TIẾP XÚC KHÁCH HÀNG</th>");
			html.append("</tr>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th rowspan=\"2\">No</th>");
			html.append("<th rowspan=\"2\">MKH</th>");
			html.append("<th rowspan=\"2\">Tên khách hàng</th>");
			html.append("<th rowspan=\"2\">NVTT</th>");
			html.append("<th colspan=\"4\">Số lần tiếp xúc KH</th>");
			html.append("<th rowspan=\"2\" colspan=\"2\">Ghi chú</th>");
			html.append("</tr>");
				
			html.append("<tr class=\"headings\">");
			html.append("<th>ĐT</th>");
			html.append("<th>Ngày ĐT</th>");
			html.append("<th>TT</th>");
			html.append("<th>Ngày TT</th>");
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
				tblContent.append("<td>" + (no) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + (listPlan.get(0).getCustomer_code()) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + (listPlan.get(0).getBusiness_name()) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + (listPlan.get(0).getNVTT()) + "</td>");
				
				int totalPhone = 0;
				int totalMeet = 0;
				StringBuilder planDatePhone  = new StringBuilder();
				StringBuilder planDateMeet  = new StringBuilder();
				for (UserPlanGeneral userPlanGeneral : listPlan) {
					if(userPlanGeneral.getPhone() > 0){
						totalPhone++;
						sum_totalPhone++;
						planDatePhone.append(DateUtils.getStringFromDate(userPlanGeneral.getStart_date(), "dd/MM")).append("; ");
					}else{
						totalMeet++;
						sum_totalMeet++;
						planDateMeet.append(DateUtils.getStringFromDate(userPlanGeneral.getStart_date(), "dd/MM")).append("; ");
					}
				}
				
				tblContent.append("<td style=\"text-align:center\">" + (totalPhone>0?totalPhone:"") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + planDatePhone.toString().trim() + "</td>");
				tblContent.append("<td style=\"text-align:center\">" + (totalMeet>0?totalMeet:"") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + planDateMeet.toString().trim() + "</td>");
				EventsNote eventNote = enHome.findEventNoteByCode(no+listPlan.get(0).getCustomer_code()+listPlan.get(0).getNVTT()+planDatePhone.toString().trim()+planDateMeet.toString().trim());
				if(eventNote != null){
					tblContent.append("<td style=\"text-align:left\" id=\"id_note\">"+eventNote.getENote()+"</td>");
				}else{
					tblContent.append("<td style=\"text-align:left\" id=\"id_note\"></td>");
				}
				tblContent.append("<td><button class=\"btn btn-info btn-xs\">Ghi chú</button></td>");
				tblContent.append("</tr>");
				no++;
			}
			
			html.append("<tr>");
			html.append("<th></th><th></th>");
			html.append("<th>TỔNG:</th>");
			html.append("<th></th>");
			html.append("<th>"+sum_totalPhone+"</th><th>dd/mm</th>");
			html.append("<th>"+sum_totalMeet+"</th>");
			html.append("<th>dd/mm</th><th>-------------------------------------------------------------</th><th></th>");
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
			String key = userPlanGeneral.getNVTT() + userPlanGeneral.getCustomer_code();
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
			EventsNoteHome enHome = new EventsNoteHome(HibernateUtil.getSessionFactory());
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
			int WEEK_OF_YEAR = getWeekFromM10(cal2.get(Calendar.WEEK_OF_YEAR));
			LinkedHashMap<String, List<UserPlanGeneral>> hm = getUserPlanGeneral(results);
			Set<String> set = hm.keySet();
			for (String username : set) {
				if(no==1){
					tblContent.append("<tr class=\"even pointer\">");
					//tblContent.append("<th rowspan=\""+set.size()+"\">" + (cal2.get(Calendar.WEEK_OF_YEAR)) + "</th>");
				}else{
					tblContent.append("<tr class=\"even pointer\">");
				}
				tblContent.append("<td>" + (WEEK_OF_YEAR) + "</td>");
				tblContent.append("<td>" + (no) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + (username) + "</td>");
				
				int totalTT = 0;
				int totalCT  = 0;
				StringBuilder lht2 = new StringBuilder("");
				StringBuilder lht3 = new StringBuilder("");
				StringBuilder lht4 = new StringBuilder("");
				StringBuilder lht5 = new StringBuilder("");
				StringBuilder lht6 = new StringBuilder("");
				StringBuilder lht7 = new StringBuilder("");
				StringBuilder lhcn = new StringBuilder("");
				StringBuilder mkh2 = new StringBuilder("");
				StringBuilder mkh3 = new StringBuilder("");
				StringBuilder mkh4 = new StringBuilder("");
				StringBuilder mkh5 = new StringBuilder("");
				StringBuilder mkh6 = new StringBuilder("");
				StringBuilder mkh7 = new StringBuilder("");
				StringBuilder mkhcn = new StringBuilder("");
				List<UserPlanGeneral> listPlan = hm.get(username);
				List<String> listTT = new ArrayList<String>();
				for (int i = 0; i < listPlan.size(); i++) {
					if(listPlan.get(i).getPhone()>0){
					}else{
						totalCT++;
						if(!listTT.contains(listPlan.get(i).getCustomer_code())){
							totalTT++;
							listTT.add(listPlan.get(i).getCustomer_code());
						}
					}
					cal2.setTime(listPlan.get(i).getStart_date());
					String lh = "";
					String mkh = "";
					if(listPlan.get(i).getPhone()>0){
						lh = "ĐT<br>";
						mkh = ""+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code())+"<br>";
					}else{
						mkh = ""+(getDaySection(listPlan.get(i).getStart_date()) + ": " + listPlan.get(i).getCustomer_code() + "-" + listPlan.get(i).getBusiness_name())+"<br>";
					}
					if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
						lht2.append(lh);
						mkh2.append(mkh);
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
						lht3.append(lh);
						mkh3.append(mkh);
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
						lht4.append(lh);
						mkh4.append(mkh);		
										}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
						lht5.append(lh);
						mkh5.append(mkh);
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
						lht6.append(lh);
						mkh6.append(mkh);
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
						lhcn.append(lh);
						mkhcn.append(mkh);
					}
					else if(cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
						lht2.append(lh);
						mkh2.append(mkh);
					}
				}
				
				//thu2
				tblContent.append("<td style=\"text-align:left\">" + lht2.append("").toString().replaceAll("<br>$", "") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + mkh2.append("").toString().replaceAll("<br>$", "") + "</td>");
				//thu3
				tblContent.append("<td style=\"text-align:left\">" + lht3.append("").toString().replaceAll("<br>$", "") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + mkh3.append("").toString().replaceAll("<br>$", "") + "</td>");
				//thu4
				tblContent.append("<td style=\"text-align:left\">" + lht4.append("").toString().replaceAll("<br>$", "") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + mkh4.append("").toString().replaceAll("<br>$", "") + "</td>");
				//thu5
				tblContent.append("<td style=\"text-align:left\">" + lht5.append("").toString().replaceAll("<br>$", "") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + mkh5.append("").toString().replaceAll("<br>$", "") + "</td>");
				//thu6
				tblContent.append("<td style=\"text-align:left\">" + lht6.append("").toString().replaceAll("<br>$", "") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + mkh6.append("").toString().replaceAll("<br>$", "") + "</td>");
				//thu7
				tblContent.append("<td style=\"text-align:left\">" + lht7.append("").toString().replaceAll("<br>$", "") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + mkh7.append("").toString().replaceAll("<br>$", "") + "</td>");
				//cn
				tblContent.append("<td style=\"text-align:left\">" + lhcn.append("").toString().replaceAll("<br>$", "") + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + mkhcn.append("").toString().replaceAll("<br>$", "") + "</td>");
				
				tblContent.append("<td>" + totalCT + "</td>");
				tblContent.append("<td>" + totalTT + "</td>");
				EventsNote eventNote = enHome.findEventNoteByCode(""+WEEK_OF_YEAR+username);
				if(eventNote != null){
					tblContent.append("<td style=\"text-align:left\" id=\"id_note\">"+eventNote.getENote()+"</td>");
				}else{
					tblContent.append("<td style=\"text-align:left\" id=\"id_note\"></td>");
				}
				tblContent.append("<td><button class=\"btn btn-info btn-xs\">Ghi chú</button></td>");
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
			if(!hm.containsKey(userPlanGeneral.getNVTT())){
				hm.put(userPlanGeneral.getNVTT(), new ArrayList<UserPlanGeneral>());
			}
			hm.get(userPlanGeneral.getNVTT()).add(userPlanGeneral);
		}
		return hm;
	}

	public String getPlanDetail(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//			String strWeek = StringUtil.notNull(request.getParameter("week"));

//			Date week = new Date(DateUtils.getDateFromString(strWeek, "dd/MM/yyyy").getTime());
//			Calendar cal = Calendar.getInstance();
//			
//			cal.setTime(week);
//			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+2);
//			Date startday = new Date(cal.getTimeInMillis());
//
//			cal.setTime(week);
//			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_WEEK)+8);
//			Date endday = new Date(cal.getTimeInMillis());
			
			String startDate = StringUtil.notNull(request.getParameter("startDate"));
			String endDate = StringUtil.notNull(request.getParameter("endDate"));
			
			Date week1 = new Date(DateUtils.getDateFromString(startDate, "dd/MM/yyyy").getTime());
			Date week2 = new Date(DateUtils.getDateFromString(endDate, "dd/MM/yyyy").getTime());
			
//			Calendar cal2 = Calendar.getInstance();
//			cal2.setTime(startday);
			WorkingPlanHome wpHome = new WorkingPlanHome(HibernateUtil.getSessionFactory());
			List<UserPlanGeneral> results = wpHome.getAllUserPlan4Report(isManager()?-1:userSes.getId(), week1, week2);

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
				tblContent.append("<td>" + (no) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + (results.get(i).getNVTT()) + "</td>");
				
				Date datePlan = results.get(i).getStart_date();
				tblContent.append("<td style=\"text-align:right\">" + (getDayName(datePlan) + ", " + DateUtils.getStringFromDate(datePlan, "dd/MM/yy")) + "</td>");
				tblContent.append("<td>" + (results.get(i).getPhone()>0?"ĐT":"") + "</td>");
				tblContent.append("<td>" + (getDaySection(datePlan)) + "</td>");
				
				tblContent.append("<td style=\"text-align:left\">" + (results.get(i).getCustomer_code()) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + (results.get(i).getBusiness_name()) + "</td>");
				String contactWay = "";
				if(results.get(i).getPhone()>0){
					contactWay = results.get(i).getTelefone();
				}else{
					if(!results.get(i).getTelefone().isEmpty()){
						contactWay += results.get(i).getTelefone() + "<br>";
					}
					if(!results.get(i).getAddress().isEmpty()){
						contactWay += results.get(i).getAddress();
					}
				}
				tblContent.append("<td style=\"text-align:left\">"+contactWay+"</td>");
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
		if(datePlan != null){
			String[] arr = new String[]{"Chủ Nhật", "Thứ Hai","Thứ Ba","Thứ Tư","Thứ Năm","Thứ Sáu","Thứ Bảy"};
			Calendar cal = Calendar.getInstance();
			cal.setTime(datePlan);
			return arr[cal.get(Calendar.DAY_OF_WEEK)-1];
		}
		return "";
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
	
	public String updatePlanNote(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			request.setCharacterEncoding("UTF-8");
//			System.out.println(URLDecoder.decode(request.getRequestURI(), "UTF-8"));
//			System.out.println(new String(request.getParameter("note").getBytes("UTF-8")));
//			System.out.println(new String(request.getParameter("note").getBytes("iso-8859-1")));
//			System.out.println(new String(request.getParameter("note").getBytes("UTF-8"), "UTF-8"));
//			System.out.println(new String(request.getParameter("note").getBytes("iso-8859-1"), "UTF-8"));
//			System.out.println(new String(request.getParameter("note").getBytes("iso-8859-1"), "iso-8859-1"));
			
			String code = StringUtil.notNull(request.getParameter("code"));
			String note = StringUtil.notNull(new String(request.getParameter("note").getBytes("iso-8859-1"), "UTF-8"));
			
			EventsNoteHome enHome = new EventsNoteHome(HibernateUtil.getSessionFactory());
			EventsNote eventNote = enHome.findEventNoteByCode(code);
			if(eventNote == null){
				//Insert new
				eventNote = new EventsNote();
				eventNote.setECode(code);
				eventNote.setENote(note);
				enHome.attachDirty(eventNote);
			}else{
				//Update
				eventNote.setENote(note);
				enHome.update(eventNote);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.NONE;
	}
	
	public String getPlanHistory(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String startDate = StringUtil.notNull(request.getParameter("startDate"));
			String endDate = StringUtil.notNull(request.getParameter("endDate"));

			Date week1 = new Date(DateUtils.getDateFromString(startDate, "dd/MM/yyyy").getTime());
			Date week2 = new Date(DateUtils.getDateFromString(endDate, "dd/MM/yyyy").getTime());
						
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(week1);
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(week2);
			EventsHistoryHome eHome = new EventsHistoryHome(HibernateUtil.getSessionFactory());
			List<UserPlanHistory> results = eHome.getListPlanHistory(isManager()?-1:userSes.getId(), week1, week2);

			StringBuilder html = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			html.append("<thead>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th colspan=\"7\">LỊCH SỬ THAY ĐỔI LỊCH CÔNG TÁC NVTT TUẦN "+getWeekFromM10(cal2.get(Calendar.WEEK_OF_YEAR))+", "+DateUtils.getStringFromDate(cal2.getTime(), "dd")+"-"+DateUtils.getStringFromDate(cal3.getTime(), "dd/MM/yyyy")+"</th>");
			html.append("</tr>");
			
			html.append("<tr class=\"headings\">");
			html.append("<th>No</th>");
			html.append("<th>NVTT</th>");
			html.append("<th>KH</th>");
			html.append("<th>Ngày lịch công tác</th>");
			html.append("<th>Ngày giờ thay đổi</th>");
			html.append("<th>Nội dung</th>");
			html.append("<th>Ghi chú</th>");
			html.append("</tr>");
				
			html.append("</thead>");
			
			StringBuilder tblContent = new StringBuilder();
			int no = 1;
			for (int i = 0; i < results.size(); i++) {

				List<WARNING> warnings = isWarningPlanHistory(results.get(i));
				if(warnings.isEmpty()){
					if("INSERT".equalsIgnoreCase(results.get(i).getAction()))
						continue;
				}
				
				// renderer html content
				tblContent.append("<tr class=\"even pointer\">");
				tblContent.append("<td>" + (no) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + (results.get(i).getNvtt()) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + (results.get(i).getEvent_name().isEmpty()?results.get(i).getCustomer_old():results.get(i).getEvent_name()) + "</td>");
				
				Date event_date = results.get(i).getEvent_date();
				tblContent.append("<td style=\"text-align:right\">" + (getDayName(event_date) + ", " + DateUtils.getStringFromDate(event_date, "dd/MM/yy hh:mm")) + "</td>");
				
				Date modified_date = results.get(i).getLast_modified();
				tblContent.append("<td style=\"text-align:right\">" + (getDayName(modified_date) + ", " + DateUtils.getStringFromDate(modified_date, "dd/MM/yy hh:mm")) + "</td>");
				
				tblContent.append("<td style=\"text-align:left\">" + getHistoryContent(results.get(i), warnings) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + getHistoryNote(results.get(i), warnings) + "</td>");
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
	
	private List<WARNING> isWarningPlanHistory(UserPlanHistory planHisory){
		List<WARNING> warnings = new ArrayList<UserPlanReportAction.WARNING>();
		try {
			/**
			 * Cac truong hop can warning 
			 */
			//Thoi gian tao plan tre hon ngay hien tai
			{
				if("INSERT".equalsIgnoreCase(planHisory.getAction())){
					//Kiem tra neu ngay tao plan sau ngay plan
					if(DateUtils.dateWithoutTime(planHisory.getLast_modified()).getTime()>DateUtils.dateWithoutTime(planHisory.getPlan_date_new()).getTime()){
						warnings.add(WARNING.LATE_PLAN);
					}
					//Kiem tra thoi gian tao plan truoc 8h AM sang neu cung ngay
					else if(DateUtils.dateWithoutTime(planHisory.getLast_modified()).getTime()==DateUtils.dateWithoutTime(planHisory.getPlan_date_new()).getTime()){
						if(DateUtils.hourFromDate(planHisory.getLast_modified()) > 8){
							warnings.add(WARNING.LATE_PLAN);
						}
					}
				}
				else if("DELETE".equalsIgnoreCase(planHisory.getAction())){
					//Kiem tra neu ngay tao plan sau ngay plan
					if(DateUtils.dateWithoutTime(planHisory.getLast_modified()).getTime()>DateUtils.dateWithoutTime(planHisory.getPlan_date_new()).getTime()){
						warnings.add(WARNING.DELETE_PLAN);
					}
					//Kiem tra thoi gian tao plan truoc 8h AM sang neu cung ngay
					else if(DateUtils.dateWithoutTime(planHisory.getLast_modified()).getTime()==DateUtils.dateWithoutTime(planHisory.getPlan_date_new()).getTime()){
						if(DateUtils.hourFromDate(planHisory.getLast_modified()) > 8){
							warnings.add(WARNING.DELETE_PLAN);
						}
					}
				}
			}
			//Thay doi thoi gian
			if(planHisory.getPlan_date_new().getTime() != planHisory.getPlan_date_old().getTime()){
				//Kiem tra neu ngay tao plan sau ngay plan
				if(DateUtils.dateWithoutTime(planHisory.getLast_modified()).getTime()>DateUtils.dateWithoutTime(planHisory.getPlan_date_new()).getTime()){
					warnings.add(WARNING.CHANGE_TIME);
				}
				//Kiem tra thoi gian tao plan truoc 8h AM sang neu cung ngay
				else if(DateUtils.dateWithoutTime(planHisory.getLast_modified()).getTime()==DateUtils.dateWithoutTime(planHisory.getPlan_date_new()).getTime()){
					if(DateUtils.hourFromDate(planHisory.getLast_modified()) > 8){
						warnings.add(WARNING.CHANGE_TIME);
					}
				}
				
			}
			//Thay doi khach hang
			if(!planHisory.getCustomer_new().equalsIgnoreCase(planHisory.getCustomer_old())){
				//Kiem tra neu ngay tao plan sau ngay plan
				if(DateUtils.dateWithoutTime(planHisory.getLast_modified()).getTime()>DateUtils.dateWithoutTime(planHisory.getPlan_date_new()).getTime()){
					warnings.add(WARNING.CHANGE_CUSTOMER);
				}
				//Kiem tra thoi gian tao plan truoc 8h AM sang neu cung ngay
				else if(DateUtils.dateWithoutTime(planHisory.getLast_modified()).getTime()==DateUtils.dateWithoutTime(planHisory.getPlan_date_new()).getTime()){
					if(DateUtils.hourFromDate(planHisory.getLast_modified()) > 8){
						warnings.add(WARNING.CHANGE_CUSTOMER);
					}
				}
			
			}
			//Thay doi hinh thuc lien lac
			//if(!planHisory.getContact_type_new().equalsIgnoreCase(planHisory.getContact_type_old())){
			//}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warnings;
	}
	
	private String getHistoryContent(UserPlanHistory planHisory, List<WARNING> warnings) throws Exception{
		try {
			if(warnings.size() > 0){
				StringBuilder content = new StringBuilder();
				for (WARNING warning : warnings) {
					switch (warning) {
					case LATE_PLAN:
						content.append(planHisory.getEvent_name()).append("<br>");
						break;
					case CHANGE_TIME:
						content.append("Thay đổi thời gian: " + DateUtils.getDateToString_hh_mm(planHisory.getPlan_date_old()) + " -> " + DateUtils.getDateToString_hh_mm(planHisory.getPlan_date_new())).append("<br>");
						break;
					case CHANGE_CUSTOMER:
						content.append("Thay đổi KH: " + planHisory.getCustomer_old() + " -> " + planHisory.getCustomer_new()).append("<br>");
						break;
					case DELETE_PLAN:
						content.append(planHisory.getEvent_name()).append("<br>");
						break;
					case CHANGE_CONTACT_TYPE:
						break;

					}
				}
				return content.toString().replaceAll("<br>$", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "";
	}
	
	private String getHistoryNote(UserPlanHistory planHisory, List<WARNING> warnings) throws Exception{
		try {
			if(warnings.size() > 0){
				StringBuilder content = new StringBuilder();
				for (WARNING warning : warnings) {
					switch (warning) {
					case LATE_PLAN:
						content.append("Lên lịch trễ").append("<br>");
						break;
					case CHANGE_TIME:
						content.append("Cập nhật lịch trễ").append("<br>");
						break;
					case CHANGE_CUSTOMER:
						content.append("Cập nhật lịch trễ").append("<br>");
						break;
					case DELETE_PLAN:
						content.append("Xóa lịch trễ").append("<br>");
						break;
					case CHANGE_CONTACT_TYPE:
						break;
					default:
						content.append(translateNote(planHisory.getAction())).append("<br>");
						break;
					}
				}
				return content.toString().replaceAll("<br>$", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return translateNote(planHisory.getAction());
	}
	
	private String translateNote(String note){
		switch (note) {
		case "INSERT":
			return "Lên lịch";
		case "UPDATE":
			return "Cập nhật lịch";
		case "DELETE":
			return "Xóa lịch";
		}
		return note;
	}
	
	enum WARNING{
		LATE_PLAN,
		CHANGE_CUSTOMER,
		CHANGE_TIME,
		CHANGE_CONTACT_TYPE,
		DELETE_PLAN,
		NONE
	}
	
	private int getWeekFromM10(int weekOfYear){
		//Week start from 1/10
		if(weekOfYear + 12 > 52){
			return weekOfYear + 12 - 52;
		}else{
			return weekOfYear+12;
		}
	}
}
