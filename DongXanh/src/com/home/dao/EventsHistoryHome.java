package com.home.dao;

// Generated Sep 5, 2016 10:48:57 PM by Hibernate Tools 3.4.0.CR1

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;

import com.home.entities.UserPlanHistory;
import com.home.model.EventsHistory;
import com.home.model.Gift;
import com.home.model.Promotion;
import com.home.model.PromotionGift;
import com.home.util.StringUtil;

/**
 * Home object for domain model class EventsHistory.
 * @see com.home.listener.test.EventsHistory
 * @author Hibernate Tools
 */
public class EventsHistoryHome {

	private static final Log log = LogFactory.getLog(EventsHistoryHome.class);

	private SessionFactory sessionFactory;

	public EventsHistoryHome(SessionFactory sessionFactory) {
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

	public void persist(EventsHistory transientInstance) {
		log.debug("persisting EventsHistory instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(EventsHistory instance) {
		log.debug("attaching dirty EventsHistory instance");
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
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public void update(EventsHistory instance) throws Exception{
		log.debug("attaching dirty EventsNote instance");
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

	public void attachClean(EventsHistory instance) {
		log.debug("attaching clean EventsHistory instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(EventsHistory persistentInstance) {
		log.debug("deleting EventsHistory instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EventsHistory merge(EventsHistory detachedInstance) {
		log.debug("merging EventsHistory instance");
		try {
			EventsHistory result = (EventsHistory) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EventsHistory findById(java.lang.Integer id) {
		log.debug("getting EventsHistory instance with id: " + id);
		try {
			EventsHistory instance = (EventsHistory) sessionFactory
					.getCurrentSession().get(
							"com.home.listener.test.EventsHistory", id);
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

	public List<EventsHistory> findByExample(EventsHistory instance) {
		log.debug("finding EventsHistory instance by example");
		try {
			List<EventsHistory> results = (List<EventsHistory>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.listener.test.EventsHistory")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<EventsHistory> getList() throws Exception{
		log.debug("retrieve list EventsHistory");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<EventsHistory> results = session.createCriteria(EventsHistory.class).list();
			tx.commit();
			log.debug("retrieve list EventsHistory successful, result size: " + results.size());
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
	
	public List<EventsHistory> getList(int user_id) throws Exception{
		log.debug("retrieve list EventsHistory");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<EventsHistory> results = session.createCriteria(EventsHistory.class)
					.add(Restrictions.eq("employeeId", user_id)).list();
			tx.commit();
			log.debug("retrieve list EventsHistory successful, result size: " + results.size());
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
	
	public List<UserPlanHistory> getListPlanHistory(int user_id, Date startday, Date endday) throws Exception{
		log.debug("retrieve list UserPlanHistory");
		Session session = null;
		List<UserPlanHistory> results = new ArrayList<UserPlanHistory>();
		try {
			session = sessionFactory.openSession();

			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT e.*, e1.event_name, e1.start_date as event_date, c1.customer_code as c1_code, c1.business_name as c1_bname, c1.statistic_name as c1_sname,"
					+ " c2.customer_code as c2_code, c2.business_name as c2_bname, c2.statistic_name as c2_sname, u.user_name, u.full_name " +
					" FROM events_history AS e " +
					" LEFT JOIN events AS e1 ON e.event_id=e1.event_id " +
					" LEFT JOIN customer AS c1 ON e.customer_id_old=c1.id " +
					" LEFT JOIN customer AS c2 ON e.customer_id_new=c2.id " +
					" LEFT JOIN user AS u ON e.employee_id=u.id " +
					" WHERE " +
					" (u.id=? or -1=?) and (DATE(e.last_modified) >= ? and DATE(e.last_modified) <= ?)" +
					" ORDER BY u.user_name, last_modified, event_id";
			
			try (PreparedStatement pre = conn.prepareStatement(sql)) {
				pre.setInt(1, user_id);
				pre.setInt(2, user_id);
				pre.setDate(3, startday);
				pre.setDate(4, endday);
				System.out.println(pre.toString());
				
				int no=0;
				ResultSet rs = pre.executeQuery();
				while(rs.next()){
					UserPlanHistory planHistory = new UserPlanHistory();
					planHistory.setNo(++no);
					planHistory.setEvent_id(rs.getInt("event_id"));
					try {
						planHistory.setEvent_name(StringUtil.notNull(rs.getString("event_name")));
						planHistory.setEvent_date(new Date(rs.getTimestamp("event_date").getTime()));
					} catch (Exception e) {}
					planHistory.setNvtt(StringUtil.notNull(rs.getString("user_name")));
					planHistory.setFull_name(StringUtil.notNull(rs.getString("full_name")));
					
					String c1_code = StringUtil.notNull(rs.getString("c1_code"));
					String c1_bname = StringUtil.notNull(rs.getString("c1_bname"));
					String c1_sname = StringUtil.notNull(rs.getString("c1_sname"));
					planHistory.setCustomer_old(c1_code + "-" + (c1_bname.isEmpty()?c1_sname:c1_bname));
					String c2_code = StringUtil.notNull(rs.getString("c2_code"));
					String c2_bname = StringUtil.notNull(rs.getString("c2_bname"));
					String c2_sname = StringUtil.notNull(rs.getString("c2_sname"));
					planHistory.setCustomer_new(c2_code + "-" + (c2_bname.isEmpty()?c2_sname:c2_bname));
					
					planHistory.setPlan_date_old(new Date(rs.getTimestamp("plan_date_old").getTime()));
					planHistory.setPlan_date_new(new Date(rs.getTimestamp("plan_date_new").getTime()));
					
					planHistory.setAction(StringUtil.notNull(rs.getString("action")));
					planHistory.setLast_modified(new Date(rs.getTimestamp("last_modified").getTime()));
					
					results.add(planHistory);
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			log.debug("retrieve list UserPlanHistory successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list UserPlanHistory failed", re);
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
	
	
	public List<UserPlanHistory> getListPlanHistory(String user_name, Date startday, Date endday) throws Exception{
		log.debug("retrieve list UserPlanHistory");
		Session session = null;
		List<UserPlanHistory> results = new ArrayList<UserPlanHistory>();
		try {
			session = sessionFactory.openSession();

			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT e.*, e1.event_name, e1.start_date as event_date, c1.customer_code as c1_code, c1.business_name as c1_bname, c1.statistic_name as c1_sname,"
					+ " c2.customer_code as c2_code, c2.business_name as c2_bname, c2.statistic_name as c2_sname, u.user_name, u.full_name " +
					" FROM events_history AS e " +
					" LEFT JOIN events AS e1 ON e.event_id=e1.event_id " +
					" LEFT JOIN customer AS c1 ON e.customer_id_old=c1.id " +
					" LEFT JOIN customer AS c2 ON e.customer_id_new=c2.id " +
					" LEFT JOIN user AS u ON e.employee_id=u.id " +
					" WHERE " +
					" (u.user_name=? or ''=?) and (DATE(e.last_modified) >= ? and DATE(e.last_modified) <= ?)" +
					" ORDER BY u.user_name, last_modified, event_id";
			
			try (PreparedStatement pre = conn.prepareStatement(sql)) {
				pre.setString(1, user_name);
				pre.setString(2, user_name);
				pre.setDate(3, startday);
				pre.setDate(4, endday);
				System.out.println(pre.toString());
				
				int no=0;
				ResultSet rs = pre.executeQuery();
				while(rs.next()){
					UserPlanHistory planHistory = new UserPlanHistory();
					planHistory.setNo(++no);
					planHistory.setEvent_id(rs.getInt("event_id"));
					try {
						planHistory.setEvent_name(StringUtil.notNull(rs.getString("event_name")));
						planHistory.setEvent_date(new Date(rs.getTimestamp("event_date").getTime()));
					} catch (Exception e) {}
					planHistory.setNvtt(StringUtil.notNull(rs.getString("user_name")));
					planHistory.setFull_name(StringUtil.notNull(rs.getString("full_name")));
					
					String c1_code = StringUtil.notNull(rs.getString("c1_code"));
					String c1_bname = StringUtil.notNull(rs.getString("c1_bname"));
					String c1_sname = StringUtil.notNull(rs.getString("c1_sname"));
					planHistory.setCustomer_old(c1_code + "-" + (c1_bname.isEmpty()?c1_sname:c1_bname));
					String c2_code = StringUtil.notNull(rs.getString("c2_code"));
					String c2_bname = StringUtil.notNull(rs.getString("c2_bname"));
					String c2_sname = StringUtil.notNull(rs.getString("c2_sname"));
					planHistory.setCustomer_new(c2_code + "-" + (c2_bname.isEmpty()?c2_sname:c2_bname));
					
					planHistory.setPlan_date_old(new Date(rs.getTimestamp("plan_date_old").getTime()));
					planHistory.setPlan_date_new(new Date(rs.getTimestamp("plan_date_new").getTime()));
					
					planHistory.setAction(StringUtil.notNull(rs.getString("action")));
					planHistory.setLast_modified(new Date(rs.getTimestamp("last_modified").getTime()));
					
					results.add(planHistory);
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			log.debug("retrieve list UserPlanHistory successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list UserPlanHistory failed", re);
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
