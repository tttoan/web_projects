package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.hibernate.type.NTextType;

import com.home.conts.MyConts;
import com.home.model.Customer;
import com.home.model.GroupCustomer;
import com.home.model.User;
import com.home.util.StringUtil;

/**
 * Home object for domain model class Customer.
 * 
 * @see com.home.dao.Customer
 * @author Hibernate Tools
 */
public class CustomerHome {

	private static final Log log = LogFactory.getLog(CustomerHome.class);
	private static final int CUSTOMER_IS_ACTIVE = 1;
	private SessionFactory sessionFactory;

	public CustomerHome(SessionFactory sessionFactory) {
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

	public void persist(Customer transientInstance) {
		log.debug("persisting Customer instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public Integer getMaxId() throws Exception {
		log.debug("retrieve max id");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Integer max = (Integer) session.createCriteria(Customer.class).setProjection(Projections.max("id")).uniqueResult();
			tx.commit();
			log.debug("retrieve max id: " + max);
			return max;
		} catch (RuntimeException re) {
			log.error("retrieve max id", re);
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
	
	public String getMaxCustomerCode(String citiCode) throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT MAX(customer_code) FROM customer WHERE UPPER(customer_code) REGEXP  '^"+citiCode+"[0-9]+'";
			
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try(Statement st = conn.createStatement()) {
				try(ResultSet rs = st.executeQuery(sql);){
					if(rs.next()){
						return rs.getString(1);
					}
					rs.close();
				} 
			} 
			return "";
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

	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		log.debug("retrieve list Customer");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Customer> results = session.createCriteria(Customer.class).list();
			tx.commit();
			log.debug("retrieve list Customer successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("retrieve list Customer failed", re);
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

	public void updateDirty(Customer transientInstance) {
		log.debug("attaching dirty Customer instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(transientInstance);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
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

	public void attachDirty(Customer transientInstance) {
		log.debug("attaching dirty Customer instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
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

	@SuppressWarnings("deprecation")
	public void attachClean(Customer instance) {
		log.debug("attaching clean Customer instance");
		try {
			Session session = sessionFactory.openSession();
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void updateCustomerStatus(int custId) {
		log.debug("Updating status instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String query = "update Customer set customer_is_active = 0 where id=:customerId";
			session.createQuery( query )
			        .setInteger( "customerId", custId )
			        .executeUpdate();
			tx.commit();
			session.close();
			log.debug("Update successful");
		} catch (RuntimeException re) {
			log.error("Update failed", re);
			throw re;
		}
	}
	
	public void updateCustomerAddress(int custId, String businessAddress) {
		log.debug("Updating status instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String query = "update Customer set businessAddress=:businessAddress where id=:customerId";
			session.createQuery( query )
			        .setInteger("customerId", custId )
			        .setString("businessAddress", businessAddress)
			        .executeUpdate();
			tx.commit();
			session.close();
			log.debug("Update successful");
		} catch (RuntimeException re) {
			log.error("Update failed", re);
			throw re;
		}
	}
	
	public void delete(Customer persistentInstance) {
		log.debug("deleting Customer instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Customer merge(Customer detachedInstance) {
		log.debug("merging Customer instance");
		try {
			Customer result = (Customer) sessionFactory.openSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Customer findCustomerByCode(String customerCode) throws Exception {
		log.debug("getting Customer instance with code: " + customerCode);
		System.out.println("getting Customer instance with code: " + customerCode);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from Customer where customer_code =:customerCode");
			query.setString("customerCode", customerCode);
			query.setMaxResults(1);
			Customer instance = (Customer) query.uniqueResult();
			tx.commit();
			if (instance == null) {
				log.debug("get Customer successful, no instance found");
				//throw new Exception("Không tìm thấy khách hàng ("+customerCode+")");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("get failed", e);
			}
		}
	}
	
	public Customer findCustomerByCode(Session session, String customerCode) throws Exception {
		//log.debug("getting Customer instance with code: " + customerCode);
		//System.out.println("getting Customer instance with code: " + customerCode);
		try {
			Query query = session.createQuery("from Customer where customer_code =:customerCode");
			query.setString("customerCode", customerCode);
			query.setMaxResults(1);
			Customer instance = (Customer) query.uniqueResult();
			if (instance == null) {
				log.debug("get Customer successful, no instance found");
				//throw new Exception("Không tìm thấy khách hàng ("+customerCode+")");
			} else {
				//log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally {
			
		}
	}

	public Customer findById(java.lang.Integer id) {
		log.debug("getting Customer instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Customer instance = (Customer) session.get(Customer.class, id);
			tx.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				if (instance.getFarmProduct1Session() == null)
					instance.setFarmProduct1Session("0");
				if (instance.getFarmProduct2Session() == null)
					instance.setFarmProduct2Session("0");
				if (instance.getFarmProduct3Session() == null)
					instance.setFarmProduct3Session("0");
				if (instance.getFarmProduct4Session() == null)
					instance.setFarmProduct4Session("0");
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
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

	@SuppressWarnings("unchecked")
	public Customer findByName(String customerName) {
		log.debug("getting Customer instance with name: " + customerName);
		Transaction tx = null;
		Session session = null;
		try {
			Customer instance = null;
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Customer> listInstance = (List<Customer>) session.createCriteria(Customer.class).add(Restrictions.eq("businessName", customerName).ignoreCase()).list();
			tx.commit();
			if (listInstance.size() < 1) {
				log.debug("get successful, no instance found");
			} else {
				instance = listInstance.get(0);
				if (instance.getFarmProduct1Session() == null)
					instance.setFarmProduct1Session("0");
				if (instance.getFarmProduct2Session() == null)
					instance.setFarmProduct2Session("0");
				if (instance.getFarmProduct3Session() == null)
					instance.setFarmProduct3Session("0");
				if (instance.getFarmProduct4Session() == null)
					instance.setFarmProduct4Session("0");
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			re.printStackTrace();
			return null;
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
	
	public List<Customer> findByExample(Customer instance) {
		log.debug("finding Customer instance by example");
		try {
			List<Customer> results = (List<Customer>) sessionFactory.openSession().createCriteria(Customer.class).add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public boolean existCustomer(int id, String customerCode) {
		log.debug("getting Customer instance with code: " + customerCode);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("customerCode", customerCode));
			criteria.add(Restrictions.eq("customerIsActive", true));
			criteria.add(Restrictions.ne("id", id));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			tx.commit();
			if (count > 0) {
				return true;
			}
		} catch (Exception re) {
			log.error("get failed", re);
			re.printStackTrace();
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("get failed", e);
			}
		}
		return false;
	}
	
	public boolean existCustomerBangKe(int id, String statisticName) {
		log.debug("getting Customer instance with code: " + statisticName);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("statisticName", statisticName));
			criteria.add(Restrictions.eq("customerIsActive", true));
			criteria.add(Restrictions.ne("id", id));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			tx.commit();
			if (count > 0) {
				return true;
			}
		} catch (Exception re) {
			log.error("get failed", re);
			re.printStackTrace();
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("get failed", e);
			}
		}
		return false;
	}

	public List<Customer> getLookupCustomer(int skipCusId) {
		return getLookupCustomer(skipCusId, 0);
	}

	public List<Customer> getLookupCustomer(int skipCusId, int groupCustomerId) {
		log.debug("finding List Customer instance by full name");
		List<Customer> results = new ArrayList<Customer>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String addCondition = "";
				if (groupCustomerId > 0)
					addCondition = " and group_customer_id = " + groupCustomerId + "";

				String query = "Select id, customer_code, business_name, statistic_name  From customer where id <> " + skipCusId + addCondition;
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						Customer cus = new Customer();
						cus.setId(rs.getInt("id"));
						cus.setCustomerCode(rs.getString("customer_code"));
						//cus.setBusinessName(rs.getString("business_name") == null?rs.getString("statistic_name"):rs.getString("business_name"));
						cus.setBusinessName(rs.getString("statistic_name") == null?rs.getString("business_name"):rs.getString("statistic_name"));
						cus.setStatisticName(rs.getString("statistic_name"));
						
						results.add(cus);
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
	
	public List<Customer> getListCustomerByUserId(int userId){
		List<Customer> listCustomer = new ArrayList<>();
		List<Object[]> results = getCustomersByNVTT(userId);
		for (Object[] objects : results) {
			Customer cus = new Customer();
			cus.setId((int)objects[0]);
			cus.setCustomerCode((String)objects[1]);
			cus.setBusinessName((String)objects[2]);
			cus.setDirector((String)objects[2]);
			cus.setTelefone((String)objects[3]);
			cus.setBusinessAddress((String)objects[4]);
			cus.setStatisticName((String)objects[4]);
			listCustomer.add(cus);
		}
		return listCustomer;
	}
	
	public List<Object[]> getCustomersByNVTT(int user_id) {
		log.debug("finding List Customer instance by full name");
		List<Object[]> results = new ArrayList<Object[]>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select id, customer_code, business_name, director, statistic_name, telefone,business_address "
						+ "From customer where user_id= "+user_id + " And customer_is_active="+CUSTOMER_IS_ACTIVE+" order by customer_code, business_name, statistic_name";
				System.out.println(query);
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						Customer cus = new Customer();
						cus.setId(rs.getInt("id"));
						cus.setCustomerCode(StringUtil.notNull(rs.getString("customer_code")));
						cus.setDirector(StringUtil.notNull(rs.getString("director")));
						cus.setBusinessName(StringUtil.notNull(rs.getString("business_name")));
						cus.setStatisticName(StringUtil.notNull(rs.getString("statistic_name")));
						cus.setTelefone(StringUtil.notNull(rs.getString("telefone")));
						cus.setBusinessAddress(StringUtil.notNull(rs.getString("business_address")));
						//results.add(new Object[]{cus.getId(), cus.getCustomerCode(), cus.getBusinessName().replace("0.0", "").isEmpty()?cus.getStatisticName():cus.getBusinessName(), cus.getTelefone(), cus.getBusinessAddress()});
						results.add(new Object[]{cus.getId(), cus.getCustomerCode(), cus.getStatisticName().isEmpty()?cus.getBusinessName().replace("0.0", ""):cus.getStatisticName(), cus.getTelefone(), cus.getBusinessAddress()});
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
	
	public List<Object[]> getCustomersFree() {
		log.debug("finding List Customer instance by full name");
		List<Object[]> results = new ArrayList<Object[]>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select id, customer_code, business_name, director, statistic_name "
						+ "From customer where user_id is null  And customer_is_active="+CUSTOMER_IS_ACTIVE+" order by customer_code, business_name, statistic_name";
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						Customer cus = new Customer();
						cus.setId(rs.getInt("id"));
						cus.setCustomerCode(StringUtil.notNull(rs.getString("customer_code")));
						cus.setDirector(StringUtil.notNull(rs.getString("director")));
						cus.setBusinessName(StringUtil.notNull(rs.getString("business_name")));
						cus.setStatisticName(StringUtil.notNull(rs.getString("statistic_name")));
						//results.add(new Object[]{cus.getId(), cus.getCustomerCode(), cus.getBusinessName().replace("0.0", "").isEmpty()?cus.getStatisticName():cus.getBusinessName()});
						results.add(new Object[]{cus.getId(), cus.getCustomerCode(), cus.getStatisticName().isEmpty()?cus.getBusinessName().replace("0.0", ""):cus.getStatisticName()});
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
	
	public void assignCustomer2NVTT(int cus_id, int user_id) throws Exception{
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
//			Query query = session.createQuery("UPDATE Customer set user = :vuser WHERE id = :vid");
//			query.setParameter("vuser", user_id);
//			query.setParameter("vid", cus_id);
//			System.out.println(query.toString());
//			query.executeUpdate();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				sta.executeUpdate("UPDATE customer set user_id = "+user_id+" WHERE id = " + cus_id);
			}
			tx.commit();
			log.debug("assignCustomer2NVTT successful");
		} catch (Exception re) {
			re.printStackTrace();
			log.error("attach failed", re);
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
	
	public void unAssignCustomer2NVTT(int cus_id) {
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("UPDATE Customer set user = null WHERE id = :vid");
			query.setParameter("vid", cus_id);
			query.executeUpdate();
			tx.commit();
			log.debug("unAssignCustomer2NVTT successful");
		} catch (Exception re) {
			re.printStackTrace();
			log.error("attach failed", re);
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
	
	public int getTotalRecords() throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM Customer";
			Query query = session.createQuery(sql);
			List results = query.list();
			return Integer.parseInt(results.get(0).toString());
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
	
	public int getTotalRecords(String searchValue, String nvtt, int assign_type, int user_id) throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			searchValue = searchValue.toLowerCase().trim();
			nvtt = nvtt.toLowerCase().trim();
			System.out.println(nvtt+"searchValue"+searchValue);
			
		/*	String sql = "SELECT COUNT(*) "
					+ " FROM  customer c " + 
					" LEFT JOIN customer c1 ON c.customer1_level1_id=c1.id " +
					" LEFT JOIN customer c2 ON c.customer2_level1_id=c2.id " +
					" LEFT JOIN customer c3 ON c.customer3_level1_id=c3.id " +
					" LEFT JOIN customer c4 ON c.customer4_level1_id=c4.id " +
					" LEFT JOIN customer c5 ON c.customer5_level1_id=c5.id " +
					" LEFT JOIN user u ON c.user_id=u.id " +
					" LEFT JOIN group_customer iv ON c.group_customer_id=iv.id " +
					"WHERE "
					+" (''='"+nvtt+"' OR (lower(user_name) like '"+nvtt+"%'))"
					+" AND ("+(assign_type==1?"c.user_id > 0":"("+(assign_type==2?"c.user_id IS NULL":"(0=0)")+")")+")"
					+" AND ("+(cusL1?"c.group_customer_id=1":"(0=0)")+")"
					+" AND (''='"+searchValue+"' OR ("
							+ " lower(c1.business_name) like '"+searchValue+"%'"
							+ " OR lower(c2.business_name) like '"+searchValue+"%'"
							+ " OR lower(c.business_name) like '"+searchValue+"%'"
							+ " OR lower(c.statistic_name) like '"+searchValue+"%'"
							+ " OR lower(c.customer_code) = '"+searchValue+"'"
							+ " OR lower(user_name) like '"+searchValue+"%'"
							+ ") ) "
					+" AND c.customer_is_active = "+CUSTOMER_IS_ACTIVE+" ";*/
			System.out.println(nvtt +"======1======="+assign_type+"=====1========"+user_id);
			/*
			String sql = " SELECT COUNT(*) "
					   + " FROM customer c"
					   + " LEFT JOIN user u ON c.user_id=u.id "
					   + " LEFT JOIN group_customer iv ON c.group_customer_id=iv.id"
					   + "WHERE c.customer_is_active = "+CUSTOMER_IS_ACTIVE+" ";
			// nvtt
			if(nvtt.length()>1){
				sql = sql + "AND lower(user_name) like '"+nvtt+"%'";
			}
			//user_id
			if(user_id>0){
				sql = sql + " AND ( c.customer1_level1_id = "+user_id +" OR ";
				sql = sql + "       c.customer2_level1_id = "+user_id +" OR ";
				sql = sql + "       c.customer3_level1_id = "+user_id +" OR ";
				sql = sql + "       c.customer4_level1_id = "+user_id +" OR ";
				sql = sql + "       c.customer5_level1_id = "+user_id +" ) ";
			}
			//assign_type
			if(assign_type==2){
				sql = sql + "AND c.user_id IS NULL";			
			}else if(assign_type==1){
				sql = sql + "AND c.user_id IS NOT NULL";			
			}
			
			//searchValue			
			if(searchValue.length()>0){
				sql = sql + " AND ( lower(c.business_name)like '%"+searchValue+"%'  OR ";
				sql = sql + "       lower(statistic_name) like '%"+searchValue+"%'  OR ";
				sql = sql + "       lower(user_name)      like '%"+searchValue+"%'  OR";
				sql = sql + "       lower(c.customer_code)like '%"+searchValue+"%'  OR";
				sql = sql + "       lower(c.tax_number)like '%"+searchValue+"%'  ) ";
			}*/
			
			
			
			//
			 String sql = "SELECT COUNT(*) "
					+ " FROM  customer c " + 
					" LEFT JOIN customer c1 ON c.customer1_level1_id=c1.id " +
					" LEFT JOIN customer c2 ON c.customer2_level1_id=c2.id " +
					" LEFT JOIN customer c3 ON c.customer3_level1_id=c3.id " +
					" LEFT JOIN customer c4 ON c.customer4_level1_id=c4.id " +
					" LEFT JOIN customer c5 ON c.customer5_level1_id=c5.id " +
					" LEFT JOIN user u ON c.user_id=u.id " +
					" LEFT JOIN group_customer iv ON c.group_customer_id=iv.id " +
					"WHERE "
					+" (''='"+nvtt+"' OR (lower(user_name) like '"+nvtt+"%'))"
					+" AND ("+(assign_type==1?"c.user_id > 0":"("+(assign_type==2?"c.user_id IS NULL":"(0=0)")+")")+")"
				
					+" AND (''='"+searchValue+"' OR ("
							+ " lower(c1.business_name) like '"+searchValue+"%'"
							+ " OR lower(c2.business_name) like '"+searchValue+"%'"
							+ " OR lower(c.business_name) like '"+searchValue+"%'"
							+ " OR lower(c.statistic_name) like '"+searchValue+"%'"
							+ " OR lower(c.customer_code) = '"+searchValue+"'"
							+ " OR lower(user_name) like '"+searchValue+"%'"
							+ ") ) "
					+" AND c.customer_is_active = "+CUSTOMER_IS_ACTIVE+" ";
			if(user_id>0){
				sql = sql + " AND ( c.customer1_level1_id = "+user_id +" OR ";
				sql = sql + "       c.customer2_level1_id = "+user_id +" OR ";
				sql = sql + "       c.customer3_level1_id = "+user_id +" OR ";
				sql = sql + "       c.customer4_level1_id = "+user_id +" OR ";
				sql = sql + "       c.customer5_level1_id = "+user_id +" ) ";
			}
			
			System.out.println("=========================================================");
			System.out.println(sql);
			System.out.println("=========================================================");
			int total = 0;
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
			rs.close();
			return total;
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
	
	public List<Customer> getListCustomer(int startPageIndex, int recordsPerPage, String searchValue, String nvtt, int assign_type, int cusL1) throws Exception{
		log.debug("retrieve list Customer");
		Session session = null;
		List<Customer> results = new ArrayList<Customer>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			searchValue = null==searchValue ? "" :  searchValue.toLowerCase().trim();
			nvtt        =  null==nvtt ? "" : nvtt.toLowerCase().trim();
			/*String sql = 
					"SELECT * FROM ( "
						+"SELECT @i:=@i+1 AS iterator, YY.* FROM ("
							+ "SELECT c.*, c1.business_name as cus1name, c2.business_name as cus2name, c3.business_name as cus3name, c4.business_name as cus4name, c5.business_name as cus5name, user_name, full_name, group_name "
							+ " FROM  customer c " + 
									" LEFT JOIN customer c1 ON c.customer1_level1_id=c1.id AND c1.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN customer c2 ON c.customer2_level1_id=c2.id AND c2.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN customer c3 ON c.customer3_level1_id=c3.id AND c3.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN customer c4 ON c.customer4_level1_id=c4.id AND c4.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN customer c5 ON c.customer5_level1_id=c5.id AND c5.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN user u ON c.user_id=u.id " +
									" LEFT JOIN group_customer iv ON c.group_customer_id=iv.id " +
								"WHERE "
								+" (''='"+nvtt+"' OR (lower(user_name) like '"+nvtt+"%'))"
								+" AND ("+(assign_type==1?"c.user_id > 0":"("+(assign_type==2?"c.user_id IS NULL":"(0=0)")+")")+")"
								+" AND ("+(cusL1?"c.group_customer_id=1":"(0=0)")+")"
								+" AND (''='"+searchValue+"' OR ("
										+ " lower(c1.business_name) like '"+searchValue+"%'"
										+ " OR lower(c2.business_name) like '"+searchValue+"%'"
										+ " OR lower(c.business_name) like '"+searchValue+"%'"
										+ " OR lower(c.statistic_name) like '"+searchValue+"%'"
										+ " OR lower(c.customer_code) = '"+searchValue+"'"
										+ " OR lower(user_name) like '"+searchValue+"%'"
										+ ") ) "
								+" AND c.customer_is_active = "+CUSTOMER_IS_ACTIVE+" "
								+ " Order by c.business_name) AS YY, (SELECT @i:=0) foo Order By business_name) AS XX "
						+" WHERE iterator > "+startPageIndex+" AND iterator <= " + range + " order by business_name";*/
			
			String user_id="";
			
			
			if(cusL1>0){
				user_id = user_id + " AND ( c.customer1_level1_id = "+cusL1 +" OR ";
				user_id = user_id + "       c.customer2_level1_id = "+cusL1 +" OR ";
				user_id = user_id + "       c.customer3_level1_id = "+cusL1 +" OR ";
				user_id = user_id + "       c.customer4_level1_id = "+cusL1 +" OR ";
				user_id = user_id + "       c.customer5_level1_id = "+cusL1 +" ) ";
			}
			String sql = 
					"SELECT * FROM ( "
						+"SELECT @i:=@i+1 AS iterator, YY.* FROM ("
							+ "SELECT c.*, c1.business_name as cus1name, c2.business_name as cus2name, c3.business_name as cus3name, c4.business_name as cus4name, c5.business_name as cus5name, user_name, full_name, group_name "
							+ " FROM  customer c " + 
									" LEFT JOIN customer c1 ON c.customer1_level1_id=c1.id AND c1.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN customer c2 ON c.customer2_level1_id=c2.id AND c2.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN customer c3 ON c.customer3_level1_id=c3.id AND c3.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN customer c4 ON c.customer4_level1_id=c4.id AND c4.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN customer c5 ON c.customer5_level1_id=c5.id AND c5.customer_is_active = "+CUSTOMER_IS_ACTIVE+"" +
									" LEFT JOIN user u ON c.user_id=u.id " +
									" LEFT JOIN group_customer iv ON c.group_customer_id=iv.id " +
								"WHERE "
								+" (''='"+nvtt+"' OR (lower(user_name) like '"+nvtt+"%'))"
								+" AND ("+(assign_type==1?"c.user_id > 0":"("+(assign_type==2?"c.user_id IS NULL":"(0=0)")+")")+")"
							
								+ user_id
								+" AND (''='"+searchValue+"' OR ("
										+ " lower(c1.business_name) like '"+searchValue+"%'"
										+ " OR lower(c2.business_name) like '"+searchValue+"%'"
										+ " OR lower(c.business_name) like '"+searchValue+"%'"
										+ " OR lower(c.statistic_name) like '"+searchValue+"%'"
										+ " OR lower(c.customer_code) = '"+searchValue+"'"
										+ " OR lower(user_name) like '"+searchValue+"%'"
										+ ") ) "
								+" AND c.customer_is_active = "+CUSTOMER_IS_ACTIVE+" "
								+ " Order by c.id) AS YY, (SELECT @i:=0) foo ) AS XX "
						+" WHERE iterator > "+startPageIndex+" AND iterator <= " + range + " order by business_name";
		/*	
			+ " Order by c.business_name) AS YY, (SELECT @i:=0) foo Order By business_name) AS XX "
			+" WHERE iterator > "+startPageIndex+" AND iterator <= " + range + " order by business_name";
					
						*/		
			
			
			
			
			ResultSet rs = conn.createStatement().executeQuery(sql);
			System.out.println(startPageIndex +"============"+ recordsPerPage);
			int no = startPageIndex+1;
			while(rs.next()){
				Customer c = new Customer();
				c.setNo(no++);
				c.setId(rs.getInt("id"));
				c.setCreateTime((rs.getDate("create_time"))	);
				c.setCertificateNumber(StringUtil.notNull(rs.getString("certificate_number"))	);
				c.setCertificateDate((rs.getDate("certificate_date"))	);
				c.setCertificateAddress(StringUtil.notNull(rs.getString("certificate_address"))	);
				c.setTaxNumber(StringUtil.notNull(rs.getString("tax_number"))	);
				c.setBusinessName(StringUtil.notNull(rs.getString("business_name"))	);
				c.setStatisticName(StringUtil.notNull(rs.getString("statistic_name"))	);
				c.setBudgetRegister((rs.getBigDecimal("budget_register"))	);
				c.setTelefone(StringUtil.notNull(rs.getString("telefone"))	);
				c.setFax(StringUtil.notNull(rs.getString("fax"))	);
				c.setEmail(StringUtil.notNull(rs.getString("email"))	);
				c.setSocialAddress(StringUtil.notNull(rs.getString("social_address"))	);
				c.setBusinessAddress(StringUtil.notNull(rs.getString("business_address"))	);
				c.setLawyer(StringUtil.notNull(rs.getString("lawyer"))	);
				c.setAdviser(StringUtil.notNull(rs.getString("adviser"))	);
				c.setDirector(StringUtil.notNull(rs.getString("director"))	);
				c.setDirectorMobile(StringUtil.notNull(rs.getString("director_mobile"))	);
				c.setDirectorBirthday((rs.getDate("director_birthday"))	);
				c.setDirectorBirthdayNotify((rs.getBoolean("director_birthday_notify"))	);
				c.setCustomerCode(StringUtil.notNull(rs.getString("customer_code"))	);
				c.setDirectorDomicile(StringUtil.notNull(rs.getString("director_domicile"))	);
				c.setSellMan(StringUtil.notNull(rs.getString("sell_man"))	);
				c.setSellManMobile(StringUtil.notNull(rs.getString("sell_man_mobile"))	);
				c.setBudgetOriginal((rs.getInt("budget_original"))	);
				c.setOtherBusiness(StringUtil.notNull(rs.getString("other_business"))	);
				
				Customer c1 = new Customer();
				c1.setId(rs.getInt("customer1_level1_id"));
				c1.setBusinessName(rs.getString("cus1name"));
				c.setCustomerByCustomer1Level1Id(c1);
				c.setCustomer1Percent((rs.getFloat("customer1_percent"))	);
				Customer c2 = new Customer();
				c2.setId(rs.getInt("customer2_level1_id"));
				c2.setBusinessName(rs.getString("cus2name"));
				c.setCustomerByCustomer2Level1Id(c2);
				c.setCustomer2Percent((rs.getFloat("customer2_percent"))	);
				Customer c3 = new Customer();
				c3.setId(rs.getInt("customer3_level1_id"));
				c3.setBusinessName(rs.getString("cus3name"));
				c.setCustomerByCustomer3Level1Id(c3);
				c.setCustomer3Percent((rs.getFloat("customer3_percent"))	);
				Customer c4 = new Customer();
				c4.setId(rs.getInt("customer4_level1_id"));
				c4.setBusinessName(rs.getString("cus4name"));
				c.setCustomerByCustomer4Level1Id(c4);
				c.setCustomer4Percent((rs.getFloat("customer4_percent"))	);
				Customer c5 = new Customer();
				c5.setId(rs.getInt("customer5_level1_id"));
				c5.setBusinessName(rs.getString("cus5name"));
				c.setCustomerByCustomer5Level1Id(c5);
				c.setCustomer5Percent((rs.getFloat("customer5_percent"))	);
				
				c.setRevenue1((rs.getBigDecimal("revenue1"))	);
				c.setRevenue2((rs.getBigDecimal("revenue2"))	);
				c.setRevenueExpect1((rs.getBigDecimal("revenue_expect1"))	);
				c.setRevenueExpect2((rs.getBigDecimal("revenue_expect2"))	);
				c.setRevenueExpect3((rs.getBigDecimal("revenue_expect3"))	);
				c.setPercentProvide1(StringUtil.notNull(rs.getString("percent_provide1"))	);
				c.setPercentProvide2(StringUtil.notNull(rs.getString("percent_provide2"))	);
				c.setPercentProvide3(StringUtil.notNull(rs.getString("percent_provide3"))	);
				c.setPercentProvide4(StringUtil.notNull(rs.getString("percent_provide4"))	);
				c.setProductSell(StringUtil.notNull(rs.getString("product_sell"))	);
				c.setProduct1Hot(StringUtil.notNull(rs.getString("product1_hot"))	);
				c.setProduct2Hot(	StringUtil.notNull(rs.getString("product2_hot"))	);
				c.setProduct3Hot(	StringUtil.notNull(rs.getString("product3_hot"))	);
				c.setProduct4Hot(	StringUtil.notNull(rs.getString("product4_hot"))	);
				c.setProduct5Hot(	StringUtil.notNull(rs.getString("product5_hot"))	);
				c.setProduct6Hot(	StringUtil.notNull(rs.getString("product6_hot"))	);
				c.setFarmProduct1((rs.getFloat("farm_product1"))	);
				c.setFarmProduct1Session(StringUtil.notNull(rs.getString("farm_product1_session"))	);
				c.setFarmProduct2((rs.getFloat("farm_product2"))	);
				c.setFarmProduct2Session(StringUtil.notNull(rs.getString("farm_product2_session"))	);
				c.setFarmProduct3((rs.getFloat("farm_product3"))	);
				c.setFarmProduct3Session(StringUtil.notNull(rs.getString("farm_product3_session"))	);
				c.setFarmProduct4((rs.getFloat("farm_product4"))	);
				c.setFarmProduct4Session(StringUtil.notNull(rs.getString("farm_product4_session"))	);
				c.setTotalVipCustomer((rs.getInt("total_vip_customer"))	);
				
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setFullName(StringUtil.notNull(rs.getString("full_name")));
				user.setUserName(StringUtil.notNull(rs.getString("user_name")));
				c.setUser(user);

				GroupCustomer group = new GroupCustomer();
				group.setId(rs.getInt("group_customer_id"));
				group.setGroupName(StringUtil.notNull(rs.getString("group_name")));
				c.setGroupCustomer(group);
				
				c.setPathDocScan(StringUtil.notNull(rs.getString("path_doc_scan"))	);
				c.setCustomer_location(StringUtil.notNull(rs.getString("customer_location")));
				
				results.add(c);
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
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}


	public List<Object[]> lookupCustomer(String cusName, String groupCustomer) throws Exception {
		//log.debug("finding List Customer instance by full name");
		List<Object[]> results = new ArrayList<Object[]>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select id, customer_code, business_name, statistic_name, business_address, telefone "
						+ "From customer "
						+ "Where group_customer_id in ("+(groupCustomer)+") AND (lower(business_name) Like ? OR lower(statistic_name) Like ?) order by customer_code, business_name, statistic_name LIMIT 20";
				//System.out.println(query);
				try(PreparedStatement pre = conn.prepareStatement(query)){
					pre.setString(1, "%"+cusName.toLowerCase()+"%");
					pre.setString(2, "%"+cusName.toLowerCase()+"%");
					try (ResultSet rs = pre.executeQuery()) {
						while (rs.next()) {
							Customer cus = new Customer();
							cus.setId(rs.getInt("id"));
							cus.setCustomerCode(StringUtil.notNull(rs.getString("customer_code")));
							cus.setBusinessName(StringUtil.notNull(rs.getString("business_name")));
							cus.setStatisticName(StringUtil.notNull(rs.getString("statistic_name")));
							cus.setTelefone(StringUtil.notNull(rs.getString("telefone")));
							cus.setBusinessAddress(StringUtil.notNull(rs.getString("business_address")));
							//results.add(new Object[]{cus.getId(), cus.getCustomerCode(), cus.getBusinessName().replace("0.0", "").isEmpty()?cus.getStatisticName():cus.getBusinessName(), cus.getTelefone(), cus.getBusinessAddress()});
							results.add(new Object[]{cus.getId(), cus.getStatisticName().isEmpty()?cus.getBusinessName().replace("0.0", ""):cus.getStatisticName(), cus.getTelefone(), cus.getBusinessAddress()});
						}
					} 
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return results;
		} catch (Exception re) {
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
	
	public List<Object[]> lookupCustomerL2WithL1(String cusName2, String cusId1) throws Exception {
		//log.debug("finding List Customer instance by full name");
		List<Object[]> results = new ArrayList<Object[]>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select id, customer_code, business_name, statistic_name, business_address, telefone "
						+ "From customer "
						+ "Where group_customer_id ="+MyConts.CUS_L2+" AND (lower(business_name) Like ? OR lower(statistic_name) Like ?) "
						+ "AND ("
						+ "(COALESCE(customer1_level1_id, 0) = "+cusId1+") OR"
						+ "(COALESCE(customer2_level1_id, 0) = "+cusId1+") OR"
						+ "(COALESCE(customer3_level1_id, 0) = "+cusId1+") OR"
						+ "(COALESCE(customer4_level1_id, 0) = "+cusId1+") OR"
						+ "(COALESCE(customer5_level1_id, 0) = "+cusId1+") )"
						+ "Order by customer_code, business_name, statistic_name LIMIT 20";
				System.out.println(query);
				try(PreparedStatement pre = conn.prepareStatement(query)){
					pre.setString(1, "%"+cusName2.toLowerCase()+"%");
					pre.setString(2, "%"+cusName2.toLowerCase()+"%");
					try (ResultSet rs = pre.executeQuery()) {
						while (rs.next()) {
							Customer cus = new Customer();
							cus.setId(rs.getInt("id"));
							cus.setCustomerCode(StringUtil.notNull(rs.getString("customer_code")));
							cus.setBusinessName(StringUtil.notNull(rs.getString("business_name")));
							cus.setStatisticName(StringUtil.notNull(rs.getString("statistic_name")));
							cus.setTelefone(StringUtil.notNull(rs.getString("telefone")));
							cus.setBusinessAddress(StringUtil.notNull(rs.getString("business_address")));
							//results.add(new Object[]{cus.getId(), cus.getCustomerCode(), cus.getBusinessName().replace("0.0", "").isEmpty()?cus.getStatisticName():cus.getBusinessName(), cus.getTelefone(), cus.getBusinessAddress()});
							results.add(new Object[]{cus.getId(), cus.getStatisticName().isEmpty()?cus.getBusinessName().replace("0.0", ""):cus.getStatisticName(), cus.getTelefone(), cus.getBusinessAddress()});
						}
					} 
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return results;
		} catch (Exception re) {
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
