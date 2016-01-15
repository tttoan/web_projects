package org.apache.struts.demoapp_struts.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.demoapp_struts.util.HibernateUtil;
import org.hibernate.Session;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.dhtmlx.planner.data.DHXCollection;

public class CustomUnitsEventsManager extends DHXEventsManager {

	public CustomUnitsEventsManager(HttpServletRequest request) {
		super(request);
	}

	public Iterable<DHXEv> getEvents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<DHXEv> evs = new ArrayList<DHXEv>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			evs = session.createCriteria(EventUnits.class).list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}
		
    	return evs;
	}

	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			if (status == DHXStatus.UPDATE)
				session.update(event);
			else if (status == DHXStatus.DELETE)
				session.delete(event);
			else if (status == DHXStatus.INSERT)
				session.save(event);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}
		return status;
	}

	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new EventUnits();
	}

	@Override
	public HashMap<String, Iterable<DHXCollection>> getCollections() {
		ArrayList<DHXCollection> event_topic = new ArrayList<DHXCollection>();
		event_topic.add(new DHXCollection("mobile", "Mobile"));
		event_topic.add(new DHXCollection("web", "Web design"));
		event_topic.add(new DHXCollection("marketing", "Marketing"));

		HashMap<String,Iterable<DHXCollection>> c = new HashMap<String,Iterable<DHXCollection>>();
		c.put("topic", event_topic);
		return c;
	}
}
