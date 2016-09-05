package com.home.dao;

// Generated Sep 5, 2016 10:29:32 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.home.model.EventsNote;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class EventsNote.
 * @see com.home.listener.test.EventsNote
 * @author Hibernate Tools
 */
public class EventsNoteHome {

	private static final Log log = LogFactory.getLog(EventsNoteHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

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

	public void attachDirty(EventsNote instance) {
		log.debug("attaching dirty EventsNote instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
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
