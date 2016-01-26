package com.home.dao;

// Generated Jan 12, 2016 9:15:32 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.home.model.User;

/**
 * Home object for domain model class User.
 * 
 * @see com.home.model.User
 * @author Hibernate Tools
 */
public class UserHome {

	private static final Log log = LogFactory.getLog(UserHome.class);
	
	private SessionFactory sessionFactory;

	public UserHome(SessionFactory sessionFactory) {
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

	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(transientInstance);
			tx.commit();
			session.close();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
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

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) sessionFactory.openSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) sessionFactory.openSession().get(
					User.class, id);
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
	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) sessionFactory
					.openSession().createCriteria(User.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public User getUserByCredentials(String userName, String password) {
		log.debug("finding User instance by credentials");
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("from User where user_name=:id and password=:pwd");
			query.setString("id", userName);
			query.setString("pwd", password);
			User results = (User) query.uniqueResult();
			tx.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by credentials failed", re);
			throw re;
		}

	}
	public User getUserByFullName(String fullName) {
		log.debug("finding User instance by full name");
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("from User where full_name=:fullName");
			query.setString("fullName", fullName);
			User results = (User) query.uniqueResult();
			tx.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by full name failed", re);
			throw re;
		}

	}
}
