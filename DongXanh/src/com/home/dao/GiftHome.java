package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import com.home.model.Gift;

/**
 * Home object for domain model class Gift.
 * @see com.home.dao.Gift
 * @author Hibernate Tools
 */
public class GiftHome {

	private static final Log log = LogFactory.getLog(GiftHome.class);


	private SessionFactory sessionFactory;
	public GiftHome(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected SessionFactory getSessionFactory() {
		try {
			return sessionFactory;
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}


	public void persist(Gift transientInstance) {
		log.debug("persisting Gift instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Gift instance) {
		log.debug("attaching dirty Gift instance");
		try {
			sessionFactory.openSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Gift instance) {
		log.debug("attaching clean Gift instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Gift persistentInstance) {
		log.debug("deleting Gift instance");
		try {
			sessionFactory.openSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Gift merge(Gift detachedInstance) {
		log.debug("merging Gift instance");
		try {
			Gift result = (Gift) sessionFactory.openSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Gift findById(java.lang.Integer id) {
		log.debug("getting Gift instance with id: " + id);
		try {
			Gift instance = (Gift) sessionFactory.openSession().get(
					"com.home.dao.Gift", id);
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

	@SuppressWarnings("unchecked")
	public List<Gift> findByExample(Gift instance) {
		log.debug("finding Gift instance by example");
		try {
			List<Gift> results = (List<Gift>) sessionFactory
					.openSession().createCriteria("com.home.dao.Gift")
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
