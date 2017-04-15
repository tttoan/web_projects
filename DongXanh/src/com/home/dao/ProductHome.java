package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;

import com.home.model.Category;
import com.home.model.Customer;
import com.home.model.Product;
import com.home.model.Statistic;

/**
 * Home object for domain model class Product.
 * @see com.home.dao.Product
 * @author Hibernate Tools
 */
public class ProductHome {

	private static final Log log = LogFactory.getLog(ProductHome.class);

	private SessionFactory sessionFactory ;//= getSessionFactory();

	public ProductHome(){
		sessionFactory = getSessionFactory();
	};

	public ProductHome(SessionFactory sessionFactory){
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

	public void persist(Product transientInstance) {
		log.debug("persisting Product instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Product instance) throws Exception{
		log.debug("attaching dirty Product instance");
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
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public void update(Product instance) throws Exception{
		log.debug("attaching dirty Product instance");
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
	
	public void updateByProductCode(Product instance) throws Exception{
		log.debug("attaching dirty Product instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("update Product set quantity=:quantity, point=:point, unitPrice=:unitPrice where productCode=:productCode");
			query.setInteger("quantity", instance.getQuantity());
			query.setInteger("point", instance.getPoint());
			query.setBigDecimal("unitPrice", instance.getUnitPrice());
			query.setString("productCode", instance.getProductCode());
			query.executeUpdate();
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

	public void attachClean(Product instance) {
		log.debug("attaching clean Product instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Product persistentInstance) throws Exception{
		log.debug("deleting Product instance");
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

	public Product merge(Product detachedInstance) {
		log.debug("merging Product instance");
		try {
			Product result = (Product) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Product findProductByCode(String productCode) throws Exception {
		log.debug("getting Product instance with code: " + productCode);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Product instance = (Product) session.createCriteria(Product.class).add(Restrictions.eq("productCode", productCode)).uniqueResult();
			tx.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("get failed", e);
			}
		}
	}
	
	public Product findProductByCode(Session session, String productCode) throws Exception {
		//log.debug("getting Product instance with code: " + productCode);
		try {
			Product instance = (Product) session.createCriteria(Product.class).add(Restrictions.eq("productCode", productCode)).uniqueResult();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				//log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally{
			
		}
	}

	public Product findById(java.lang.Integer id) throws Exception{
		log.debug("getting Product instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Product instance = (Product)session.get(Product.class, id);
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

	public List<Product> findByExample(Product instance) {
		log.debug("finding Product instance by example");
		try {
			List<Product> results = (List<Product>) sessionFactory
					.getCurrentSession().createCriteria("com.home.dao.Product")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> getListProduct() {
		log.debug("finding Product instance by example");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<Product> results = session.createCriteria(Product.class).list();
			tx.commit();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public List<Product> getListProducts(int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list Product");
		//Transaction tx = null;
		Session session = null;
		List<Product> results = new ArrayList<Product>();
		try {
			session = sessionFactory.openSession();
			//			tx = session.beginTransaction();
			//			List<Product> results = session.createQuery("FROM Product").list();
			//			tx.commit();

			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM product t,(SELECT @i:=0) foo Order By id) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setProductCode(rs.getString("product_code"));
				p.setProductName(rs.getString("product_name"));
				p.setDescription(rs.getString("description"));
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				p.setCategory(category);
				p.setCategory_id(category.getId());
				p.setUnitPrice(rs.getBigDecimal("unit_price"));
				p.setQuantity(rs.getInt("quantity"));
				p.setPoint(rs.getInt("point"));
				p.setExportDate(rs.getDate("export_date"));
				results.add(p);
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
	
	public List<Product> getListProducts(int startPageIndex, int recordsPerPage, String name) throws Exception{
		log.debug("retrieve list Product");
		//Transaction tx = null;
		Session session = null;
		List<Product> results = new ArrayList<Product>();
		try {
			session = sessionFactory.openSession();
			//			tx = session.beginTransaction();
			//			List<Product> results = session.createQuery("FROM Product").list();
			//			tx.commit();

			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM product t,(SELECT @i:=0) foo Order By id) AS XX WHERE "
					+ " (UPPER(product_code) like '%" + name.toUpperCase().trim() +"%'  OR UPPER(product_name) like '%" + name.toUpperCase().trim() + "%')"
					+ " AND iterator > "+startPageIndex+" AND iterator <= " + range);
			while(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setProductCode(rs.getString("product_code"));
				p.setProductName(rs.getString("product_name"));
				p.setDescription(rs.getString("description"));
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				p.setCategory(category);
				p.setCategory_id(category.getId());
				p.setUnitPrice(rs.getBigDecimal("unit_price"));
				p.setQuantity(rs.getInt("quantity"));
				p.setPoint(rs.getInt("point"));
				p.setExportDate(rs.getDate("export_date"));
				results.add(p);
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

	public int getTotalRecords() throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM Product";
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
	
	public int getTotalRecords(String name) throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM Product WHERE (UPPER(product_code) like '%" + name.toUpperCase().trim() +"%'  OR UPPER(product_name) like '%" + name.toUpperCase().trim() + "%')";
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
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public LinkedHashMap<Integer, String> getListProducts() throws Exception{
		log.debug("retrieve list Product");
		Session session = null;
		LinkedHashMap<Integer, String> results = new LinkedHashMap<Integer, String>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `product` Order by product_name");
			while(rs.next()){
				results.put(rs.getInt("id"), rs.getString("product_name"));
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

	public LinkedHashMap<Integer, String> getListProducts(int promotion_id) throws Exception{
		log.debug("retrieve list Product");
		Session session = null;
		LinkedHashMap<Integer, String> results = new LinkedHashMap<Integer, String>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT pp.id, product_name FROM `product` p JOIN `promotion_product` pp ON p.id=pp.product_id WHERE promotion_id="+promotion_id+" Order by product_name");
			while(rs.next()){
				results.put(rs.getInt("id"), rs.getString("product_name"));
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


	public boolean existProduct(Product product) {
		log.debug("Checking Statistic duplicate with: ");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Product where productCode=:productCode");
			query.setString("productCode", product.getProductCode());
			Product instance = (Product) query.uniqueResult();
			tx.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
				return false;
			} else {
				log.debug("get successful, instance found");

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

	public List<Product> getLookupProduct() {
		log.debug("finding List Product to Auto Lookup");
		List<Product> results = new ArrayList<Product>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select id, product_code, product_name From product";
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						Product p = new Product();
						p.setId(rs.getInt("id"));
						p.setProductCode(rs.getString("product_code"));
						p.setProductName(rs.getString("product_name"));
						results.add(p);
					}
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by Product failed", re);
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
	
	public List<Object[]> lookupProduct(String productName) {
		//log.debug("finding List Product to Auto Lookup");
		List<Object[]> results = new ArrayList<Object[]>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select * From product Where (lower(product_code) Like ? OR lower(product_name) Like ?) Order By product_name LIMIT 20";
				try(PreparedStatement pre = conn.prepareStatement(query)){
					pre.setString(1, productName.toLowerCase()+"%");
					pre.setString(2, productName.toLowerCase()+"%");
					try (ResultSet rs = pre.executeQuery()) {
						while (rs.next()) {
							Product p = new Product();
							p.setId(rs.getInt("id"));
							p.setProductCode(rs.getString("product_code"));
							p.setProductName(rs.getString("product_name"));
							p.setUnitPrice(rs.getBigDecimal("unit_price"));
							p.setQuantity(rs.getInt("quantity"));
							p.setPoint(rs.getInt("point"));
							results.add(new Object[]{p.getId(), p.getProductName(), p.getProductCode(), p.getUnitPrice(), p.getQuantity()});
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by Product failed", re);
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
