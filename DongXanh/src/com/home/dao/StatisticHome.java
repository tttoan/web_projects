package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.home.model.Statistic;

/**
 * Home object for domain model class Statistic.
 * 
 * @see com.home.dao.Statistic
 * @author Hibernate Tools
 */
public class StatisticHome {

	private static final Log log = LogFactory.getLog(StatisticHome.class);

	private SessionFactory sessionFactory;

	public StatisticHome(SessionFactory sessionFactory) {
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

	public void persist(Statistic transientInstance) {
		log.debug("persisting Statistic instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Statistic instance) {
		log.debug("attaching dirty Statistic instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(instance);
			tx.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Statistic instance) {
		log.debug("attaching clean Statistic instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Statistic persistentInstance) {
		log.debug("deleting Statistic instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Statistic merge(Statistic detachedInstance) {
		log.debug("merging Statistic instance");
		try {
			Statistic result = (Statistic) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Statistic findById(java.lang.Integer id) {
		log.debug("getting Statistic instance with id: " + id);
		try {
			Statistic instance = (Statistic) sessionFactory.getCurrentSession().get("com.home.dao.Statistic", id);
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

	public List<Statistic> findByExample(Statistic instance) {
		log.debug("finding Statistic instance by example");
		try {
			List<Statistic> results = (List<Statistic>) sessionFactory.getCurrentSession().createCriteria(Statistic.class).add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Statistic> getListInvoice() {
		log.debug("retrieve list Statistic");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Statistic> results = session.createCriteria(Statistic.class).list();
			tx.commit();
			log.debug("retrieve list Statistic successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("retrieve list Statistic failed", re);
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
