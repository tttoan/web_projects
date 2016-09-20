package com.home.dao;

// Generated Sep 19, 2016 11:34:17 PM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.home.model.TimelineType;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TimelineType.
 * @see com.home.model.TimelineType
 * @author Hibernate Tools
 */
public class TimelineTypeHome {

	private static final Log log = LogFactory.getLog(TimelineTypeHome.class);

	private SessionFactory sessionFactory;

	public TimelineTypeHome(SessionFactory sessionFactory) {
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

	public void persist(TimelineType transientInstance) {
		log.debug("persisting TimelineType instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TimelineType instance) {
		log.debug("attaching dirty TimelineType instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TimelineType instance) {
		log.debug("attaching clean TimelineType instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TimelineType persistentInstance) {
		log.debug("deleting TimelineType instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TimelineType merge(TimelineType detachedInstance) {
		log.debug("merging TimelineType instance");
		try {
			TimelineType result = (TimelineType) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TimelineType findById(java.lang.Integer id) {
		log.debug("getting TimelineType instance with id: " + id);
		try {
			TimelineType instance = (TimelineType) sessionFactory
					.getCurrentSession().get("com.home.test.TimelineType", id);
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

	public List<TimelineType> findByExample(TimelineType instance) {
		log.debug("finding TimelineType instance by example");
		try {
			List<TimelineType> results = (List<TimelineType>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.test.TimelineType")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<TimelineType> findAll() {
		log.debug("finding TimelineType instance by example");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			List<TimelineType> results = session
					.createCriteria(TimelineType.class).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
