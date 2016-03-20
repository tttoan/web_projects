package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import com.home.model.Category;
import com.home.model.Customer;
import com.home.model.Product;

/**
 * Home object for domain model class Category.
 * 
 * @see com.home.dao.Category
 * @author Hibernate Tools
 */
public class CategoryHome {

	private static final Log log = LogFactory.getLog(CategoryHome.class);

	private SessionFactory sessionFactory;

	public CategoryHome(SessionFactory sessionFactory) {
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

	public void persist(Category transientInstance) {
		log.debug("persisting Category instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Category instance) {
		log.debug("attaching dirty Category instance");
		try {
			sessionFactory.openSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Category instance) {
		log.debug("attaching clean Category instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Category persistentInstance) {
		log.debug("deleting Category instance");
		try {
			sessionFactory.openSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Category merge(Category detachedInstance) {
		log.debug("merging Category instance");
		try {
			Category result = (Category) sessionFactory.openSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Category findById(java.lang.Integer id) {
		log.debug("getting Category instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Category instance = (Category) session.get(Category.class, id);
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

	@SuppressWarnings("unchecked")
	public List<Category> findByExample(Category instance) {
		log.debug("finding Category instance by example");
		try {
			List<Category> results = (List<Category>) sessionFactory.openSession().createCriteria("com.home.dao.Category").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}

	}

	// public List<Category> getListCategory() {
	// log.debug("retrieve list Category");
	// Transaction tx = null;
	// Session session = null;
	// try {
	// session = sessionFactory.openSession();
	// tx = session.beginTransaction();
	// List<Category> results = session.createCriteria(Category.class).list();
	// tx.commit();
	// session.close();
	// log.debug("retrieve list Customer successful, result size: " +
	// results.size());
	// return results;
	// } catch (Exception re) {
	// re.printStackTrace();
	// log.error("retrieve list Product failed", re);
	// throw re;
	// } finally{
	// try {
	// if(session != null){
	// session.close();
	// }
	// } catch (Exception e) {
	// }
	// }
	// }

	public HashMap<Integer, String> getListCategory() throws Exception {
		log.debug("retrieve list Category");
		Session session = null;
		HashMap<Integer, String> results = new HashMap<Integer, String>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `category`");
			while (rs.next()) {
				results.put(rs.getInt("id"), rs.getString("category_name"));
			}
			rs.close();
			log.debug("retrieve list Category successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Category failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
