package com.home.dao;

// Generated Sep 5, 2016 10:29:32 PM by Hibernate Tools 3.4.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.home.model.EventsNote;

/**
 * Home object for domain model class EventsNote.
 * @see com.home.listener.test.EventsNote
 * @author Hibernate Tools
 */
public class EventsNoteHome {

	private static final Log log = LogFactory.getLog(EventsNoteHome.class);

	private SessionFactory sessionFactory;

	public EventsNoteHome(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected SessionFactory getSessionFactory() {
		try {
			return sessionFactory;
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	
	public void attachDirty(EventsNote instance) throws Exception{
		log.debug("attaching dirty EventsNote instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			re.printStackTrace();
			log.error("attach failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public void update(EventsNote instance) throws Exception{
		log.debug("attaching dirty EventsNote instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			re.printStackTrace();
			log.error("attach failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public void updateEventNote(EventsNote instance) throws Exception{
		log.debug("updateEventNote EventsNote instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("update EventsNote set ENote=:note where ECode=:code");
			query.setString("note", instance.getENote());
			query.setString("code", instance.getECode());
			query.executeUpdate();
			tx.commit();
			log.debug("updateEventNote successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			re.printStackTrace();
			log.error("updateEventNote failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public EventsNote findEventNoteByCode(String code) throws Exception {
		log.debug("getting EventsNote instance with code: " + code);
		System.out.println("findEventNoteByCode=" + code);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			EventsNote instance = (EventsNote) session.createCriteria(EventsNote.class)
					.add(Restrictions.eq("ECode", code)).uniqueResult();
			tx.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.flush();
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("get failed", e);
			}
		}
	}
	
	public List<EventsNote> findEventNotesByCodes(List<String> codes) throws Exception {
		Transaction tx = null;
		Session session = null;
		List<EventsNote> listEventNotes = new ArrayList<EventsNote>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for (String code : codes) {
				log.debug("getting EventsNote instance with code: " + code);
				EventsNote instance = (EventsNote) session.createCriteria(EventsNote.class)
						.add(Restrictions.eq("ECode", code)).uniqueResult();
				if (instance != null) {
					listEventNotes.add(instance);
				}
			}
			tx.commit();
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.flush();
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("get failed", e);
			}
		}
		return listEventNotes;
	}
	
	public EventsNote findProductByCode(Session session, String code) throws Exception {
		//log.debug("getting Product instance with code: " + productCode);
		try {
			EventsNote instance = (EventsNote) session.createCriteria(EventsNote.class)
					.add(Restrictions.eq("ECode", code)).uniqueResult();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				//log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally{
			//no close connection
		}
	}
	
	///
	public void persist(EventsNote transientInstance) {
		log.debug("persisting EventsNote instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachClean(EventsNote instance) {
		log.debug("attaching clean EventsNote instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(EventsNote persistentInstance) {
		log.debug("deleting EventsNote instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EventsNote merge(EventsNote detachedInstance) {
		log.debug("merging EventsNote instance");
		try {
			EventsNote result = (EventsNote) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EventsNote findById(java.lang.Integer id) {
		log.debug("getting EventsNote instance with id: " + id);
		try {
			EventsNote instance = (EventsNote) sessionFactory
					.getCurrentSession().get(
							"com.home.listener.test.EventsNote", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<EventsNote> findByExample(EventsNote instance) {
		log.debug("finding EventsNote instance by example");
		try {
			List<EventsNote> results = (List<EventsNote>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.listener.test.EventsNote")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
