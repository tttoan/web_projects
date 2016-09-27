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

import com.home.model.Gift;
import com.home.model.Product;
import com.home.model.Promotion;
import com.home.model.PromotionGift;

/**
 * Home object for domain model class PromotionGift.
 * @see com.home.dao.PromotionGift
 * @author Hibernate Tools
 */
public class PromotionGiftHome {

	private static final Log log = LogFactory
			.getLog(PromotionGiftHome.class);

	private SessionFactory sessionFactory ;//= getSessionFactory();

	public PromotionGiftHome(){
		sessionFactory = getSessionFactory();
	};

	public PromotionGiftHome(SessionFactory sessionFactory){
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

	public void persist(PromotionGift transientInstance) {
		log.debug("persisting PromotionGift instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PromotionGift instance) throws Exception{
		log.debug("attaching dirty PromotionGift instance");
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

	public void update(PromotionGift instance) throws Exception{
		log.debug("attaching dirty PromotionGift instance");
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

	public void delete(PromotionGift persistentInstance) throws Exception{
		log.debug("deleting PromotionGift instance");
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

	public void attachClean(PromotionGift instance) {
		log.debug("attaching clean PromotionGift instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public PromotionGift merge(PromotionGift detachedInstance) {
		log.debug("merging PromotionGift instance");
		try {
			PromotionGift result = (PromotionGift) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PromotionGift findById(java.lang.Integer id) {
		log.debug("getting PromotionGift instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			PromotionGift instance = (PromotionGift)session.get(PromotionGift.class, id);
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

	public List<PromotionGift> findByExample(PromotionGift instance) {
		log.debug("finding PromotionGift instance by example");
		try {
			List<PromotionGift> results = (List<PromotionGift>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.PromotionGift")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<PromotionGift> getListPromotionGifts(int promotion_id, int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list PromotionGift");
		Session session = null;
		List<PromotionGift> results = new ArrayList<PromotionGift>();
		try {
			session = sessionFactory.openSession();

			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM promotion_gift t,(SELECT @i:=0) foo Where promotion_id="+promotion_id+" Order By id) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				PromotionGift pp = new PromotionGift();
				pp.setId(rs.getInt("id"));

				Gift gift = new Gift();
				gift.setId(rs.getInt("gift_id"));		
				pp.setGift(gift);
				pp.setGift_id(gift.getId());

				Promotion promotion = new Promotion();
				promotion.setId(rs.getInt("promotion_id"));
				pp.setPromotion(promotion);
				pp.setPromotion_id(promotion.getId());

				pp.setMaxQuantity(rs.getInt("max_quantity"));
				pp.setMaxPoint(rs.getInt("max_point"));
				pp.setUnit(rs.getString("unit"));
				pp.setFormula(rs.getString("formula"));
				pp.setPrice(rs.getDouble("price"));

				results.add(pp);
			}
			rs.close();
			log.debug("retrieve list PromotionGift successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Product failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public int getTotalRecords(int promotion_id) throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM PromotionGift WHERE promotion=:promotion";
			Query query = session.createQuery(sql);
			query.setInteger("promotion", promotion_id);
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
