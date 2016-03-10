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

import com.home.model.Category;
import com.home.model.Product;
import com.home.model.Promotion;
import com.home.model.PromotionProduct;

/**
 * Home object for domain model class PromotionProduct.
 * @see com.home.dao.PromotionProduct
 * @author Hibernate Tools
 */
public class RegisterProductHome {

	private static final Log log = LogFactory
			.getLog(RegisterProductHome.class);

	private SessionFactory sessionFactory ;//= getSessionFactory();

	public RegisterProductHome(){
		sessionFactory = getSessionFactory();
	};

	public RegisterProductHome(SessionFactory sessionFactory){
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

	public void persist(PromotionProduct transientInstance) {
		log.debug("persisting PromotionProduct instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PromotionProduct instance) throws Exception{
		log.debug("attaching dirty PromotionProduct instance");
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

	public void update(PromotionProduct instance) throws Exception{
		log.debug("attaching dirty PromotionProduct instance");
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

	public void delete(PromotionProduct persistentInstance) throws Exception{
		log.debug("deleting PromotionProduct instance");
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

	public void attachClean(PromotionProduct instance) {
		log.debug("attaching clean PromotionProduct instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public PromotionProduct merge(PromotionProduct detachedInstance) {
		log.debug("merging PromotionProduct instance");
		try {
			PromotionProduct result = (PromotionProduct) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PromotionProduct findById(java.lang.Integer id) {
		log.debug("getting PromotionProduct instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			PromotionProduct instance = (PromotionProduct)session.get(PromotionProduct.class, id);
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

	public List<PromotionProduct> findByExample(PromotionProduct instance) {
		log.debug("finding PromotionProduct instance by example");
		try {
			List<PromotionProduct> results = (List<PromotionProduct>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.PromotionProduct")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<PromotionProduct> getListPromotionProducts(int promotion_id, int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list PromotionProduct");
		Session session = null;
		List<PromotionProduct> results = new ArrayList<PromotionProduct>();
		try {
			session = sessionFactory.openSession();

			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM promotion_product t,(SELECT @i:=0) foo Where promotion_id="+promotion_id+" Order By id) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				PromotionProduct pp = new PromotionProduct();
				pp.setId(rs.getInt("id"));

				Product product = new Product();
				product.setId(rs.getInt("product_id"));		
				pp.setProduct(product);
				pp.setProduct_id(product.getId());

				Promotion promotion = new Promotion();
				promotion.setId(rs.getInt("promotion_id"));
				pp.setPromotion(promotion);
				pp.setPromotion_id(promotion.getId());

				pp.setMaxQuantity(rs.getInt("max_quantity"));
				pp.setMaxPoint(rs.getInt("max_point"));

				results.add(pp);
			}
			rs.close();
			log.debug("retrieve list PromotionProduct successful, result size: " + results.size());
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

	public int getTotalRecords(int promotion_id) throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM PromotionProduct WHERE promotion=:promotion";
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
