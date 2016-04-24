package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;

import com.home.entities.RevenuesComparison;
import com.home.entities.RevenuesCustomerDetail;
import com.home.entities.RevenuesCustomerL1;
import com.home.entities.StatisticCustom;
import com.home.model.Product;
import com.home.model.Statistic;
import com.home.util.StringUtil;

/**
 * Home object for domain model class Statistic.
 * 
 * @see com.home.dao.StatisticCustom
 * @author Hibernate Tools
 */
public class StatisticHome {

	private static final Log log = LogFactory.getLog(StatisticHome.class);

	private SessionFactory sessionFactory;

	public StatisticHome(SessionFactory sessionFactory) {
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

	public void persist(Statistic transientInstance) {
		log.debug("persisting Statistic instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Statistic instance) {
		log.debug("attaching dirty Statistic instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(instance);
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

	public void updateDirty(Statistic instance) {
		log.debug("attaching dirty Statistic instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(instance);
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

	public void attachClean(Statistic instance) {
		log.debug("attaching clean Statistic instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Statistic persistentInstance) {
		log.debug("deleting Statistic instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
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

	public Statistic merge(Statistic detachedInstance) {
		log.debug("merging Statistic instance");
		try {
			Statistic result = (Statistic) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public boolean isStatictisDuplicateLevel1(Date dateReceived, Integer custCodeLevel1Id, Integer productId, Integer invoiceTypeId) {
		log.debug("Checking Statistic duplicate with: ");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria cre = session.createCriteria(Statistic.class);
			cre.add(Restrictions.eq("dateReceived", dateReceived));
			cre.add(Restrictions.eq("customerByCustomerCodeLevel1.id", custCodeLevel1Id));
			cre.add(Restrictions.eq("product.id", productId));
			cre.add(Restrictions.eq("invoiceType.id", invoiceTypeId));
			Statistic instance = (Statistic) cre.uniqueResult();
			tx.commit();
			if (instance == null) {
				return false;
			}
			return true;
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

	public boolean isStatictisDuplicateLevel2(Date dateReceived, Integer custCodeLevel1Id, Integer custCodeLevel2Id, Integer productId, Integer userId, Integer invoiceTypeId) {
		log.debug("Checking Statistic duplicate with: ");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria cre = session.createCriteria(Statistic.class);
			cre.add(Restrictions.eq("dateReceived", dateReceived));
			cre.add(Restrictions.eq("customerByCustomerCodeLevel1.id", custCodeLevel1Id));
			cre.add(Restrictions.eq("customerByCustomerCodeLevel2.id", custCodeLevel2Id));
			cre.add(Restrictions.eq("product.id", productId));
			cre.add(Restrictions.eq("user.id", userId));
			cre.add(Restrictions.eq("invoiceType.id", invoiceTypeId));
			Statistic instance = (Statistic) cre.uniqueResult();
			tx.commit();
			if (instance == null) {
				return false;
			}
			return true;
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

	public Statistic findById(java.lang.Integer id) {
		log.debug("getting Statistic instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Statistic instance = (Statistic) session.get(Statistic.class, id);
			tx.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
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

	public List<Statistic> findByExample(Statistic instance) {
		log.debug("finding Statistic instance by example");
		try {
			List<Statistic> results = (List<Statistic>) sessionFactory.getCurrentSession().createCriteria(Statistic.class).add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Statistic> getListExportStatisticLevel2(StatisticCustom sttCustom) {
		log.debug("retrieve list Statistic");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Statistic> results = session.createCriteria(Statistic.class).add(Restrictions.between("dateReceived", sttCustom.getFromDate(), sttCustom.getToDate()))
					.add(Restrictions.eq("customerByCustomerCodeLevel2.id", sttCustom.getCustLevel2Id())).add(Restrictions.eq("customerByCustomerCodeLevel1.id", sttCustom.getCustLevel1Id()))
					.add(Restrictions.eq("user.id", sttCustom.getEmpId())).addOrder(Order.asc("dateReceived")).list();
			tx.commit();
			log.debug("retrieve list Statistic successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("retrieve list Statistic failed", re);
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
	public List<Statistic> getListStatistic() {
		log.debug("retrieve list Statistic");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Statistic> results = session.createCriteria(Statistic.class).list();
			tx.commit();
			log.debug("retrieve list Statistic successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("retrieve list Statistic failed", re);
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

	public LinkedHashMap<String, RevenuesComparison> getRevenuesComparison(java.sql.Date start, java.sql.Date end, float minRevenues) throws Exception {
		log.debug("retrieve list Statistic");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			LinkedHashMap<String, RevenuesComparison> results = new LinkedHashMap<String, RevenuesComparison>();
			String sql = "Select  c1.customer_code as customer_code1, c1.business_name as business_name1, c2.customer_code, c2.business_name, c2.business_address, u.user_name, sum(s.total_box) as total_box, sum(s.quantity) as quantity, sum(s.total) as total "
					+ "From `statistic` s "
					+ "JOIN `customer` c1 ON s.customer_code_level1=c1.id "
					+ "JOIN `customer` c2 ON s.customer_code_level2=c2.id "
					+ "JOIN `user` u ON u.id=s.user_id "
					+ "Where date_received >= ? And date_received <= ? "
					+ "Group By customer_code1, business_name1, c2.customer_code, c2.business_name, c2.business_address, u.user_name "
					+ "Order By c2.customer_code";
			System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start);
			pre.setDate(2, end);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				String customer_code1 = rs.getString("customer_code1");
				String business_name1 = rs.getString("business_name1");
				String customer_code = rs.getString("customer_code");
				String business_name = rs.getString("business_name");
				String business_address = rs.getString("business_address");
				String user_name = rs.getString("user_name");
				long total_box = rs.getLong("total_box");
				long quantity = rs.getLong("quantity");
				BigDecimal total = rs.getBigDecimal("total");

				if (results.containsKey(customer_code)) {
					if (!results.get(customer_code).getSellMan().contains(user_name)) {
						results.get(customer_code).setSellMan(results.get(customer_code).getSellMan() + ";" + user_name);
					}
					if (!results.get(customer_code).getProvider().contains(business_name1)) {
						results.get(customer_code).setProvider(results.get(customer_code).getProvider() + ";" + business_name1);
					}
					results.get(customer_code).setRevenues1(results.get(customer_code).getRevenues1().add(total));
				} else {
					RevenuesComparison revenues = new RevenuesComparison();
					revenues.setCustomerCode(customer_code);
					revenues.setCustomerName(business_name);
					revenues.setCustomerGroup("B");
					revenues.setCustomerLocation(business_address);
					revenues.setRevenues1(total);
					revenues.setProvider(business_name1);
					revenues.setSellMan(user_name);
					results.put(customer_code, revenues);
				}
			}
			rs.close();
			pre.close();
			log.debug("retrieve getListStatistic successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			log.error("retrieve list Statistic failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public boolean isBalanceDuplicate(Date dateReceived, int custCodeLevel1Id, int productId, int invoiceTypeId) {
		log.debug("Checking Statistic duplicate with: ");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria cre = session.createCriteria(Statistic.class);
			cre.add(Restrictions.eq("dateReceived", dateReceived));
			cre.add(Restrictions.eq("customerByCustomerCodeLevel1.id", custCodeLevel1Id));
			cre.add(Restrictions.eq("product.id", productId));
			cre.add(Restrictions.eq("invoiceType.id", invoiceTypeId));
			Statistic instance = (Statistic) cre.uniqueResult();
			tx.commit();
			if (instance == null)
				return false;
			return true;
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
	
	public RevenuesCustomerDetail getRevenuesDetail(java.sql.Date start, java.sql.Date end, String customerCode) throws Exception {
		log.debug("retrieve list Statistic");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			String sql = "Select c1.customer_code as customer_code1, c1.business_name as business_name1, c2.customer_code, c2.business_name, c2.business_address, p.product_code, p.product_name, u.user_name, sum(s.total_box) as total_box, sum(s.quantity) as quantity, sum(s.total) as total "
					+ "From `statistic` s "
					+ "JOIN `customer` c1 ON s.customer_code_level1=c1.id "
					+ "JOIN `customer` c2 ON s.customer_code_level2=c2.id "
					+ "JOIN `product` p ON p.id=s.product_id "
					+ "JOIN `user` u ON u.id=s.user_id "
					+ "Where date_received >= ? And date_received <= ? And c2.customer_code=? "
					+ "Group By customer_code1, business_name1, c2.customer_code, c2.business_name, c2.business_address, u.user_name, p.product_code, p.product_name "
					+ "Order By c2.customer_code";
			System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start);
			pre.setDate(2, end);
			pre.setString(3, customerCode);
			ResultSet rs = pre.executeQuery();
			RevenuesCustomerDetail result = null;
			while (rs.next()) {
				String business_name1 = rs.getString("business_name1");
				String customer_code = rs.getString("customer_code");
				String business_name = rs.getString("business_name");
				String business_address = rs.getString("business_address");
				String user_name = rs.getString("user_name");
				long total_box = rs.getLong("total_box");
				long quantity = rs.getLong("quantity");
				BigDecimal revenues = rs.getBigDecimal("total");

				Product product = new Product();
				//product.setId(rs.getInt("product_id"));
				product.setProductCode(StringUtil.notNull(rs.getString("product_code")));
				product.setProductName(StringUtil.notNull(rs.getString("product_name")));
				product.setTotalBox((int)total_box);
				product.setQuantity((int)quantity);
				product.setUnitPrice(revenues);
				
				if(result == null){
					result = new RevenuesCustomerDetail();
					result.setCustomerCode(customer_code);
					result.setCustomerName(business_name);
					result.setCustomerLocation(business_address);
					result.setSellMan(user_name);
					result.setRevenues(revenues);
					result.setTotalProduct(total_box);
					result.setProvider(business_name1);
					
					result.setListProduct(new ArrayList<Product>());
					result.getListProduct().add(product);
				}else{
					if (!result.getProvider().contains(business_name1)) {
						result.setProvider(result.getProvider() + ";" + business_name1);
					}
					result.setRevenues(result.getRevenues().add(revenues));
					result.setTotalProduct(result.getTotalProduct() + total_box);
					result.getListProduct().add(product);
				}
			}
			rs.close();
			pre.close();
			return result;
		} catch (Exception re) {
			log.error("retrieve list Statistic failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public LinkedHashMap<String, RevenuesCustomerL1> getRevenuesCustomerL1(java.sql.Date start, java.sql.Date end) throws Exception {
		log.debug("retrieve list Statistic");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			String sql = "Select c1.customer_code as customer_code1, c1.business_name as business_name1, c2.customer_code, c2.business_name, c2.business_address, p.product_code, p.product_name, sum(s.total_box) as total_box, sum(s.quantity) as quantity, sum(s.total) as total "
					+ "From `statistic` s "
					+ "JOIN `customer` c1 ON s.customer_code_level1=c1.id "
					+ "JOIN `customer` c2 ON s.customer_code_level2=c2.id "
					+ "JOIN `product` p ON p.id=s.product_id "
					+ "Where date_received >= ? And date_received <= ? "
					+ "Group By customer_code1, business_name1, c2.customer_code, c2.business_name, c2.business_address, u.user_name, p.product_code, p.product_name "
					+ "Order By customer_code1";
			System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start);
			pre.setDate(2, end);
			ResultSet rs = pre.executeQuery();
			LinkedHashMap<String, RevenuesCustomerL1> results = new LinkedHashMap<String, RevenuesCustomerL1>();
			while (rs.next()) {
				String customer_code1 = rs.getString("customer_code1");
				String business_name1 = rs.getString("business_name1");
				String customer_code = rs.getString("customer_code");
//				String business_name = rs.getString("business_name");
//				String business_address = rs.getString("business_address");
//				String user_name = rs.getString("user_name");
				long total_box = rs.getLong("total_box");
				long quantity = rs.getLong("quantity");
				BigDecimal revenues = rs.getBigDecimal("total");

				Product product = new Product();
				//product.setId(rs.getInt("product_id"));
				product.setProductCode(StringUtil.notNull(rs.getString("product_code")));
				product.setProductName(StringUtil.notNull(rs.getString("product_name")));
				product.setTotalBox((int)total_box);
				product.setQuantity((int)quantity);
				product.setUnitPrice(revenues);
			
				if(results.containsKey(customer_code1)){
					results.get(customer_code1).getListProduct().add(product);
					results.get(customer_code1).setTotalRevenues(results.get(customer_code1).getTotalRevenues().add(revenues));
					results.get(customer_code1).setTotalProduct(results.get(customer_code1).getTotalProduct()+total_box);
					if(!results.get(customer_code1).getCustomerCodeL2().contains(customer_code)){
						results.get(customer_code1).setCustomerCodeL2(results.get(customer_code1).getCustomerCodeL2() + ";" + customer_code);
						results.get(customer_code1).setTotalCustomerL2(results.get(customer_code1).getTotalCustomerL2()+1);
					}
				}else{
					RevenuesCustomerL1 cusL1 = new RevenuesCustomerL1();
					cusL1.setCustomerCodeL1(customer_code1);
					cusL1.setCustomerNameL1(business_name1);
					cusL1.setTotalCustomerL2(1);
					cusL1.setCustomerCodeL2(customer_code);
					cusL1.setTotalRevenues(revenues);
					cusL1.setTotalProduct(total_box);
					cusL1.setListProduct(new ArrayList<Product>());
					cusL1.getListProduct().add(product);
					results.put(customer_code1, cusL1);
				}
			}
			rs.close();
			pre.close();
			return results;
		} catch (Exception re) {
			log.error("retrieve list Statistic failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
