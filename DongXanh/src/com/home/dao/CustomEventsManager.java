package com.home.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.dhtmlx.planner.data.DHXCollection;
import com.home.conts.UserPlanDefine;
import com.home.model.Event;
import com.home.model.EventsHistory;
import com.home.model.User;
import com.home.util.HibernateUtil;

public class CustomEventsManager extends DHXEventsManager implements UserPlanDefine {
	private User userSes;

	public CustomEventsManager(HttpServletRequest request) {
		super(request);
	}

	public CustomEventsManager(HttpServletRequest request, User userSes) {
		super(request);
		this.userSes = userSes;
	}
	@Override
	public HashMap<String, Iterable<DHXCollection>> getCollections() {
		ArrayList<DHXCollection> type_of_day = new ArrayList<DHXCollection>();
		type_of_day.add(new DHXCollection(MORNING_ID, "SÁNG"));
		type_of_day.add(new DHXCollection(EVENING_ID, "CHIỀU"));
		HashMap<String,Iterable<DHXCollection>> c = 
                          new HashMap<String,Iterable<DHXCollection>>();
		c.put("type_of_day", type_of_day);
		return c;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<DHXEv> getEvents() {
		DHXEventsManager.date_format = "yyyy-MM-dd HH:mm:ss";
		HibernateUtil.getSessionFactory();
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<DHXEv> evs = new ArrayList<DHXEv>();
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
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		DHXEventsManager.date_format = "MM/dd/yyyy HH:mm";
		return evs;
	}

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
							evt.getEmployeeId(), evt.getPlanDateOld(),
							evt.getStart_date(),
							evt.getCustomerIdOld() == 0?evt.getCustomerId():evt.getCustomerIdOld(),
							evt.getCustomerId(),evt.getContactType(), status.toString(),evt.getTypeOfDay(), new Date());
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
}
