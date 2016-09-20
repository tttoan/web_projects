package com.home.dao;

// Generated Sep 19, 2016 11:34:17 PM by Hibernate Tools 4.3.1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.home.model.ContactType;

/**
 * Home object for domain model class ContactType.
 * @see com.home.model.ContactType
 * @author Hibernate Tools
 */
public class ContactTypeHome {

	private static final Log log = LogFactory.getLog(ContactTypeHome.class);

	private SessionFactory sessionFactory;

	public ContactTypeHome(SessionFactory sessionFactory) {
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

	public void persist(ContactType transientInstance) {
		log.debug("persisting ContactType instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ContactType instance) {
		log.debug("attaching dirty ContactType instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ContactType instance) {
		log.debug("attaching clean ContactType instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ContactType persistentInstance) {
		log.debug("deleting ContactType instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ContactType merge(ContactType detachedInstance) {
		log.debug("merging ContactType instance");
		try {
			ContactType result = (ContactType) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ContactType findById(java.lang.Integer id) {
		log.debug("getting ContactType instance with id: " + id);
		try {
			ContactType instance = (ContactType) sessionFactory
					.getCurrentSession().get("com.home.test.ContactType", id);
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

	public List<ContactType> findByExample(ContactType instance) {
		log.debug("finding ContactType instance by example");
		try {
			List<ContactType> results = (List<ContactType>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.test.ContactType")
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
	public List<ContactType> findAll() {
		log.debug("finding ContactType instance by example");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			List<ContactType> results = session.createCriteria(ContactType.class).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}finally{
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
