package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;

import com.home.conts.MyConts;
import com.home.entities.RevenuesComparison;
import com.home.entities.RevenuesCustomerDetail;
import com.home.entities.RevenuesCustomerL1;
import com.home.entities.RevenuesCustomerL2;
import com.home.entities.RevenuesSellman;
import com.home.entities.StatisticCustom;
import com.home.entities.StatisticHistory;
import com.home.model.Customer;
import com.home.model.InvoiceType;
import com.home.model.Product;
import com.home.model.Statistic;
import com.home.model.StatisticCompare;
import com.home.model.User;
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

	public Integer attachDirty(Statistic instance) {
		log.debug("attaching dirty Statistic instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			log.debug("attach successful");
			return instance.getId();
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
	
	public void attachDirty(Session session, Statistic instance) {
		//Transaction tx = null;
		try {
			//tx = session.beginTransaction();
			session.save(instance);
			//tx.commit();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		} finally {}
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

	public boolean isStatictisDuplicateLevel1(Session session, Date dateReceived, Integer custCodeLevel1Id, Integer productId, Integer invoiceTypeId) {
		log.debug("Checking Statistic duplicate with: ");
		try {
			Criteria cre = session.createCriteria(Statistic.class);
			cre.add(Restrictions.eq("dateReceived", dateReceived));
			cre.add(Restrictions.eq("customerByCustomerCodeLevel1.id", custCodeLevel1Id));
			cre.add(Restrictions.eq("product.id", productId));
			cre.add(Restrictions.eq("invoiceType.id", invoiceTypeId));
			cre.setMaxResults(1);
			Statistic instance = (Statistic) cre.uniqueResult();
			if (instance == null) {
				return false;
			}
			return true;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
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
			//Statistic instance = (Statistic) cre.uniqueResult();
			List<Statistic> results = cre.list();
			tx.commit();
			if (results == null || results.isEmpty()) {
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

	public boolean isStatictisDuplicateLevel2(Date dateReceived, Integer custCodeLevel1Id, Integer custCodeLevel2Id, Integer productId, Integer userId, Integer invoiceTypeId, Float totalBox, Integer quantity) {
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
			cre.add(Restrictions.eq("totalBox", totalBox));
			cre.add(Restrictions.eq("quantity", quantity));
			//Statistic instance = (Statistic) cre.uniqueResult();
			List<Statistic> results = cre.list();
			tx.commit();
			if (results == null || results.isEmpty()) {
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
			Criteria criteria = session.createCriteria(Statistic.class);
			criteria.add(Restrictions.eq("invoiceType.id", MyConts.INVOICE_STATISTIC_CUS_L2));
			if(sttCustom.getFromDate() != null && sttCustom.getToDate() != null){
				criteria.add(Restrictions.between("dateReceived", sttCustom.getFromDate(), sttCustom.getToDate()));
			}
			if(sttCustom.getCustLevel2Id() > 0){
				criteria.add(Restrictions.eq("customerByCustomerCodeLevel2.id", sttCustom.getCustLevel2Id()));
			}
			if(sttCustom.getCustLevel1Id() > 0){
				criteria.add(Restrictions.eq("customerByCustomerCodeLevel1.id", sttCustom.getCustLevel1Id()));
			}
			if(sttCustom.getEmpId() > 0){
				criteria.add(Restrictions.eq("user.id", sttCustom.getEmpId()));
			}
			criteria.addOrder(Order.asc("dateReceived"));
			List<Statistic> results = criteria.list();
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
					+ "Group By customer_code1, business_name1, c2.customer_code, c2.business_name, c2.business_address, u.user_name, p.product_code, p.product_name " + "Order By c2.customer_code";
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
				// product.setId(rs.getInt("product_id"));
				product.setProductCode(StringUtil.notNull(rs.getString("product_code")));
				product.setProductName(StringUtil.notNull(rs.getString("product_name")));
				product.setTotalBox((int) total_box);
				product.setQuantity((int) quantity);
				product.setUnitPrice(revenues);

				if (result == null) {
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
				} else {
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
					+ "Group By customer_code1, business_name1, c2.customer_code, c2.business_name, c2.business_address, p.product_code, p.product_name " 
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
				// String business_name = rs.getString("business_name");
				// String business_address = rs.getString("business_address");
				// String user_name = rs.getString("user_name");
				long total_box = rs.getLong("total_box");
				long quantity = rs.getLong("quantity");
				BigDecimal revenues = rs.getBigDecimal("total");

				Product product = new Product();
				// product.setId(rs.getInt("product_id"));
				product.setProductCode(StringUtil.notNull(rs.getString("product_code")));
				product.setProductName(StringUtil.notNull(rs.getString("product_name")));
				product.setTotalBox((int) total_box);
				product.setQuantity((int) quantity);
				product.setUnitPrice(revenues);

				if (results.containsKey(customer_code1)) {
					results.get(customer_code1).getListProduct().add(product);
					results.get(customer_code1).setTotalRevenues(results.get(customer_code1).getTotalRevenues().add(revenues));
					results.get(customer_code1).setTotalProduct(results.get(customer_code1).getTotalProduct() + total_box);
					if (!results.get(customer_code1).getCustomerCodeL2().contains(customer_code)) {
						results.get(customer_code1).setCustomerCodeL2(results.get(customer_code1).getCustomerCodeL2() + ";" + customer_code);
						results.get(customer_code1).setTotalCustomerL2(results.get(customer_code1).getTotalCustomerL2() + 1);
					}
				} else {
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

	public List<StatisticCompare> getDataStatisticCompare(Date atDate, int custCodeLevel1Id, int invoiceTypeId) {

		return getDataStatisticCompare(atDate, null, custCodeLevel1Id, invoiceTypeId);
	}

	@SuppressWarnings("unchecked")
	public List<StatisticCompare> getDataStatisticCompare(Date fromDate, Date toDate, int custCodeLevel1Id, int invoiceTypeId) {
		log.debug("Checking Statistic duplicate with: ");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria cre = session.createCriteria(Statistic.class);
			if (toDate == null)
				cre.add(Restrictions.eq("dateReceived", fromDate));
			else
				cre.add(Restrictions.between("dateReceived", fromDate, toDate));
			cre.add(Restrictions.eq("customerByCustomerCodeLevel1.id", custCodeLevel1Id));
			cre.add(Restrictions.eq("invoiceType.id", invoiceTypeId));
			ProjectionList pList = Projections.projectionList();
			pList.add(Projections.groupProperty("product.id"), "productId");
			pList.add(Projections.sum("totalBox"), "totalBox");
			pList.add(Projections.sum("total"), "total");
			cre.setProjection(pList).setResultTransformer(Transformers.aliasToBean(StatisticCompare.class));
			List<StatisticCompare> instance = cre.list();
			tx.commit();
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
	
	public LinkedHashMap<String, RevenuesCustomerL2> getRevenuesCustomerL2(java.sql.Date start, java.sql.Date end) throws Exception {
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
					+ "Where date_received >= ? And date_received <= ? "
					+ "Group By customer_code1, business_name1, c2.customer_code, c2.business_name, c2.business_address, u.user_name, p.product_code, p.product_name " 
					+ "Order By customer_code";
			System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start);
			pre.setDate(2, end);
			ResultSet rs = pre.executeQuery();
			LinkedHashMap<String, RevenuesCustomerL2> results = new LinkedHashMap<String, RevenuesCustomerL2>();
			while (rs.next()) {
				String customer_code1 = rs.getString("customer_code1");
				String business_name1 = rs.getString("business_name1");
				String customer_code = rs.getString("customer_code");
				String business_name = rs.getString("business_name");
				// String business_address = rs.getString("business_address");
				String user_name = rs.getString("user_name");
				long total_box = rs.getLong("total_box");
				long quantity = rs.getLong("quantity");
				BigDecimal revenues = rs.getBigDecimal("total");

				Product product = new Product();
				// product.setId(rs.getInt("product_id"));
				product.setProductCode(StringUtil.notNull(rs.getString("product_code")));
				product.setProductName(StringUtil.notNull(rs.getString("product_name")));
				product.setTotalBox((int) total_box);
				product.setQuantity((int) quantity);
				product.setUnitPrice(revenues);

				String key = customer_code + customer_code1;
				if (results.containsKey(key)) {
					results.get(key).getListProduct().add(product);
					results.get(key).setTotalRevenues(results.get(key).getTotalRevenues().add(revenues));
					results.get(key).setTotalProduct(results.get(key).getTotalProduct() + total_box);
				} else {
					RevenuesCustomerL2 cusL2 = new RevenuesCustomerL2();
					cusL2.setCustomerNameL1(business_name1);
					cusL2.setCustomerCodeL2(customer_code);
					cusL2.setCustomerNameL2(business_name);
					cusL2.setCustomerCodeL2(customer_code);
					cusL2.setSellman(user_name);
					cusL2.setTotalRevenues(revenues);
					cusL2.setTotalProduct(total_box);
					cusL2.setListProduct(new ArrayList<Product>());
					cusL2.getListProduct().add(product);
					results.put(key, cusL2);
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
	
	public LinkedHashMap<String, RevenuesSellman> getRevenuesSellman(java.sql.Date start, java.sql.Date end) throws Exception {
		log.debug("retrieve list Statistic");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			String sql = "Select c2.customer_code, c2.business_name, c2.business_address, p.product_code, p.product_name, u.user_name, sum(s.total_box) as total_box, sum(s.quantity) as quantity, sum(s.total) as total "
					+ "From `statistic` s "
					+ "JOIN `customer` c2 ON s.customer_code_level2=c2.id "
					+ "JOIN `product` p ON p.id=s.product_id "
					+ "JOIN `user` u ON u.id=s.user_id "
					+ "Where date_received >= ? And date_received <= ? "
					+ "Group By c2.customer_code, c2.business_name, c2.business_address, u.user_name, p.product_code, p.product_name " 
					+ "Order By customer_code";
			System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start);
			pre.setDate(2, end);
			ResultSet rs = pre.executeQuery();
			LinkedHashMap<String, RevenuesSellman> results = new LinkedHashMap<String, RevenuesSellman>();
			while (rs.next()) {
				//String customer_code1 = rs.getString("customer_code1");
				//String business_name1 = rs.getString("business_name1");
				String customer_code = rs.getString("customer_code");
				String business_name = rs.getString("business_name");
				// String business_address = rs.getString("business_address");
				String user_name = rs.getString("user_name");
				long total_box = rs.getLong("total_box");
				long quantity = rs.getLong("quantity");
				BigDecimal revenues = rs.getBigDecimal("total");

				Product product = new Product();
				// product.setId(rs.getInt("product_id"));
				product.setProductCode(StringUtil.notNull(rs.getString("product_code")));
				product.setProductName(StringUtil.notNull(rs.getString("product_name")));
				product.setTotalBox((int) total_box);
				product.setQuantity((int) quantity);
				product.setUnitPrice(revenues);

				String key = user_name;
				if (results.containsKey(key)) {
					results.get(key).getListProduct().add(product);
					results.get(key).setTotalRevenues(results.get(key).getTotalRevenues().add(revenues));
					results.get(key).setTotalProduct(results.get(key).getTotalProduct() + total_box);
					results.get(key).setTotalCustomer(results.get(key).getTotalCustomer()+1);
				} else {
					RevenuesSellman revenuesSellman = new RevenuesSellman();
					revenuesSellman.setTotalCustomer(1);
					revenuesSellman.setSellman(user_name);
					revenuesSellman.setTotalRevenues(revenues);
					revenuesSellman.setTotalProduct(total_box);
					revenuesSellman.setListProduct(new ArrayList<Product>());
					revenuesSellman.getListProduct().add(product);
					results.put(key, revenuesSellman);
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
	
	public List<Statistic> getListStatistic(
			int startPageIndex, 
			int recordsPerPage, 
			String searchValue, 
			java.sql.Date startday, 
			java.sql.Date endday, 
			int statistic_type,
			int emp_id) throws Exception{
		log.debug("retrieve list getListStatistic");
		Session session = null;
		List<Statistic> results = new ArrayList<Statistic>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			searchValue = searchValue.toLowerCase().trim();
			String sql = 
					"SELECT * FROM ( "
						+"SELECT @i:=@i+1 AS iterator, YY.* FROM ("
							+"SELECT t.*, c1.statistic_name as cus1name, c2.statistic_name as cus2name, product_name, unit_price, user_name, full_name, invoice_type "
							+" FROM statistic t "+
									" LEFT JOIN customer c1 ON t.customer_code_level1=c1.id " +
									" LEFT JOIN customer c2 ON t.customer_code_level2=c2.id " +
									" LEFT JOIN product p ON t.product_id=p.id " +
									" LEFT JOIN user u ON t.user_id=u.id " +
									" LEFT JOIN invoice_type iv ON t.invoice_type_id=iv.id " +
								" WHERE "
								+ " (0="+(startday==null?0:1)+" Or (date_received >= ? And date_received <= ?))"
								+ " AND (0="+statistic_type+" Or ("+ (statistic_type==1?"customer_code_level2 IS NULL OR customer_code_level2<=0":"1=1")+"))"
								+ " AND (-1="+emp_id+" Or (t.user_id="+emp_id+"))"
								+ " AND (''='"+searchValue+"' OR ("
										+ " lower(c1.statistic_name) like '"+searchValue+"%'"
										+ " OR lower(c2.statistic_name) like '"+searchValue+"%'"
										+ " OR lower(product_name) like '"+searchValue+"%'"
										//+ " OR lower(user_name) like '"+searchValue+"%'"
										+ ") ) "
										+ " Order by date_received desc, id) AS YY, (SELECT @i:=0) foo Order By date_received desc, id) AS XX "
						+" WHERE iterator > "+startPageIndex+" AND iterator <= " + range + " order by date_received";
			System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, startday);
			pre.setDate(2, endday);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			int no = 1;
			while(rs.next()){
				Statistic s = new Statistic();
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
				s.setTotalBox(rs.getFloat("total_box"));
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
	
	private String getCusName(Connection conn, int id) throws Exception{
		try {
			String name = null;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT business_name FROM `customer` WHERE id="+id);
			if(rs.next()){
				name = StringUtil.notNull(rs.getString("business_name"));
			}
			rs.close();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private String getProductName(Connection conn, int id) throws Exception{
		try {
			String name = null;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT product_name FROM `product` WHERE id="+id);
			if(rs.next()){
				name = StringUtil.notNull(rs.getString("product_name"));
			}
			rs.close();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private String getUserName(Connection conn, int id) throws Exception{
		try {
			String name = null;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT user_name FROM `user` WHERE id="+id);
			if(rs.next()){
				name = StringUtil.notNull(rs.getString("user_name"));
			}
			rs.close();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	private String getInvoiceType(Connection conn, int id) throws Exception{
		try {
			String name = null;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT invoice_type FROM `invoice_type` WHERE id="+id);
			if(rs.next()){
				name = StringUtil.notNull(rs.getString("invoice_type"));
			}
			rs.close();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int getTotalRecords(
			String searchValue, 
			java.sql.Date startday, 
			java.sql.Date endday, 
			int statistic_type,
			int emp_id) throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			String sql = "SELECT COUNT(*) "
					+" FROM statistic t "+
					" LEFT JOIN customer c1 ON t.customer_code_level1=c1.id " +
					" LEFT JOIN customer c2 ON t.customer_code_level2=c2.id " +
					" LEFT JOIN product p ON t.product_id=p.id " +
					" LEFT JOIN user u ON t.user_id=u.id " +
					" LEFT JOIN invoice_type iv ON t.invoice_type_id=iv.id " +
				" WHERE "
				+ " (0="+(startday==null?0:1)+" Or (date_received >= ? And date_received <= ?))"
				+ " AND (0="+statistic_type+" Or ("+ (statistic_type==1?"customer_code_level2 IS NULL OR customer_code_level2<=0":"1=1")+"))"
				+ " AND (-1="+emp_id+" Or (t.user_id="+emp_id+"))"
				+ " AND (''='"+searchValue+"' OR ("
						+ " lower(c1.statistic_name) like '"+searchValue+"%'"
						+ " OR lower(c2.statistic_name) like '"+searchValue+"%'"
						+ " OR lower(product_name) like '"+searchValue+"%'"
						//+ " OR lower(user_name) like '"+searchValue+"%'"
						+ ") ) ";
					
			System.out.println(sql);
			int total = 0;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, startday);
			pre.setDate(2, endday);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			if(rs.next()){
				total = rs.getInt(1);
			}
			rs.close();
			pre.close();
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
	
	public int getTotalRecords() throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM Statistic";
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
	
	public static void main(String[] args) {
		try {
			System.out.println( Calendar.getInstance().get(Calendar.DATE));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public LinkedHashMap<String, HashMap<Integer, StatisticHistory>> getStatisticHistory(int cus_id) throws Exception{
		Session session = null;
		LinkedHashMap<String, HashMap<Integer, StatisticHistory>> hmStatistic = new LinkedHashMap<String, HashMap<Integer, StatisticHistory>>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			/*String sql = "SELECT YEAR( date_received ) AS import_date, sum( total ) AS total, sum( XX.quantity ) AS quantity, product_name, unit_price "
						+" FROM `statistic` XX "
						+" LEFT JOIN customer c2 ON XX.customer_code_level2 = c2.id "
						+" LEFT JOIN product p ON XX.product_id = p.id "
						+" WHERE XX.customer_code_level2 = " + cus_id
						+" AND date_received <= CURDATE( ) "
						+" AND date_received >= CURDATE( ) - INTERVAL 2 YEAR "
						+" GROUP BY YEAR( date_received ) , product_name, unit_price "
						+" ORDER BY product_name, date_received";*/
			String sql = "SELECT date_received, total, coalesce(XX.total_box, 0) AS total_box, product_name, unit_price "
					+" FROM `statistic` XX "
					+" LEFT JOIN customer c2 ON XX.customer_code_level2 = c2.id "
					+" LEFT JOIN product p ON XX.product_id = p.id "
					+" WHERE XX.customer_code_level2 = " + cus_id
					+" AND date_received <= CURDATE( ) "
					+" AND date_received >= CURDATE( ) - INTERVAL 3 YEAR "
					+" ORDER BY product_name, date_received";
					
			System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			int yearNow = Calendar.getInstance().get(Calendar.YEAR);
			//int monthNow = Calendar.getInstance().get(Calendar.MONTH)+1;
			
			while(rs.next()){
				java.sql.Date date_received = rs.getDate("date_received");
				Calendar c = Calendar.getInstance();
				c.setTime(new Date(date_received.getTime()));
				int import_year = c.get(Calendar.YEAR);
				int import_month = c.get(Calendar.MONTH)+1;
				if((import_year == yearNow && import_month >= 10) ||
						(import_year == yearNow-3 && import_month < 10)){
					continue;
				}
				else if(import_month >= 10){
					import_year++;
				}
				
				String product_name = StringUtil.notNull(rs.getString("product_name"));
				//int import_date = rs.getInt("import_date");
				
				BigDecimal total = rs.getBigDecimal("total");
				//int quantity = rs.getInt("quantity");
				float total_box = rs.getFloat("total_box");
				BigDecimal unit_price = rs.getBigDecimal("unit_price");
				
				StatisticHistory s = new StatisticHistory();
				s.setProduct_name(product_name);
				s.setImport_date(import_year);
				s.setTotal(total);
				//s.setQuantity(quantity);
				s.setTotal_box(total_box);
				s.setUnit_price(unit_price);
				
				if(hmStatistic.containsKey(product_name)){
					if(hmStatistic.get(product_name).containsKey(import_year)){
						StatisticHistory ss = hmStatistic.get(product_name).get(import_year);
						ss.setTotal(ss.getTotal().add(s.getTotal()));
						//ss.setQuantity(ss.getQuantity() + s.getQuantity());
						ss.setTotal_box(ss.getTotal_box() + s.getTotal_box());
					}else{
						hmStatistic.get(product_name).put(import_year, s);
					}
				}else{
					hmStatistic.put(product_name, new HashMap<Integer, StatisticHistory>());
					hmStatistic.get(product_name).put(import_year, s);
				}
			}
			rs.close();
			pre.close();
			return hmStatistic;
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
