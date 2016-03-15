package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.home.model.Customer;
import com.home.model.User;

/**
 * Home object for domain model class Customer.
 * 
 * @see com.home.dao.Customer
 * @author Hibernate Tools
 */
public class CustomerHome {

	private static final Log log = LogFactory.getLog(CustomerHome.class);

	private SessionFactory sessionFactory;

	public CustomerHome(SessionFactory sessionFactory) {
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

	public void persist(Customer transientInstance) {
		log.debug("persisting Customer instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getListCustomer() {
		log.debug("retrieve list Customer");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Customer> results = session.createCriteria(Customer.class).list();
			tx.commit();
			log.debug("retrieve list Customer successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("retrieve list Customer failed", re);
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

	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		log.debug("retrieve list Customer");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Customer> results = session.createCriteria(Customer.class).list();
			tx.commit();
			log.debug("retrieve list Customer successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("retrieve list Customer failed", re);
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
	
	public void updateDirty(Customer transientInstance) {
		log.debug("attaching dirty Customer instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(transientInstance);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
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
	
	public void attachDirty(Customer transientInstance) {
		log.debug("attaching dirty Customer instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
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

	@SuppressWarnings("deprecation")
	public void attachClean(Customer instance) {
		log.debug("attaching clean Customer instance");
		try {
			Session session = sessionFactory.openSession();
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Customer persistentInstance) {
		log.debug("deleting Customer instance");
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

	public Customer merge(Customer detachedInstance) {
		log.debug("merging Customer instance");
		try {
			Customer result = (Customer) sessionFactory.openSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Customer findById(java.lang.Integer id) {
		log.debug("getting Customer instance with id: " + id);
		Transaction tx = null;
		Session session=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Customer instance = (Customer) session.get(Customer.class, id);
			tx.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
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

	public List<Customer> findByExample(Customer instance) {
		log.debug("finding Customer instance by example");
		try {
			List<Customer> results = (List<Customer>) sessionFactory.openSession().createCriteria(Customer.class).add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
