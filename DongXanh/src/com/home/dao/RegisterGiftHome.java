package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import com.home.model.PromotionGift;
import com.home.model.RegisterGift;

/**
 * Home object for domain model class PromotionGift.
 * @see com.home.dao.PromotionGift
 * @author Hibernate Tools
 */
public class RegisterGiftHome {

	private static final Log log = LogFactory
			.getLog(RegisterGiftHome.class);

	private SessionFactory sessionFactory ;//= getSessionFactory();

	public RegisterGiftHome(){
		sessionFactory = getSessionFactory();
	};

	public RegisterGiftHome(SessionFactory sessionFactory){
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

	public void persist(RegisterGift transientInstance) {
		log.debug("persisting RegisterGift instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RegisterGift instance) throws Exception{
		log.debug("attaching dirty RegisterGift instance");
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

	public void update(RegisterGift instance) throws Exception{
		log.debug("attaching dirty RegisterGift instance");
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

	public void delete(RegisterGift persistentInstance) throws Exception{
		log.debug("deleting RegisterGift instance");
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

	public void attachClean(RegisterGift instance) {
		log.debug("attaching clean RegisterGift instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public RegisterGift merge(RegisterGift detachedInstance) {
		log.debug("merging RegisterGift instance");
		try {
			RegisterGift result = (RegisterGift) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RegisterGift findById(java.lang.Integer id) {
		log.debug("getting RegisterGift instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			RegisterGift instance = (RegisterGift)session.get(RegisterGift.class, id);
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

	public List<RegisterGift> findByExample(RegisterGift instance) {
		log.debug("finding RegisterGift instance by example");
		try {
			List<RegisterGift> results = (List<RegisterGift>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.RegisterGift")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<RegisterGift> getListRegisterGifts(int register_id, int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list RegisterGift");
		Session session = null;
		List<RegisterGift> results = new ArrayList<RegisterGift>();
		try {
			session = sessionFactory.openSession();

			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM register_gift t,(SELECT @i:=0) foo Where register_id="+register_id+" Order By id) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				RegisterGift pp = new RegisterGift();
				pp.setId(rs.getInt("id"));
				pp.setTotal(rs.getInt("total"));

				PromotionGift promotionGift = new PromotionGift();
				promotionGift.setId(rs.getInt("p_gift_id"));
				pp.setPromotionGift(promotionGift);
				pp.setP_gift_id(promotionGift.getId());

				results.add(pp);
			}
			rs.close();
			log.debug("retrieve list RegisterGift successful, result size: " + results.size());
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

	public int getTotalRecords(int register_id) throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM RegisterGift WHERE promotionRegister=:register_id";
			Query query = session.createQuery(sql);
			query.setInteger("register_id", register_id);
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
	
}
