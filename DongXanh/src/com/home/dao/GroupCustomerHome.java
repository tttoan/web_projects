package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import com.home.model.Customer;
import com.home.model.GroupCustomer;

/**
 * Home object for domain model class GroupCustomer.
 * @see com.home.dao.GroupCustomer
 * @author Hibernate Tools
 */
public class GroupCustomerHome {

	private static final Log log = LogFactory.getLog(GroupCustomerHome.class);

	private SessionFactory sessionFactory;
	public GroupCustomerHome(SessionFactory sessionFactory) {
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

	public void persist(GroupCustomer transientInstance) {
		log.debug("persisting GroupCustomer instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(GroupCustomer instance) {
		log.debug("attaching dirty GroupCustomer instance");
		try {
			sessionFactory.openSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GroupCustomer instance) {
		log.debug("attaching clean GroupCustomer instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(GroupCustomer persistentInstance) {
		log.debug("deleting GroupCustomer instance");
		try {
			sessionFactory.openSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GroupCustomer merge(GroupCustomer detachedInstance) {
		log.debug("merging GroupCustomer instance");
		try {
			GroupCustomer result = (GroupCustomer) sessionFactory.openSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GroupCustomer findById(java.lang.Integer id) {
		log.debug("getting GroupCustomer instance with id: " + id);
		try {
			GroupCustomer instance = (GroupCustomer) sessionFactory.openSession()
					.get("com.home.dao.GroupCustomer", id);
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
	public List<GroupCustomer> findByExample(GroupCustomer instance) {
		log.debug("finding GroupCustomer instance by example");
		try {
			List<GroupCustomer> results = (List<GroupCustomer>) sessionFactory
					.openSession()
					.createCriteria("com.home.dao.GroupCustomer")
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
	public List<GroupCustomer> getListGrpCustomer() {
		log.debug("retrieve list GroupCustomer");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<GroupCustomer> results = session.createCriteria(GroupCustomer.class).list();
			tx.commit();
			log.debug("retrieve list Customer successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Product failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public LinkedHashMap<Integer, String> getListGroupCustomer() throws Exception{
		log.debug("retrieve list GroupCustomer");
		Session session = null;
		LinkedHashMap<Integer, String> results = new LinkedHashMap<Integer, String>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `group_customer` Order By group_name");
			while(rs.next()){
				results.put(rs.getInt("id"), rs.getString("group_name"));
			}
			rs.close();
			log.debug("retrieve list GroupCustomer successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list GroupCustomer failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public HashMap<Integer, String> getListCustomers() throws Exception{
		log.debug("retrieve list GroupCustomer");
		Session session = null;
		HashMap<Integer, String> results = new HashMap<Integer, String>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `customer` Order By customer_code");
			while(rs.next()){
				results.put(rs.getInt("id"), rs.getString("customer_code"));
			}
			rs.close();
			log.debug("retrieve list GroupCustomer successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list GroupCustomer failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
