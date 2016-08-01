package com.home.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;

import com.home.model.Customer;
import com.home.util.StringUtil;

public class UserPlanHome {
	private static final Log log = LogFactory.getLog(CategoryHome.class);

	private SessionFactory sessionFactory;

	public UserPlanHome(SessionFactory sessionFactory) {
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

	public List<Object[]> getEventExport() {
		log.debug("finding List Event instance");
		List<Object[]> results = new ArrayList<Object[]>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "select t1.customer_code, t1.business_name, t1.full_name, t1.start_date, sum(t1.phone) as phone ,sum(t1.meet) as meet "
						+ "from ("
				+" select c.customer_code, c.business_name, u.full_name, DATE_FORMAT(e.start_date, '%d/%m/%Y') as start_date," 
				+" case when e.contact_type > 0 then 1 else 0 end as phone, case when e.contact_type < 0 then 1 else 0 end as meet"
				+" from events e"
				+" left join user u on e.employee_id = u.id "
				+" left join customer c on e.customer_id = c.id where e.customer_id > 0) as t1"
				+" group by t1.customer_code,t1.business_name, t1.full_name, t1.start_date";
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						results.add(new Object[] { StringUtil.notNull(rs.getString("customer_code")),
								StringUtil.notNull(rs.getString("business_name")),
								StringUtil.notNull(rs.getString("full_name")),
								rs.getInt("phone"),
								rs.getInt("meet"),
								StringUtil.notNull(rs.getString("start_date")),
								""});
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by Customer failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
