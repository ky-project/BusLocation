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
 * A data access object (DAO) providing persistence and search support for
 * Station entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Station
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class StationDAO {
	private static final Logger log = LoggerFactory.getLogger(StationDAO.class);
	// property constants
	public static final String STA_NAME = "staName";
	public static final String STA_LATI = "staLati";
	public static final String STA_LONGI = "staLongi";

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

	public void save(Station transientInstance) {
		log.debug("saving Station instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Station persistentInstance) {
		log.debug("deleting Station instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Station findById(Integer id) {
		log.debug("getting Station instance with id: " + id);
		try {
			Station instance = (Station) getCurrentSession().get(
					"com.hibernatespring.Station", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Station instance) {
		log.debug("finding Station instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.hibernatespring.Station")
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
		log.debug("finding Station instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Station as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStaName(Object staName) {
		return findByProperty(STA_NAME, staName);
	}

	public List findByStaLati(Object staLati) {
		return findByProperty(STA_LATI, staLati);
	}

	public List findByStaLongi(Object staLongi) {
		return findByProperty(STA_LONGI, staLongi);
	}

	public List findAll() {
		log.debug("finding all Station instances");
		try {
			String queryString = "from Station";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Station merge(Station detachedInstance) {
		log.debug("merging Station instance");
		try {
			Station result = (Station) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Station instance) {
		log.debug("attaching dirty Station instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Station instance) {
		log.debug("attaching clean Station instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StationDAO) ctx.getBean("StationDAO");
	}
}