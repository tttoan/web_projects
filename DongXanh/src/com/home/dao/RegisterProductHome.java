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

import com.home.model.PromotionProduct;
import com.home.model.RegisterProduct;

/**
 * Home object for domain model class RegisterProduct.
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

	public void persist(RegisterProduct transientInstance) {
		log.debug("persisting RegisterProduct instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RegisterProduct instance) throws Exception{
		log.debug("attaching dirty RegisterProduct instance");
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

	public void update(RegisterProduct instance) throws Exception{
		log.debug("attaching dirty RegisterProduct instance");
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

	public void delete(RegisterProduct persistentInstance) throws Exception{
		log.debug("deleting RegisterProduct instance");
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

	public void attachClean(RegisterProduct instance) {
		log.debug("attaching clean RegisterProduct instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public RegisterProduct merge(RegisterProduct detachedInstance) {
		log.debug("merging RegisterProduct instance");
		try {
			RegisterProduct result = (RegisterProduct) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RegisterProduct findById(java.lang.Integer id) {
		log.debug("getting RegisterProduct instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			RegisterProduct instance = (RegisterProduct)session.get(RegisterProduct.class, id);
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

	public List<RegisterProduct> findByExample(RegisterProduct instance) {
		log.debug("finding RegisterProduct instance by example");
		try {
			List<RegisterProduct> results = (List<RegisterProduct>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.RegisterProduct")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<RegisterProduct> getListRegisterProducts(int register_id, int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list RegisterProduct");
		Session session = null;
		List<RegisterProduct> results = new ArrayList<RegisterProduct>();
		try {
			session = sessionFactory.openSession();

			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM register_product t,(SELECT @i:=0) foo Where register_id="+register_id+" Order By id) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				RegisterProduct pp = new RegisterProduct();
				pp.setId(rs.getInt("id"));

				PromotionProduct promotionProduct = new PromotionProduct();
				promotionProduct.setId(rs.getInt("p_product_id"));		
				pp.setPromotionProduct(promotionProduct);
				pp.setP_product_id(promotionProduct.getId());

				pp.setBox(rs.getInt("box"));
				pp.setPoint(rs.getInt("point"));

				results.add(pp);
			}
			rs.close();
			log.debug("retrieve list RegisterProduct successful, result size: " + results.size());
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
			String sql = "SELECT COUNT(*) AS COUNT FROM RegisterProduct WHERE promotionRegister=:register_id";
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
