package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.home.model.PromotionCustomer;

/**
 * Home object for domain model class PromotionCustomer.
 * @see com.home.dao.PromotionCustomer
 * @author Hibernate Tools
 */
public class PromotionCustomerHome {

	private static final Log log = LogFactory
			.getLog(PromotionCustomerHome.class);

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

	public void persist(PromotionCustomer transientInstance) {
		log.debug("persisting PromotionCustomer instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PromotionCustomer instance) {
		log.debug("attaching dirty PromotionCustomer instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PromotionCustomer instance) {
		log.debug("attaching clean PromotionCustomer instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PromotionCustomer persistentInstance) {
		log.debug("deleting PromotionCustomer instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PromotionCustomer merge(PromotionCustomer detachedInstance) {
		log.debug("merging PromotionCustomer instance");
		try {
			PromotionCustomer result = (PromotionCustomer) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PromotionCustomer findById(java.lang.Integer id) {
		log.debug("getting PromotionCustomer instance with id: " + id);
		try {
			PromotionCustomer instance = (PromotionCustomer) sessionFactory
					.getCurrentSession().get("com.home.dao.PromotionCustomer",
							id);
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

	public List<PromotionCustomer> findByExample(PromotionCustomer instance) {
		log.debug("finding PromotionCustomer instance by example");
		try {
			List<PromotionCustomer> results = (List<PromotionCustomer>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.PromotionCustomer")
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
