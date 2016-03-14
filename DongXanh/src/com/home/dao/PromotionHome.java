package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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

import com.home.model.GroupCustomer;
import com.home.model.Promotion;
import com.home.model.PromotionCus;

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
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public List<PromotionCus> listPromotionCusResult(Date start, Date end) throws Exception{
		log.debug("retrieve list PromotionCus");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query  = session.createQuery(
					"Select  customerNameLevel2, userId, productCode, categoryName, productName, sum(totalBox),sum(quantiy),sum(unitPrice),sum(total) "
					+ "From Statistic "
					+ "Where dateReceived >= :start And dateReceived <= :end "
					+ "Group By customerNameLevel2, userId, productCode, categoryName, productName "
					+ "Order By customerNameLevel2");
			query.setParameter("start", start);
			query.setParameter("end", end);
			List lists = query.list();
			List<PromotionCus> results = new ArrayList<PromotionCus>();
			int idx = 1;
			for (Object obj : lists) {
				PromotionCus pc = new PromotionCus();
				pc.setRow_index(idx++);
				pc.setCustomerCode(((Object[])obj)[0].toString());
				pc.setCustomerName(((Object[])obj)[0].toString());
				pc.setUserId((int)((Object[])obj)[1]);
				pc.setProductCode(((Object[])obj)[2].toString());
				pc.setCategoryName(((Object[])obj)[3].toString());
				pc.setProductName(((Object[])obj)[4].toString());
				pc.setTotalBox((long)((Object[])obj)[5]);
				pc.setQuality((long)((Object[])obj)[6]);
				pc.setTotaPrice((BigDecimal)((Object[])obj)[7]);
				//pc.setTotaPoint((long)((Object[])obj)[8]);
				results.add(pc);
		    }
			tx.commit();
			log.debug("retrieve list PromotionCus successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("retrieve list PromotionCus failed", re);
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
