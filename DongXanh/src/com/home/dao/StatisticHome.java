package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.home.model.Statistic;

/**
 * Home object for domain model class Statistic.
 * 
 * @see com.home.dao.Statistic
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

	public boolean isStatictisDuplicateLevel1(Date dateReceived, Integer customerCodeLevel1, Integer productId) {
		log.debug("Checking Statistic duplicate with: ");
		Transaction tx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Statistic where date_received=:dateReceived and customer_code_level1=:customerCodeLevel1"
					+ " and product_id=:productId");
			query.setDate("dateReceived", dateReceived);
			query.setInteger("customerCodeLevel1", customerCodeLevel1);
			query.setInteger("productId", productId);
			Statistic instance = (Statistic) query.uniqueResult();
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
	
	public boolean isStatictisDuplicateLevel2(Date dateReceived, Integer customerCodeLevel1, Integer custLevel2Id, Integer productId, Integer userId) {
		log.debug("Checking Statistic duplicate with: ");
		Transaction tx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Statistic where date_received=:dateReceived and customer_code_level1=:customerCodeLevel1"
					+ " and customer_code_level2=:customerCodeLevel2 and product_id=:productId and user_id=:userId");
			query.setDate("dateReceived", dateReceived);
			query.setInteger("customerCodeLevel1", customerCodeLevel1);
			query.setInteger("customerCodeLevel2", custLevel2Id);
			query.setInteger("productId", productId);
			query.setInteger("userId", userId);
			Statistic instance = (Statistic) query.uniqueResult();
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
}
