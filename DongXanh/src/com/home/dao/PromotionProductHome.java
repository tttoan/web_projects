package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.home.model.PromotionProduct;

/**
 * Home object for domain model class PromotionProduct.
 * @see com.home.dao.PromotionProduct
 * @author Hibernate Tools
 */
public class PromotionProductHome {

	private static final Log log = LogFactory
			.getLog(PromotionProductHome.class);

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

	public void persist(PromotionProduct transientInstance) {
		log.debug("persisting PromotionProduct instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PromotionProduct instance) {
		log.debug("attaching dirty PromotionProduct instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PromotionProduct instance) {
		log.debug("attaching clean PromotionProduct instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PromotionProduct persistentInstance) {
		log.debug("deleting PromotionProduct instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PromotionProduct merge(PromotionProduct detachedInstance) {
		log.debug("merging PromotionProduct instance");
		try {
			PromotionProduct result = (PromotionProduct) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PromotionProduct findById(java.lang.Integer id) {
		log.debug("getting PromotionProduct instance with id: " + id);
		try {
			PromotionProduct instance = (PromotionProduct) sessionFactory
					.getCurrentSession().get("com.home.dao.PromotionProduct",
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

	public List<PromotionProduct> findByExample(PromotionProduct instance) {
		log.debug("finding PromotionProduct instance by example");
		try {
			List<PromotionProduct> results = (List<PromotionProduct>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.PromotionProduct")
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
