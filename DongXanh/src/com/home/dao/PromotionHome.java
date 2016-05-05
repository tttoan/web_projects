package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

import com.home.model.GroupCustomer;
import com.home.model.Product;
import com.home.model.Promotion;
import com.home.model.PromotionCus;
import com.home.util.StringUtil;
import com.home.util.SystemUtil;

/**
 * Home object for domain model class Promotion.
 * @see com.home.dao.Promotion
 * @author Hibernate Tools
 */
public class PromotionHome {

	private static final Log log = LogFactory.getLog(PromotionHome.class);

	private SessionFactory sessionFactory ;//= getSessionFactory();

	public PromotionHome(){
		sessionFactory = getSessionFactory();
	};

	public PromotionHome(SessionFactory sessionFactory){
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

	public void persist(Promotion transientInstance) {
		log.debug("persisting Promotion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Promotion instance) {
		log.debug("attaching dirty Promotion instance");
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
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public void update(Promotion instance) throws Exception{
		log.debug("attaching dirty Promotion instance");
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
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public void attachClean(Promotion instance) {
		log.debug("attaching clean Promotion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Promotion persistentInstance) throws Exception {
		log.debug("deleting Promotion instance");
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
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public Promotion merge(Promotion detachedInstance) {
		log.debug("merging Promotion instance");
		try {
			Promotion result = (Promotion) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Promotion findById(java.lang.Integer id) throws Exception{
		log.debug("getting Promotion instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Promotion instance = (Promotion)session.get(Promotion.class, id);
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
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public List<Promotion> findByExample(Promotion instance) {
		log.debug("finding Promotion instance by example");
		try {
			List<Promotion> results = (List<Promotion>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.Promotion")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Promotion> getListPromotion() throws Exception{
		log.debug("retrieve list Promotion");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Promotion> results = session.createCriteria(Promotion.class).list();
			tx.commit();
			log.debug("retrieve list Promotion successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("retrieve list Promotion failed", re);
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
	
	public List<Promotion> getListPromotionActive() throws Exception{
		log.debug("retrieve list Promotion");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query  = session.createQuery("From Promotion Where startDate <= :startDate Order By startDate Desc");
			query.setParameter("startDate", new Date(new java.util.Date().getTime()));
			List<Promotion> results =  query.list();
			tx.commit();
			log.debug("retrieve list Promotion successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("retrieve list Promotion failed", re);
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
	
	public List<Promotion> getListPromotionActive(int type) throws Exception{
		log.debug("retrieve list Promotion");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = null;
			String sql ;
			switch (type) {
			case 1:
				sql = "From Promotion Where status=true AND startDate <= :CURDATE AND endDate >= :CURDATE Order By startDate Desc" ;
				query  = session.createQuery(sql);
				query.setParameter("CURDATE", new Date(new java.util.Date().getTime()));
				break;
			case 2:
				sql = "From Promotion Where status=true AND startDate > :CURDATE AND endDate > :CURDATE Order By startDate Desc" ;
				query  = session.createQuery(sql);
				query.setParameter("CURDATE", new Date(new java.util.Date().getTime()));
				break;
			case 3:
				sql = "From Promotion Where endDate < :CURDATE AND endDate >= :CURDATE1 Order By startDate Desc" ;
				query  = session.createQuery(sql);
				query.setParameter("CURDATE", new Date(new java.util.Date().getTime()));
				query.setParameter("CURDATE1", new Date(SystemUtil.getCurrentDate().getTime()-(7*1000*60*60*24)));
				break;
			case 4:
				sql = "From Promotion Where startDate < :CURDATE1 AND endDate < :CURDATE1 Order By startDate Desc" ; 
				query  = session.createQuery(sql);
				query.setParameter("CURDATE1", new Date(SystemUtil.getCurrentDate().getTime()-(7*1000*60*60*24)));
				break;
			default:
				sql = "From Promotion Where startDate <= :CURDATE Order By startDate Desc"; 
				query  = session.createQuery(sql);
				query.setParameter("CURDATE", new Date(new java.util.Date().getTime()));
				break;
			}
			List<Promotion> results =  query.list();
			tx.commit();
			log.debug("retrieve list Promotion successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("retrieve list Promotion failed", re);
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
	
	public void setFinish() throws Exception{
		Session session = null;
		try {
			String sql = "UPDATE `promotion` SET status=0 WHERE start_date<? AND end_date<?";
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, new Date(SystemUtil.getCurrentDate().getTime()));
			pre.setDate(2, new Date(SystemUtil.getCurrentDate().getTime()));
			pre.executeUpdate();
			conn.commit();
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Promotion failed", re);
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
	
	public List<Promotion> getListPromotions(int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list promotion");
		Session session = null;
		List<Promotion> results = new ArrayList<Promotion>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM promotion t,(SELECT @i:=0) foo Order By status Desc, id Desc) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				//System.out.println("====> " + rs.getShort("customer_regist"));
				Promotion p = new Promotion();
				p.setId(rs.getInt("id"));
				p.setPromotion_id(rs.getInt("id"));
				p.setPromotionName(rs.getString("promotion_name"));
				p.setStartDate(rs.getDate("start_date"));
				p.setEndDate(rs.getDate("end_date"));
				p.setRemarks(rs.getString("remarks"));
				p.setStatus(rs.getBoolean("status"));
				p.setCustomerRegist(rs.getShort("customer_regist"));
				p.setRule(rs.getString("rule"));
				GroupCustomer g = new GroupCustomer();
				g.setId(rs.getInt("group_customer_id"));
				p.setGroupCustomer(g);
				p.setGroup_customer_id(g.getId());
				results.add(p);
			}
			rs.close();
			log.debug("retrieve list Promotion successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Promotion failed", re);
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

	public int getTotalRecords() throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM Promotion";
			Query query = session.createQuery(sql);
			List results = query.list();
			return Integer.parseInt(results.get(0).toString());
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Promotion failed", re);
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
	
	public LinkedHashMap<String, PromotionCus> listPromotionCusResult(Date start, Date end) throws Exception{
		log.debug("retrieve list PromotionCus");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			PreparedStatement pre = conn.prepareStatement(
					"Select  c2.customer_code, c2.business_name, u.user_name, p.id as product_id, ct.category_name, p.product_code, p.product_name, sum(s.total_box) as total_box,sum(s.quantity) as quantity,sum(s.total) as total "
					+ "From `statistic` s "//JOIN Customer c1 ON s.customerByCustomerCodeLevel1=c1.id "
					+ "JOIN `customer` c2 ON s.customer_code_level2=c2.id "
					+ "JOIN `product` p ON p.id=s.product_id "
					+ "JOIN `category` ct ON ct.id=p.category_id "
					+ "JOIN `user` u ON u.id=s.user_id "
					+ "Where date_received >= ? And date_received <= ? "
					+ "Group By c2.customer_code, c2.business_name, u.user_name, product_id, ct.category_name, p.product_code, p.product_name "
					+ "Order By c2.customer_code, p.product_code");
			
			pre.setDate(1, start);
			pre.setDate(2, end);
			//System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			LinkedHashMap<String, PromotionCus> results = new LinkedHashMap<>();
			int idx = 1;
			while(rs.next()){
				PromotionCus pc = new PromotionCus();
				pc.setRow_index(idx++);
				pc.setCustomerCode(StringUtil.notNull(rs.getString("customer_code")));
				pc.setCustomerName(StringUtil.notNull(rs.getString("business_name")));
				pc.setSellMan(StringUtil.notNull(rs.getString("user_name")));
				pc.setProductCode(StringUtil.notNull(rs.getString("product_code")));
				pc.setCategoryName(StringUtil.notNull(rs.getString("category_name")));
				pc.setProductName(StringUtil.notNull(rs.getString("product_name")));
				pc.setTotalBox(rs.getLong("total_box"));
				pc.setQuality(rs.getLong("quantity"));
				pc.setTotaPrice(rs.getBigDecimal("total"));
				//pc.setTotaPoint((long)((Object[])obj)[8]);
				
				Product product = new Product();
				product.setId(rs.getInt("product_id"));
				product.setProductCode(StringUtil.notNull(rs.getString("product_code")));
				product.setProductName(StringUtil.notNull(rs.getString("product_name")));
				product.setTotalBox((int)rs.getLong("total_box"));
				product.setQuantity((int)rs.getLong("quantity"));
				product.setUnitPrice(rs.getBigDecimal("total"));
				
				if(results.containsKey(pc.getCustomerCode())){
					PromotionCus pcOld = results.get(pc.getCustomerCode());
					pcOld.getProducts().add(product);
					pcOld.setTotalBox(pcOld.getTotalBox() + pc.getTotalBox());
					pcOld.setQuality(pcOld.getQuality() + pc.getQuality());
					pcOld.setTotaPrice(pcOld.getTotaPrice().add(pc.getTotaPrice()));
					pcOld.setTotalProduct(pcOld.getProducts().size());
				}else{
					results.put(pc.getCustomerCode(), pc);
					pc.getProducts().add(product);
					pc.setTotalProduct(1);
				}
				
			}
			rs.close();
			pre.close();
			log.debug("retrieve list PromotionCus successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list PromotionCus failed", re);
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
	
	public int[] totalStatisticPromotions() throws Exception{
		log.debug("retrieve list promotion");
		Session session = null;
		int[] results = new int[5];
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT " +
					"(SELECT count(*)  FROM `promotion` WHERE status=1 AND start_date<=CURDATE() AND end_date >= CURDATE()) as sl1, " +
					"(SELECT count(*)  FROM `promotion` WHERE status=1 AND start_date>CURDATE() AND end_date > CURDATE()) as sl2, " +
					"(SELECT count(*)  FROM `promotion` WHERE end_date<CURDATE() AND end_date >= CURDATE()-INTERVAL 7 DAY) as sl3, " +
					"(SELECT count(*)  FROM `promotion` WHERE end_date<CURDATE()-INTERVAL 7 DAY) as sl4"; 
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if(rs.next()){
				results[0] = rs.getInt("sl1");
				results[1] = rs.getInt("sl2");
				results[2] = rs.getInt("sl3");
				results[3] = rs.getInt("sl4");
				results[4] = results[0]+results[1]+results[2]+results[3];
			}
			rs.close();
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list totalStatisticPromotions failed", re);
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
}
