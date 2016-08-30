package com.home.dao;

// Generated Jan 12, 2016 9:15:32 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;

import com.home.model.User;

/**
 * Home object for domain model class User.
 * 
 * @see com.home.model.User
 * @author Hibernate Tools
 */
public class UserHome {

	private static final Log log = LogFactory.getLog(UserHome.class);

	private SessionFactory sessionFactory;

	public UserHome(SessionFactory sessionFactory) {
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

	@SuppressWarnings("unchecked")
	public int countUserByUserName(String userName) throws Exception {
		log.debug("Retrieve User instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<User> results = session.createCriteria(User.class).add(Restrictions.like("userName", userName + "%")).list();
			tx.commit();
			log.debug("Retrieve successful");
			return results.size();
		} catch (RuntimeException re) {
			log.error("Retrieve failed", re);
			throw re;
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
	}

	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(transientInstance);
			tx.commit();
			session.close();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void updateDirty(User instance) {
		log.debug("attaching dirty User instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			tx.rollback();
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

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			tx.rollback();
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
	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
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

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) sessionFactory.openSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			User instance = (User) session.get(User.class, id);
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

	@SuppressWarnings("unchecked")
	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) sessionFactory.openSession().createCriteria(User.class).add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public User getUserByCredentials(String userName, String password) {
		log.debug("finding User instance by credentials");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			User result = (User) session.createCriteria(User.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password)).uniqueResult();
			tx.commit();
			return result;
		} catch (RuntimeException re) {
			log.error("find by credentials failed", re);
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

	public User getUserByFullName(String fullName) {
		log.debug("finding User instance by full name");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from User where full_name=:fullName");
			query.setString("fullName", fullName);
			User results = (User) query.uniqueResult();
			tx.commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by full name failed", re);
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
	public List<User> getListUser() {
		log.debug("retrieve list Users");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<User> results = session.createCriteria(User.class).list();
			tx.commit();
			log.debug("retrieve list Users successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("retrieve list Users failed", re);
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
	public List<User> getLookupEmployee() {
		return getLookupEmployee(-1, true);
	}
	public List<User> getLookupEmployee(int employeeId) {
		return getLookupEmployee(employeeId, false);
	}
	public List<User> getLookupEmployee(int employeeId, boolean isAllEmployee) {
		log.debug("finding List User instance by full name");
		List<User> results = new ArrayList<User>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select id, user_name, full_name From user where id = "+employeeId;
				if(isAllEmployee)
					query += " or id <> "+employeeId;
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setUserName(rs.getString("user_name"));
						user.setFullName(rs.getString("full_name"));
						results.add(user);
					}
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by full name failed", re);
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
	public HashMap<Integer, String> getFilterEmployee() {
		log.debug("finding List User instance by full name");
		 HashMap<Integer, String> results = new HashMap<>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String query = "Select id, full_name From user";
				try (ResultSet rs = sta.executeQuery(query)) {
					while (rs.next()) {
						results.put(rs.getInt("id"), rs.getString("full_name"));
					}
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by full name failed", re);
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
