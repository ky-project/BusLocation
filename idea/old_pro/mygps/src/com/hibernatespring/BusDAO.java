package com.hibernatespring;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for Bus
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see Bus
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusDAO {
	private static final Logger log = LoggerFactory.getLogger(BusDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String SEAT_SUM = "seatSum";
	public static final String LICENSE = "license";
	public static final String DRIVER_NAME = "driverName";
	public static final String DRIVER_TEL = "driverTel";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Bus transientInstance) {
		log.debug("saving Bus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Bus persistentInstance) {
		log.debug("deleting Bus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bus findById(Integer id) {
		log.debug("getting Bus instance with id: " + id);
		try {
			Bus instance = (Bus) getCurrentSession().get(
					"com.hibernatespring.Bus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Bus instance) {
		log.debug("finding Bus instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.hibernatespring.Bus")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Bus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Bus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findBySeatSum(Object seatSum) {
		return findByProperty(SEAT_SUM, seatSum);
	}

	public List findByLicense(Object license) {
		return findByProperty(LICENSE, license);
	}

	public List findByDriverName(Object driverName) {
		return findByProperty(DRIVER_NAME, driverName);
	}

	public List findByDriverTel(Object driverTel) {
		return findByProperty(DRIVER_TEL, driverTel);
	}

	public List findAll() {
		log.debug("finding all Bus instances");
		try {
			String queryString = "from Bus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Bus merge(Bus detachedInstance) {
		log.debug("merging Bus instance");
		try {
			Bus result = (Bus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Bus instance) {
		log.debug("attaching dirty Bus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bus instance) {
		log.debug("attaching clean Bus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BusDAO) ctx.getBean("BusDAO");
	}
}