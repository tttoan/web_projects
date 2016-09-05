package com.home.dao;

// Generated Sep 5, 2016 10:48:57 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.home.model.EventsHistory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class EventsHistory.
 * @see com.home.listener.test.EventsHistory
 * @author Hibernate Tools
 */
public class EventsHistoryHome {

	private static final Log log = LogFactory.getLog(EventsHistoryHome.class);

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

	public void persist(EventsHistory transientInstance) {
		log.debug("persisting EventsHistory instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(EventsHistory instance) {
		log.debug("attaching dirty EventsHistory instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EventsHistory instance) {
		log.debug("attaching clean EventsHistory instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(EventsHistory persistentInstance) {
		log.debug("deleting EventsHistory instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EventsHistory merge(EventsHistory detachedInstance) {
		log.debug("merging EventsHistory instance");
		try {
			EventsHistory result = (EventsHistory) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EventsHistory findById(java.lang.Integer id) {
		log.debug("getting EventsHistory instance with id: " + id);
		try {
			EventsHistory instance = (EventsHistory) sessionFactory
					.getCurrentSession().get(
							"com.home.listener.test.EventsHistory", id);
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

	public List<EventsHistory> findByExample(EventsHistory instance) {
		log.debug("finding EventsHistory instance by example");
		try {
			List<EventsHistory> results = (List<EventsHistory>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.listener.test.EventsHistory")
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
