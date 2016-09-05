package com.home.dao;

import javax.servlet.http.HttpServletRequest;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.home.model.Event;
import com.home.model.EventsHistory;
import com.home.model.Statistic;
import com.home.model.User;
import com.home.util.HibernateUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomEventsManager extends DHXEventsManager {
	private User userSes;

	public CustomEventsManager(HttpServletRequest request) {
		super(request);
	}

	public CustomEventsManager(HttpServletRequest request, User userSes) {
		super(request);
		this.userSes = userSes;
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
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		DHXEventsManager.date_format = "MM/dd/yyyy HH:mm";
		System.out.println("sasdasdasd " + evs.size());
		return evs;
	}

	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			int eventId = event.getId();
			if (status == DHXStatus.UPDATE) {
				session.update(event);
			} else if (status == DHXStatus.DELETE) {
				session.delete(event);
			} else if (status == DHXStatus.INSERT) {
				session.save(event);
				eventId =  (int) session.createSQLQuery(
						"SELECT LAST_INSERT_ID()").uniqueResult();
				System.out.println(eventId);
			}

			try {
				Event evt = (Event) event;
				EventsHistory evth = new EventsHistory(eventId,
						evt.getEmployeeId(), evt.getStart_date(),
						evt.getStart_date(), evt.getCustomerId(),
						evt.getCustomerId(), status.toString(),
						new Date());
				session.save(evth);
			} catch (Exception e) {
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
