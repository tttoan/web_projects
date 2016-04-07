package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.home.model.Customer;
import com.home.model.Gift;
import com.home.model.GroupCustomer;
import com.home.model.Product;
import com.home.model.Promotion;
import com.home.model.PromotionCus;
import com.home.model.PromotionGift;
import com.home.model.PromotionProduct;
import com.home.model.PromotionRegister;
import com.home.model.RegisterGift;
import com.home.model.RegisterProduct;

/**
 * Home object for domain model class Promotion.
 * @see com.home.dao.Promotion
 * @author Hibernate Tools
 */
public class PromotionRegistHome {

	private static final Log log = LogFactory.getLog(PromotionRegistHome.class);

	private SessionFactory sessionFactory ;//= getSessionFactory();

	public PromotionRegistHome(){
		sessionFactory = getSessionFactory();
	};

	public PromotionRegistHome(SessionFactory sessionFactory){
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

	public void persist(PromotionRegister transientInstance) {
		log.debug("persisting PromotionRegister instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PromotionRegister instance) {
		log.debug("attaching dirty PromotionRegister instance");
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

	public void update(PromotionRegister instance) throws Exception{
		log.debug("attaching dirty PromotionRegister instance");
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

	public void attachClean(PromotionRegister instance) {
		log.debug("attaching clean PromotionRegister instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PromotionRegister persistentInstance) throws Exception {
		log.debug("deleting PromotionRegister instance");
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

	public PromotionRegister merge(PromotionRegister detachedInstance) {
		log.debug("merging PromotionRegister instance");
		try {
			PromotionRegister result = (PromotionRegister) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PromotionRegister findById(java.lang.Integer id) throws Exception{
		log.debug("getting PromotionRegister instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			PromotionRegister instance = (PromotionRegister)session.get(PromotionRegister.class, id);
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

	public List<PromotionRegister> findByExample(PromotionRegister instance) {
		log.debug("finding PromotionRegister instance by example");
		try {
			List<PromotionRegister> results = (List<PromotionRegister>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.PromotionRegister")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<PromotionRegister> getListPromotionRegister() throws Exception{
		log.debug("retrieve list PromotionRegister");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<PromotionRegister> results = session.createCriteria(PromotionRegister.class).list();
			tx.commit();
			log.debug("retrieve list PromotionRegister successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("retrieve list PromotionRegister failed", re);
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
	
	public PromotionRegister getPromotionRegister(int promotion_id, int customer_id) throws Exception{
		log.debug("retrieve list PromotionRegister");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query  = session.createQuery("From PromotionRegister Where promotion = :promotion And customer_id = :customer_id");
			query.setParameter("promotion", promotion_id);
			query.setParameter("customer_id", customer_id);
			List<PromotionRegister> results =  query.list();
			tx.commit();
			log.debug("retrieve list PromotionRegister successful, result size: " + results.size());
			return results.get(0);
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("retrieve list PromotionRegister failed", re);
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
	
	public List<RegisterGift> getRegisterGifts(int register_id, int promotion_id) throws Exception{
		log.debug("retrieve list PromotionRegister");
		Session session = null;
		List<RegisterGift> results = new ArrayList<RegisterGift>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT * FROM `register_gift` rg JOIN `promotion_gift` pg ON rg.p_gift_id=pg.id "
					+ "JOIN `gift` g ON g.id=pg.gift_id "
					+ "WHERE pg.promotion_id="+promotion_id+" AND rg.register_id="+register_id+" ORDER BY pg.id, rg.id";
			System.out.println(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next()){
				RegisterGift registerGift = new RegisterGift();
				PromotionGift promotionGift = new PromotionGift();
				promotionGift.setPromotion_id(rs.getInt("promotion_id"));
				promotionGift.setGift_id(rs.getInt("gift_id"));
				promotionGift.setMaxQuantity(rs.getInt("max_quantity"));
				promotionGift.setMaxPoint(rs.getInt("max_point"));
				promotionGift.setUnit(rs.getString("unit"));
				promotionGift.setFormula(rs.getString("formula"));
				promotionGift.setPrice(rs.getDouble("price"));
				
				Gift gift = new Gift();
				gift.setId(rs.getInt("gift_id"));
				gift.setGiftName(rs.getString("gift_name"));
				promotionGift.setGift(gift);
				
				registerGift.setPromotionGift(promotionGift);
				registerGift.setTotal(rs.getInt("total"));
				results.add(registerGift);
			}
			rs.close();
			log.debug("retrieve list PromotionRegister successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			log.error("retrieve list PromotionRegister failed", re);
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
	
	public List<RegisterProduct> getRegisterProducts(int register_id, int promotion_id) throws Exception{
		log.debug("retrieve list PromotionRegister");
		Session session = null;
		List<RegisterProduct> results = new ArrayList<RegisterProduct>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM `register_product` rg JOIN  `promotion_product` pg ON rg.p_product_id=pg.id JOIN `product` p ON p.id=pg.product_id "
					+ "WHERE promotion_id="+promotion_id+" AND register_id="+register_id+" ORDER BY pg.id, rg.id");
			while(rs.next()){
				RegisterProduct registerProduct = new RegisterProduct();
				registerProduct.setPoint(rs.getInt("point"));
				registerProduct.setBox(rs.getInt("box"));
				
				PromotionProduct promotionProduct = new PromotionProduct();
				promotionProduct.setProduct_id(rs.getInt("product_id"));
				promotionProduct.setMaxPoint(rs.getInt("max_point"));
				registerProduct.setPromotionProduct(promotionProduct);
				
				Product product = new Product();
				product.setId(rs.getInt("product_id"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				promotionProduct.setProduct(product);
				
				results.add(registerProduct);
			}
			rs.close();
			log.debug("retrieve list PromotionRegister successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			log.error("retrieve list PromotionRegister failed", re);
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
	
	public List<PromotionRegister> getListPromotionRegister(int promotion_id) throws Exception{
		log.debug("retrieve list PromotionRegister");
		Session session = null;
		List<PromotionRegister> results = new ArrayList<PromotionRegister>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM `promotion_register`"
					+ "WHERE promotion_id="+promotion_id+" ORDER BY id");
			while(rs.next()){
				PromotionRegister promotionRegister = new PromotionRegister();
				promotionRegister.setId(rs.getInt("id"));
				promotionRegister.setCustomer_id(rs.getInt("customer_id"));
				Customer cus = new Customer();
				cus.setId(rs.getInt("customer_id"));
				promotionRegister.setCustomer(cus);
				promotionRegister.setTotalBox(rs.getInt("total_box"));
				promotionRegister.setTotalPoint(rs.getInt("total_point"));
				promotionRegister.setPromotion_id(promotion_id);
				results.add(promotionRegister);
			}
			rs.close();
			log.debug("retrieve list PromotionRegister successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			log.error("retrieve list PromotionRegister failed", re);
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
	
	public List<PromotionRegister> getListPromotionRegisters(int promotion_id, int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list PromotionRegister");
		Session session = null;
		List<PromotionRegister> results = new ArrayList<PromotionRegister>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM promotion_register t,(SELECT @i:=0) foo Order By id) AS XX WHERE promotion_id = "+promotion_id+" AND iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				PromotionRegister p = new PromotionRegister();
				p.setId(rs.getInt("id"));
				Customer cus = new Customer();
				cus.setId(rs.getInt("customer_id"));
				p.setCustomer(cus);
				p.setCustomer_id(cus.getId());
				Promotion promotion = new Promotion();
				promotion.setId(rs.getInt("promotion_id"));
				p.setPromotion(promotion);
				p.setPromotion_id(promotion.getId());
				p.setTotalBox(rs.getInt("total_box"));
				p.setTotalPoint(rs.getInt("total_point"));
				results.add(p);
			}
			rs.close();
			log.debug("retrieve list PromotionRegister successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list PromotionRegister failed", re);
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
			String sql = "SELECT COUNT(*) AS COUNT FROM PromotionRegister";
			Query query = session.createQuery(sql);
			List results = query.list();
			return Integer.parseInt(results.get(0).toString());
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list PromotionRegister failed", re);
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
	
	public HashMap<Integer, Integer> getMapProductsRegister(int promotion_id) throws Exception{
		log.debug("retrieve list Product");
		Session session = null;
		HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `promotion_product` WHERE promotion_id="+promotion_id+" ");
			while(rs.next()){
				results.put(rs.getInt("product_id"), rs.getInt("max_point"));
			}
			rs.close();
			log.debug("retrieve list Product successful, result size: " + results.size());
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
	
}
