package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;

import com.home.entities.UserPlanGeneral;
import com.home.model.WorkingPlan;
import com.home.util.StringUtil;

/**
 * Home object for domain model class WorkingPlan.
 * @see com.home.dao.WorkingPlan
 * @author Hibernate Tools
 */
public class WorkingPlanHome {

	private static final Log log = LogFactory.getLog(WorkingPlanHome.class);

	private SessionFactory sessionFactory;

	public WorkingPlanHome(SessionFactory sessionFactory) {
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

	public void persist(WorkingPlan transientInstance) {
		log.debug("persisting WorkingPlan instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(WorkingPlan instance) {
		log.debug("attaching dirty WorkingPlan instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WorkingPlan instance) {
		log.debug("attaching clean WorkingPlan instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(WorkingPlan persistentInstance) {
		log.debug("deleting WorkingPlan instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WorkingPlan merge(WorkingPlan detachedInstance) {
		log.debug("merging WorkingPlan instance");
		try {
			WorkingPlan result = (WorkingPlan) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WorkingPlan findById(java.lang.Integer id) {
		log.debug("getting WorkingPlan instance with id: " + id);
		try {
			WorkingPlan instance = (WorkingPlan) sessionFactory
					.getCurrentSession().get("com.home.dao.WorkingPlan", id);
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

	public List<WorkingPlan> findByExample(WorkingPlan instance) {
		log.debug("finding WorkingPlan instance by example");
		try {
			List<WorkingPlan> results = (List<WorkingPlan>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.home.dao.WorkingPlan")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<UserPlanGeneral> getAllUserPlan4Report(int user_id, Date startday, Date endday) throws Exception{
		log.debug("finding List Event instance");
		List<UserPlanGeneral> results = new ArrayList<UserPlanGeneral>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			
			String query = ""
			+" SELECT u.user_name, u.full_name, c.customer_code, c.business_name, c.statistic_name, e.start_date, e.end_date, "
				//+ "DATE_FORMAT(e.start_date, '%d/%m/%Y') as start_date," 
				+" c.telefone, c.business_address, e.contact_type, e.contact_address, "
				+" case when e.contact_type = 2 then 1 else 0 end as phone, "
				+" case when e.contact_type = 1 then 1 else 0 end as meet"
			+" FROM events as e"
				+" left join user as u on e.employee_id = u.id "
				+" left join customer as c on e.customer_id = c.id "
			+" WHERE e.customer_id > 0"
				+ " and (u.id=? or -1=?) and (DATE(e.start_date) >= ? and DATE(e.start_date) <= ?)"
			+" ORDER BY u.user_name, start_date, c.customer_code";
			
			try (PreparedStatement pre = conn.prepareStatement(query)) {
				pre.setInt(1, user_id);
				pre.setInt(2, user_id);
				pre.setDate(3, startday);
				pre.setDate(4, endday);
				System.out.println(pre.toString());
				
				try (ResultSet rs = pre.executeQuery()) {
					while (rs.next()) {
						UserPlanGeneral userPlanGeneral = new UserPlanGeneral();
						userPlanGeneral.setUser_name(StringUtil.notNull(rs.getString("user_name")));
						userPlanGeneral.setFull_name(StringUtil.notNull(rs.getString("full_name")));
						userPlanGeneral.setCustomer_code(StringUtil.notNull(rs.getString("customer_code")));
						userPlanGeneral.setBusiness_name(StringUtil.notNull(rs.getString("business_name")));
						userPlanGeneral.setStatistic_name(StringUtil.notNull(rs.getString("statistic_name")));
						//System.out.println(rs.getTime("start_date"));
						//System.out.println(rs.getTimestamp("start_date"));
						//System.out.println(rs.getDate("start_date"));
						userPlanGeneral.setStart_date(new Date(rs.getTimestamp("start_date").getTime()));
						userPlanGeneral.setEnd_date(new Date(rs.getTimestamp("end_date").getTime()));
						userPlanGeneral.setPhone((rs.getInt("phone")));
						userPlanGeneral.setMeet((rs.getInt("meet")));
						userPlanGeneral.setContact_type(rs.getInt("contact_type"));
						userPlanGeneral.setContact_address(StringUtil.notNull(rs.getString("contact_address")));
						userPlanGeneral.setTelefone(StringUtil.notNull(rs.getString("telefone")));
						userPlanGeneral.setAddress(StringUtil.notNull(rs.getString("business_address")));
						results.add(userPlanGeneral);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			System.out.println("results size = " + results.size());
			return results;
		} catch (Exception re) {
			log.error("find by Customer failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.flush();
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
