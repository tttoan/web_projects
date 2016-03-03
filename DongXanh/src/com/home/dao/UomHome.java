package com.home.dao;

// Generated Mar 1, 2016 10:29:17 PM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.home.model.Uom;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Uom.
 * @see com.home.dao.Uom
 * @author Hibernate Tools
 */
public class UomHome {

	private static final Log log = LogFactory.getLog(UomHome.class);

	private SessionFactory sessionFactory;

	public UomHome(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Uom transientInstance) {
		log.debug("persisting Uom instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Uom instance) {
		log.debug("attaching dirty Uom instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Uom instance) {
		log.debug("attaching clean Uom instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Uom persistentInstance) {
		log.debug("deleting Uom instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Uom merge(Uom detachedInstance) {
		log.debug("merging Uom instance");
		try {
			Uom result = (Uom) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Uom findById(java.lang.Integer id) {
		log.debug("getting Uom instance with id: " + id);
		try {
			Uom instance = (Uom) sessionFactory.getCurrentSession().get("com.home.dao.Uom", id);
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

	public List<Uom> findByExample(Uom instance) {
		log.debug("finding Uom instance by example");
		try {
			List<Uom> results = (List<Uom>) sessionFactory.getCurrentSession().createCriteria("com.home.dao.Uom").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Uom> getListUOM() {
		log.debug("finding Uom instance by example");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Uom> results = session.createCriteria(Uom.class).list();
			tx.commit();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}finally {
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
