package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.home.model.PromotionCustomerProduct;
import com.home.model.PromotionCustomerProductId;

/**
 * Home object for domain model class PromotionCustomerProduct.
 * @see com.home.dao.PromotionCustomerProduct
 * @author Hibernate Tools
 */
public class PromotionCustomerProductHome {

	private static final Log log = LogFactory
			.getLog(PromotionCustomerProductHome.class);

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

	public void persist(PromotionCustomerProduct transientInstance) {
		log.debug("persisting PromotionCustomerProduct instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PromotionCustomerProduct instance) {
		log.debug("attaching dirty PromotionCustomerProduct instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PromotionCustomerProduct instance) {
		log.debug("attaching clean PromotionCustomerProduct instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PromotionCustomerProduct persistentInstance) {
		log.debug("deleting PromotionCustomerProduct instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PromotionCustomerProduct merge(
			PromotionCustomerProduct detachedInstance) {
		log.debug("merging PromotionCustomerProduct instance");
		try {
			PromotionCustomerProduct result = (PromotionCustomerProduct) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PromotionCustomerProduct findById(
			PromotionCustomerProductId id) {
		log.debug("getting PromotionCustomerProduct instance with id: " + id);
		try {
			PromotionCustomerProduct instance = (PromotionCustomerProduct) sessionFactory
					.getCurrentSession().get(
							"com.home.dao.PromotionCustomerProduct", id);
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

	public List<PromotionCustomerProduct> findByExample(
			PromotionCustomerProduct instance) {
		log.debug("finding PromotionCustomerProduct instance by example");
		try {
			List<PromotionCustomerProduct> results = (List<PromotionCustomerProduct>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.PromotionCustomerProduct")
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
