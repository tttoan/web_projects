package com.home.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.dhtmlx.planner.data.DHXCollection;
import com.home.action.UserPlanReportAction.WARNING;
import com.home.conts.UserPlanDefine;
import com.home.entities.UserPlanDetail;
import com.home.entities.UserPlanGeneral;
import com.home.model.Event;
import com.home.model.EventsHistory;
import com.home.model.TimelineType;
import com.home.model.User;
import com.home.util.DateUtils;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;

public class CustomEventsManager extends DHXEventsManager implements UserPlanDefine {
	private User userSes;
	private boolean isLogDetailMode;
	public CustomEventsManager(HttpServletRequest request) {
		super(request);
	}

	public CustomEventsManager(HttpServletRequest request, User userSes) {
		super(request);
		this.userSes = userSes;
		this.isLogDetailMode = false;
	}
	public CustomEventsManager(HttpServletRequest request, User userSes, boolean isLogDetailMode) {
		super(request);
		this.userSes = userSes;
		this.isLogDetailMode = isLogDetailMode;
	}
	@Override
	public HashMap<String, Iterable<DHXCollection>> getCollections() {
		ArrayList<DHXCollection> type_of_day = new ArrayList<DHXCollection>();
		TimelineTypeHome timeHome = new TimelineTypeHome(HibernateUtil.getSessionFactory());
		List<TimelineType> result = timeHome.findAll();
		for (TimelineType timelineType : result) 
			type_of_day.add(new DHXCollection(timelineType.getId()+"", timelineType.getTimelineTypeName()));
		HashMap<String,Iterable<DHXCollection>> c = 
                          new HashMap<String,Iterable<DHXCollection>>();
		c.put("typeOfDayCollect", type_of_day);
		return c;
	}
	@Override
	public Iterable<DHXEv> getEvents() {
		if(isLogDetailMode)
			return getListLogDetail();
		else
			return getListEvents();
	}
	@SuppressWarnings("unchecked")
	public Iterable<DHXEv> getListEvents() {
		DHXEventsManager.date_format = "yyyy-MM-dd HH:mm:ss";
		HibernateUtil.getSessionFactory();
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<DHXEv> evs = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cre = session.createCriteria(Event.class);
			if (userSes != null)
				cre.add(Restrictions.eq("employeeId", userSes.getId()));
			evs = cre.list();
			for (DHXEv dhxEv : evs) {
				Event e = (Event) dhxEv;
				((Event) dhxEv).setCustomerIdOld(e.getCustomerId());
				((Event) dhxEv).setPlanDateOld(dhxEv.getStart_date());
				e.setWarningType(getWarningType(session, e));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		DHXEventsManager.date_format = "MM/dd/yyyy HH:mm";
		return evs;
	}
	
	public Iterable<DHXEv> getListLogDetail() {
		DHXEventsManager.date_format = "yyyy-MM-dd HH:mm:ss";
		List<DHXEv> evs = new ArrayList<>();
		try {
			WorkingPlanHome wpHome = new WorkingPlanHome(HibernateUtil.getSessionFactory());
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(new Date());
			cal1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			
			List<UserPlanGeneral> results = wpHome.getAllUserPlan4Report(userSes.getId(), new java.sql.Date(cal1.getTime().getTime()), new java.sql.Date(new Date().getTime() + 30*24*60*60*1000l));
			for (UserPlanGeneral userPlanGeneral : results) {
				DHXEv dhxEv = new UserPlanDetail();
				((UserPlanDetail)dhxEv).setNvtt(userPlanGeneral.getNVTT());
				((UserPlanDetail)dhxEv).setDayPlan(DateUtils.getStringFromDate(userPlanGeneral.getStart_date(), "dd/MM/yy") +", "+ StringUtil.getDayName(userPlanGeneral.getStart_date()));
				dhxEv.setStart_date(userPlanGeneral.getStart_date());
				dhxEv.setEnd_date(userPlanGeneral.getEnd_date());
				((UserPlanDetail)dhxEv).setContactTypeName(userPlanGeneral.getContactType());
				((UserPlanDetail)dhxEv).setTimelineTypeName(StringUtil.getDaySection(userPlanGeneral.getStart_date()));
				((UserPlanDetail)dhxEv).setCustomerCode(userPlanGeneral.getCustomer_code());
				((UserPlanDetail)dhxEv).setCustomerName(userPlanGeneral.getBusiness_name());
				dhxEv.setText(userPlanGeneral.getCustomer_code()+"-"+userPlanGeneral.getBusiness_name());
				String contactWay = "";
				if(userPlanGeneral.getPhone()>0){
					contactWay = userPlanGeneral.getTelefone();
				}else{
					if(!userPlanGeneral.getTelefone().isEmpty()){
						contactWay += userPlanGeneral.getTelefone() + "<br>";
					}
					if(!userPlanGeneral.getAddress().isEmpty()){
						contactWay += userPlanGeneral.getAddress();
					}
				}
				((UserPlanDetail)dhxEv).setCustomerAddress(contactWay);
				evs.add(dhxEv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DHXEventsManager.date_format = "MM/dd/yyyy HH:mm";
		return evs;
	}
	
	/*public Iterable<DHXEv> getListLogDetail() {
		DHXEventsManager.date_format = "yyyy-MM-dd HH:mm:ss";
		HibernateUtil.getSessionFactory();
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<DHXEv> evs = new ArrayList<>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "SELECT eh.*, u.full_name, co.business_name as customerNameOld, "
						+ " cn.business_name as customerNameNew, tt.timeline_type_name,"
						+ " ct.contact_type_name FROM events_history eh"
						+ " left join user u on eh.employee_id = u.id"
						+ " left join customer co on eh.customer_id_old = co.id"
						+ " left join customer cn on eh.customer_id_new = cn.id"
						+ " left join timeline_type tt on eh.type_of_day = tt.id"
						+ " left join contact_type ct on eh.contact_type = ct.id";
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						DHXEv dhxEv = new EventsHistory();
						dhxEv.setStart_date(rs.getDate("plan_date_old"));
						dhxEv.setEnd_date(rs.getDate("plan_date_old"));
						((EventsHistory)dhxEv).setPlanDateOld(rs.getDate("plan_date_old"));
						((EventsHistory)dhxEv).setPlanDateNew(rs.getDate("plan_date_new"));
						((EventsHistory)dhxEv).setEmployeeName(StringUtil.notNull(rs.getString("full_name")));
						((EventsHistory)dhxEv).setCustomerNameOld(StringUtil.notNull(rs.getString("customerNameOld")));
						((EventsHistory)dhxEv).setCustomerNameNew(StringUtil.notNull(rs.getString("customerNameNew")));
						((EventsHistory)dhxEv).setTimelineTypeName(StringUtil.notNull(rs.getString("timeline_type_name")));
						((EventsHistory)dhxEv).setContactTypeName(StringUtil.notNull(rs.getString("contact_type_name")));
						((EventsHistory)dhxEv).setLastModified(rs.getDate("last_modified"));
						evs.add(dhxEv);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} catch (RuntimeException re) {
			throw re;
		} finally {
			try {
				if (session != null) {
					session.flush();
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		DHXEventsManager.date_format = "MM/dd/yyyy HH:mm";
		return evs;
	}*/
	private Date getDateByTypeOfDay(Date date, String typeOfDayId, boolean isStartDate){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(typeOfDayId.equals(MORNING_ID)){
			if(isStartDate){
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
			}else{
				c.set(Calendar.HOUR_OF_DAY, 12);
				c.set(Calendar.MINUTE, 0);
			}
		}else{
			if(isStartDate){
				c.set(Calendar.HOUR_OF_DAY,13);
				c.set(Calendar.MINUTE, 0);
			}else{
				c.set(Calendar.HOUR_OF_DAY, 23);
				c.set(Calendar.MINUTE, 59);
			}
		}
		return c.getTime();
	}
	
	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Event evt = (Event) event;
			evt.setStart_date(getDateByTypeOfDay(evt.getStart_date(),evt.getTypeOfDay()+"",true));
			evt.setEnd_date(getDateByTypeOfDay(evt.getStart_date(),evt.getTypeOfDay()+"",false));
			
			int eventId = -1;
			boolean flag = true;
			if (status == DHXStatus.UPDATE) {
				session.update(event);
				eventId = evt.getId();
				//Checking changed
				if (event.getStart_date().getTime() == evt.getPlanDateOld()
						.getTime()
						&& evt.getCustomerIdOld() == evt.getCustomerId()){
					flag = false;
				}
					
			} else if (status == DHXStatus.DELETE) {
				session.delete(event);
				eventId = evt.getId();
			} else if (status == DHXStatus.INSERT) {
				session.save(event);
				eventId = ((BigInteger) session.createSQLQuery(
						"SELECT LAST_INSERT_ID()").uniqueResult()).intValue();
			}
			if (status != DHXStatus.UPDATE) {
				((Event) event).setPlanDateOld(event.getStart_date());
				((Event) event).setCustomerIdOld(evt.getCustomerId());
			}
			try {
				if (flag) {
					EventsHistory evth = new EventsHistory(eventId,
							evt.getEmployeeId(), 
							evt.getPlanDateOld(),
							evt.getStart_date(),
							evt.getCustomerIdOld() == 0?evt.getCustomerId():evt.getCustomerIdOld(),
							evt.getCustomerId(),
							evt.getContactType(), 
							status.toString(),
							evt.getTypeOfDay(), new Date());
					session.save(evth);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return status;
	}

	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new Event();
	}
	
	private int getWarningType(Session session, Event event) throws Exception{
		try {
			String hql = "FROM EventsHistory WHERE eventId=:eventId AND lastModified>=:lastModified";
			Query query = session.createQuery(hql);
			query.setParameter("eventId",event.getId());
			query.setParameter("lastModified",event.getStart_date());
			List results = query.list();
			if(results != null && !results.isEmpty()){
				for (Object obj : results) {
					EventsHistory evtHistory = (EventsHistory) obj;
					if("INSERT".equalsIgnoreCase(evtHistory.getAction())){
						//Kiem tra neu ngay tao plan sau ngay plan
						if(DateUtils.dateWithoutTime(evtHistory.getLastModified()).getTime()>DateUtils.dateWithoutTime(event.getStart_date()).getTime()){
							return 1;
						}
						//Kiem tra thoi gian tao plan truoc 8h AM sang neu cung ngay
						if(DateUtils.dateWithoutTime(evtHistory.getLastModified()).getTime()==DateUtils.dateWithoutTime(event.getStart_date()).getTime()){
							if(DateUtils.hourFromDate(evtHistory.getLastModified()) > 8){
								return 1;
							}
						}
					}
					else if("UPDATE".equalsIgnoreCase(evtHistory.getAction())){
						//Thay doi thoi gian
						if(evtHistory.getPlanDateOld().getTime() != evtHistory.getPlanDateNew().getTime()){
							//Kiem tra neu ngay tao plan sau ngay plan
							if(DateUtils.dateWithoutTime(evtHistory.getLastModified()).getTime()>DateUtils.dateWithoutTime(event.getStart_date()).getTime()){
								return 2;
							}
							//Kiem tra thoi gian tao plan truoc 8h AM sang neu cung ngay
							if(DateUtils.dateWithoutTime(evtHistory.getLastModified()).getTime()==DateUtils.dateWithoutTime(event.getStart_date()).getTime()){
								if(DateUtils.hourFromDate(evtHistory.getLastModified()) > 8){
									return 2;
								}
							}
						}
						//Thay doi khach hang
						if(evtHistory.getCustomerIdOld() != evtHistory.getCustomerIdNew()){
							//Kiem tra neu ngay tao plan sau ngay plan
							if(DateUtils.dateWithoutTime(evtHistory.getLastModified()).getTime()>DateUtils.dateWithoutTime(event.getStart_date()).getTime()){
								return 3;
							}
							//Kiem tra thoi gian tao plan truoc 8h AM sang neu cung ngay
							if(DateUtils.dateWithoutTime(evtHistory.getLastModified()).getTime()==DateUtils.dateWithoutTime(event.getStart_date()).getTime()){
								if(DateUtils.hourFromDate(evtHistory.getLastModified()) > 8){
									return 3;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 0;
	}
}
