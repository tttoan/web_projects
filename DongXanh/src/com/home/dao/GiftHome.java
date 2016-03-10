package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import com.home.model.Category;
import com.home.model.Gift;
import com.home.model.Product;

/**
 * Home object for domain model class Gift.
 * @see com.home.dao.Gift
 * @author Hibernate Tools
 */
public class GiftHome {

	private static final Log log = LogFactory.getLog(GiftHome.class);

	private SessionFactory sessionFactory;
	
	public GiftHome(SessionFactory sessionFactory) {
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


	public void persist(Gift transientInstance) {
		log.debug("persisting Gift instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Gift instance) throws Exception{
		log.debug("attaching dirty Gift instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			re.printStackTrace();
			log.error("attach failed", re);
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
	
	public void update(Gift instance) throws Exception{
		log.debug("attaching dirty Gift instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			re.printStackTrace();
			log.error("attach failed", re);
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

	@SuppressWarnings("deprecation")
	public void attachClean(Gift instance) {
		log.debug("attaching clean Gift instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Gift persistentInstance) throws Exception{
		log.debug("deleting Gift instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("delete failed", re);
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

	public Gift merge(Gift detachedInstance) {
		log.debug("merging Gift instance");
		try {
			Gift result = (Gift) sessionFactory.openSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Gift findById(java.lang.Integer id) throws Exception{
		log.debug("getting Gift instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Gift instance = (Gift)session.get(Gift.class, id);
			tx.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("get failed", re);
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

	@SuppressWarnings("unchecked")
	public List<Gift> findByExample(Gift instance) {
		log.debug("finding Gift instance by example");
		try {
			List<Gift> results = (List<Gift>) sessionFactory
					.openSession().createCriteria("com.home.dao.Gift")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
	public List<Gift> getListGifts(int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list Product");
		Transaction tx = null;
		Session session = null;
		List<Gift> results = new ArrayList<Gift>();
		try {
			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//			Query query = session.createQuery("FROM Gift");
//			query.setFirstResult(startPageIndex);
//			query.setMaxResults(recordsPerPage);
//			List<Gift> results = query.list();
//			tx.commit();
			
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM gift t,(SELECT @i:=0) foo Order By id) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				Gift g = new Gift();
				g.setId(rs.getInt("id"));
				g.setGiftName(rs.getString("gift_name"));
				results.add(g);
			}
			rs.close();
			log.debug("retrieve list Gift successful, result size: " + results.size());
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
	
	public int getTotalRecords() throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM Gift";
			Query query = session.createQuery(sql);
			List results = query.list();
			return Integer.parseInt(results.get(0).toString());
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
	
	public LinkedHashMap<Integer, String> getListGifts() throws Exception{
		log.debug("retrieve list Gift");
		Session session = null;
		LinkedHashMap<Integer, String> results = new LinkedHashMap<Integer, String>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `gift` Order by gift_name");
			while(rs.next()){
				results.put(rs.getInt("id"), rs.getString("gift_name"));
			}
			rs.close();
			log.debug("retrieve list Gift successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Gift failed", re);
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
	
	public LinkedHashMap<Integer, String> getListGifts(int promotion_id) throws Exception{
		log.debug("retrieve list Gift");
		Session session = null;
		LinkedHashMap<Integer, String> results = new LinkedHashMap<Integer, String>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT pg.id,  gift_name FROM `gift` g JOIN `promotion_gift` pg ON g.id=pg.gift_id WHERE promotion_id="+promotion_id+"  Order by gift_name");
			while(rs.next()){
				results.put(rs.getInt("id"), rs.getString("gift_name"));
			}
			rs.close();
			log.debug("retrieve list Gift successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Gift failed", re);
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
