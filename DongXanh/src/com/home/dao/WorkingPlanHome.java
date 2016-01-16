package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.home.model.WorkingPlan;

/**
 * Home object for domain model class WorkingPlan.
 * @see com.home.dao.WorkingPlan
 * @author Hibernate Tools
 */
public class WorkingPlanHome {

	private static final Log log = LogFactory.getLog(WorkingPlanHome.class);

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

	public void persist(WorkingPlan transientInstance) {
		log.debug("persisting WorkingPlan instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(WorkingPlan instance) {
		log.debug("attaching dirty WorkingPlan instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WorkingPlan instance) {
		log.debug("attaching clean WorkingPlan instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(WorkingPlan persistentInstance) {
		log.debug("deleting WorkingPlan instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WorkingPlan merge(WorkingPlan detachedInstance) {
		log.debug("merging WorkingPlan instance");
		try {
			WorkingPlan result = (WorkingPlan) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WorkingPlan findById(java.lang.Integer id) {
		log.debug("getting WorkingPlan instance with id: " + id);
		try {
			WorkingPlan instance = (WorkingPlan) sessionFactory
					.getCurrentSession().get("com.home.dao.WorkingPlan", id);
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

	public List<WorkingPlan> findByExample(WorkingPlan instance) {
		log.debug("finding WorkingPlan instance by example");
		try {
			List<WorkingPlan> results = (List<WorkingPlan>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.WorkingPlan")
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
