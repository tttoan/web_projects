package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.home.model.Customer;
import com.home.model.Promotion;

/**
 * Home object for domain model class Promotion.
 * @see com.home.dao.Promotion
 * @author Hibernate Tools
 */
public class PromotionHome {

	private static final Log log = LogFactory.getLog(PromotionHome.class);

	private SessionFactory sessionFactory ;//= getSessionFactory();

	public PromotionHome(){
		sessionFactory = getSessionFactory();
	};
			
	public PromotionHome(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
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

	public void persist(Promotion transientInstance) {
		log.debug("persisting Promotion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Promotion instance) {
		log.debug("attaching dirty Promotion instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Promotion instance) {
		log.debug("attaching clean Promotion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Promotion persistentInstance) {
		log.debug("deleting Promotion instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Promotion merge(Promotion detachedInstance) {
		log.debug("merging Promotion instance");
		try {
			Promotion result = (Promotion) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Promotion findById(java.lang.Integer id) {
		log.debug("getting Promotion instance with id: " + id);
		try {
			Promotion instance = (Promotion) sessionFactory.getCurrentSession()
					.get("com.home.dao.Promotion", id);
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

	public List<Promotion> findByExample(Promotion instance) {
		log.debug("finding Promotion instance by example");
		try {
			List<Promotion> results = (List<Promotion>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.Promotion")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Promotion> getListPromotion() {
		log.debug("retrieve list Promotion");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Promotion> results = session.createCriteria(Promotion.class).list();
			tx.commit();
			session.close();
			log.debug("retrieve list Promotion successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("retrieve list Promotion failed", re);
			throw re;
		}
	}
}
