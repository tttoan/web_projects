package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;

import com.home.model.Customer;
import com.home.model.InvoiceType;
import com.home.model.Product;
import com.home.model.Statistic;
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

	public Integer getMaxId() {
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
		log.debug("getting Customer instance with code: " + customerCode);
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
				log.debug("get successful, instance found");
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

	public boolean isCustomerExist(String customerCode) {
		log.debug("getting Customer instance with code: " + customerCode);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("customerCode", customerCode));
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

				String query = "Select id, customer_code, business_name From customer where id <> " + skipCusId + addCondition;
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						Customer cus = new Customer();
						cus.setId(rs.getInt("id"));
						cus.setCustomerCode(rs.getString("customer_code"));
						cus.setDirector(rs.getString("business_name"));
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
	
	public List<Object[]> getCustomersByNVTT(int user_id) {
		log.debug("finding List Customer instance by full name");
		List<Object[]> results = new ArrayList<Object[]>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select id, customer_code, business_name, director From customer where user_id= "+user_id + " order by business_name";
				System.out.println(query);
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						Customer cus = new Customer();
						cus.setId(rs.getInt("id"));
						cus.setCustomerCode(rs.getString("customer_code"));
						cus.setDirector(rs.getString("director"));
						cus.setBusinessName(rs.getString("business_name"));
						results.add(new Object[]{cus.getId(), cus.getCustomerCode(), cus.getBusinessName()});
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
				String query = "Select id, customer_code, business_name, director From customer where user_id is null order by business_name";
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						Customer cus = new Customer();
						cus.setId(rs.getInt("id"));
						cus.setCustomerCode(rs.getString("customer_code"));
						cus.setDirector(rs.getString("director"));
						cus.setBusinessName(rs.getString("business_name"));
						results.add(new Object[]{cus.getId(), cus.getCustomerCode(), cus.getBusinessName()});
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
	
	public List<Customer> getListCustomer(int startPageIndex, int recordsPerPage, String searchValue) throws Exception{
		log.debug("retrieve list Customer");
		Session session = null;
		List<Customer> results = new ArrayList<Customer>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			searchValue = searchValue.toLowerCase().trim();
			
			String sql = 
					"SELECT XX.*, c1.business_name as cus1name, c2.business_name as cus2name, product_name, unit_price, user_name, full_name, invoice_type FROM (SELECT @i:=@i+1 AS iterator, t.* FROM statistic t,(SELECT @i:=0) foo Order By date_received desc) AS XX " + 
							" LEFT JOIN customer c1 ON XX.customer_code_level1=c1.id " +
							" LEFT JOIN customer c2 ON XX.customer_code_level2=c2.id " +
							" LEFT JOIN product p ON XX.product_id=p.id " +
							" LEFT JOIN user u ON XX.user_id=u.id " +
							" LEFT JOIN invoice_type iv ON XX.invoice_type_id=iv.id " +
						"WHERE iterator > "+startPageIndex+" AND iterator <= " + range +
						" AND (''='"+searchValue+"' OR ("
								+ " lower(c1.business_name) like '"+searchValue+"%'"
								+ " OR lower(c2.business_name) like '"+searchValue+"%'"
								+ " OR lower(product_name) like '"+searchValue+"%'"
								+ " OR lower(user_name) like '"+searchValue+"%'"
								+ ") ) order by date_received desc, id";
			//System.out.println(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			int no = 1;
			while(rs.next()){
				Customer s = new Customer();
				s.setNo(no++);
				s.setId(rs.getInt("id"));
				s.setDateReceived(rs.getDate("date_received"));
				Customer cus1 = new Customer();
				cus1.setId(rs.getInt("customer_code_level1"));
				//if(cus1.getId() != null && cus1.getId() > 0){
					cus1.setBusinessName(StringUtil.notNull(rs.getString("cus1name")));
				//}
				s.setCustomerByCustomerCodeLevel1(cus1);
				Customer cus2 = new Customer();
				cus2.setId(rs.getInt("customer_code_level2"));
				//if(cus2.getId() != null && cus2.getId() > 0){
					cus2.setBusinessName(StringUtil.notNull(rs.getString("cus2name")));
				//}
				s.setCustomerByCustomerCodeLevel2(cus2);
				Product product = new Product();
				product.setId(rs.getInt("product_id"));
				//if(product.getId() != null && product.getId() > 0){
					product.setProductName(StringUtil.notNull(rs.getString("product_name")));
					product.setUnitPrice(rs.getBigDecimal("unit_price"));
				//}
				s.setProduct(product);
				s.setTotalBox(rs.getInt("total_box"));
				s.setQuantity(rs.getInt("quantity"));
				s.setTotal(rs.getBigDecimal("total"));
				User user = new User();
				user.setId(rs.getInt("user_id"));
				//if(user.getId() != null && user.getId() > 0){
					user.setUserName(StringUtil.notNull(rs.getString("user_name")));
					user.setFullName(StringUtil.notNull(rs.getString("full_name")));
				//}
				s.setUser(user);
				InvoiceType invoiceType = new InvoiceType();
				invoiceType.setId(rs.getInt("invoice_type_id"));
				//if(invoiceType.getId() != null && invoiceType.getId() > 0){
					invoiceType.setInvoiceType(StringUtil.notNull(rs.getString("invoice_type")));
				//}
				results.add(s);
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


}
